package br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.avisodeviagem.AvisarViagemRequest;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.avisodeviagem.AvisoViagem;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.avisodeviagem.AvisoViagemRequest;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.bloqueio.BloquearCartao;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.bloqueio.BloqueioCartao;
import feign.FeignException;

@RestController
@RequestMapping("/api/cartoes")
public class CartaoController {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private BloquearCartao bloquearCartao;

	@Autowired
	private ClientCartao clientCartao;

	@PostMapping("/{uuid}/bloquear")
	private ResponseEntity<?> bloquearCartao(@PathVariable String uuid, HttpServletRequest request) {

		Optional<Cartao> possivelCartao = cartaoRepository.findByUuid(uuid);
		if (possivelCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Cartao cartao = possivelCartao.get();

		if (cartao.bloqueado()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Este cartão encontra-se bloqueado.");
		}

		try {
			bloquearCartao.bloquear(cartao, request);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Bloqueio não efetuado, tente novamente mais tarde.");
		}

		String ip = request.getRemoteAddr();
		String userAgent = request.getHeader("User-Agent");

		cartao.bloquearCartao(new BloqueioCartao(ip, userAgent, cartao));

		cartaoRepository.save(cartao);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/{uuid}/avisoviagem")
	private ResponseEntity<?> criaAvisoViagem(@PathVariable String uuid, @Valid @RequestBody AvisoViagemRequest request,
			HttpServletRequest http) {

		Optional<Cartao> possivelCartao = cartaoRepository.findByUuid(uuid);
		if (possivelCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Cartao cartao = possivelCartao.get();

		AvisarViagemRequest avisarViagemRequest = request.toAPI();
		try {
			clientCartao.avisarViagem(cartao.getId(), avisarViagemRequest);
		} catch (FeignException e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Não foi permitido criar o aviso de viagem, tente novamente!");
		}

		String ip = http.getRemoteAddr();
		String userAgent = http.getHeader("User-Agent");

		AvisoViagem avisoViagem = request.toModel(ip, userAgent, cartao);

		cartao.addAvisoViagem(avisoViagem);

		cartaoRepository.save(cartao);

		return ResponseEntity.ok().build();

	}
}
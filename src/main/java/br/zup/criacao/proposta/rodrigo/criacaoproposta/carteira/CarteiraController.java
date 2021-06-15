package br.zup.criacao.proposta.rodrigo.criacaoproposta.carteira;

import java.net.URI;
import java.util.Optional;

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
import org.springframework.web.util.UriComponentsBuilder;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.Cartao;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.CartaoRepository;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.ClientCartao;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.carteira.paypal.PaypalCarteiraRequest;

@RestController
@RequestMapping("/api/cartoes")
public class CarteiraController {

	@Autowired
	private CarteiraRepository carteiraRepository;

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private ClientCartao clientCartao;

	@PostMapping("/{uuid}/carteiras/paypal")
	private ResponseEntity<?> criaCarteiraPayPal(@PathVariable String uuid,
			@Valid @RequestBody PaypalCarteiraRequest request, UriComponentsBuilder uriComponentsBuilder) {
		return criaCarteira(uuid, request, uriComponentsBuilder, TipoCarteira.PayPal);
	}

	private ResponseEntity<?> criaCarteira(@RequestBody @Valid @PathVariable String uuid, CarteiraRequest request,
			UriComponentsBuilder uriComponentBuilder, TipoCarteira tipoCarteira) {

		Optional<Cartao> possivelCartao = cartaoRepository.findByUuid(uuid);
		if (possivelCartao.isEmpty()) {
			return ResponseEntity.notFound().build();
		}

		Cartao cartao = possivelCartao.get();

		Integer count = carteiraRepository.countByCartaoAndTipoCarteira(cartao, tipoCarteira);
		if (count >= tipoCarteira.getCarteiraLimite()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					"associações para carteiras do tipo " + tipoCarteira.toString() + " está no maximo.");
		}

		AssociarCarteiraRequest api = request.toAPI(cartao.getId());

		ResponseEntity<AssociarCarteiraResponse> response;
		try {
			response = clientCartao.associarCarteira(cartao.getId(), api);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Não foi possível associar a carteira neste momento, tente novamente mais tarde.");
		}

		Carteira carteira = request.toModel(response.getBody().getnCarteira(), cartao);
		cartao.addCarteira(carteira);
		cartaoRepository.save(cartao);

		URI uri = uriComponentBuilder.path("api/carteiras/paypal/{nCarteira}")
				.buildAndExpand(response.getBody().getnCarteira()).toUri();
		return ResponseEntity.created(uri).build();
	}
}

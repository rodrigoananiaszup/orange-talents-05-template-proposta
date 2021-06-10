package br.zup.criacao.proposta.rodrigo.criacaoproposta.biometria;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
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

@RestController
@RequestMapping("/api/cartoes/{uuidCartao}/biometria")
public class BiometriaController {

	@Autowired
	private CartaoRepository cartaoRepository;

	@Autowired
	private BiometriaRepository biometriaRepository;

	@PostMapping
	@Transactional
	private ResponseEntity<?> criaBiometria(@PathVariable String uuidCartao,
			@RequestBody @Valid BiometriaRequest biometriaRequest, UriComponentsBuilder uriComponentsBuilder) {

		Optional<Cartao> possivelCartao = cartaoRepository.findByUuid(uuidCartao);
		if (possivelCartao.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O cartão informado não existe.");
		}

		Cartao cartao = possivelCartao.get();

		Biometria biometria = biometriaRequest.toModel(cartao);

		cartao.addBiometria(biometria);

		biometriaRepository.save(biometria);
		cartaoRepository.save(cartao);

		URI uri = uriComponentsBuilder.path("/api/cartoes/{uuidCartao}/biometrias/{uuidBiometria}")
				.buildAndExpand(uuidCartao, biometria.getUuid()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
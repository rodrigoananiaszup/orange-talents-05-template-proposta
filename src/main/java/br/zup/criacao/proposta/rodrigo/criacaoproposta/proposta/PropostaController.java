package br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/proposta")
public class PropostaController {

	
	@Autowired
	private PropostaRepository propostaRepository;
	
	@Autowired
	private List<NovaPropostaEvento> novaPropostaEvento;

	@PostMapping
	@Transactional
	private ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriComponentsBuilder){
		Optional<Proposta> possivelProposta = propostaRepository.findByDocumento(propostaRequest.getDocumento());
		if (possivelProposta.isPresent()) {
			throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY,
					"Este documento ja foi cadastrado em nossa base de dados durante a criação de outra proposta.");
		}
		Proposta proposta = propostaRequest.toModel();
		propostaRepository.save(proposta);
		
		for (NovaPropostaEvento evento : novaPropostaEvento) {
			evento.executarNovaProposta(proposta);
		}
		
		URI uri = uriComponentsBuilder.path("/proposta/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
}

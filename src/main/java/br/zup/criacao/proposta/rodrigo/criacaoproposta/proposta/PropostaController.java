package br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/proposta")
public class PropostaController {

	
	@Autowired
	private PropostaRepository propostaRepository;

	@PostMapping
	@Transactional
	private ResponseEntity<?> criaProposta(@RequestBody @Valid PropostaRequest propostaRequest, UriComponentsBuilder uriComponentsBuilder){
		
		Proposta proposta = propostaRequest.toModel();
		propostaRepository.save(proposta);
		
		URI uri = uriComponentsBuilder.path("/proposta/proposta/{id}").buildAndExpand(proposta.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
		
	}
}

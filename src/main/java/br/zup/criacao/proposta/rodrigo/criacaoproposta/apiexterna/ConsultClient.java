package br.zup.criacao.proposta.rodrigo.criacaoproposta.apiexterna;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.consultadadosdosolicitante.ConsultaRequest;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.consultadadosdosolicitante.ConsultaResponse;

@FeignClient(value = "ConsultaClient", url = "${consulta.uri}")
public interface ConsultClient {

	@PostMapping("/api/solicitacao")
	ResponseEntity<ConsultaResponse> consultar(ConsultaRequest request);

	@GetMapping("/actuator/health")
	ResponseEntity<Void> healthCheck();

}
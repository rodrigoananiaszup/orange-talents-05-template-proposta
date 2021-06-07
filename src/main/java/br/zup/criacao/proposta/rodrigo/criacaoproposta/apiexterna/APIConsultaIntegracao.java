package br.zup.criacao.proposta.rodrigo.criacaoproposta.apiexterna;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.consultadadosdosolicitante.ConsultaRequest;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.consultadadosdosolicitante.ConsultaResponse;

@FeignClient(value = "consulta", url = "${consulta.uri}")
public interface APIConsultaIntegracao {

	@PostMapping("/api/solicitacao")
	ResponseEntity<ConsultaResponse> consultar(ConsultaRequest request);
}
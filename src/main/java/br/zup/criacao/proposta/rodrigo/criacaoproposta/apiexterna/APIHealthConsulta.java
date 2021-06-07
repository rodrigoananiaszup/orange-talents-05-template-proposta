package br.zup.criacao.proposta.rodrigo.criacaoproposta.apiexterna;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "healthCheckAPI", url = "${consulta.uri}")
public interface APIHealthConsulta {
	@GetMapping("actuator/health")
	ResponseEntity<Void> healthCheck();
}
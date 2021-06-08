package br.zup.criacao.proposta.rodrigo.criacaoproposta.health;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.ClientCartao;

@Component
public class AccountsHealthCheckAPI implements HealthIndicator {

	@Autowired
	ClientCartao clientCartao;

	@Override
	public Health health() {
		Map<String, Object> details = new HashMap<>();

		try {
			ResponseEntity<Void> healthCheck = clientCartao.healthCheck();
			details.put("Descrição", "Contato com o endpoint da API efetivado");
			details.put("status", healthCheck.getStatusCode());
		} catch (Exception e) {
			details.put("descrição", e.getMessage());
			return Health.status(Status.DOWN).withDetails(details).build();
		}
		return Health.status(Status.UP).withDetails(details).build();
	}

}
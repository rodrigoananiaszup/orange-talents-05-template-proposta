package br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.avisodeviagem.AvisarViagemRequest;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.avisodeviagem.AvisarViagemResponse;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.bloqueio.BloquearCartaoRequest;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.bloqueio.BloquearCartaoResponse;

@FeignClient(value = "CartaoClient", url = "${accounts.uri}")
public interface ClientCartao {

	@PostMapping("/api/cartoes")
	ResponseEntity<AssociaCartaoResponse> associarCartao(AssociaCartaoRequest request);

	@PostMapping("/api/cartoes/{numeroCartao}/bloqueios")
	ResponseEntity<BloquearCartaoResponse> bloquearCartao(@PathVariable String numeroCartao,
			BloquearCartaoRequest request);

	@PostMapping("/api/cartoes/{numeroCartao}/avisos")
	ResponseEntity<AvisarViagemResponse> avisarViagem(@PathVariable String numeroCartao, AvisarViagemRequest request);

	@GetMapping("/actuator/health")
	ResponseEntity<Void> healthCheck();

}
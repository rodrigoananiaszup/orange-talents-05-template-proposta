package br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.Proposta;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.PropostaRepository;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.StatusProposta;
import feign.FeignException;

@Component
public class AssociaCartao {

	@Autowired
	ClientCartao clientCartao;

	@Autowired
	PropostaRepository propostaRepository;

	Logger logger = LoggerFactory.getLogger(AssociaCartao.class);

	@Scheduled(fixedDelay = 10000)
	public void executar() {
		Set<Proposta> propostasElegiveis = propostaRepository.findByCartaoNullAndStatus(StatusProposta.ELEGIVEL);
		propostasElegiveis.forEach(proposta -> associarCartao(proposta));
	}

	public void associarCartao(Proposta proposta) {
		try {
			AssociaCartaoRequest request = new AssociaCartaoRequest(proposta);
			ResponseEntity<AssociaCartaoResponse> responseEntity = clientCartao.associarCartao(request);
			AssociaCartaoResponse response = responseEntity.getBody();
			Cartao cartao = response.toModel(proposta);
			proposta.setCartao(cartao);
			propostaRepository.save(proposta);
		} catch (FeignException e) {
			logger.warn(e.contentUTF8().toString());
		}
	}

}
package br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.consultadadosdosolicitante;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.apiexterna.APIConsultaIntegracao;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.NovaPropostaEvento;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.Proposta;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.PropostaRepository;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.StatusProposta;
import feign.FeignException;

@Component
class ConsultaDadosSolicitante implements NovaPropostaEvento {

	@Autowired
	private APIConsultaIntegracao integracao;

	@Autowired
	private PropostaRepository propostaRepository;

	@Override
	public void executarNovaProposta(Proposta proposta) {

		ConsultaRequest request = new ConsultaRequest(proposta);
		try {
			ResponseEntity<ConsultaResponse> responseEntity = integracao.consultar(request);
			ConsultaResponse response = responseEntity.getBody();
			//proposta.setStatus(response.getResultadoSolicitacao());
			proposta.setStatus(StatusProposta.ELEGIVEL);
		} catch (FeignException e) {
			proposta.setStatus(StatusProposta.NAO_ELEGIVEL);
		}

		propostaRepository.save(proposta);

	}

}

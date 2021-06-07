package br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.consultadadosdosolicitante;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.StatusProposta;

public class ConsultaResponse {

	private String resultadoSolicitacao;

	public void setResultadoSolicitacao(String resultadoSolicitacao) {
		this.resultadoSolicitacao = resultadoSolicitacao;
	}

	public StatusProposta getResultadoSolicitacao() {
		switch (this.resultadoSolicitacao) {
		case "SEM_RESTRICAO":
			return StatusProposta.ELEGIVEL;
		case "COM_RESTRICAO":
			return StatusProposta.NAO_ELEGIVEL;
		default:
			return null;
		}
	}
}
package br.zup.criacao.proposta.rodrigo.criacaoproposta.avisodeviagem;

import javax.validation.constraints.NotNull;

public class AvisarViagemResponse {

	@NotNull
	private String resultado;

	public String getResultado() {
		return resultado;
	}

	public void setResultado(String resultado) {
		this.resultado = resultado;
	}

}

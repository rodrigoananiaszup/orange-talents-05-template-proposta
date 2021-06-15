package br.zup.criacao.proposta.rodrigo.criacaoproposta.carteira;

import javax.validation.constraints.NotBlank;

public class AssociarCarteiraResponse {

	@NotBlank
	private String resultado;
	@NotBlank
	private String id;

	public AssociarCarteiraResponse(String resultado, String id) {
		this.resultado = resultado;
		this.id = id;
	}

	public String getnCarteira() {
		return id;
	}

}

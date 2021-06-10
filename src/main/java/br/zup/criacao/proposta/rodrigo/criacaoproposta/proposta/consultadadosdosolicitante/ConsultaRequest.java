package br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.consultadadosdosolicitante;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.Proposta;

public class ConsultaRequest {

	private String idProposta;
	private String nome;
	private String documento;

	public ConsultaRequest(Proposta proposta) {
		this.idProposta = proposta.getUuid();
		this.nome = proposta.getNome();
		this.documento = proposta.getDocumento();
	}

	public String getIdProposta() {
		return idProposta;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}
}

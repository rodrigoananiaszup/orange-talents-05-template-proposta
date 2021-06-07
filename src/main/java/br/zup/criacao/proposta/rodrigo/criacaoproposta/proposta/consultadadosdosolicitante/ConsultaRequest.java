package br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.consultadadosdosolicitante;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.Proposta;

public class ConsultaRequest {

	private Long idProposta;
	private String nome;
	private String documento;

	public ConsultaRequest(Proposta proposta) {
		this.idProposta = proposta.getId();
		this.nome = proposta.getNome();
		this.documento = proposta.getDocumento();
	}

	public Long getIdProposta() {
		return idProposta;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}
}

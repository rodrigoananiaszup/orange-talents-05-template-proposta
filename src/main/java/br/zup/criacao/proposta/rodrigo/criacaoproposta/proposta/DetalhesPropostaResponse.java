package br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta;

import java.math.BigDecimal;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.DetalhesPropostaCartaoResponse;

public class DetalhesPropostaResponse {

	//atributos
	
	private String documento;

	private String email;

	private String nome;

	private String endereco;

	private BigDecimal salario;

	private StatusProposta status;

	private DetalhesPropostaCartaoResponse cartao;

	//metodo construtor
	public DetalhesPropostaResponse(Proposta proposta) {
		this.documento = proposta.getDocumento();
		this.email = proposta.getEmail();
		this.nome = proposta.getNome();
		this.endereco = proposta.getEndereco();
		this.salario = proposta.getSalario();
		this.status = proposta.getStatus();
		
		if (proposta.getCartao() != null) {
			this.cartao = new DetalhesPropostaCartaoResponse(proposta.getCartao());
		}
	}

	//getters
	public String getDocumento() {
		return documento;
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public BigDecimal getSalario() {
		return salario;
	}

	public StatusProposta getStatus() {
		return status;
	}

	public DetalhesPropostaCartaoResponse getCartao() {
		return cartao;
	}
	
	
	
	
}

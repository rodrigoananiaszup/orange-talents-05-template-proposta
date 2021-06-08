package br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DetalhesPropostaCartaoResponse {

	//atributos
	private String numeroCartao;
	private LocalDateTime emitidoEm;
	private String titular;
	private BigDecimal limite;

	//metodo construtor
	public DetalhesPropostaCartaoResponse(Cartao cartao) {
		this.numeroCartao = cartao.getId();
		this.emitidoEm = cartao.getEmitidoEm();
		this.titular = cartao.getTitular();
		this.limite = cartao.getLimite();
	}

	//getters
	public String getNumeroCartao() {
		return numeroCartao;
	}

	public LocalDateTime getEmitidoEm() {
		return emitidoEm;
	}

	public String getTitular() {
		return titular;
	}

	public BigDecimal getLimite() {
		return limite;
	}

}
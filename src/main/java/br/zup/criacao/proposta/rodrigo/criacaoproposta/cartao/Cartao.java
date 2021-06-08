package br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.Proposta;

@Entity
public class Cartao {

	@Id
	private String id;

	@NotNull
	private LocalDateTime emitidoEm;

	@NotNull
	private String titular;

	@PositiveOrZero
	private BigDecimal limite;

	@OneToOne
	Proposta proposta;

	/**
	 * No argument constructor for Hibernate, should not be used.
	 */
	@Deprecated
	public Cartao() {
	}

	public Cartao(String id, @NotNull LocalDateTime emitidoEm, @NotNull String titular, BigDecimal limite,
			Proposta proposta) {
		this.id = id;
		this.emitidoEm = emitidoEm;
		this.titular = titular;
		this.limite = limite;
		this.proposta = proposta;
	}

}
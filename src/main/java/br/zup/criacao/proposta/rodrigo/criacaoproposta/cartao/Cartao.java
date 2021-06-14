package br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import com.sun.istack.Nullable;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.avisodeviagem.AvisoViagem;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.biometria.Biometria;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.bloqueio.BloqueioCartao;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta.Proposta;

@Entity
public class Cartao {

	@Id
	private String id;

	@NotNull
	private String uuid = UUID.randomUUID().toString();


	@NotNull
	private LocalDateTime emitidoEm;

	@NotNull
	private String titular;

	@PositiveOrZero
	private BigDecimal limite;

	@OneToOne
	private Proposta proposta;

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.PERSIST)
	private Set<Biometria> biometrias;

	@OneToOne(mappedBy = "cartao", cascade = CascadeType.PERSIST)
	@Nullable
	private BloqueioCartao bloqueioCartao;

	@Enumerated(EnumType.STRING)
	private StatusCartao status = StatusCartao.DISPONIVEL;

	@OneToMany(mappedBy = "cartao", cascade = CascadeType.MERGE)
	@Nullable
	private Set<AvisoViagem> avisosViagens;

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


	public String getId() {
		return id;
	}

	public String getUuid() {
		return uuid;
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

	public Proposta getProposta() {
		return proposta;
	}

	public void addBiometria(Biometria biometria) {
		this.biometrias.add(biometria);
	}

	public boolean bloqueado() {
		return this.status == StatusCartao.BLOQUEADO;
	}

	public void bloquearCartao(BloqueioCartao bloqueioCartao) {
		this.bloqueioCartao = bloqueioCartao;
		this.status = StatusCartao.BLOQUEADO;
	}

	public void addAvisoViagem(AvisoViagem avisoViagem) {
		this.avisosViagens.add(avisoViagem);
	}

}
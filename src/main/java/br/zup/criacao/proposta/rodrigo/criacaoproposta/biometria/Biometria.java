package br.zup.criacao.proposta.rodrigo.criacaoproposta.biometria;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.Cartao;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.validation.Base64Value;

@Entity
public class Biometria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String uuid = UUID.randomUUID().toString();

	@NotBlank
	@Base64Value
	private String fingerprint;

	@NotNull
	private LocalDateTime registradoEm = LocalDateTime.now();

	@NotNull
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Biometria() {
	}

	public Biometria(@NotBlank String fingerprint, @NotNull Cartao cartao) {
		this.fingerprint = fingerprint;
		this.cartao = cartao;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}

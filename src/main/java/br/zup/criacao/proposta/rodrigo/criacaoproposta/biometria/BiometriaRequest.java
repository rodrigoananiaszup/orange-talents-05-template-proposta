package br.zup.criacao.proposta.rodrigo.criacaoproposta.biometria;

import javax.validation.constraints.NotBlank;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.Cartao;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.validation.Base64Value;

public class BiometriaRequest {

	@NotBlank
	@Base64Value
	private String fingerprint;

	public void setFingerprint(String fingerprint) {
		this.fingerprint = fingerprint;
	}

	public Biometria toModel(Cartao cartao) {
		return new Biometria(this.fingerprint, cartao);
	}
}
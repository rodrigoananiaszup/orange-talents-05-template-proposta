package br.zup.criacao.proposta.rodrigo.criacaoproposta.carteira;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AssociarCarteiraRequest {

	@NotBlank
	@Email
	private String email;

	@Enumerated(EnumType.STRING)
	private TipoCarteira carteira;

	public AssociarCarteiraRequest(@NotBlank @Email String email, TipoCarteira carteira) {
		this.email = email;
		this.carteira = carteira;
	}

	public String getEmail() {
		return email;
	}

	public TipoCarteira getCarteira() {
		return carteira;
	}
}

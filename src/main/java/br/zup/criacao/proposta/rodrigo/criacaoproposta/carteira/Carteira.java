package br.zup.criacao.proposta.rodrigo.criacaoproposta.carteira;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.Cartao;

@Entity
public class Carteira {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Enumerated(EnumType.STRING)
	private TipoCarteira tipoCarteira;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String numeroCarteira;

	@NotNull
	@ManyToOne
	private Cartao cartao;

	@Deprecated
	public Carteira() {}

	public Carteira(TipoCarteira tipoCarteira, @NotBlank @Email String email, @NotBlank String numeroCarteira,
			@NotNull Cartao cartao) {
		this.tipoCarteira = tipoCarteira;
		this.email = email;
		this.numeroCarteira = numeroCarteira;
		this.cartao = cartao;
	}

}
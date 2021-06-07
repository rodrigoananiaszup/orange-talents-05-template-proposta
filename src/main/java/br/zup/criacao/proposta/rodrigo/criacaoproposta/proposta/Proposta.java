package br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.validation.CPFOuCNPJ;

@Entity
public class Proposta {

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@CPFOuCNPJ
	@NotBlank
	private String documento;

	@Email
	@NotBlank
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String endereco;

	@NotNull
	@PositiveOrZero
	private BigDecimal salario;

	@Enumerated(EnumType.STRING)
	private StatusProposta status;

	// construtor padrão sem campos
	@Deprecated
	public Proposta() {
	}

	// construtor com campos
	public Proposta(@NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @PositiveOrZero BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}

	// metodos getters e setters.

	public Long getId() {
		return id;
	}

	public void setStatus(StatusProposta status) {
		this.status = status;
	}

	public String getNome() {
		return nome;
	}

	public String getDocumento() {
		return documento;
	}

}

package br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta;

import java.math.BigDecimal;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.validation.CPFOuCNPJ;

public class PropostaRequest {

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

	public PropostaRequest(@NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @PositiveOrZero BigDecimal salario) {
		this.documento = documento;
		this.email = email;
		this.nome = nome;
		this.endereco = endereco;
		this.salario = salario;
	}
	
	public Proposta toModel() {
		return new Proposta(this.documento, this.email, this.nome, this.endereco, this.salario);
	}
	
	
}

package br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import com.sun.istack.Nullable;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.Cartao;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.validation.CPFOuCNPJ;

@Entity
public class Proposta {

	// Atributos
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String uuid = UUID.randomUUID().toString();

	
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

	@Nullable
	@OneToOne(mappedBy = "proposta", cascade = CascadeType.MERGE)
	private Cartao cartao;

	// construtor padrão sem campos
	@Deprecated
	public Proposta() {
	}

	// construtor com campos
	public Proposta(@NotBlank String documento, @Email @NotBlank String email, @NotBlank String nome,
			@NotBlank String endereco, @NotNull @PositiveOrZero BigDecimal salario) {
		Assert.state(documento.length() != 60,
				"A proposta não deve ser construida com um documento neste formato!");
		this.documento = new BCryptPasswordEncoder().encode(documento);
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

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public String getEmail() {
		return email;
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

	public Cartao getCartao() {
		return cartao;
	}

	public String getUuid() {
		return uuid;
	}

}
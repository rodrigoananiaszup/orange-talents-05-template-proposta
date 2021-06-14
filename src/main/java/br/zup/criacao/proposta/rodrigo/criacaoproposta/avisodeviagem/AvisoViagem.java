package br.zup.criacao.proposta.rodrigo.criacaoproposta.avisodeviagem;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.Cartao;

@Entity
public class AvisoViagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String destinoViagem;

	@NotNull
	@FutureOrPresent
	private LocalDate dataTermino;

	@NotNull
	private LocalDateTime instante = LocalDateTime.now();

	@NotNull
	private String ip;

	@NotNull
	private String userAgent;

	@ManyToOne
	@NotNull
	private Cartao cartao;

	@Deprecated
	public AvisoViagem() {
	}

	public AvisoViagem(@NotBlank String destinoViagem, @NotNull @FutureOrPresent LocalDate dataTermino,
			@NotNull String ip, @NotNull String userAgent, @NotNull Cartao cartao) {
		this.destinoViagem = destinoViagem;
		this.dataTermino = dataTermino;
		this.ip = ip;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}

}

package br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.bloqueio;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.Cartao;

@Entity
public class BloqueioCartao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String uuid = UUID.randomUUID().toString();
	@NotNull
	private LocalDateTime instanteBloqueio = LocalDateTime.now();
	@NotNull
	private String ip;
	@NotNull
	private String userAgent;

	@OneToOne
	@NotNull
	private Cartao cartao;

	@Deprecated
	public BloqueioCartao() {

	}

	public BloqueioCartao(@NotNull String ip, @NotNull String userAgent, @NotNull Cartao cartao) {
		this.ip = ip;
		this.userAgent = userAgent;
		this.cartao = cartao;
	}

}

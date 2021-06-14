package br.zup.criacao.proposta.rodrigo.criacaoproposta.avisodeviagem;

import java.time.LocalDate;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.Cartao;

public class AvisoViagemRequest {

	@NotBlank
	private String destinoViagem;

	@NotNull
	@FutureOrPresent
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataTermino;

	public void setDestinoViagem(String destinoViagem) {
		this.destinoViagem = destinoViagem;
	}

	public void setDataTermino(LocalDate dataTermino) {
		this.dataTermino = dataTermino;
	}

	public AvisoViagem toModel(String ip, String userAgent, Cartao cartao) {
		return new AvisoViagem(this.destinoViagem, this.dataTermino, ip, userAgent, cartao);
	}

}

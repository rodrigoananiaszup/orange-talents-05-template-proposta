package br.zup.criacao.proposta.rodrigo.criacaoproposta.avisodeviagem;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class AvisarViagemRequest {

	@NotBlank
	private String destinoViagem;

	@NotNull
	private @NotNull LocalDate dataTermino;

	public AvisarViagemRequest(@NotBlank String destinoViagem, @NotNull LocalDate dataTermino) {
		this.destinoViagem = destinoViagem;
		this.dataTermino = dataTermino;
	}

	public String getDestinoViagem() {
		return destinoViagem;
	}

	public LocalDate getDataTermino() {
		return dataTermino;
	}

	@Override
	public String toString() {
		return "AvisarViagemRequest [destinoViagem=" + destinoViagem + ", dataTermino=" + dataTermino + "]";
	}

}
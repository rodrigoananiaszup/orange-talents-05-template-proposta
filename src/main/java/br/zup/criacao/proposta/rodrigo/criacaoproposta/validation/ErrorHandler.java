package br.zup.criacao.proposta.rodrigo.criacaoproposta.validation;

import java.util.Collection;

public class ErrorHandler {

	private Collection<String> mensagens;

	//construtor
	public ErrorHandler(Collection<String> mensagens) {
		this.mensagens = mensagens;
	}

	//getter
	public Collection<String> getMensagens() {
		return mensagens;
	}

}

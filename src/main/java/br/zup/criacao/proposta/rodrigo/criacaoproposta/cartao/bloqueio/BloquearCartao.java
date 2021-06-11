package br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.bloqueio;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.Cartao;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.ClientCartao;
import feign.FeignException;

@Component
public class BloquearCartao {

	@Autowired
	private ClientCartao clientCartao;
	
	public void bloquear(Cartao cartao, HttpServletRequest request) throws FeignException {
		clientCartao.bloquearCartao(cartao.getId(), new BloquearCartaoRequest());
	}
}
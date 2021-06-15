package br.zup.criacao.proposta.rodrigo.criacaoproposta.carteira.paypal;

import javax.validation.constraints.NotBlank;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.Cartao;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.carteira.AssociarCarteiraRequest;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.carteira.Carteira;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.carteira.CarteiraRequest;
import br.zup.criacao.proposta.rodrigo.criacaoproposta.carteira.TipoCarteira;

public class PaypalCarteiraRequest implements CarteiraRequest {

	@NotBlank
	private String email;

	private final TipoCarteira tipoCarteira = TipoCarteira.PayPal;

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public Carteira toModel(String numeroCarteira, Cartao cartao) {
		return new Carteira(this.tipoCarteira, this.email, numeroCarteira, cartao);
	}

	@Override
	public AssociarCarteiraRequest toAPI(String numeroCartao) {
		return new AssociarCarteiraRequest(numeroCartao, this.tipoCarteira);
	}

}

package br.zup.criacao.proposta.rodrigo.criacaoproposta.carteira;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.Cartao;

public interface CarteiraRequest {


	public Carteira toModel(String numeroCarteira, Cartao cartao);

	public AssociarCarteiraRequest toAPI(String numeroCartao);
	
	TipoCarteira getTipoCarteira(); 
}

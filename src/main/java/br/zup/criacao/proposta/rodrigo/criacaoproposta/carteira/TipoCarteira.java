package br.zup.criacao.proposta.rodrigo.criacaoproposta.carteira;

public enum TipoCarteira {

	PayPal(1);

	Integer carteiraLimite;

	TipoCarteira(Integer limiteCarteira) {
		if (limiteCarteira == null) {
			this.carteiraLimite = Integer.MAX_VALUE;
		}
		this.carteiraLimite = limiteCarteira;
	}

	public Integer getCarteiraLimite() {
		return carteiraLimite;
	}
}
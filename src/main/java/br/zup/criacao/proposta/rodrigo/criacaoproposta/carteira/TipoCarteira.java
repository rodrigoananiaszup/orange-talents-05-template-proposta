package br.zup.criacao.proposta.rodrigo.criacaoproposta.carteira;

public enum TipoCarteira {

	PayPal(1, "paypal"), SamsungPay(Integer.MAX_VALUE, "samsungpay");

	private Integer carteiraLimite;
	private String uri;

	TipoCarteira(Integer carteiraLimite, String uri) {
		if (carteiraLimite == null) {
			carteiraLimite = Integer.MAX_VALUE;
		}
		this.carteiraLimite = carteiraLimite;
		this.uri = uri;
	}

	public Integer getCarteiraLimite() {
		return carteiraLimite;
	}
	
	public String getUri() {
		return uri;
	}

}
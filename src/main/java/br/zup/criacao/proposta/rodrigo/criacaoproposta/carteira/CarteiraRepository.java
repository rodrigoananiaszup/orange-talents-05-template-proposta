package br.zup.criacao.proposta.rodrigo.criacaoproposta.carteira;

import org.springframework.data.repository.CrudRepository;

import br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao.Cartao;

public interface CarteiraRepository extends CrudRepository<Carteira, Long> {

	Integer countByCartaoAndTipoCarteira(Cartao cartao, TipoCarteira tipoCarteira);

}
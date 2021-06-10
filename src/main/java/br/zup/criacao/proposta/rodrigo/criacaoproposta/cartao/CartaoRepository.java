package br.zup.criacao.proposta.rodrigo.criacaoproposta.cartao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface CartaoRepository extends CrudRepository<Cartao, Long> {
	Optional<Cartao> findByUuid(String uuid);

}

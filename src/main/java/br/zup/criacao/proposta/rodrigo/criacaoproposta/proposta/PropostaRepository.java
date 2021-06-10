package br.zup.criacao.proposta.rodrigo.criacaoproposta.proposta;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {

	Optional<Proposta> findByDocumento(String documento);

	Set<Proposta> findByCartaoNullAndStatus(StatusProposta status);
	
	Optional<Proposta> findByUuid(String uuid);
}
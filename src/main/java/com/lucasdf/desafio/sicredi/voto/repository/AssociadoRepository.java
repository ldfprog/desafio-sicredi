package com.lucasdf.desafio.sicredi.voto.repository;

import com.lucasdf.desafio.sicredi.voto.model.Associado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociadoRepository extends CrudRepository<Associado, Long> {

    Optional<Associado> findByCpf(String cpf);
}

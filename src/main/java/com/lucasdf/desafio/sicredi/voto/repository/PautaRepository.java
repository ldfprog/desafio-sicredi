package com.lucasdf.desafio.sicredi.voto.repository;

import com.lucasdf.desafio.sicredi.voto.model.Pauta;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PautaRepository extends CrudRepository<Pauta, Long> {

    Optional<Pauta> findByDescricao(String cpf);
}

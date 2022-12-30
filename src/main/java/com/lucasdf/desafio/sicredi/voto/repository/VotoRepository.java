package com.lucasdf.desafio.sicredi.voto.repository;

import com.lucasdf.desafio.sicredi.voto.model.Voto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotoRepository extends CrudRepository<Voto, Long> {

    List<Voto> findBySessaoPautaId(Long id);
}

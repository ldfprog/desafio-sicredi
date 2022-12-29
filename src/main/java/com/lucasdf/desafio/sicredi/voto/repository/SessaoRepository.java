package com.lucasdf.desafio.sicredi.voto.repository;

import com.lucasdf.desafio.sicredi.voto.model.Sessao;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessaoRepository extends CrudRepository<Sessao, Long> {


}

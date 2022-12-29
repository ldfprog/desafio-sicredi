package com.lucasdf.desafio.sicredi.voto.service;

import com.lucasdf.desafio.sicredi.voto.model.Associado;
import com.lucasdf.desafio.sicredi.voto.model.Pauta;
import com.lucasdf.desafio.sicredi.voto.repository.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PautaService {

    @Autowired
    private PautaRepository repository;

    public Pauta criar(Pauta pauta) {

        Optional<Pauta> optionalPauta = repository.findByDescricao(pauta.getDescricao());

        return optionalPauta.isEmpty()?
                repository.save(pauta):
                optionalPauta.get();
    }
}

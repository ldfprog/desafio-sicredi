package com.lucasdf.desafio.sicredi.voto.service;

import com.lucasdf.desafio.sicredi.voto.model.Associado;
import com.lucasdf.desafio.sicredi.voto.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AssociadoService {

    @Autowired
    private AssociadoRepository repository;

    public Associado cadastrar(Associado associado) {

        Optional<Associado> optionalAssociado = repository.findByCpf(associado.getCpf());

        return optionalAssociado.isEmpty()?
                repository.save(associado):
                optionalAssociado.get();
    }
}

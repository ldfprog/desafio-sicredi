package com.lucasdf.desafio.sicredi.voto.service;

import com.lucasdf.desafio.sicredi.voto.model.Sessao;
import com.lucasdf.desafio.sicredi.voto.repository.SessaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class SessaoService {

    @Autowired
    private SessaoRepository repository;

    public Sessao abrir(Sessao sessao) {

        if (sessao.getDuracao() == null) sessao.setDuracao(1);
        if (sessao.getDataCriacao() == null) sessao.setDataCriacao(new Date());

        return repository.save(sessao);
    }

    public Boolean sessaoAberta(Long id) {

        Boolean response = false;

        Optional<Sessao> result = repository.findById(id);

        if (result.isPresent()) {

            Sessao sessao = result.get();

            long timeInSecs = sessao.getDataCriacao().getTime();
            Date dataVencimento = new Date(timeInSecs + (sessao.getDuracao() * 60 * 1000));
            Date dataAtual = new Date();
            if (dataAtual.compareTo(dataVencimento) < 0)
                response = true;
        }

        return response;
    }
}

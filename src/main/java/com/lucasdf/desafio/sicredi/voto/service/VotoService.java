package com.lucasdf.desafio.sicredi.voto.service;

import com.lucasdf.desafio.sicredi.voto.dto.ResultadoVotoDto;
import com.lucasdf.desafio.sicredi.voto.model.Voto;
import com.lucasdf.desafio.sicredi.voto.repository.VotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VotoService {

    @Autowired
    private VotoRepository repository;

    public Voto votar(Voto voto) {

        if (voto.getDataVoto() == null) voto.setDataVoto(new Date());

        return repository.save(voto);
    }

    public ResultadoVotoDto obterResultado(Long id) {

        ResultadoVotoDto resultadoVotoDto = new ResultadoVotoDto();

        List<Voto> votos = repository.findBySessaoPautaId(id);

        resultadoVotoDto.setSim(votos.stream().filter(voto -> voto.getVoto().equals(true)).count());
        resultadoVotoDto.setNao(votos.stream().filter(voto -> voto.getVoto().equals(false)).count());

        return resultadoVotoDto;
    }
}
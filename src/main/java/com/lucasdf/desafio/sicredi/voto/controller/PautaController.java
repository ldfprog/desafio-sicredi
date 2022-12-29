package com.lucasdf.desafio.sicredi.voto.controller;

import com.lucasdf.desafio.sicredi.voto.dto.ResponseDto;
import com.lucasdf.desafio.sicredi.voto.model.Associado;
import com.lucasdf.desafio.sicredi.voto.model.Pauta;
import com.lucasdf.desafio.sicredi.voto.service.PautaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/pautas")
@RestController
public class PautaController {

    @Autowired
    private PautaService service;

    @PostMapping
    public ResponseEntity<ResponseDto> salvar(@RequestBody Pauta pauta) {

        ResponseDto response = new ResponseDto();

        if (pauta.getDescricao() == null) {
            response.setErro("Descrição da pauta não informada.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Pauta pautaSalva = service.criar(pauta);
            response.setId(pautaSalva.getId());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException re) {
            response.setErro(re.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}

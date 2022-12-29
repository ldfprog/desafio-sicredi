package com.lucasdf.desafio.sicredi.voto.controller;

import com.lucasdf.desafio.sicredi.voto.dto.ResponseDto;
import com.lucasdf.desafio.sicredi.voto.model.Pauta;
import com.lucasdf.desafio.sicredi.voto.model.Sessao;
import com.lucasdf.desafio.sicredi.voto.service.SessaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/sessoes")
@RestController
public class SessaoController {

    @Autowired
    private SessaoService service;

    @PostMapping
    public ResponseEntity<ResponseDto> salvar(@RequestBody Sessao sessao) {

        ResponseDto response = new ResponseDto();

        if (sessao.getPauta() == null || sessao.getPauta().getId() == null) {
            response.setErro("Pauta da sessão não informada.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Sessao sessaoSalva = service.abrir(sessao);
            response.setId(sessaoSalva.getId());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException re) {
            response.setErro(re.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}

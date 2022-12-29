package com.lucasdf.desafio.sicredi.voto.controller;

import com.lucasdf.desafio.sicredi.voto.dto.ResponseDto;
import com.lucasdf.desafio.sicredi.voto.dto.ResultadoVotoDto;
import com.lucasdf.desafio.sicredi.voto.model.Pauta;
import com.lucasdf.desafio.sicredi.voto.model.Sessao;
import com.lucasdf.desafio.sicredi.voto.model.Voto;
import com.lucasdf.desafio.sicredi.voto.service.SessaoService;
import com.lucasdf.desafio.sicredi.voto.service.VotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/votos")
@RestController
public class VotoController {

    @Autowired
    private VotoService service;

    @Autowired
    private SessaoService sessaoService;

    @PostMapping
    public ResponseEntity<ResponseDto> salvar(@RequestBody Voto voto) {

        ResponseDto response = new ResponseDto();

        if (voto.getSessao() == null || voto.getSessao().getId() == null) {
            response.setErro("Sessão do voto não informado.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (voto.getAssociado() == null || voto.getAssociado().getId() == null) {
            response.setErro("Associado não informado.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (!sessaoService.sessaoAberta(voto.getSessao().getId())) {
            response.setErro("Sessão de votação já foi encerrada.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Voto votoSalvo = service.votar(voto);
            response.setId(votoSalvo.getId());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException re) {
            response.setErro(re.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<?> resultadoVotacao(@RequestBody Pauta pauta) {

        if (pauta.getId() == null) {
            ResponseDto response = new ResponseDto();
            response.setErro("Sessão do voto não informado.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            ResultadoVotoDto resultado = service.obterResultado(pauta.getId());
            return new ResponseEntity<>(resultado, HttpStatus.CREATED);
        } catch (RuntimeException re) {
            ResponseDto response = new ResponseDto();
            response.setErro(re.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}

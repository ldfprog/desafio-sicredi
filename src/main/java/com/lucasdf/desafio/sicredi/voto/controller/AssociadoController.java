package com.lucasdf.desafio.sicredi.voto.controller;

import com.lucasdf.desafio.sicredi.voto.dto.ResponseDto;
import com.lucasdf.desafio.sicredi.voto.model.Associado;
import com.lucasdf.desafio.sicredi.voto.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/associados")
@RestController
public class AssociadoController {

    @Autowired
    private AssociadoService service;

    @PostMapping
    public ResponseEntity<ResponseDto> salvar(@RequestBody Associado associado) {

        ResponseDto response = new ResponseDto();

        if (associado.getCpf() == null) {
            response.setErro("CPF do associado não informado.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            Associado associadoSalvo = service.cadastrar(associado);
            response.setId(associadoSalvo.getId());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException re) {
            response.setErro(re.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}

package com.lucasdf.desafio.sicredi.voto.controller.v2;

import com.lucasdf.desafio.sicredi.voto.controller.v1.VotoControllerV1;
import com.lucasdf.desafio.sicredi.voto.dto.ResponseDto;
import com.lucasdf.desafio.sicredi.voto.dto.VotoDto;
import com.lucasdf.desafio.sicredi.voto.service.AssociadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v2/votos")
@RestController
public class VotoControllerV2 extends VotoControllerV1 {

    @Autowired
    AssociadoService associadoService;

    @Override
    public ResponseEntity<ResponseDto> salvar(@RequestBody VotoDto votoDto) {

        ResponseDto response = new ResponseDto();

        if (votoDto.getAssociado() == null) {
            response.setErro("Associado não informado.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (!associadoService.validarCpf(votoDto.getAssociado())) {
            response.setErro("Associado não cadastrado ou impossibilitado de votar.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return super.salvar(votoDto);
    }
}

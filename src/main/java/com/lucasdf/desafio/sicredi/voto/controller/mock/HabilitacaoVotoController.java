package com.lucasdf.desafio.sicredi.voto.controller.mock;

import com.lucasdf.desafio.sicredi.voto.dto.ResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/mock/users")
@RestController
public class HabilitacaoVotoController {

    @Operation(summary = "Mock da consulta externa que verifica pelo CPF se a pessoa pode votar.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "CPF inválido.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)) }) })
    @GetMapping("/{cpf}")
    public ResponseEntity<?> resultadoVotacao(@PathVariable String cpf) {

        Map<String, String> retorno;

        switch (cpf) {
            case "19839091069":
                retorno = new HashMap<>();
                retorno.put("status","ABLE_TO_VOTE");
                return new ResponseEntity<>(retorno, HttpStatus.OK);
            case "62289608068":
                retorno = new HashMap<>();
                retorno.put("status","UNABLE_TO_VOTE");
                return new ResponseEntity<>(retorno, HttpStatus.OK);
            default:
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}

package com.lucasdf.desafio.sicredi.voto.controller.v1;

import com.lucasdf.desafio.sicredi.voto.dto.ResponseDto;
import com.lucasdf.desafio.sicredi.voto.dto.SessaoDto;
import com.lucasdf.desafio.sicredi.voto.model.Pauta;
import com.lucasdf.desafio.sicredi.voto.model.Sessao;
import com.lucasdf.desafio.sicredi.voto.service.SessaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/v1/sessoes")
@RestController
public class SessaoControllerV1 {

    @Autowired
    private SessaoService service;

    @Operation(summary = "Abertura de uma nova sessão de votação.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Pauta não informada.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)) }),
            @ApiResponse(responseCode = "409", description = "Erro ao persistir o dado.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)) }) })
    @PostMapping
    public ResponseEntity<ResponseDto> salvar(@RequestBody SessaoDto sessaoDto) {

        ResponseDto response = new ResponseDto();

        //Validações do objeto de entrada
        if (sessaoDto.getPauta() == null) {
            response.setErro("Pauta da sessão não informada.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        //De-Para Dto -> Entity
        Pauta pauta = new Pauta();
        pauta.setId(sessaoDto.getPauta());

        Sessao sessao = new Sessao(
                sessaoDto.getDuracao(),
                sessaoDto.getDataCriacao(),
                pauta);

        //Chamada do serviço
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

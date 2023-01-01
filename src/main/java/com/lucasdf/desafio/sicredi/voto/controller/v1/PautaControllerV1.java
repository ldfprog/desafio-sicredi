package com.lucasdf.desafio.sicredi.voto.controller.v1;

import com.lucasdf.desafio.sicredi.voto.dto.PautaDto;
import com.lucasdf.desafio.sicredi.voto.dto.ResponseDto;
import com.lucasdf.desafio.sicredi.voto.model.Pauta;
import com.lucasdf.desafio.sicredi.voto.service.PautaService;
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

@RequestMapping("/v1/pautas")
@RestController
public class PautaControllerV1 {

    @Autowired
    private PautaService service;

    @Operation(summary = "Criação de uma nova pauta. " +
            "Se a descrição informada já existe retorna o id da pauta já cadastrada.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Descrição não informada.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)) }),
            @ApiResponse(responseCode = "409", description = "Erro ao persistir o dado.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)) }) })
    @PostMapping
    public ResponseEntity<ResponseDto> salvar(@RequestBody PautaDto pautaDto) {

        ResponseDto response = new ResponseDto();

        //Validações do objeto de entrada
        if (pautaDto.getDescricao() == null) {
            response.setErro("Descrição da pauta não informada.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        //De-Para Dto -> Entity
        Pauta pauta = new Pauta(pautaDto.getDescricao());

        //Chamada do serviço
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

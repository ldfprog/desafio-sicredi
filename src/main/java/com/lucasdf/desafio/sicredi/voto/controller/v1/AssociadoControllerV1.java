package com.lucasdf.desafio.sicredi.voto.controller.v1;

import com.lucasdf.desafio.sicredi.voto.dto.AssociadoDto;
import com.lucasdf.desafio.sicredi.voto.dto.ResponseDto;
import com.lucasdf.desafio.sicredi.voto.model.Associado;
import com.lucasdf.desafio.sicredi.voto.service.AssociadoService;
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

@RequestMapping("/v1/associados")
@RestController
public class AssociadoControllerV1 {

    @Autowired
    private AssociadoService service;

    @Operation(summary = "Cadastro de associado. " +
            "Se o CPF informado já existe retorna o id do associado já cadastrado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso.",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Cpf não informado.",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseDto.class)) }),
            @ApiResponse(responseCode = "409", description = "Erro ao persistir o dado.",
                    content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = ResponseDto.class)) }) })
    @PostMapping
    public ResponseEntity<ResponseDto> salvar(@RequestBody AssociadoDto associadoDto) {

        ResponseDto response = new ResponseDto();

        //Validações do objeto de entrada
        if (associadoDto.getCpf() == null) {
            response.setErro("CPF do associado não informado.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        //De-Para Dto -> Entity
        Associado associado = new Associado(associadoDto.getCpf());

        //Chamada do serviço
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

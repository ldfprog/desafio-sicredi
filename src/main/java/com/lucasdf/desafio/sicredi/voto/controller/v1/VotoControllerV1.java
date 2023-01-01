package com.lucasdf.desafio.sicredi.voto.controller.v1;

import com.lucasdf.desafio.sicredi.voto.dto.ResponseDto;
import com.lucasdf.desafio.sicredi.voto.dto.ResultadoVotoDto;
import com.lucasdf.desafio.sicredi.voto.dto.VotoDto;
import com.lucasdf.desafio.sicredi.voto.model.Associado;
import com.lucasdf.desafio.sicredi.voto.model.Sessao;
import com.lucasdf.desafio.sicredi.voto.model.Voto;
import com.lucasdf.desafio.sicredi.voto.service.SessaoService;
import com.lucasdf.desafio.sicredi.voto.service.VotoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/v1/votos")
@RestController
public class VotoControllerV1 {

    @Autowired
    private VotoService service;

    @Autowired
    private SessaoService sessaoService;

    @Operation(summary = "Registro de um voto.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Operação realizada com sucesso.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Sessão ou associado não informado ou a sessão já foi encerrada.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)) }),
            @ApiResponse(responseCode = "409", description = "Erro ao persistir o dado.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)) }) })
    @PostMapping
    public ResponseEntity<ResponseDto> salvar(@RequestBody VotoDto votoDto) {

        ResponseDto response = new ResponseDto();

        //Validações do objeto de entrada
        if (votoDto.getSessao() == null) {
            response.setErro("Sessão do voto não informado.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (votoDto.getAssociado() == null) {
            response.setErro("Associado não informado.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        if (!sessaoService.sessaoAberta(votoDto.getSessao())) {
            response.setErro("Sessão de votação já foi encerrada.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        //De-Para Dto -> Entity
        Associado associado = new Associado();
        associado.setId(votoDto.getAssociado());

        Sessao sessao = new Sessao();
        sessao.setId(votoDto.getSessao());

        Voto voto = new Voto(
                associado,
                sessao,
                votoDto.getVoto(),
                votoDto.getDataVoto());

        //Chamada do serviço
        try {
            Voto votoSalvo = service.votar(voto);
            response.setId(votoSalvo.getId());
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (RuntimeException re) {
            response.setErro(re.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }

    @Operation(summary = "Consulta do resultado dos votos de uma pauta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Operação realizada com sucesso.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Pauta não informada.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)) }),
            @ApiResponse(responseCode = "409", description = "Erro ao consultar os dados.",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseDto.class)) }) })
    @GetMapping
    public ResponseEntity<?> resultadoVotacao(@RequestParam Long pauta) {

        if (pauta == null) {
            ResponseDto response = new ResponseDto();
            response.setErro("Pauta não informada.");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            ResultadoVotoDto resultado = service.obterResultado(pauta);
            return new ResponseEntity<>(resultado, HttpStatus.OK);
        } catch (RuntimeException re) {
            ResponseDto response = new ResponseDto();
            response.setErro(re.getMessage());
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }
    }
}

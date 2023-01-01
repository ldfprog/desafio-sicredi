package com.lucasdf.desafio.sicredi.voto.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lucasdf.desafio.sicredi.voto.model.Associado;
import com.lucasdf.desafio.sicredi.voto.repository.AssociadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;
import java.util.Optional;

@Service
public class AssociadoService {

    static final String ABLE_TO_VOTE = "ABLE_TO_VOTE";

    @Value("${url.validacao.cpf}")
    private String urlValidacao;

    @Autowired
    private AssociadoRepository repository;

    public Associado cadastrar(Associado associado) {

        Optional<Associado> optionalAssociado = repository.findByCpf(associado.getCpf());

        return optionalAssociado.isEmpty()?
                repository.save(associado):
                optionalAssociado.get();
    }

    public Boolean validarCpf(Long id) throws RuntimeException {

        Optional<Associado> associado = repository.findById(id);

        if (associado.isPresent())
            try {
                URI uri = new URI(urlValidacao + associado.get().getCpf());

                HttpRequest request = HttpRequest.newBuilder()
                        .uri(uri)
                        .GET()
                        .build();

                HttpResponse<String> response = HttpClient
                        .newBuilder()
                        .proxy(ProxySelector.getDefault())
                        .build()
                        .send(request, BodyHandlers.ofString());

                if (response.statusCode() == HttpStatus.OK.value()) {

                    ObjectMapper mapper = new ObjectMapper();
                    Map<String, String> map = mapper.readValue(response.body(), Map.class);

                    return map.get("status").equals(ABLE_TO_VOTE);
                } else {
                    return false;
                }
            } catch (URISyntaxException | IOException | InterruptedException e) {
                e.printStackTrace(System.out);
                throw new RuntimeException("Ocorreu um erro ao consultar API externa!");
            }
        else
            return false;
    }
}

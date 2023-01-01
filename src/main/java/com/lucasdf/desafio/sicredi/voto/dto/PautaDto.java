package com.lucasdf.desafio.sicredi.voto.dto;

import com.lucasdf.desafio.sicredi.voto.model.Sessao;
import jakarta.persistence.*;

import java.util.Set;

public class PautaDto {

    private String descricao;

    public PautaDto() {
    }

    public PautaDto(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

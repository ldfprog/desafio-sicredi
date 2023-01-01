package com.lucasdf.desafio.sicredi.voto.dto;

import com.lucasdf.desafio.sicredi.voto.model.Voto;
import jakarta.persistence.*;

import java.util.Set;

public class AssociadoDto {

    private String cpf;

    public AssociadoDto() {
    }

    public AssociadoDto(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}

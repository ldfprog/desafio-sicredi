package com.lucasdf.desafio.sicredi.voto.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Pauta {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String descricao;

    @OneToMany(mappedBy="pauta")
    private Set<Sessao> sessoes;

    public Pauta() {
    }

    public Pauta(String descricao) {
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

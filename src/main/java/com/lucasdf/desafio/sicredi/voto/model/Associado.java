package com.lucasdf.desafio.sicredi.voto.model;

import jakarta.persistence.*;
import java.util.Set;

@Entity
public class Associado {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String cpf;

    @OneToMany(mappedBy="associado")
    private Set<Voto> votos;

    public Associado() {
    }

    public Associado(String cpf) {
        this.cpf = cpf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Set<Voto> getVotos() {
        return votos;
    }

    public void setVotos(Set<Voto> votos) {
        this.votos = votos;
    }
}

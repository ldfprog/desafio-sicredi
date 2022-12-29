package com.lucasdf.desafio.sicredi.voto.model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Sessao {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Integer duracao;

    private Date dataCriacao;

    @OneToMany(mappedBy="sessao")
    private Set<Voto> votos;

    @ManyToOne
    @JoinColumn(name = "pauta_id", referencedColumnName = "id")
    private Pauta pauta;

    public Sessao() {
    }

    public Sessao(Integer duracao, Date dataCriacao, Pauta pauta) {
        this.duracao = duracao;
        this.dataCriacao = dataCriacao;
        this.pauta = pauta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Set<Voto> getVotos() {
        return votos;
    }

    public void setVotos(Set<Voto> votos) {
        this.votos = votos;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }
}

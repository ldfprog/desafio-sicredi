package com.lucasdf.desafio.sicredi.voto.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Voto {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "associado_id", referencedColumnName = "id")
    private Associado associado;

    @ManyToOne
    @JoinColumn(name = "sessao_id", referencedColumnName = "id")
    private Sessao sessao;

    private Boolean voto;

    private Date dataVoto;

    public Voto() {
    }

    public Voto(Associado associado, Sessao sessao, Boolean voto, Date dataVoto) {
        this.associado = associado;
        this.sessao = sessao;
        this.voto = voto;
        this.dataVoto = dataVoto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    public Sessao getSessao() {
        return sessao;
    }

    public void setSessao(Sessao sessao) {
        this.sessao = sessao;
    }

    public Boolean getVoto() {
        return voto;
    }

    public void setVoto(Boolean voto) {
        this.voto = voto;
    }

    public Date getDataVoto() {
        return dataVoto;
    }

    public void setDataVoto(Date dataVoto) {
        this.dataVoto = dataVoto;
    }
}

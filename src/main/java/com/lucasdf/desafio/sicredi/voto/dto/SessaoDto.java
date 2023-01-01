package com.lucasdf.desafio.sicredi.voto.dto;

import com.lucasdf.desafio.sicredi.voto.model.Pauta;
import com.lucasdf.desafio.sicredi.voto.model.Voto;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

public class SessaoDto {

    private Integer duracao;

    private Date dataCriacao;

    private Long pauta;

    public SessaoDto() {
    }

    public SessaoDto(Integer duracao, Date dataCriacao, Long pauta) {
        this.duracao = duracao;
        this.dataCriacao = dataCriacao;
        this.pauta = pauta;
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

    public Long getPauta() {
        return pauta;
    }

    public void setPauta(Long pauta) {
        this.pauta = pauta;
    }
}

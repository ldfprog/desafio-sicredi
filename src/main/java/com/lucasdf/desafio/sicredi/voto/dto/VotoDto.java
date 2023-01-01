package com.lucasdf.desafio.sicredi.voto.dto;

import java.util.Date;

public class VotoDto {

    private Long associado;
    private Long sessao;
    private Boolean voto;
    private Date dataVoto;

    public VotoDto() {
    }

    public VotoDto(Long associado, Long sessao, Boolean voto, Date dataVoto) {
        this.associado = associado;
        this.sessao = sessao;
        this.voto = voto;
        this.dataVoto = dataVoto;
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

    public Long getAssociado() {
        return associado;
    }

    public void setAssociado(Long associado) {
        this.associado = associado;
    }

    public Long getSessao() {
        return sessao;
    }

    public void setSessao(Long sessao) {
        this.sessao = sessao;
    }
}

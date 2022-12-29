package com.lucasdf.desafio.sicredi.voto.dto;

public class ResponseDto {

    private Long id;
    private String erro;

    public Long getId() { return id; }
    public void setId(Long id) {
        this.id = id;
    }
    public String getErro() {
        return erro;
    }
    public void setErro(String erro) {
        this.erro = erro;
    }
}

package br.unitins.topicos1.model;

import jakarta.persistence.Entity;

@Entity
public class Endereco extends DefaultEntity {
    private String cep;
    private String complemento;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getComplemento() {
        return complemento;
    }
    
    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

}

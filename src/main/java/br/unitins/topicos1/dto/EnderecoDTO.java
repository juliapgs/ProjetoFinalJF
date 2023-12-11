package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Endereco;
import jakarta.validation.constraints.NotEmpty;

public record EnderecoDTO(
    @NotEmpty(message = "O campo cep não pode ser nulo.")
    String cep,
    @NotEmpty(message = "O campo complemento não pode ser nulo.")
    String complemento
) {
    public static EnderecoDTO valueOf(Endereco endereco){
        return new EnderecoDTO(
            endereco.getCep(), 
            endereco.getComplemento()
        );
    }
}   

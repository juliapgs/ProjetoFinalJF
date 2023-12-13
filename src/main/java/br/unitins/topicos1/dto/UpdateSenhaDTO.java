package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateSenhaDTO(

    @NotBlank String senhaAtual,

    @NotBlank(message = "A nova senha não pode ser vazia") 
    String novaSenha

) {

}
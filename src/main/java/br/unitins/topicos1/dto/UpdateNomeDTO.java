package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotEmpty;

public record UpdateNomeDTO(   

    @NotEmpty(message = "O campo nome não pode ser nulo.")
    String senha,    

    @NotEmpty(message = "O campo nome não pode ser nulo.")
    String nome
) {
}
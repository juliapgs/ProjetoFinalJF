package br.unitins.topicos1.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UpdateTelefoneDTO(
    @NotEmpty(message = "O campo nome não pode ser nulo.")
    String senha,

    @NotBlank(message = "O campo código da área deve ser informado.")
    @Size(min = 2 ,max = 2, message = "O código da área deve posssuir 2 caracteres.")
    String codigoArea,

    @NotBlank(message = "O campo número deve ser informado.")
    @Size(min = 9, max = 9, message = "O campo número deve possuir 9 caracteres.")
    String numero,
    
    List<TelefoneDTO> listaTelefones
) {
}

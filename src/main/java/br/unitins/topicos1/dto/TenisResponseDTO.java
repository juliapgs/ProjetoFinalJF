package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.CategoriaTenis;
import br.unitins.topicos1.model.Tenis;

public record TenisResponseDTO(
    Long id,
    String marca,
    String modelo,
    CategoriaTenis categoriaTenis,
    String cor,
    Integer tamanho,
    Double valor
) { 

    public static TenisResponseDTO valueOf(Tenis tenis){
        return new TenisResponseDTO(
            tenis.getId(),
            tenis.getMarca(),
            tenis.getModelo(),
            tenis.getCategoria(),
            tenis.getCor(),
            tenis.getTamanho(),
            tenis.getValor());
    }
}

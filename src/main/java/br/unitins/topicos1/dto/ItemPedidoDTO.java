package br.unitins.topicos1.dto;

import jakarta.validation.constraints.NotNull;

public record ItemPedidoDTO(
    @NotNull(message = "Campo quantidade deve ser informado")
    Integer quantidade,

    @NotNull(message = "Produto deve ser informado")
    Long idProduto
) {
}

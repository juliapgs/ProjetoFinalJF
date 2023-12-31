package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.ItemPedido;

public record ItemPedidoResponseDTO(

        Long id,

        Integer quantidade,

        TenisResponseDTO produto

) {
    public static ItemPedidoResponseDTO valueOf(ItemPedido item) {
        return new ItemPedidoResponseDTO(
            item.getId(),
            item.getQuantidade(),
            TenisResponseDTO.valueOf(item.getProduto()));
    }

}
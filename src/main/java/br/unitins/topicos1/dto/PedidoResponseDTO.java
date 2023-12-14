package br.unitins.topicos1.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import br.unitins.topicos1.model.FormaPagamento;
import br.unitins.topicos1.model.ItemPedido;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.StatusPedido;

public record PedidoResponseDTO(
    Long id,
    List<ItemPedidoResponseDTO> itensCompra,
    List<EnderecoDTO> listaEndereco,
    LocalDate dataCompra,
    FormaPagamento formaPagamento,
    StatusPedido statusPedido
) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {
        return new PedidoResponseDTO(
            pedido.getId(),
            gerarItemPedidoResponseDTO(pedido.getItemPedido()),
            pedido.getListaEndereco()
                .stream()
                .map(t -> EnderecoDTO.valueOf(t)).toList(),
            pedido.getDataCompra(),
            pedido.getFormaPagamento(),
            pedido.getStatusPedido()
        );
    }

    private static List<ItemPedidoResponseDTO> gerarItemPedidoResponseDTO(List<ItemPedido> itemPedido) {
        if (itemPedido != null)
            return itemPedido.stream().map(produto -> ItemPedidoResponseDTO.valueOf(produto)).collect(Collectors.toList());
        return null;
    }
}

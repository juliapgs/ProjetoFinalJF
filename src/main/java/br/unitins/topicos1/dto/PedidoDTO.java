package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.FormaPagamento;
import br.unitins.topicos1.model.StatusPedido;

import java.time.LocalDate;
import java.util.List;

public record PedidoDTO (
    List<ItemPedidoDTO> itensPedido,
    List<EnderecoDTO> listaEndereco,
    LocalDate dataCompra,
    FormaPagamento formaPagamento,
    StatusPedido statusPedido
) {
}
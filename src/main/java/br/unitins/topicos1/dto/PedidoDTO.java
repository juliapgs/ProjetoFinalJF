package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.FormaPagamento;
import br.unitins.topicos1.model.StatusPedido;

import java.time.LocalDate;
import java.util.List;

public record PedidoDTO (
    Long usuarioId,
    Long tenisId,
    List<EnderecoDTO> listaEndereco,
    LocalDate dataCompra,
    FormaPagamento formaPagamento,
    StatusPedido statusPedido
) {
}
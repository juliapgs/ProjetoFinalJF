package br.unitins.topicos1.dto;

import java.time.LocalDate;
import java.util.List;

import br.unitins.topicos1.model.FormaPagamento;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.model.StatusPedido;

public record PedidoResponseDTO(
    Long id,
    Long usuarioId,
    Long tenisId,
    //Perfil perfil,
    List<EnderecoDTO> listaEndereco,
    LocalDate dataCompra,
    FormaPagamento formaPagamento,
    StatusPedido statusPedido
) {
    public static PedidoResponseDTO valueOf(Pedido pedido) {
        return new PedidoResponseDTO(
            pedido.getId(),
            pedido.getUsuarioId(),
            pedido.getTenisId(),
            //pedido.getPerfil(), 
            pedido.getListaEndereco()
                .stream()
                .map(t -> EnderecoDTO.valueOf(t)).toList(),
            pedido.getDataCompra(),
            pedido.getFormaPagamento(),
            pedido.getStatusPedido()
        );
    }
}

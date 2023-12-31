package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.ItemPedidoDTO;
import br.unitins.topicos1.dto.PedidoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.model.ItemPedido;
import jakarta.validation.Valid;

import java.util.List;

public interface PedidoService {

    List<PedidoResponseDTO> getAll();

    PedidoResponseDTO insert(@Valid PedidoDTO dto,  Long idUsuario);

    PedidoResponseDTO update(PedidoDTO dto, Long id);

    void delete(Long id);

    List<PedidoResponseDTO> findByUsuario(Long idUsuario);

    PedidoResponseDTO findById(Long id);

    List<PedidoResponseDTO> findByAll();

    ItemPedido toModel(@Valid ItemPedidoDTO dto);

}

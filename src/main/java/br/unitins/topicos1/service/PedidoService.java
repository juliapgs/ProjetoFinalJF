package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.PedidoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import jakarta.validation.Valid;

import java.util.List;

public interface PedidoService {

    List<PedidoResponseDTO> getAll();

    PedidoResponseDTO insert(@Valid PedidoDTO dto,  Long idUsuario);

    PedidoResponseDTO update(PedidoDTO dto, Long id);

    void delete(Long id);

    PedidoResponseDTO findById(Long id);

    List<PedidoResponseDTO> findByAll();

}

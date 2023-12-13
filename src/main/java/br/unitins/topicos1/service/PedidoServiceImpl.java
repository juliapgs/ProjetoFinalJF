package br.unitins.topicos1.service;

import java.util.ArrayList;
import java.util.List;
import br.unitins.topicos1.dto.EnderecoDTO;
import br.unitins.topicos1.dto.PedidoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.model.Endereco;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.repository.PedidoRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class PedidoServiceImpl implements PedidoService {

    @Inject
    PedidoRepository repository;

    @Inject
    UsuarioService usuarioService;

    @Inject
    HashService hashService;

    @Inject
    TenisService tenisService;

    @Override
    @Transactional
    public PedidoResponseDTO insert(PedidoDTO dto) {

        Pedido novoPedido = new Pedido();
        novoPedido.setDataCompra(dto.dataCompra());
        novoPedido.setFormaPagamento(dto.formaPagamento());
        novoPedido.setStatusPedido(dto.statusPedido());
        novoPedido.setUsuarioId(dto.usuarioId());
        novoPedido.setTenisId(dto.tenisId());

        if (dto.listaEndereco() != null && !dto.listaEndereco().isEmpty()) {
            novoPedido.setListaEndereco(new ArrayList<Endereco>());
            for (EnderecoDTO end : dto.listaEndereco()) {
                Endereco endereco = new Endereco();
                endereco.setCep(end.cep());
                endereco.setComplemento(end.complemento());
                novoPedido.getListaEndereco().add(endereco);
            }
        }

        repository.persist(novoPedido);

        return PedidoResponseDTO.valueOf(novoPedido);
    }

    @Override
    @Transactional
    public PedidoResponseDTO update(PedidoDTO dto, Long id) {

        Pedido existingPedido = repository.findById(id);
        if (existingPedido == null) {
            throw new NotFoundException("Pedido not found with ID: " + id);
        }

        existingPedido.setDataCompra(dto.dataCompra());
        existingPedido.setFormaPagamento(dto.formaPagamento());
        existingPedido.setStatusPedido(dto.statusPedido());
        existingPedido.setUsuarioId(dto.usuarioId());
        existingPedido.setTenisId(dto.tenisId());

        if (dto.listaEndereco() != null && !dto.listaEndereco().isEmpty()) {
            existingPedido.setListaEndereco(new ArrayList<>());
            for (EnderecoDTO end : dto.listaEndereco()) {
                Endereco endereco = new Endereco();
                endereco.setCep(end.cep());
                endereco.setComplemento(end.complemento());
                existingPedido.getListaEndereco().add(endereco);
            }
        }

        repository.persist(existingPedido);

        return PedidoResponseDTO.valueOf(existingPedido);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Pedido pedido = repository.findById(id);
        if (pedido != null) {
            repository.delete(pedido);
        } else {
            throw new NotFoundException("Pedido not found with ID: " + id);
        }
    }

    @Override
    public PedidoResponseDTO findById(Long id) {
        Pedido pedido = repository.findById(id);
        if (pedido != null) {
            return PedidoResponseDTO.valueOf(pedido);
        } else {
            throw new NotFoundException("Pedido not found with ID: " + id);
        }
    }

    @Override
    public List<PedidoResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> PedidoResponseDTO.valueOf(e)).toList();
    }
}

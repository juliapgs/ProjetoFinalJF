package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.TenisDTO;
import br.unitins.topicos1.dto.TenisResponseDTO;
import br.unitins.topicos1.model.Tenis;
import br.unitins.topicos1.repository.TenisRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class TenisServiceImpl implements TenisService {

    @Inject
    TenisRepository repository;

    @Override
    @Transactional
    public TenisResponseDTO insert(TenisDTO dto) {
        Tenis novoTenis = new Tenis();
        novoTenis.setMarca(dto.getMarca());
        novoTenis.setModelo(dto.getModelo());
        novoTenis.setCategoria(dto.getCategoriaTenis());
        novoTenis.setCor(dto.getCor());
        novoTenis.setTamanho(dto.getTamanho());
        novoTenis.setValor(dto.getValor());
        
        repository.persist(novoTenis);

        return TenisResponseDTO.valueOf(novoTenis);
    }

    @Override
    @Transactional
    public TenisResponseDTO update(TenisDTO dto, Long id) {
        
        Tenis tenis = repository.findById(id);
        if (tenis!= null) {
            tenis.setMarca(dto.getMarca());
            tenis.setModelo(dto.getModelo());
            tenis.setCategoria(dto.getCategoriaTenis());
            tenis.setCor(dto.getCor());
            tenis.setTamanho(dto.getTamanho());
            tenis.setValor(dto.getValor());
        } else 
            throw new NotFoundException();

        return TenisResponseDTO.valueOf(tenis);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.deleteById(id)) 
            throw new NotFoundException();
    }

    @Override
    public TenisResponseDTO findById(Long id) {
        return TenisResponseDTO.valueOf(repository.findById(id));
    }

    @Override
    public List<TenisResponseDTO> findByModelo(String modelo) {
        return repository.findByModelo(modelo).stream()
            .map(e -> TenisResponseDTO.valueOf(e)).toList();
   }

    @Override
    public List<TenisResponseDTO> findByAll() {
        return repository.listAll().stream()
            .map(e -> TenisResponseDTO.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public TenisResponseDTO updateImagem(Long id, String nomeImagem) {
        Tenis tenis = repository.findById(id);
        tenis.setNomeImagem(nomeImagem);
        return TenisResponseDTO.valueOf(tenis);
    }

}

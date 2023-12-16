package br.unitins.topicos1.service;

import java.util.List;

import br.unitins.topicos1.dto.UpdateNomeDTO;
import br.unitins.topicos1.dto.UpdateSenhaDTO;
import br.unitins.topicos1.dto.UpdateTelefoneDTO;
import br.unitins.topicos1.dto.UsuarioDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import jakarta.validation.Valid;

public interface UsuarioService {

    public UsuarioResponseDTO insert(@Valid UsuarioDTO dto);

    public UsuarioResponseDTO update(UsuarioDTO dto, Long id);

    public void delete(Long id);

    public UsuarioResponseDTO findById(Long id);

    public List<UsuarioResponseDTO> findByNome(String nome);

    public UsuarioResponseDTO findByLoginAndSenha(String login, String senha);

    public UsuarioResponseDTO findByLogin(String login);

    public List<UsuarioResponseDTO> findByAll(); 

    public UsuarioResponseDTO updateSenha(@Valid UpdateSenhaDTO dto, String login);

    public UsuarioResponseDTO updateNome(@Valid UpdateNomeDTO dto, String login);

    public UsuarioResponseDTO updateTelefone(@Valid UpdateTelefoneDTO dto, String login);
    
}

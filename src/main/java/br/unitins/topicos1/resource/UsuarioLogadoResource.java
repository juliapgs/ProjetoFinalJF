package br.unitins.topicos1.resource;

import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import br.unitins.topicos1.service.PedidoService;
import br.unitins.topicos1.service.UsuarioService;
import br.unitins.topicos1.application.Error;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.dto.UpdateNomeDTO;
import br.unitins.topicos1.dto.UpdateSenhaDTO;
import br.unitins.topicos1.dto.UpdateTelefoneDTO;
import br.unitins.topicos1.dto.UsuarioResponseDTO;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/usuariologado")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioLogadoResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    UsuarioService usuarioService;

    @Inject
    PedidoService pedidoService;

    private static final Logger LOG = Logger.getLogger(UsuarioLogadoResource.class);

    private Long getIdUsuario() {
        String login = jwt.getSubject();
        UsuarioResponseDTO usuario = usuarioService.findByLogin(login);
        return usuario.id();
    }

    @PATCH
    @Path("/alterar/senha")
    @RolesAllowed({ "User", "Admin" })
    public Response updateSenha(@Valid UpdateSenhaDTO dto){
        LOG.info("Atualizando senha");
        String login = jwt.getSubject();
        
        UsuarioResponseDTO retorno = usuarioService.updateSenha(dto, login);

        return Response.status(201).entity(retorno).build();
    }

    @PATCH
    @Path("/alterar/nome")
    @RolesAllowed({ "User", "Admin" })
    public Response updateNome(UpdateNomeDTO dto){
        
        String login = jwt.getSubject();

        UsuarioResponseDTO retorno = usuarioService.updateNome(dto, login);
        
        return Response.status(201).entity(retorno).build();
    }

    @PATCH
    @Path("/alterar/telefone")
    @RolesAllowed({ "User", "Admin" })
    public Response updateTelefone(UpdateTelefoneDTO dto){
        
        String login = jwt.getSubject();

        UsuarioResponseDTO retorno = usuarioService.updateTelefone(dto, login);
        
        return Response.status(201).entity(retorno).build();
    }

    @GET
    @Path("/meus-pedidos")
    @RolesAllowed({"Admin", "User"})
    public Response minhasCompras() {
        LOG.infof("Buscando compras");

        try {
            List<PedidoResponseDTO> response = pedidoService.findByUsuario(this.getIdUsuario());
            LOG.info("Pesquisa realizada com sucesso.");
            return Response.ok(response).build();
        } catch (ConstraintViolationException e) {
            LOG.error("Erro ao buscar compras.");
            LOG.debug(e.getMessage());
            Error error = new Error("constraint_violation", "Erro de validação. Verifique os dados fornecidos.");
            return Response.status(Status.BAD_REQUEST).entity(error).build();
        } catch (Exception e) {
            LOG.fatal("Erro sem identificacao: " + e.getMessage());
            Error error = new Error("internal_error", "Erro interno no servidor.");
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

}
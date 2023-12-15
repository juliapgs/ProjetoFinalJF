package br.unitins.topicos1.resource;

import java.io.IOException;
import java.util.List;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.form.UsuarioImageForm;
import br.unitins.topicos1.service.PedidoService;
import br.unitins.topicos1.service.UsuarioFileService;
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
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
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

    @Inject
    UsuarioFileService fileService;

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

    @PATCH
    @Path("/upload/imagem")
    @RolesAllowed({ "User", "Admin" })
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm UsuarioImageForm form){
        String nomeImagem;
        try {
            nomeImagem = fileService.salvar(form.getNomeImagem(), form.getImagem());
        } catch (IOException e) {
            e.printStackTrace();
            Error error = new Error("409", e.getMessage());
            return Response.status(Status.CONFLICT).entity(error).build();
        }

        String login = jwt.getSubject();
        UsuarioResponseDTO usuarioDTO = usuarioService.findByLogin(login);
        usuarioDTO = usuarioService.updateNomeImagem(usuarioDTO.id(), nomeImagem);

        return Response.ok(usuarioDTO).build();

    }

    @GET
    @Path("/download/imagem/{nomeImagem}")
    @RolesAllowed({ "User", "Admin" })
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.obter(nomeImagem));
        response.header("Content-Disposition", "attachment;filename="+nomeImagem);
        return response.build();
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
package br.unitins.topicos1.resource;

import java.io.IOException;

import org.eclipse.microprofile.jwt.JsonWebToken;
import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.dto.TenisDTO;
import br.unitins.topicos1.dto.TenisResponseDTO;
import br.unitins.topicos1.form.ImageForm;
import br.unitins.topicos1.application.Error;
import br.unitins.topicos1.service.FileService;
import br.unitins.topicos1.service.TenisService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.ResponseBuilder;
import jakarta.ws.rs.core.Response.Status;

@Path("/tenis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TenisResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    TenisService service;

    @Inject
    FileService fileService;

    private static final Logger LOG = Logger.getLogger(UsuarioLogadoResource.class);

    @POST
    @RolesAllowed({"User", "Admin"})
    public Response insert(@Valid TenisDTO dto) {
        TenisResponseDTO retorno = service.insert(dto);
        return Response.status(Status.CREATED).entity(retorno).build();
        // return Response.status(201).entity(retorno).build();
    }

    @PUT
    @Path("/{id}")
    @RolesAllowed({"User", "Admin"})
    public Response update(TenisDTO dto, @PathParam("id") Long id) {
        service.update(dto, id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @RolesAllowed({"User", "Admin"})
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(service.findByAll()).build();
    }

    @GET
    @Path("/{id}")
    @RolesAllowed({"User", "Admin"})
    public Response findById(@PathParam("id") Long id) {
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/modelo/{modelo}")
    public Response findByModelo(@PathParam("modelo") String modelo) {
        return Response.ok(service.findByModelo(modelo)).build();
    }

    @PATCH
    @Path("/upload/imagem/tenis/{id}")
    @RolesAllowed({ "Admin" })
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response salvarImagem(@MultipartForm ImageForm form, @PathParam("id") Long id) {
        try {
            LOG.info("Iniciando a inserção de imagem");
            String nomeImagem = fileService.salvar(form.getNomeImagem(), form.getImagem());
            LOG.info("Atualizando a nova imagem.");

            TenisResponseDTO imagem = service.updateImagem(id, nomeImagem);

            LOG.info("Retornando a imagem.");
            return Response.ok(imagem).build();

        } catch (IOException e) {
            e.printStackTrace();

            LOG.info("Retornando um erro do servidor.");
            Error error = new Error("500", "Erro ao processar a imagem.");
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build();
        }
    }

    @GET
    @Path("/download/imagem/tenis/{nomeImagem}")
    @RolesAllowed({ "Admin" })
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        LOG.info("Iniciando a inserção download imagem");
        ResponseBuilder response = Response.ok(fileService.obter(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    }

}
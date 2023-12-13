package br.unitins.topicos1.resource;

import org.eclipse.microprofile.jwt.JsonWebToken;

import br.unitins.topicos1.dto.TenisDTO;
import br.unitins.topicos1.dto.TenisResponseDTO;
import br.unitins.topicos1.service.TenisService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/tenis")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TenisResource {

    @Inject
    JsonWebToken jwt;

    @Inject
    TenisService service;

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
}
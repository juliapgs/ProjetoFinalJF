package br.unitins.topicos1.resource;

import br.unitins.topicos1.dto.PedidoDTO;
import br.unitins.topicos1.dto.PedidoResponseDTO;
import br.unitins.topicos1.service.PedidoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.List;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoService service;

    @POST
    public Response insert(@Valid PedidoDTO dto) {
        PedidoResponseDTO retorno = service.insert(dto);
        return Response.status(201).entity(retorno).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(PedidoDTO dto, @PathParam("id") Long id) {
        try {
            PedidoResponseDTO retorno = service.update(dto, id);
            return Response.status(Status.OK).entity(retorno).build();
        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        try {
            service.delete(id);
            return Response.status(Status.NO_CONTENT).build();
        } catch (NotFoundException e) {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @GET
    public Response findByAll() {
        List<PedidoResponseDTO> pedidos = service.findByAll();
        return Response.ok(pedidos).build();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {
        PedidoResponseDTO pedido = service.findById(id);
        if (pedido != null) {
            return Response.ok(pedido).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

}

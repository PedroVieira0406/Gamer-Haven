package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.CartaoDTO;
import br.unitins.topicos1.dto.CartaoResponseDTO;
import br.unitins.topicos1.service.CartaoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@Path("/Cartoes")
public class CartaoResource {

    @Inject
    CartaoService service;

    private static final Logger LOG = Logger.getLogger(CartaoResource.class);

    @POST
    @RolesAllowed("Cliente")
    public Response create(CartaoDTO dto) {
        LOG.info("Requisição para criar Cartao recebida");
        CartaoResponseDTO retorno = service.create(dto);
        LOG.info("Cartao criada com sucesso");
        return Response.status(Status.CREATED).entity(retorno).build();
    }

    @PUT
    @Transactional
    @RolesAllowed("Cliente")
    @Path("/update/{id}")
    public Response update(CartaoDTO dto, @PathParam("id") Long id) {
        LOG.infof("Requisição para atualizar Cartao com ID: " + id + " recebida");
        service.update(id, dto);
        LOG.infof("Cartao com ID: " + id + " atualizada com sucesso");
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
    @RolesAllowed("Cliente")
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Requisição para deletar Cartao com ID: " + id + " recebida");
        service.delete(id);
        LOG.infof("Cartao com ID: " + id + " deletada com sucesso");
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @RolesAllowed("Funcionario")
    public Response findAll() {
        LOG.debug("Requisição para buscar todas as Cartaos recebida");
        return Response.ok(service.getAll()).build();
    }

    @GET
    @RolesAllowed("Funcionario")
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Requisição para buscar Cartao pelo ID: " + id + " recebida");
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Cliente","Funcionario"})
    @Path("/search/dono/{id}")
    public Response findByDono(@PathParam("id") Long idDono) {
        LOG.infof("Requisição para buscar Cartao pelo id do dono: " + idDono + " recebida");
        return Response.ok(service.findByDono(idDono)).build();
    }
}


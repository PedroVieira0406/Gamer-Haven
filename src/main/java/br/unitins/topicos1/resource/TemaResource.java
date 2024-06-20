package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.TemaDTO;
import br.unitins.topicos1.dto.TemaResponseDTO;
import br.unitins.topicos1.service.TemaService;
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
@Path("/temas")
public class TemaResource {

    @Inject
    TemaService service;

    private static final Logger LOG = Logger.getLogger(TemaResource.class);

    @POST
    @RolesAllowed("Funcionario")
    public Response create(TemaDTO dto) {
        LOG.info("Criando novo Tema");
        TemaResponseDTO retorno = service.create(dto);
        LOG.info("Tema criado com sucesso");
        return Response.status(Status.CREATED).entity(retorno).build();
    }

    @PUT
    @Transactional
    @RolesAllowed("Funcionario")
    @Path("/update/{id}")
    public Response update(@PathParam("id") Long id, TemaDTO dto) {
        LOG.info("Atualizando Tema com ID: " + id);
        service.update(id, dto);
        LOG.info("Tema com ID: " + id + " atualizado com sucesso");
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
    @RolesAllowed("Funcionario")
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando Tema com ID: " + id);
        service.delete(id);
        LOG.info("Tema com ID: " + id + " deletado com sucesso");
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    public Response findAll() {
        LOG.debug("Buscando todos os Temas");
        return Response.ok(service.getAll()).build();
    }

    @GET
    @RolesAllowed("Funcionario")
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando Tema pelo ID: " + id);
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.info("Buscando Tema pelo nome: " + nome);
        return Response.ok(service.findByNome(nome)).build();
    }
}
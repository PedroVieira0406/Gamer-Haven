package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.PlataformaDTO;
import br.unitins.topicos1.dto.PlataformaResponseDTO;
import br.unitins.topicos1.service.PlataformaService;
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

@Path("/plataformas")
public class PlataformaResource {

    @Inject
    PlataformaService service;

    private static final Logger LOG = Logger.getLogger(PlataformaResource.class);

    @POST
    @RolesAllowed("Funcionario")
    public Response create(PlataformaDTO dto) {
        LOG.info("Criando nova Plataforma");
        PlataformaResponseDTO retorno = service.create(dto);
        LOG.info("Plataforma criada com sucesso");
        return Response.status(Status.CREATED).entity(retorno).build();
    }

    @PUT
    @Transactional
    @RolesAllowed("Funcionario")
    @Path("/update/{id}")
    public Response update(PlataformaDTO dto, @PathParam("id") Long id) {
        LOG.info("Atualizando Plataforma com ID: " + id);
        service.update(id, dto);
        LOG.info("Plataforma com ID: " + id + " atualizada com sucesso");
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
    @RolesAllowed("Funcionario")
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.info("Deletando Plataforma com ID: " + id);
        service.delete(id);
        LOG.info("Plataforma com ID: " + id + " deletada com sucesso");
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    public Response findAll() {
        LOG.debug("Buscando todas as Plataformas");
        return Response.ok(service.getAll()).build();
    }

    @GET
    @Path("/search/id/{id}")
    @RolesAllowed("Funcionario")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando Plataforma pelo ID: " + id);
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.info("Buscando Plataforma pelo nome: " + nome);
        return Response.ok(service.findByNome(nome)).build();
    }
}
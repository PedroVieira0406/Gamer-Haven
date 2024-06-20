package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.EmpresaDTO;
import br.unitins.topicos1.dto.EmpresaResponseDTO;
import br.unitins.topicos1.service.EmpresaService;
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

@Path("/empresas")
public class EmpresaResource {

    @Inject
    EmpresaService service;

    private static final Logger LOG = Logger.getLogger(EmpresaResource.class);

    @POST
    @RolesAllowed("Funcionario")
    public Response create(EmpresaDTO dto) {
        LOG.info("Requisição para criar Empresa recebida");
        EmpresaResponseDTO retorno = service.create(dto);
        LOG.info("Empresa criada com sucesso");
        return Response.status(Status.CREATED).entity(retorno).build();
    }

    @PUT
    @Transactional
    @RolesAllowed("Funcionario")
    @Path("/update/{id}")
    public Response update(EmpresaDTO dto, @PathParam("id") Long id) {
        LOG.infof("Requisição para atualizar Empresa com ID: " + id + " recebida");
        service.update(id, dto);
        LOG.infof("Empresa com ID: " + id + " atualizada com sucesso");
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
    @RolesAllowed("Funcionario")
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Requisição para deletar Empresa com ID: " + id + " recebida");
        service.delete(id);
        LOG.infof("Empresa com ID: " + id + " deletada com sucesso");
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    public Response findAll() {
        LOG.debug("Requisição para buscar todas as Empresas recebida");
        return Response.ok(service.getAll()).build();
    }

    @GET
    @RolesAllowed("Funcionario")
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Requisição para buscar Empresa pelo ID: " + id + " recebida");
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.infof("Requisição para buscar Empresa pelo nome: " + nome + " recebida");
        return Response.ok(service.findByNome(nome)).build();
    }
}

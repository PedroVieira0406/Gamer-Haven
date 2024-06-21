package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.CompraDTO;
import br.unitins.topicos1.dto.CompraResponseDTO;
import br.unitins.topicos1.service.CompraService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
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

@Path("/compras")
public class CompraResource {

    @Inject
    CompraService service;

    private static final Logger LOG = Logger.getLogger(CompraResource.class);

    @POST
    @RolesAllowed({"Cliente","Funcionario"})
    public Response create(CompraDTO dto) {
        LOG.info("Criando nova Compra");
        CompraResponseDTO retorno = service.create(dto);
        LOG.info("Compra criada com sucesso");
        return Response.status(Status.CREATED).entity(retorno).build();
    }

    @PUT
    @Transactional
    @RolesAllowed("Funcionario")
    @Path("/update/{id}")
    public Response update(CompraDTO dto, @PathParam("id") Long id) {
        LOG.infof("Atualizando compra com ID: " + id);
        service.update(dto,id);
        LOG.infof("Compra com ID: " + id + " atualizado com sucesso");
        return Response.status(Status.NO_CONTENT).build();
    }

    @PUT
    @Transactional
    @RolesAllowed({"Cliente","Funcionario"})
    @Path("/ativacao/id/{id}")
    public Response ativarJogo(@PathParam("id") Long id){
        LOG.infof("Ativando jogo apartir do pagamento do ID: " + id);
        return Response.ok("Jogo ativado com sucesso.").build();
    }

    @GET
    @RolesAllowed("Funcionario")
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Buscando Compra pelo ID: " + id);
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Cliente","Funcionario"})
    @Path("/search/pagante/{id}")
    public Response findByPagante(@PathParam("id") Long idCliente) {
        LOG.infof("Buscando Compra pelo ID do pagante: " + idCliente);
        return Response.ok(service.findByPagante(idCliente)).build();
    }

    @GET
    @RolesAllowed("Funcionario")
    @Path("/search/dono/{id}")
    public Response findByDono(@PathParam("id") Long idDono) {
        LOG.infof("Buscando Compra pelo ID do dono: " + idDono);
        return Response.ok(service.findByDono(idDono)).build();
    }

    @GET
    @RolesAllowed("Funcionario")
    @Path("/search/jogo/{id}")
    public Response findByJogo(@PathParam("id") Long idJogo) {
        LOG.infof("Buscando Compra pelo ID do jogo: " + idJogo);
        return Response.ok(service.findByJogo(idJogo)).build();
    }

    @GET
    @RolesAllowed("Funcionario")
    public Response findAll() {
        LOG.debug("Buscando todas as Compras");
        return Response.ok(service.getAll()).build();
    }
}
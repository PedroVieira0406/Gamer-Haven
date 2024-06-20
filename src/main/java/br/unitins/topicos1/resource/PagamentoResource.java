package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.EfetuarPagamentoDTO;
import br.unitins.topicos1.dto.PagamentoDTO;
import br.unitins.topicos1.dto.PagamentoResponseDTO;
import br.unitins.topicos1.service.PagamentoService;
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

@Path("/Pagamentos")
public class PagamentoResource {

    @Inject
    PagamentoService service;

    private static final Logger LOG = Logger.getLogger(PagamentoResource.class);

    @POST
    @Transactional
    @RolesAllowed("Funcionario")
    public Response create(PagamentoDTO dto) {
        LOG.info("Criando nova Pagamento");
        PagamentoResponseDTO retorno = service.create(dto);
        LOG.info("Pagamento criada com sucesso");
        return Response.status(Status.CREATED).entity(retorno).build();
    }

    @PUT
    @Transactional
    @RolesAllowed("Funcionario")
    @Path("/update/{id}")
    public Response update(PagamentoDTO dto, @PathParam("id") Long id) {
        LOG.infof("Atualizando Pagamento com ID: " + id);
        service.update(id, dto);
        LOG.infof("Pagamento com ID: " + id + " atualizada com sucesso");
        return Response.status(Status.NO_CONTENT).build();
    }

    @PUT
    @Transactional
    @RolesAllowed({"Cliente","Funcionario"})
    @Path("/efetivacao/{id}")
    public Response efetuarPagamento(EfetuarPagamentoDTO dto, @PathParam("id") Long id) {
        LOG.infof("Efetuando pagamento com ID: " + id);
        service.efetuarPagamento(id, dto);
        LOG.infof("pagamento com ID: " + id + " efetuado com sucesso");
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
    @RolesAllowed("Funcionario")
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Efetuando delete do funcionario com id:"+id);
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @RolesAllowed("Funcionario")
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Buscando Pagamento pelo ID: " + id);
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @RolesAllowed({"Cliente","Funcionario"})
    @Path("/search/cliente/{id}")
    public Response findByCliente(@PathParam("id") Long idCliente) {
        LOG.infof("Buscando Pagamento pelo ID do Cliente: " + idCliente);
        return Response.ok(service.findByCliente(idCliente)).build();
    }

    @GET
    @RolesAllowed("Funcionario")
    public Response findAll() {
        LOG.debug("Requisição para buscar todas os Pagamenos recebida");
        return Response.ok(service.findAll()).build();
    }
}
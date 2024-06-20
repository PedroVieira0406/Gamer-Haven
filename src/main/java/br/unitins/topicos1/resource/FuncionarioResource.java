package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.dto.FuncionarioDTO;
import br.unitins.topicos1.dto.FuncionarioResponseDTO;
import br.unitins.topicos1.form.ImageForm;
import br.unitins.topicos1.service.FileServiceFuncionario;
import br.unitins.topicos1.service.FuncionarioService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
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

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@Path("/funcionarios")
public class FuncionarioResource {
    @Inject
    FuncionarioService service;

    @Inject
    FileServiceFuncionario fileService;

    private static final Logger LOG = Logger.getLogger(FuncionarioResource.class);

    @POST
    @RolesAllowed("Funcionario")
    public Response create(FuncionarioDTO dto) {
        LOG.info("Requisição para criar Funcionario");
        FuncionarioResponseDTO retorno = service.create(dto);
        LOG.info("Jogo criado com sucesso");
        return Response.status(201).entity(retorno).build();
    }

    @PUT
    @Transactional
    @RolesAllowed("Funcionario")
    @Path("/update/{id}")
    public Response update(FuncionarioDTO dto, @PathParam("id") Long id) {
        LOG.info("Requisição para atualizar Funcionario com ID: " + id);
        service.update(id, dto);
        LOG.info("Funcionario com ID: " + id + " atualizada com sucesso");
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
    @RolesAllowed("Funcionario")
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        service.delete(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @RolesAllowed("Funcionario")
    public Response findAll() {
        LOG.debug("Requisição para buscar todas as Funcionarios");
        return Response.ok(service.getAll()).build();
    }
    @GET
    @RolesAllowed("Funcionario")
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Requisição para buscar Funcionario pelo ID: " + id);
        return Response.ok(service.findById(id)).build();
    }
    @GET
    @RolesAllowed("Funcionario")
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(service.findByNome(nome)).build();
    }
    @GET
    @RolesAllowed("Funcionario")
    @Path("/search/cargo/{cargo}")
    public Response findByCargo(@PathParam("cargo") String nome) {
        return Response.ok(service.findByCargo(nome)).build();
    }

    @PATCH
    @RolesAllowed("Funcionario")
    @Path("/{id}/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@PathParam("id") Long id, @MultipartForm ImageForm form) {
        fileService.salvar(id, form.getNomeImagem(), form.getImagem());
        return Response.noContent().build();
    }

    @GET
    @RolesAllowed("Funcionario")
    @Path("/image/download/{nomeImagem}")
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        return response.build();
    } 
    
}
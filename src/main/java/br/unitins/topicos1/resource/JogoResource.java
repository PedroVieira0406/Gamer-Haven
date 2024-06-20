package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.dto.JogoDTO;
import br.unitins.topicos1.dto.JogoResponseDTO;
import br.unitins.topicos1.form.ImageForm;
import br.unitins.topicos1.service.FileServiceJogo;
import br.unitins.topicos1.service.JogoService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
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

@Path("/jogos")
public class JogoResource {

    @Inject
    JogoService service;

    @Inject
    FileServiceJogo fileService;

    private static final Logger LOG = Logger.getLogger(JogoResource.class);

    @POST
    @RolesAllowed("Funcionario")
    public Response create(JogoDTO dto) {
        LOG.infof("Requisição para criar Jogo  ");
        JogoResponseDTO retorno = service.create(dto);
        LOG.infof("Jogo criado com sucesso");
        return Response.status(Status.CREATED).entity(retorno).build();
    }

    @PUT
    @RolesAllowed("Funcionario")
    @Path("/update/{id}")
    public Response update(JogoDTO dto, @PathParam("id") Long id) {
        LOG.infof("Requisição para atualizar Jogo com ID: " + id  );
        service.update(id, dto);
        LOG.infof("Jogo com ID: " + id + " atualizado com sucesso");
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @RolesAllowed("Funcionario")
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Requisição para deletar Jogo com ID: " + id  );
        service.delete(id);
        LOG.infof("Jogo com ID: " + id + " deletado com sucesso");
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    public Response findAll() {
        LOG.debug("Requisição para buscar todos os Jogos  ");
        return Response.ok(service.getAll()).build();
    }

    @GET
    @RolesAllowed("Funcionario")
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.infof("Requisição para buscar Jogo pelo ID: " + id  );
        return Response.ok(service.findById(id)).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.info("Requisição para buscar Jogo pelo nome: " + nome  );
        return Response.ok(service.findByNome(nome)).build();
    }

    @PATCH
    @RolesAllowed("Funcionario")
    @Path("/{id}/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@PathParam("id") Long id, @MultipartForm ImageForm form) {
        LOG.info("Requisição para upload de imagem do Jogo com ID: " + id  );
        fileService.salvar(id, form.getNomeImagem(), form.getImagem());
        LOG.info("Imagem do Jogo com ID: " + id + " salva com sucesso");
        return Response.noContent().build();
    }

    @GET
    @RolesAllowed({"Cliente","Funcionario"})
    @Path("/image/download/{nomeImagem}")
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        LOG.info("Requisição para download de imagem do jogo com nome: " + nomeImagem  );
        ResponseBuilder response = Response.ok(fileService.download(nomeImagem));
        response.header("Content-Disposition", "attachment;filename=" + nomeImagem);
        LOG.infof("Imagem do jogo com nome: " + nomeImagem + " salva com sucesso");
        return response.build();
    }
}
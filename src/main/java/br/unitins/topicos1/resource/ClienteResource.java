package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.providers.multipart.MultipartForm;

import br.unitins.topicos1.dto.ClienteDTO;
import br.unitins.topicos1.dto.ClienteResponseDTO;
import br.unitins.topicos1.dto.UpdatePasswordDTO;
import br.unitins.topicos1.dto.UpdateUsernameDTO;
import br.unitins.topicos1.form.ImageForm;
import br.unitins.topicos1.service.ClienteService;
import br.unitins.topicos1.service.FileServiceCliente;
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
import jakarta.ws.rs.core.Response.Status;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

@Path("/clientes")
public class ClienteResource {

    @Inject
    ClienteService service;

    @Inject
    FileServiceCliente fileService;

    private static final Logger LOG = Logger.getLogger(ClienteResource.class);

    @POST
    @RolesAllowed("Funcionario")
    public Response create(ClienteDTO dto) {
        LOG.info("Criando novo Cliente");
        ClienteResponseDTO retorno = service.create(dto);
        LOG.info("Cliente criado com sucesso");
        return Response.status(Status.CREATED).entity(retorno).build();
    }

    @PATCH
    @RolesAllowed("Cliente")
    @Path("/update-password/{id}")
    public Response updateUsuarioSenha(@PathParam("id") Long id, UpdatePasswordDTO dto){
        LOG.info("Atualizando senha");
        service.updatePassword(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PATCH
    @RolesAllowed("Cliente")
    @Path("/update-username/{id}")
    public Response updateUsuarioUsername(@PathParam("id") Long id, UpdateUsernameDTO dto){
        LOG.info("Atualizando username");
        service.updateUsername(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @PUT
    @Transactional
    @RolesAllowed("Cliente")
    @Path("/update/{id}")
    public Response update(ClienteDTO dto, @PathParam("id") Long id) {
        LOG.infof("Atualizando Cliente com ID: " + id);
        service.update(id, dto);
        LOG.infof("Cliente com ID: " + id + " atualizado com sucesso");
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Transactional
    @RolesAllowed({"Cliente","Funcionario"})
    @Path("/delete/{id}")
    public Response delete(@PathParam("id") Long id) {
        LOG.infof("Deletando Cliente com ID: " + id);
        service.delete(id);
        LOG.infof("Cliente com ID: " + id + " deletado com sucesso");
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @RolesAllowed({"Cliente","Funcionario"})
    public Response findAll() {
        LOG.debug("Buscando todos os Clientes");
        return Response.ok(service.getAll()).build();
    }

    @GET
    @RolesAllowed({"Cliente","Funcionario"})
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        LOG.infof("Buscando Cliente pelo nome: " + nome);
        return Response.ok(service.findByNome(nome)).build();
    }

    @GET
    @RolesAllowed("Funcionario")
    @Path("/search/email/{email}")
    public Response findByEmail(@PathParam("email") String email) {
        LOG.infof("Buscando Cliente pelo email: " + email);
        return Response.ok(service.findByEmail(email)).build();
    }

    @GET
    @RolesAllowed("Funcionario")
    @Path("/search/id/{id}")
    public Response findById(@PathParam("id") Long id) {
        LOG.info("Buscando Cliente pelo ID: " + id);
        return Response.ok(service.findById(id)).build();
    }

    @PATCH
    @Path("/{id}/image/upload")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public Response upload(@PathParam("id") Long id, @MultipartForm ImageForm form) {
        LOG.infof("Fazendo upload de imagem do Cliente com ID: " + id);
        fileService.salvar(id, form.getNomeImagem(), form.getImagem());
        LOG.infof("Imagem do Cliente com ID: " + id + " salva com sucesso");
        return Response.noContent().build();
    }

    @GET
    @Path("/image/download/{nomeImagem}")
    public Response download(@PathParam("nomeImagem") String nomeImagem) {
        LOG.infof("Fazendo download da imagem: " + nomeImagem);
        return Response.ok(fileService.download(nomeImagem)).header("Content-Disposition", "attachment;filename=" + nomeImagem).build();
    }
}

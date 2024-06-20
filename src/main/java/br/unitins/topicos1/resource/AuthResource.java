package br.unitins.topicos1.resource;

import org.jboss.logging.Logger;

import br.unitins.topicos1.dto.AuthLoginDTO;
import br.unitins.topicos1.dto.LoginResponseDTO;
import br.unitins.topicos1.service.ClienteService;
import br.unitins.topicos1.service.FuncionarioService;
import br.unitins.topicos1.service.HashService;
import br.unitins.topicos1.service.JwtService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/auth")
public class AuthResource {

    @Inject
    public ClienteService clienteService;

    @Inject
    public FuncionarioService funcionarioService;

    @Inject
    public HashService hashService;

    @Inject
    public JwtService jwtService;

    private static final Logger LOG = Logger.getLogger(AuthResource.class);
    
    @POST
    public Response login(AuthLoginDTO dto) {
        String hash = hashService.getHashSenha(dto.senha());

        LoginResponseDTO usuario = null;
        // perfil 1 = Cliente
        if (dto.perfil() == 1) {
            LOG.info("efetuando login em cliente");
            usuario = clienteService.login(dto.username(), hash);
        } else if (dto.perfil() == 2) { // Funcionario
            LOG.info("efetuando login em funcionario");
            usuario = funcionarioService.login(dto.username(), hash);
        } else {
            LOG.info("Login negado");
            return Response.status(Status.NOT_FOUND).build();
        }

        LOG.info("Login realizado");
        return Response.ok(usuario)
            .header("Authorization", jwtService.generateJwt(usuario,dto.perfil()))
            .build();  
    }
}
package br.unitins.topicos1.service;

import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import br.unitins.topicos1.dto.LoginResponseDTO;
import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class JwtServiceImpl implements JwtService {

    private static final Duration EXPIRATION_TIME = Duration.ofHours(2);

    @Override
    public String generateJwt(LoginResponseDTO dto, Integer perfil) {
        Instant now = Instant.now();
        Instant expiryDate = now.plus(EXPIRATION_TIME);

        Set<String> roles = new HashSet<String>();
        if(perfil == 1){
            roles.add("Cliente");
        } else if (perfil == 2){
            roles.add("Funcionario");
        }
        return Jwt.issuer("unitins-jwt")
            .subject(dto.name())
            .groups(roles)
            .claim("loginId",dto.id().toString())
            .expiresAt(expiryDate)
            .sign();
    }

}
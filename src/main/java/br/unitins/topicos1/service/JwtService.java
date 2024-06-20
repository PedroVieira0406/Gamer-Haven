package br.unitins.topicos1.service;

import br.unitins.topicos1.dto.LoginResponseDTO;

public interface JwtService {
    String generateJwt(LoginResponseDTO dto, Integer perfil);

}
package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Login;


public record LoginResponseDTO(
    Long id,
    String name,
    String senha
) {
    public static LoginResponseDTO valueOf(Login login) {
        return new LoginResponseDTO(
            login.getId(),
            login.getName(),
            login.getSenha()
        );
    }
}
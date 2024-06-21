package br.unitins.topicos1.dto;

public record UpdatePasswordDTO(
    String oldPassword,
    String newPassword,
    String newPassword2
) {
    
}
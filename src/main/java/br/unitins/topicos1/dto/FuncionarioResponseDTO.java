package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Funcionario;

public record FuncionarioResponseDTO(
    Long id,
    String login,
    String nome,
    String email,
    String cpf,
    String cargo,
    String senha,
    String nomeImagem
    
) {
    public static FuncionarioResponseDTO valueOf(Funcionario funcionario) {
        return new FuncionarioResponseDTO(
            funcionario.getId(),
            funcionario.getLogin().getName(),
            funcionario.getNome(),
            funcionario.getEmail(),
            funcionario.getCpf(),
            funcionario.getCargo(),
            funcionario.getLogin().getSenha(),
            funcionario.getNomeImagem());
    }
    
}
package br.unitins.topicos1.dto;

import br.unitins.topicos1.model.Empresa;

public record EmpresaResponseDTO(Long id, String nome,String cnpj, String Email ){
    public static EmpresaResponseDTO valueOf(Empresa empresa){
        return new EmpresaResponseDTO(
        empresa.getId(),
        empresa.getNome(),
        empresa.getCnpj(),
        empresa.getEmail());
    }
    
}

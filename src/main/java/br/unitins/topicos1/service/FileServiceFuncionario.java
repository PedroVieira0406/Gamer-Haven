package br.unitins.topicos1.service;

import java.io.File;

public interface FileServiceFuncionario {

    void salvar(Long id, String nomeImagem, byte[] imagem);
    File download(String nomeImagem);
    

}
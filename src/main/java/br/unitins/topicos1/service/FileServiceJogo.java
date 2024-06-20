package br.unitins.topicos1.service;

import java.io.File;

public interface FileServiceJogo {

    void salvar(Long id, String nomeImagem, byte[] imagem);
    File download(String nomeImagem);
    

}
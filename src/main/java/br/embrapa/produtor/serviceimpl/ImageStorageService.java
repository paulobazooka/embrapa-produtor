package br.embrapa.produtor.serviceimpl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.io.*;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.io.IOException;



/**
 *  Classe auxiliar para armasenamento de imagens
 *
 * */
@Service
public class ImageStorageService {

    public final static String PATH_STRING = "/home/paulo/Desktop/storage/";
    private Path path = Paths.get(PATH_STRING);

    // inicializar a pasta padrão de storage de imagens
    protected void init() {
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                throw new RuntimeException("Não foi possível inicializar o storage!");
            }
        }
    }

    // Metodo para armazenar as imagens e retornar uma lista de caminhos.
    public String salvarImagens(MultipartFile file, String solicitacaoId, String usuarioId) {

        this.init();

        String caminho = String.valueOf(LocalDateTime.now().getYear() + "/" +
                usuarioId + "/" + solicitacaoId + "/");
        Path path_caminho = Paths.get(PATH_STRING + caminho);

        if (!Files.exists(path_caminho)) {
            try {
                Files.createDirectories(path_caminho);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        String resposta = "";

            if (file != null) {
                if (!Files.exists(path_caminho.resolve(file.getOriginalFilename()))) {
                    try {
                        Files.copy(file.getInputStream(), path_caminho.resolve(file.getOriginalFilename()));
                        return caminho + file.getOriginalFilename();
                    } catch (IOException e) {
                        e.printStackTrace();
                        return resposta;
                    }
                }
            }else{
                return resposta;
            }
        return resposta;
    }
}






package br.embrapa.produtor.interfaces;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String uploadFile(MultipartFile multipartFile, String solicitacaoId, String produtorId);
}

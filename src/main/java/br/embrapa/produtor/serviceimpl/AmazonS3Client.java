package br.embrapa.produtor.serviceimpl;

import br.embrapa.produtor.interfaces.FileStorageService;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class AmazonS3Client implements FileStorageService {

    protected final String BUCKET_NAME = "embrapa-produtor";
    protected final String END_POINT_URL = "https://s3.sa-east-1.amazonaws.com";

    @Autowired
    AmazonS3 s3client;

    @Override
    public String uploadFile(MultipartFile multipartFile, String solicitacaoId, String produtorId) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = solicitacaoId + produtorId + generateFileName(multipartFile);
            fileUrl = END_POINT_URL + "/" + BUCKET_NAME + "/"  + fileName;
            uploadFileTos3bucket(fileName, file);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }


    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }


    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }


    private void uploadFileTos3bucket(String fileName, File file) {
        s3client.putObject(new PutObjectRequest(BUCKET_NAME, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }


}

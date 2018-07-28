package br.embrapa.produtor.auxiliar;


import br.embrapa.produtor.serviceimpl.ImageStorageService;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class FilePathToString64 {

    // Variável que armazenará caminho padrão do storage
    private static String root = ImageStorageService.PATH_STRING;
    private static Path path = Paths.get(root);


    public static String retornaString64(String caminho_arquivo) {

        Path file = path.resolve(caminho_arquivo);
        File img = file.toFile();

        if (img.exists() || img.isFile()) {

            FileInputStream fis;

            try {
                fis = new FileInputStream(img);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                int b;
                byte[] buffer = new byte[1024];

                while ((b = fis.read(buffer)) != -1) {
                    bos.write(buffer, 0, b);
                }

                byte[] fileBytes = bos.toByteArray();
                fis.close();
                bos.close();

                return Base64.encode(fileBytes);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return "";
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }

        }else{
            return "arquivo não existe!";
        }
    }
}



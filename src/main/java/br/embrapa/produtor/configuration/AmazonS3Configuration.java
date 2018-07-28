package br.embrapa.produtor.configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Paulo Sérgio do Nascimento
 * @since 2018
 *
 *     Configuração de acesso ao bucket da Amazon S3
 */

@Configuration
public class AmazonS3Configuration {

    private final String ACCESS_KEY_ID = "AKIAJPD6DSVMEW4DIF4A";
    private final String SECRET_ACCESS_KEY = "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";
    private final String REGION = "sa-east-1";

    @Bean
    public AmazonS3 s3client() {

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY);
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(REGION))
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();

        return s3Client;
    }
}

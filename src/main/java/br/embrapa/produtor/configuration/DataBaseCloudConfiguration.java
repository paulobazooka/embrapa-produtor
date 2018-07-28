package br.embrapa.produtor.configuration;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("prod")
public class DataBaseCloudConfiguration {

    @Bean
    public BasicDataSource dataSource(){
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl("jdbc:postgresql://embrapa-produtor.ctko8o4goca2.sa-east-1.rds.amazonaws.com:5432/embrapaprodutor");
        basicDataSource.setUsername("postgres");
        basicDataSource.setPassword("postgres");

        return basicDataSource;
    }
}

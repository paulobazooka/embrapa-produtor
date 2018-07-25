package prototype.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
public class JpaConfiguration {

    @Bean
    public JpaVendorAdapter jpaVendorAdapter(){
        HibernateJpaVendorAdapter adapter = new HibernateJpaVendorAdapter();
        adapter.setDatabase(Database.POSTGRESQL);
        adapter.setDatabasePlatform("org.hibernate.dialect.PostgreSQL95Dialect");
        adapter.setShowSql(true);
        adapter.setGenerateDdl(true);
        adapter.setPrepareConnection(true);
        return adapter;
    }
}

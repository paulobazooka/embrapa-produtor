package br.embrapa.produtor.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration @EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    private final static String LOGIN_PAGE = "/login";

    private final static String[] PAGES_PERMITED = {
            "/cadastrar-produtor",
            "/cadastrar-novo-produtor",
            "/confirmar-cadastro/*",
            "/produtor/cadastro",
            "/usuario/solicitar-nova-senha",
            "/usuario/enviar-nova-senha",
            "/novasenha",
            "/negado",
            "/usuario/pagina-solicitacao-nova-senha/*"
    };

    private final static String[] STATIC_DIR = {
            "/materialize/**",
            "/images/**",
            "/scripts/**",
            "/styles/**"
    };

    @Autowired
    private ImplementsUserDetails implementsUserDetails;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,  PAGES_PERMITED).permitAll()
                .antMatchers(HttpMethod.POST, PAGES_PERMITED).permitAll()
                .antMatchers(STATIC_DIR).permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/negado")
                .and()
                .formLogin().loginPage(LOGIN_PAGE).permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .and()
                .logout().logoutSuccessUrl("/login?logout").permitAll();
    }



    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring().antMatchers(STATIC_DIR);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(implementsUserDetails)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}

package br.embrapa.produtor.security;

import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.serviceimpl.UsuarioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *  Classe para obter um usuário da banco de dados com
 *      autienticação de acesso
 *
 * */
@Repository
public class ImplementsUserDetails implements UserDetailsService{

    @Autowired
    UsuarioServiceImpl usi;



    /**
     *  Método da sobrescrito da interface UserDetailsService ao qual
     *      retornará um userdetails customizado para obter acesso ao sistema.
     *
     * */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // Se encontrar no banco retorna um usário válido, se não retorna uma exception
        Usuario usuario = Optional.ofNullable(usi.buscarPorEmail(username)).orElseThrow(()-> new UsernameNotFoundException("Usuário não encontrado!"));

        return usuario;
    }
}

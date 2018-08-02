package br.embrapa.produtor.auxiliar;

import br.embrapa.produtor.models.Usuario;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class RefreshAuth {


    public void autenticarNovaSenhaUsuario(Usuario usuario){
        Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, usuario.getPassword(), usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    public void autenticarNovoStatusUsuario(Usuario usuario){

    }
}

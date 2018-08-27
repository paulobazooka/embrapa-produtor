package br.embrapa.produtor.serviceimpl;

import br.embrapa.produtor.auxiliar.RefreshAuth;
import br.embrapa.produtor.auxiliar.UsuarioUtils;
import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.repositories.UsuarioRepository;
import br.embrapa.produtor.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 */
@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository ur;

    @Autowired
    UsuarioUtils usuarioUtils;

    @Autowired
    RefreshAuth refreshAuth;

    /**
     *
     * @param usuario recebe usuário para persistir no banco de dados
     * @return retorna o usuário persistido no banco de dados
     */
    @Override
    public Usuario persistir(Usuario usuario) {
      //  refreshAuth.autenticarNovaSenhaUsuario(ur.save(usuario));
        return this.ur.save(usuario);
    }


    /**
     *
     * @param usuarioAtualizado entra com o usuário com os dados atualizados
     * @param id codigo do usuário no banco de dados a ser atualizado
     * @return retorna o usuário com os dados atualizados do banco de dados
     *
     */
    @Override
    public Usuario atualizarUsuario(Usuario usuarioAtualizado, Long id) {
        Usuario banco = this.ur.findById(id).get();
        usuarioUtils.atualizarUsuario(banco, usuarioAtualizado);
        refreshAuth.autenticarNovaSenhaUsuario(banco);
        return this.ur.save(banco);
    }

    /**
     *
     * @param email e-mail do usuário a ser encontrado no banco de dados
     * @return retorna o usuário que possua o email cadastrado
     *
     */
    @Override
    public Usuario buscarPorEmail(String email) {
        return ur.findByEmail(email);
    }



    /**
     *
     * @param id Código do usuário a ser buscado no banco de dados
     * @return retorna o usuário do banco de dados
     *
     */
    @Override
    public Usuario buscarPorId(Long id) {
        Usuario user = null;
        Optional<Usuario> usuarioOptional = Optional.ofNullable(this.ur.findById(id)).get();
        if (usuarioOptional.isPresent())
            user = usuarioOptional.get();
        return user;
    }


    @Override
    public void removerTodosUsuarios() {
        this.ur.deleteAll();
    }

    protected void atualizarAutenticacao(Usuario usuario){
        Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, usuario.getPassword(), usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }



    protected void atualizarAutenticacao2(Usuario usuario){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Authentication newAuth = new UsernamePasswordAuthenticationToken(usuario, usuario.isCredentialsNonExpired(), usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(newAuth);
    }
}

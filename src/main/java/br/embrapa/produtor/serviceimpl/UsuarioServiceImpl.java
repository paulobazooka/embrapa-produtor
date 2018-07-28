package br.embrapa.produtor.serviceimpl;

import br.embrapa.produtor.auxiliar.UsuarioUtils;
import br.embrapa.produtor.models.Usuario;
import br.embrapa.produtor.repositories.UsuarioRepository;
import br.embrapa.produtor.services.UsuarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository ur;

    @Autowired
    UsuarioUtils usuarioUtils;

    @Override
    public Usuario persistir(Usuario usuario) {
        this.atualizarAutenticação(usuario);
        return this.ur.save(usuario);
    }

    @Override
    public Usuario atualizarUsuario(Usuario usuarioAtualizado, Long id) {
        Usuario banco = this.ur.findById(id).get();
        usuarioUtils.atualizarUsuario(banco, usuarioAtualizado);
        this.atualizarAutenticação(banco);
        return this.ur.save(banco);
    }

    @Override
    public Usuario buscarPorEmail(String email) {
        return ur.findByEmail(email);
    }

    @Override
    public Usuario buscarPorId(Long id) {
        return this.ur.findById(id).get();
    }

    @Override
    public void removerTodosUsuarios() {
        this.ur.deleteAll();
    }

    protected void atualizarAutenticação(Usuario usuario){
        Authentication authentication = new UsernamePasswordAuthenticationToken(usuario, usuario.getPassword(), usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}

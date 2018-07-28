package br.embrapa.produtor.auxiliar;

import br.embrapa.produtor.models.Usuario;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioUtils {

    public void atualizarUsuario(Usuario banco, Usuario atualizado){

        atualizado.setId(banco.getId());

        if (!atualizado.getNome().isEmpty() && !atualizado.getNome().equals(banco.getNome()))
            banco.setNome(atualizado.getNome());

        if (!atualizado.getEmail().isEmpty() && !atualizado.getEmail().equals(banco.getEmail()))
            banco.setEmail(atualizado.getEmail());

        if (!atualizado.getTelefone().isEmpty() && !atualizado.getTelefone().equals(banco.getTelefone()))
            banco.setTelefone(atualizado.getTelefone());

        if (!atualizado.getSenha().isEmpty() && atualizado.getSenha() != null)
            banco.setSenha(new BCryptPasswordEncoder().encode(atualizado.getSenha()));
    }

}

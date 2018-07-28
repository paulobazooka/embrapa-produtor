package br.embrapa.produtor.services;

import br.embrapa.produtor.models.Usuario;

public interface UsuarioService {

    Usuario persistir(Usuario usuario);

    Usuario atualizarUsuario(Usuario usuarioAtualizado, Long id);

    Usuario buscarPorEmail(String email);

    Usuario buscarPorId(Long id);

    void removerTodosUsuarios();
}

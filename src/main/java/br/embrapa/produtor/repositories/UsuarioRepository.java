package br.embrapa.produtor.repositories;


import br.embrapa.produtor.models.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Usuario findByEmail(String email);

    Optional<Usuario> findById(Long id);

    Iterable<Usuario> findAllByOrderByIdAsc();

}

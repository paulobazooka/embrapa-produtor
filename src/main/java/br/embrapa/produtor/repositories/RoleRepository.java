package br.embrapa.produtor.repositories;

import br.embrapa.produtor.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long>{

    Role findRoleByNomeRole(String nomeRole);
}

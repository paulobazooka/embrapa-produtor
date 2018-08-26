package br.embrapa.produtor.serviceimpl;

import br.embrapa.produtor.models.Role;
import br.embrapa.produtor.repositories.RoleRepository;
import br.embrapa.produtor.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImplents implements RoleService {

    @Autowired
    RoleRepository rr;

    @Override
    public List<Role> listarRoles() {
        return null;
    }

    @Override
    public Role cadastrarRole(Role role) {
        return this.rr.save(role);
    }

    @Override
    public Role buscarRole(String nome) {
        return this.rr.findRoleByNomeRole(nome);
    }

    @Override
    public void deletarRoles() {
        this.rr.deleteAll();
    }
}

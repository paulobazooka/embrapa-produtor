package br.embrapa.produtor.services;

import br.embrapa.produtor.models.Role;

import java.util.List;

public interface RoleService {

    List<Role> listarRoles();

    Role cadastrarRole(Role role);

    Role buscarRole(String nome);

    void deletarRoles();
}

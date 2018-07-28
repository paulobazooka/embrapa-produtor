package br.embrapa.produtor.models;


import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "role")
public class Role extends Abstract implements GrantedAuthority{

    @Column(unique = true)
    private String nomeRole;

    @Override
    public String getAuthority() {
        return this.nomeRole;
    }


    public Role() {
    }

    public Role(String nomeRole) {
        this.nomeRole = nomeRole;
    }

    public String getNomeRole() {
        return nomeRole;
    }

    public void setNomeRole(String nomeRole) {
        this.nomeRole = nomeRole;
    }
}

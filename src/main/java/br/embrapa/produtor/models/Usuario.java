package br.embrapa.produtor.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "usuario")
public class Usuario extends Abstract implements UserDetails {

    private String senha;
    private String email;
    private String nome;
    private String telefone;
    private LocalDateTime data_cadastro;
    private LocalDateTime data_remocao;
    private String tipo;
    private boolean ativo;
    private boolean nova_senha;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();

    public Usuario() {
    }


    @NotEmpty(message = "senha não pode ser vazio!")
    @Transient
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @NotEmpty(message = "e-mail não pode ser vazio!")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @NotEmpty(message = "nome não pode ser vazio!")
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @NotEmpty(message = "telefone não pode ser vazio!")
    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDateTime getData_cadastro() {
        return data_cadastro;
    }

    public void setData_cadastro(LocalDateTime data_cadastro) {
        this.data_cadastro = data_cadastro;
    }

    public LocalDateTime getData_remocao() {
        return data_remocao;
    }

    public void setData_remocao(LocalDateTime data_remocao) {
        this.data_remocao = data_remocao;
    }

    @Column(columnDefinition="tinyint(1) default false")
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Column(insertable = false)
    public boolean isNova_senha() {
        return nova_senha;
    }

    public void setNova_senha(boolean nova_senha) {
        this.nova_senha = nova_senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.ativo;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

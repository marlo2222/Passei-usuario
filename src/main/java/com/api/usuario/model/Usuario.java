package com.api.usuario.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "primeiroNome", nullable = false)
    private String primeiroNome;

    @Column(name = "SegundoNome", nullable = false)
    private String SegundoNome;

    @Column(name = "matricula", nullable = false, length = 6, unique = true)
    //@Max(value = 6, message = "O tamanho da senha é no maximo 6 caracteres")
    private String matricula;

    @Column(name = "email", nullable = false, unique = true)
    //@Email(message = "o email fornecido nao e valido!")
    private String email;

    @Column(name = "senha", nullable = false)
    //@Min(value = 4, message = "a senha deve ter no minimo 6 caracteres")
    private String senha;

    @Column(name = "curso", nullable = false)
    private  long curso;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.matricula;
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
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

package com.metaway.petshop.api.config.auth;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.metaway.petshop.api.entities.Usuario;

public class CustomUserDetails implements UserDetails {

    private Usuario user;

    public CustomUserDetails(Usuario user) {
        this.user = user;
    }

    @Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(user.getPerfil().name()));
	}
    @Override
    public String getPassword() {
        return user.getSenha();
    }

    @Override
    public String getUsername() {
        return user.getCpf();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // For simplicity, using static true. You can add logic here as per requirements.
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // For simplicity, using static true.
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // For simplicity, using static true.
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

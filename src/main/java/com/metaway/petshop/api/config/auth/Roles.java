package com.metaway.petshop.api.config.auth;

import org.springframework.security.core.GrantedAuthority;

import com.metaway.petshop.api.enums.EnumPerfilUsuario;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Roles implements GrantedAuthority{
	
	private EnumPerfilUsuario perfil;

	@Override
	public String getAuthority() {
		return this.perfil.toString();
	}
	
	
}

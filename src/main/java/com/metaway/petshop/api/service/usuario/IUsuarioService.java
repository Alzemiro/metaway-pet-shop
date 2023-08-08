package com.metaway.petshop.api.service.usuario;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.metaway.petshop.api.entities.Usuario;

public interface IUsuarioService {
	
	UserDetailsService userDetailsService();

	Usuario findUsuarioByCPF(String cpf);

}

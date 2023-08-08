package com.metaway.petshop.api.service.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.metaway.petshop.api.config.auth.CustomUserDetails;
import com.metaway.petshop.api.entities.Usuario;
import com.metaway.petshop.api.repository.usuario.UsuarioRepository;

@Service
public class UsuarioService implements IUsuarioService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public Usuario findUsuarioByCPF(String cpf) {
		Usuario usuario = repository.findById(cpf)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado " + cpf));

		return usuario;
	}

	@Override
	public UserDetailsService userDetailsService() {
		return new UserDetailsService() {
			@Override
			public UserDetails loadUserByUsername(String cpf) {
				return new CustomUserDetails(repository.findById(cpf)
						.orElseThrow(() -> new UsernameNotFoundException("User not found")));
			}
		};
	}

}

package com.metaway.petshop.api.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.metaway.petshop.api.config.auth.CustomUserDetails;
import com.metaway.petshop.api.dao.request.CadastroUsuarioRequest;
import com.metaway.petshop.api.dao.request.LoginRequest;
import com.metaway.petshop.api.dao.response.JwtAuthenticationResponse;
import com.metaway.petshop.api.entities.Usuario;
import com.metaway.petshop.api.enums.EnumPerfilUsuario;
import com.metaway.petshop.api.repository.usuario.UsuarioRepository;
import com.metaway.petshop.api.service.jwt.IJwtService;

@Service
public class AuthenticationService implements IAuthenticationService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private IJwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public JwtAuthenticationResponse cadastraUsuario(CadastroUsuarioRequest request) {
		var user = Usuario.builder().cpf(request.getCpf()).perfil(EnumPerfilUsuario.CLIENTE).nome(request.getNome())
				.senha(passwordEncoder.encode(request.getSenha())).build();

		usuarioRepository.save(user);
		var jwt = jwtService.generateToken(new CustomUserDetails(user));
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}

	@Override
	public JwtAuthenticationResponse login(LoginRequest request) {
		authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(request.getCpf(), request.getSenha()));
		var user = usuarioRepository.findById(request.getCpf())
				.orElseThrow(() -> new IllegalArgumentException("Cpf ou senha inválido"));
		var jwt = jwtService.generateToken(new CustomUserDetails(user));
		return JwtAuthenticationResponse.builder().token(jwt).build();
	}

}

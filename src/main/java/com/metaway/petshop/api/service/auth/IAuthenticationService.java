package com.metaway.petshop.api.service.auth;

import com.metaway.petshop.api.dao.request.CadastroUsuarioRequest;
import com.metaway.petshop.api.dao.request.LoginRequest;
import com.metaway.petshop.api.dao.response.JwtAuthenticationResponse;

public interface IAuthenticationService {

	JwtAuthenticationResponse cadastraUsuario(CadastroUsuarioRequest request);

	JwtAuthenticationResponse login(LoginRequest request);

}

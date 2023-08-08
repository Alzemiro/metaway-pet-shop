package com.metaway.petshop.api.controller.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metaway.petshop.api.dao.request.CadastroUsuarioRequest;
import com.metaway.petshop.api.dao.request.LoginRequest;
import com.metaway.petshop.api.dao.response.JwtAuthenticationResponse;
import com.metaway.petshop.api.service.auth.AuthenticationService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    
	@Autowired
	private AuthenticationService authenticationService;
    
	@PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authenticationService.login(request));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<JwtAuthenticationResponse> cadastro(@RequestBody CadastroUsuarioRequest request) {
        return ResponseEntity.ok(authenticationService.cadastraUsuario(request));
    }
}
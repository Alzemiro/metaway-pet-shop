package com.metaway.petshop.api.dao.request;

import com.metaway.petshop.api.enums.EnumPerfilUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {
	private String cpf;
    private String senha;
    private EnumPerfilUsuario perfil;
}

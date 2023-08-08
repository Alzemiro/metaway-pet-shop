package com.metaway.petshop.api.dao.request;

import com.metaway.petshop.api.commons.GenericRequest;
import com.metaway.petshop.api.enums.EnumPerfilUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastroUsuarioRequest implements GenericRequest {

	private String cpf;
	private EnumPerfilUsuario perfil;
	private String nome;
	private String senha;
	
}

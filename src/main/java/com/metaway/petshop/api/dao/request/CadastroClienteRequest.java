package com.metaway.petshop.api.dao.request;

import com.metaway.petshop.api.commons.GenericRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastroClienteRequest implements GenericRequest{
	
	private String nome;
	
	
}

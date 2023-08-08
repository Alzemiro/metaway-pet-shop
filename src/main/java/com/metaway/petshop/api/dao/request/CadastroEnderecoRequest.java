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
public class CadastroEnderecoRequest implements GenericRequest{
	
	private Integer idCliente;
	private String logradouro;
	private String cidade;
	private String bairro;
	private String complemento;	
	private String tag;

}

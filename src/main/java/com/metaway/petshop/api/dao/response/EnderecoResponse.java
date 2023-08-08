package com.metaway.petshop.api.dao.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoResponse {

	private Integer clienteId;
	private Integer enderecoId;
	private String logradouro;
	private String cidade;
	private String bairro;	
    private String complemento;	
    private String tag;
	
}

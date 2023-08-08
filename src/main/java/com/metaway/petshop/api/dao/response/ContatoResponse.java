package com.metaway.petshop.api.dao.response;

import com.metaway.petshop.api.enums.EnumTipoContato;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContatoResponse {

	private Integer clienteId;
	private Integer contatoId;
	private String valor;
	private EnumTipoContato tipo;
    private String tag;
	
}

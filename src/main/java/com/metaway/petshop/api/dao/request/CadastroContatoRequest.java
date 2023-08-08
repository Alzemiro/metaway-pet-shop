package com.metaway.petshop.api.dao.request;

import com.metaway.petshop.api.commons.GenericRequest;
import com.metaway.petshop.api.enums.EnumTipoContato;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastroContatoRequest implements GenericRequest {
	
	private Integer idCliente;
	private String tag;
	private EnumTipoContato tipoContato;
	private String valor;
	
}

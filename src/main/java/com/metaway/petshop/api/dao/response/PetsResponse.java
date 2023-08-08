package com.metaway.petshop.api.dao.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PetsResponse {

	private Integer petId;
	private String raca;
	private String nome;
	private Integer clienteId;
	
}

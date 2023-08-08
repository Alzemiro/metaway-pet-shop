package com.metaway.petshop.api.dao.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RacaResponse {

	private Integer id;
	private String descricao;	
	
}

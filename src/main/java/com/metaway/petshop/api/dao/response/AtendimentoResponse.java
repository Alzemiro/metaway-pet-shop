package com.metaway.petshop.api.dao.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoResponse{

	private Integer antendimentoId;
	private Integer petId;
	private Long valor;
	private String descricao;
	private LocalDateTime data;
	
}

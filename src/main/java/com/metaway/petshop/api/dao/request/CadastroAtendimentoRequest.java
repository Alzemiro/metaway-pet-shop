package com.metaway.petshop.api.dao.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.metaway.petshop.api.commons.GenericRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CadastroAtendimentoRequest implements GenericRequest {	
	
	private Integer petId;
	private String descricaoAtendimento;
	private BigDecimal valor;
	private LocalDateTime data;
	
}

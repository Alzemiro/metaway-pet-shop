package com.metaway.petshop.api.service.atendimento;

import com.metaway.petshop.api.commons.GenericCRUD;
import com.metaway.petshop.api.dao.request.CadastroAtendimentoRequest;
import com.metaway.petshop.api.entities.Atendimento;

public interface IAtendimentoService extends GenericCRUD<Integer, Atendimento> {

	Atendimento updateFromRequest(Integer id, CadastroAtendimentoRequest request);

}

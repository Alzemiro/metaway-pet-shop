package com.metaway.petshop.api.service.contato;

import com.metaway.petshop.api.commons.GenericCRUD;
import com.metaway.petshop.api.dao.request.CadastroContatoRequest;
import com.metaway.petshop.api.dao.response.ContatoResponse;
import com.metaway.petshop.api.entities.Contato;

public interface IContatoService extends GenericCRUD<Integer, Contato> {
	ContatoResponse register(CadastroContatoRequest register);
}

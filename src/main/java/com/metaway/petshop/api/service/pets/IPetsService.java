package com.metaway.petshop.api.service.pets;

import com.metaway.petshop.api.commons.GenericCRUD;
import com.metaway.petshop.api.dao.request.CadastroPetRequest;
import com.metaway.petshop.api.dao.response.PetsResponse;
import com.metaway.petshop.api.entities.Pets;
import com.metaway.petshop.api.entities.Usuario;

public interface IPetsService extends GenericCRUD<Integer, Pets>{

	boolean petBelongsToUser(Integer petId, Usuario usuario);
	
}

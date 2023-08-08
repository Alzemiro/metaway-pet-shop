package com.metaway.petshop.api.service.contato;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.metaway.petshop.api.dao.request.CadastroContatoRequest;
import com.metaway.petshop.api.dao.response.ContatoResponse;
import com.metaway.petshop.api.entities.Contato;
import com.metaway.petshop.api.repository.contato.ContatoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContatoService implements IContatoService{

	private final ContatoRepository contatoRepository;

	@Override
	public Contato create(Contato register) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Contato update(Contato register) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer register) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Contato> findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Contato> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ContatoResponse register(CadastroContatoRequest register) {
		// TODO Auto-generated method stub
		return null;
	}

	
	


}

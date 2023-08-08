package com.metaway.petshop.api.service.raca;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.metaway.petshop.api.entities.Raca;
import com.metaway.petshop.api.repository.raca.RacaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RacaService implements IRacaService{

	private final RacaRepository racaRepository;

	@Override
	public Raca create(Raca register) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Raca update(Raca register) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer register) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Raca> findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Raca> findAll() {
		// TODO Auto-generated method stub
		return null;
	}




}

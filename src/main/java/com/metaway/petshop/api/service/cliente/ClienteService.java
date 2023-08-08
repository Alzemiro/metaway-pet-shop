package com.metaway.petshop.api.service.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.metaway.petshop.api.entities.Cliente;
import com.metaway.petshop.api.repository.cliente.ClienteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService implements IClienteService{

	private final ClienteRepository clienteRepository;

	@Override
	public Cliente create(Cliente register) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Cliente update(Cliente register) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Cliente> findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
	


}

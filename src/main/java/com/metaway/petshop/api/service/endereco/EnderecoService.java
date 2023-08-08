package com.metaway.petshop.api.service.endereco;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.metaway.petshop.api.entities.Endereco;
import com.metaway.petshop.api.repository.endereco.EnderecoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnderecoService implements IEnderecoService {

	private final EnderecoRepository enderecoRepository;

	@Override
	public Endereco create(Endereco register) {
		return enderecoRepository.save(register);
	}

	@Override
	public Endereco update(Endereco register) {
		if (!enderecoRepository.existsById(register.getId())) {
			throw new RuntimeException("Endereço não encontrado!");
		}
		return enderecoRepository.save(register);
	}

	@Override
	public void delete(Integer id) {
		enderecoRepository.deleteById(id);
	}

	@Override
	public Optional<Endereco> findById(Integer id) {
		return enderecoRepository.findById(id);
	}

	@Override
	public List<Endereco> findAll() {
		return enderecoRepository.findAll();
	}

}

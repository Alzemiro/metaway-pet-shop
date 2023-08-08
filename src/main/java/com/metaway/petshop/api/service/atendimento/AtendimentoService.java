package com.metaway.petshop.api.service.atendimento;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.metaway.petshop.api.dao.request.CadastroAtendimentoRequest;
import com.metaway.petshop.api.entities.Atendimento;
import com.metaway.petshop.api.entities.Pets;
import com.metaway.petshop.api.repository.atendimento.AtendimentoRepository;
import com.metaway.petshop.api.repository.pets.PetsRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AtendimentoService implements IAtendimentoService{
	
	private final AtendimentoRepository atendimentoRepository;
	private final PetsRepository petRepository;
		

	@Override
	public Atendimento create(Atendimento register) {
		Pets pet = petRepository.findById(register.getPet().getId()).orElseThrow(() -> new UsernameNotFoundException("Pet não localizado"));
		 
		new Atendimento();
		Atendimento atendimento = Atendimento.builder()
				 				.descricaoAtendimento(register.getDescricaoAtendimento())
				 				.data(register.getData())
				 				.valor(register.getValor())
				 				.pet(pet)
				 				.build();
		 
		return atendimentoRepository.save(atendimento);	
	}


	@Override
	public Atendimento update(Atendimento register) {
		  if(register.getId() == null) {
		        throw new IllegalArgumentException("ID de Atendimento não pode ser nulo ao atualizar");
		    }

		    Atendimento existingAtendimento = atendimentoRepository.findById(register.getId())
		            .orElseThrow(() -> new EntityNotFoundException("Não foi possível localizar o atendimento com ID: " + register.getId()));

		    Atendimento updated = existingAtendimento.builder()
		    					 .data(register.getData())
		    					 .descricaoAtendimento(register.getDescricaoAtendimento())
		    					 .pet(register.getPet())
		    					 .valor(register.getValor())
		    					 .id(register.getId())
		    					 .build();

		    return atendimentoRepository.save(updated);
	}


	@Override
	public void delete(Integer id) {
		if(id == null) {
	        throw new IllegalArgumentException("ID de Atendimento não pode ser nulo ao atualizar");
	    }

		atendimentoRepository.deleteById(id);		
	}


	@Override
	public Optional<Atendimento> findById(Integer register) {
		return atendimentoRepository.findById(register);
	}


	@Override
	public List<Atendimento> findAll() {
		return atendimentoRepository.findAll();
	}
	
	@Override
	public Atendimento updateFromRequest(Integer id, CadastroAtendimentoRequest request) {
        return atendimentoRepository.findById(id)
            .map(atendimento -> {
                atendimento.setDescricaoAtendimento(request.getDescricaoAtendimento());
                atendimento.setValor(request.getValor());
                atendimento.setData(request.getData());

                return atendimentoRepository.save(atendimento);
            })
            .orElseThrow(() -> new EntityNotFoundException("Atendimento não encontrado"));
    }
	
}

package com.metaway.petshop.api.service.pets;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.metaway.petshop.api.dao.request.CadastroPetRequest;
import com.metaway.petshop.api.dao.response.PetsResponse;
import com.metaway.petshop.api.entities.Cliente;
import com.metaway.petshop.api.entities.Pets;
import com.metaway.petshop.api.entities.Usuario;
import com.metaway.petshop.api.repository.pets.PetsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PetsService implements IPetsService {

    @Autowired
    private PetsRepository petsRepository;

    @Override
    public Pets create(Pets pet) {
        return petsRepository.save(pet);
    }

    @Override
    public Pets update(Pets pet) {
        if (!petsRepository.existsById(pet.getId())) {
            throw new EntityNotFoundException("Pet não encontrado");
        }
        return petsRepository.save(pet);
    }

    @Override
    public void delete(Integer petId) {
        petsRepository.deleteById(petId);
    }

    @Override
    public Optional<Pets> findById(Integer petId) {
        return petsRepository.findById(petId);
    }

    @Override
    public List<Pets> findAll() {
        return petsRepository.findAll();
    }

    public PetsResponse register(CadastroPetRequest request) {
        Pets pet = Pets.builder()
                .cliente(new Cliente(request.getClienteId(), null, null, null))
                .nome(request.getNome()) 
                .build();

        Pets savedPet = create(pet);

        return PetsResponse.builder()
                .petId(savedPet.getId())
                .clienteId(savedPet.getCliente().getId())
                .nome(savedPet.getNome())
                .build();
    }
    
    @Override
    public boolean petBelongsToUser(Integer petId, Usuario usuario) {
        return petsRepository.findById(petId)
            .map(pet -> pet.getCliente().getUsuario().equals(usuario))
            .orElse(false);
    }

}
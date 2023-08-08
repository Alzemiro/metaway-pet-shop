package com.metaway.petshop.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.metaway.petshop.api.dao.request.CadastroPetRequest;
import com.metaway.petshop.api.dao.response.PetsResponse;
import com.metaway.petshop.api.entities.Cliente;
import com.metaway.petshop.api.entities.Pets;
import com.metaway.petshop.api.entities.Usuario;
import com.metaway.petshop.api.enums.EnumPerfilUsuario;
import com.metaway.petshop.api.service.pets.IPetsService;
import com.metaway.petshop.api.service.usuario.IUsuarioService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/pets")
public class PetsController {

	@Autowired
	private IPetsService petsService;

	@Autowired
	private IUsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<PetsResponse> createPet(@RequestBody CadastroPetRequest request) {
		String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioService.findUsuarioByCPF(cpf);

		if (usuario.getPerfil() != EnumPerfilUsuario.ADMIN) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		Pets pet = Pets.builder().nome(request.getNome()).cliente(new Cliente(request.getClienteId(), usuario, cpf, null))
				.build();

		Pets createdPet = petsService.create(pet);

		PetsResponse response = PetsResponse.builder().petId(createdPet.getId()).nome(createdPet.getNome())
				.clienteId(createdPet.getCliente().getId())
				.build();

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<PetsResponse> updatePet(@PathVariable Integer id, @RequestBody CadastroPetRequest request) {
		String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioService.findUsuarioByCPF(cpf);

		Pets petToUpdate = petsService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Pet não encontrado"));

		if (usuario.getPerfil() == EnumPerfilUsuario.CLIENTE
				&& !petToUpdate.getCliente().getId().equals(request.getClienteId())) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		petToUpdate.setNome(request.getNome());

		Pets updatedPet = petsService.update(petToUpdate);

		PetsResponse response = PetsResponse.builder().petId(updatedPet.getId()).nome(updatedPet.getNome())
				.clienteId(updatedPet.getCliente().getId())
				.build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletePet(@PathVariable Integer id) {
		String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioService.findUsuarioByCPF(cpf);

		if (usuario.getPerfil() != EnumPerfilUsuario.ADMIN) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		petsService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PetsResponse> getPetById(@PathVariable Integer id) {
		String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioService.findUsuarioByCPF(cpf);

		return petsService.findById(id).map(pet -> {
			if (usuario.getPerfil() == EnumPerfilUsuario.CLIENTE && !pet.getCliente().getId().equals(usuario.getCpf())) {
				return new ResponseEntity<PetsResponse>(HttpStatus.FORBIDDEN);
			}

			return new ResponseEntity<>(PetsResponse.builder().petId(pet.getId()).nome(pet.getNome())
					.clienteId(pet.getCliente().getId())
					.build(), HttpStatus.OK);
		}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public ResponseEntity<List<Pets>> getAllPets() {
		String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioService.findUsuarioByCPF(cpf);

		if (usuario.getPerfil() == EnumPerfilUsuario.CLIENTE) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		List<Pets> pets = petsService.findAll();
		return new ResponseEntity<>(pets, HttpStatus.OK);
	}
}

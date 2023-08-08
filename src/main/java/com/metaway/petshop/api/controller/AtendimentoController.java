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

import com.metaway.petshop.api.dao.request.CadastroAtendimentoRequest;
import com.metaway.petshop.api.dao.response.AtendimentoResponse;
import com.metaway.petshop.api.entities.Atendimento;
import com.metaway.petshop.api.entities.Pets;
import com.metaway.petshop.api.entities.Usuario;
import com.metaway.petshop.api.enums.EnumPerfilUsuario;
import com.metaway.petshop.api.service.atendimento.IAtendimentoService;
import com.metaway.petshop.api.service.pets.IPetsService;
import com.metaway.petshop.api.service.usuario.IUsuarioService;

@RestController
@RequestMapping("/atendimentos")
public class AtendimentoController {

	@Autowired
	private IAtendimentoService atendimentoService;
	
	@Autowired
	private IPetsService petService;

	@Autowired
	private IUsuarioService usuarioService;

	@PostMapping
	public ResponseEntity<AtendimentoResponse> createAtendimento(@RequestBody CadastroAtendimentoRequest request) {
	    String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
	    Usuario usuario = usuarioService.findUsuarioByCPF(cpf);

	    if (usuario.getPerfil() != EnumPerfilUsuario.ADMIN) {
	        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	    }

	    Atendimento atendimento = Atendimento.builder()
	            .pet(new Pets(request.getPetId(), null, cpf, null))  // supondo que Pets tenha um construtor que aceite um ID
	            .descricaoAtendimento(request.getDescricaoAtendimento())
	            .valor(request.getValor())
	            .data(request.getData())
	            .build();

	    Atendimento savedAtendimento = atendimentoService.create(atendimento);

	    AtendimentoResponse response = AtendimentoResponse.builder()
	            .antendimentoId(savedAtendimento.getId())
	            .petId(savedAtendimento.getPet().getId())
	            .valor(savedAtendimento.getValor().longValue())
	            .descricao(savedAtendimento.getDescricaoAtendimento())
	            .data(savedAtendimento.getData())
	            .build();

	    return new ResponseEntity<>(response, HttpStatus.CREATED);
	}


	@PutMapping("/{id}")
	public ResponseEntity<AtendimentoResponse> updateAtendimento(@PathVariable Integer id,
			@RequestBody CadastroAtendimentoRequest request) {
		String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioService.findUsuarioByCPF(cpf);

		if (usuario.getPerfil() == EnumPerfilUsuario.CLIENTE
				&& !petService.petBelongsToUser(request.getPetId(), usuario)) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		Atendimento updatedAtendimento = atendimentoService.updateFromRequest(id, request);

		AtendimentoResponse response = AtendimentoResponse.builder().antendimentoId(updatedAtendimento.getId())
				.petId(updatedAtendimento.getPet().getId()).valor(updatedAtendimento.getValor().longValue())
				.descricao(updatedAtendimento.getDescricaoAtendimento()).data(updatedAtendimento.getData()).build();

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAtendimento(@PathVariable Integer id) {
		String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioService.findUsuarioByCPF(cpf);

		if (usuario.getPerfil() != EnumPerfilUsuario.ADMIN) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		atendimentoService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@GetMapping("/{id}")
	public ResponseEntity<AtendimentoResponse> getAtendimentoById(@PathVariable Integer id) {
		String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioService.findUsuarioByCPF(cpf);

		return atendimentoService.findById(id).map(atendimento -> {
			if (usuario.getPerfil() == EnumPerfilUsuario.CLIENTE
					&& !petService.petBelongsToUser(atendimento.getPet().getId(), usuario)) {
				return new ResponseEntity<AtendimentoResponse>(HttpStatus.FORBIDDEN);
			}

			return new ResponseEntity<>(
					AtendimentoResponse.builder().antendimentoId(atendimento.getId())
							.petId(atendimento.getPet().getId()).valor(atendimento.getValor().longValue())
							.descricao(atendimento.getDescricaoAtendimento()).data(atendimento.getData()).build(),
					HttpStatus.OK);
		}).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public ResponseEntity<List<Atendimento>> getAllAtendimentos() {
		String cpf = SecurityContextHolder.getContext().getAuthentication().getName();
		Usuario usuario = usuarioService.findUsuarioByCPF(cpf);

		if (usuario.getPerfil() == EnumPerfilUsuario.CLIENTE) {
			return new ResponseEntity<>(HttpStatus.FORBIDDEN);
		}

		List<Atendimento> atendimentos = atendimentoService.findAll();
		return new ResponseEntity<>(atendimentos, HttpStatus.OK);
	}
}
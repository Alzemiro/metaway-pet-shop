package com.metaway.petshop.api.entities;

import com.metaway.petshop.api.enums.EnumPerfilUsuario;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
public class Usuario {

	@Id
	@Column(name = "cpf", nullable = false, length = 11)
	private String cpf;

	@Enumerated(EnumType.STRING)
	@Column(name = "perfil")
	private EnumPerfilUsuario perfil;

	@Column(name = "nome")
	private String nome;

	@Column(name = "senha")
	private String senha;
		
}

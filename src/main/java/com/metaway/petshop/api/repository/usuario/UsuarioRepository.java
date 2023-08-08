package com.metaway.petshop.api.repository.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metaway.petshop.api.entities.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
}

package com.metaway.petshop.api.repository.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metaway.petshop.api.entities.Cliente;
import com.metaway.petshop.api.entities.Usuario;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer>{
	
	Optional<Cliente> findByUsuario(Usuario usuario);
	
	List<Cliente> findByNomeContaining(String nome);
}

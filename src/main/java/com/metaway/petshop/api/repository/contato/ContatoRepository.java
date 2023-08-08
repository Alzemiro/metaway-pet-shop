package com.metaway.petshop.api.repository.contato;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metaway.petshop.api.entities.Cliente;
import com.metaway.petshop.api.entities.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer>{
	
	List<Contato> findByCliente(Cliente cliente);	
	
}
package com.metaway.petshop.api.repository.atendimento;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metaway.petshop.api.entities.Atendimento;
import com.metaway.petshop.api.entities.Cliente;
import com.metaway.petshop.api.entities.Pets;

@Repository
public interface AtendimentoRepository extends JpaRepository<Atendimento, Integer>{
	
	List<Atendimento> findAllByPet(Pets pet);
	
	List<Atendimento> findAllByCliente(Cliente cliente);
	
}

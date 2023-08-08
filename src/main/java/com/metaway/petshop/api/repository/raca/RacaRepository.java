package com.metaway.petshop.api.repository.raca;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metaway.petshop.api.entities.Raca;

@Repository
public interface RacaRepository extends JpaRepository<Raca, Integer>{
	
}

package com.metaway.petshop.api.repository.pets;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metaway.petshop.api.entities.Pets;

@Repository
public interface PetsRepository extends JpaRepository<Pets, Integer>{
	
}

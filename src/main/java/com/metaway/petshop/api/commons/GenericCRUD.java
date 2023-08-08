package com.metaway.petshop.api.commons;

import java.util.List;
import java.util.Optional;

public interface GenericCRUD<ID, T> {
	
	public T create(T register);	
	
	public T update(T register);	
	
	public void delete(ID register);
	
	public Optional<T> findById(ID id);
	
	List<T> findAll();
}

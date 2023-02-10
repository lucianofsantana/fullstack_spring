package com.lucianofsantana.fullstack_spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianofsantana.fullstack_spring.domain.Categoria;
import com.lucianofsantana.fullstack_spring.repositories.CategoriaRepository;
import com.lucianofsantana.fullstack_spring.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		Categoria cat = obj.orElse(null);

		if (cat == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName());
		}
		return obj.orElse(null);
	}
	
	public Categoria insert(Categoria obj) {
		obj.setId(null); //garante que seja inserção
		return repo.save(obj);
	}
	
}

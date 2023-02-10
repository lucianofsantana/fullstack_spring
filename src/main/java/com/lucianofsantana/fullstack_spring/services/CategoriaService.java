package com.lucianofsantana.fullstack_spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lucianofsantana.fullstack_spring.domain.Categoria;
import com.lucianofsantana.fullstack_spring.repositories.CategoriaRepository;
import com.lucianofsantana.fullstack_spring.services.exceptions.DataIntegrityException;
import com.lucianofsantana.fullstack_spring.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;
	
	public Categoria find(Integer id) {
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
	
	public Categoria update(Categoria obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possibel excluir uma categoria que possui produtos");
		}
	}
}

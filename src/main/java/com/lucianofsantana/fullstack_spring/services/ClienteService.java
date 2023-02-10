package com.lucianofsantana.fullstack_spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianofsantana.fullstack_spring.domain.Cliente;
import com.lucianofsantana.fullstack_spring.repositories.ClienteRepository;
import com.lucianofsantana.fullstack_spring.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		Cliente cat = obj.orElse(null);

		if (cat == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName());
		}
		return obj.orElse(null);
	}
	
}

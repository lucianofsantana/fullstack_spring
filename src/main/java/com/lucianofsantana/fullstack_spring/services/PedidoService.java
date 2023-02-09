package com.lucianofsantana.fullstack_spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lucianofsantana.fullstack_spring.domain.Pedido;
import com.lucianofsantana.fullstack_spring.repositories.PedidoRepository;
import com.lucianofsantana.fullstack_spring.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		Pedido cat = obj.orElse(null);

		if (cat == null) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName());
		}
		return obj.orElse(null);
	}
	
}

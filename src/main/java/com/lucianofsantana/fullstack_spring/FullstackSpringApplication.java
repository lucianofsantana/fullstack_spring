package com.lucianofsantana.fullstack_spring;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucianofsantana.fullstack_spring.domain.Categoria;
import com.lucianofsantana.fullstack_spring.domain.Produto;
import com.lucianofsantana.fullstack_spring.repositories.CategoriaRepository;
import com.lucianofsantana.fullstack_spring.repositories.ProdutoRepository;

@SpringBootApplication
public class FullstackSpringApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(FullstackSpringApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		
		Produto p1 = new Produto(null, "Computador", 20000.00);
		Produto p2 = new Produto(null, "Impressora", 200.00);
		Produto p3 = new Produto(null, "Teclado", 100.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
	}
	
}

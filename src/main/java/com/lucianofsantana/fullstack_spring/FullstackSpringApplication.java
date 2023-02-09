package com.lucianofsantana.fullstack_spring;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lucianofsantana.fullstack_spring.domain.Categoria;
import com.lucianofsantana.fullstack_spring.domain.Cidade;
import com.lucianofsantana.fullstack_spring.domain.Cliente;
import com.lucianofsantana.fullstack_spring.domain.Endereco;
import com.lucianofsantana.fullstack_spring.domain.Estado;
import com.lucianofsantana.fullstack_spring.domain.Pagamento;
import com.lucianofsantana.fullstack_spring.domain.PagamentoComBoleto;
import com.lucianofsantana.fullstack_spring.domain.PagamentoComCartao;
import com.lucianofsantana.fullstack_spring.domain.Pedido;
import com.lucianofsantana.fullstack_spring.domain.Produto;
import com.lucianofsantana.fullstack_spring.domain.enums.EstadoPagamento;
import com.lucianofsantana.fullstack_spring.domain.enums.TipoCliente;
import com.lucianofsantana.fullstack_spring.repositories.CategoriaRepository;
import com.lucianofsantana.fullstack_spring.repositories.CidadeRepository;
import com.lucianofsantana.fullstack_spring.repositories.ClienteRepository;
import com.lucianofsantana.fullstack_spring.repositories.EnderecoRepository;
import com.lucianofsantana.fullstack_spring.repositories.EstadoRepository;
import com.lucianofsantana.fullstack_spring.repositories.PagamentoRepository;
import com.lucianofsantana.fullstack_spring.repositories.PedidoRepository;
import com.lucianofsantana.fullstack_spring.repositories.ProdutoRepository;

@SpringBootApplication
public class FullstackSpringApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
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
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Patos de Minas", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
		
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Luciano Fernandes de Santana", "luciano6750@gmail.com", "77753391622", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("349899886655","34988774445"));
		
		Endereco e1 = new Endereco(null, "Rua das orquideas" , "3", "Ap 803", "Centro", "38113245", cli1, c1);
		Endereco e2 = new Endereco(null, "Av Paracatu" , "65", "Casa", "Centro", "38113245", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1,e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2022 10:32"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2022 08:32"), cli1, e2);
		
		Pagamento pag1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2,sdf.parse("20/11/2022 00:00"),null);
		ped2.setPagamento(pag2);
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
		
		
		
	}
	
}

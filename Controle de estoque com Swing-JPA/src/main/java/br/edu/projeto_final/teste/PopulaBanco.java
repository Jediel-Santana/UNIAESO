package br.edu.projeto_final.teste;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;

import br.edu.projeto_final.dao.FuncionarioDAO;
import br.edu.projeto_final.dao.PontoDeVendaDAO;
import br.edu.projeto_final.dao.ProdutoDAO;
import br.edu.projeto_final.enums.Categoria;
import br.edu.projeto_final.enums.Cor;
import br.edu.projeto_final.enums.Modelo;
import br.edu.projeto_final.enums.Sexo;
import br.edu.projeto_final.enums.Tamanho;
import br.edu.projeto_final.enums.Tipo;
import br.edu.projeto_final.embutiveis.Endereco;
import br.edu.projeto_final.modelo.Funcionario;
import br.edu.projeto_final.modelo.PontoDeVenda;
import br.edu.projeto_final.modelo.Produto;
import br.edu.projeto_final.embutiveis.Telefone;
import br.edu.projeto_final.util.JPAutil;

public class PopulaBanco {
		
	
	
	public static void main(String[] args) {
		
		
//		EntityManager em = JPAutil.getEntityManager();
//		
//		PontoDeVendaDAO pontoDeVendaDAO = new PontoDeVendaDAO(em);
//		FuncionarioDAO funcionarioDAO = new FuncionarioDAO(em);
//		ProdutoDAO produtoDAO = new ProdutoDAO(em);
//		
//		
//		//PONTO DE VENDA
//		//Endereco enderecoPontoDeVenda = new Endereco("53439370", "80", "D");
//		Telefone telefonePontoDeVenda = new Telefone("81", "98080-8080");
//		PontoDeVenda pv = new PontoDeVenda(enderecoPontoDeVenda, telefonePontoDeVenda, "pontodeVenda1@dominio.com");
//		
//		//PONTO DE VENDA
//		//Endereco enderecoPontoDeVenda2 = new Endereco("53130610", "14", "B");
//		Telefone telefonePontoDeVenda2 = new Telefone("81", "98080-9090");
//		PontoDeVenda pv2 = new PontoDeVenda(enderecoPontoDeVenda2, telefonePontoDeVenda2, "pontodeVenda2@dominio.com");
//		
//		//PONTO DE VENDA
//		//Endereco enderecoPontoDeVenda3 = new Endereco("52050500", "65", "A");
//		Telefone telefonePontoDeVenda3 = new Telefone("81", "98080-1010");
//		PontoDeVenda pv3 = new PontoDeVenda(enderecoPontoDeVenda3, telefonePontoDeVenda3, "pontodeVenda3@dominio.com");
//		
//		//PONTO DE VENDA
//		//Endereco enderecoPontoDeVenda4 = new Endereco("51020000", "20", "");
//		Telefone telefonePontoDeVenda4 = new Telefone("81", "98082-5010");
//		PontoDeVenda pv4 = new PontoDeVenda(enderecoPontoDeVenda4, telefonePontoDeVenda4, "pontodeVenda4@dominio.com");
//		
//		
//		//FUNCIONARIO
//		Telefone telefone = new Telefone("81", "98080-8080");
//		//Endereco endereco = new Endereco("53250050", "80", "D");
//		LocalDate dataNascimento = LocalDate.of(2000, 9, 5);
//		Funcionario funcionario = new Funcionario(126377533485l, "Jediel Santana", Sexo.MASCULINO, endereco, dataNascimento, telefone, "jediel@dominio.com", "@JedielSantana");
//	
//		
//		//FUNCIONARIO
//		Telefone telefone2 = new Telefone("81", "98080-8080");
//		//Endereco endereco2 = new Endereco("51020000", "20", "A");
//		LocalDate dataNascimento2 = LocalDate.of(1995, 4, 6);
//		Funcionario funcionario2 = new Funcionario(12345678910l, "Icaro Batalha", Sexo.MASCULINO, endereco2, dataNascimento2, telefone2, "Icaro@dominio.com", "@IcaroBatalha");
//		
//		//FUNCIONARIO
//		Telefone telefone3 = new Telefone("81", "98080-8080");
//		//Endereco endereco3 = new Endereco("52050502", "50", "A");
//		LocalDate dataNascimento3 = LocalDate.of(1997, 7, 19);
//		Funcionario funcionario3 = new Funcionario(98765432101l, "Daniella Farias", Sexo.FEMININO, endereco3, dataNascimento3, telefone3, "Daniella@dominio.com", "@DaniellaFarias");
//		
//		//FUNCIONARIO
//		Telefone telefone4 = new Telefone("81", "98080-8080");
//		//Endereco endereco4 = new Endereco("53130610", "30", "D");
//		LocalDate dataNascimento4 = LocalDate.of(1990, 4, 10);
//		Funcionario funcionario4 = new Funcionario(12332145689l, "Renato Antonio", Sexo.MASCULINO, endereco4, dataNascimento4, telefone4, "Renato@dominio.com", "@RenatoAntonio");
//		
//		pv.adicionaFuncionario(funcionario);
//		pv2.adicionaFuncionario(funcionario2);
//		pv3.adicionaFuncionario(funcionario3);
//		pv4.adicionaFuncionario(funcionario4);
//		
//		PontoDeVenda pvCadastro = em.find(PontoDeVenda.class, 4l);
//		
//		Produto camisetaSAMBA = new Produto("Camiseta UNIDOS DO SAMBA", 782383142550l, Categoria.Vestuario, Tipo.Camisa, Tamanho.P, Modelo.Feminino, Cor.Colorido, 10, new BigDecimal("59.90"), pvCadastro);
//		Produto camisetaSAMBA2 = new Produto("Camiseta UNIDOS DO SAMBA", 782383142550l, Categoria.Vestuario, Tipo.Camisa, Tamanho.M, Modelo.Feminino, Cor.Colorido, 20, new BigDecimal("59.90"), pvCadastro);
//		Produto camisetaSAMBA3 = new Produto("Camiseta UNIDOS DO SAMBA", 782383142550l, Categoria.Vestuario, Tipo.Camisa, Tamanho.G, Modelo.Feminino, Cor.Colorido, 30, new BigDecimal("59.90"), pvCadastro);
//		Produto camisetaSAMBA4 = new Produto("Camiseta UNIDOS DO SAMBA", 782383142550l, Categoria.Vestuario, Tipo.Camisa, Tamanho.XG, Modelo.Feminino, Cor.Colorido, 40, new BigDecimal("59.90"), pvCadastro);
//		
//		Produto MANGUEIRA = new Produto("Camiseta MANGUEIRA", 783383142560l, Categoria.Vestuario, Tipo.Camisa, Tamanho.P, Modelo.Feminino, Cor.Colorido, 10, new BigDecimal("59.90"), pvCadastro);
//		Produto MANGUEIRA2 = new Produto("Camiseta MANGUEIRA", 783383142560l, Categoria.Vestuario, Tipo.Camisa, Tamanho.M, Modelo.Masculino, Cor.Colorido, 20, new BigDecimal("59.90"), pvCadastro);
//		Produto MANGUEIRA3 = new Produto("Camiseta MANGUEIRA", 783383142560l, Categoria.Vestuario, Tipo.Camisa, Tamanho.G, Modelo.Feminino, Cor.Colorido, 30, new BigDecimal("59.90"), pvCadastro);
//		Produto MANGUEIRA4 = new Produto("Camiseta MANGUEIRA", 783383142560l, Categoria.Vestuario, Tipo.Camisa, Tamanho.XG, Modelo.Masculino, Cor.Colorido, 40, new BigDecimal("59.90"), pvCadastro);
//	
//		Produto GAVIOES = new Produto("Camiseta GAVIOES", 783383542440l, Categoria.Vestuario, Tipo.Camisa, Tamanho.P, Modelo.Feminino, Cor.Colorido, 10, new BigDecimal("59.90"), pvCadastro);
//		Produto GAVIOES2 = new Produto("Camiseta GAVIOES", 783383542440l, Categoria.Vestuario, Tipo.Camisa, Tamanho.M, Modelo.Masculino, Cor.Colorido, 20, new BigDecimal("59.90"), pvCadastro);
//		Produto GAVIOES3 = new Produto("Camiseta GAVIOES", 783383542440l, Categoria.Vestuario, Tipo.Camisa, Tamanho.G, Modelo.Feminino, Cor.Colorido, 30, new BigDecimal("59.90"), pvCadastro);
//		Produto GAVIOES4 = new Produto("Camiseta GAVIOES", 783383542440l, Categoria.Vestuario, Tipo.Camisa, Tamanho.XG, Modelo.Masculino, Cor.Colorido, 40, new BigDecimal("59.90"), pvCadastro);
//		
//		
//                Produto produto = produtoDAO.buscaPorProduto(783383542440l, Tamanho.XG, Cor.Colorido, Modelo.Masculino, pontoDeVendaDAO.buscarPontoDeVenda(4l));
//                 produto.setPreco(new BigDecimal(29.90));
//		
//	
//		em.getTransaction().begin();
//		
		//cadastrar pv
//		pontoDeVendaDAO.cadastrar(pv);
//		pontoDeVendaDAO.cadastrar(pv2);
//		pontoDeVendaDAO.cadastrar(pv3);
//		pontoDeVendaDAO.cadastrar(pv4);
//                Funcionario func = em.find(Funcionario.class, 126377533485l);
		
//                produtoDAO.atualizar(produto);


               


//                PontoDeVenda pv1 = em.find(PontoDeVenda.class, 3l);
//                pontoDeVendaDAO.cadastrarFuncionarioAoPontoDeVenda(func, pv1);
                
		//cadastrar produtos c ponto de venda
//		produtoDAO.cadastrar(camisetaSAMBA);
//		produtoDAO.cadastrar(camisetaSAMBA2);
//		produtoDAO.cadastrar(camisetaSAMBA3);
//		produtoDAO.cadastrar(camisetaSAMBA4);
//		
//		produtoDAO.cadastrar(MANGUEIRA);
//		produtoDAO.cadastrar(MANGUEIRA2);
//		produtoDAO.cadastrar(MANGUEIRA3);
//		produtoDAO.cadastrar(MANGUEIRA4);
//		
//		produtoDAO.cadastrar(GAVIOES);
//		produtoDAO.cadastrar(GAVIOES2);
//		produtoDAO.cadastrar(GAVIOES3);
//		produtoDAO.cadastrar(GAVIOES4);
//		
		
		
		//cadastrar funcionario com m�todo.
//		PontoDeVenda pontoDeVenda1 = pontoDeVendaDAO.buscarPontoDeVenda(1l);
		//PontoDeVenda pontoDeVenda2 = pontoDeVendaDAO.buscarPontoDeVenda(2l);
//		PontoDeVenda pontoDeVenda3 = pontoDeVendaDAO.buscarPontoDeVenda(3l);
//		PontoDeVenda pontoDeVenda4 = pontoDeVendaDAO.buscarPontoDeVenda(4l);
//		
//		pontoDeVendaDAO.cadastrarFuncionarioAoPontoDeVenda(funcionario, pontoDeVenda1);
//		pontoDeVendaDAO.cadastrarFuncionarioAoPontoDeVenda(funcionario2, pontoDeVenda2);
//		pontoDeVendaDAO.cadastrarFuncionarioAoPontoDeVenda(funcionario3, pontoDeVenda3);
//		pontoDeVendaDAO.cadastrarFuncionarioAoPontoDeVenda(funcionario4, pontoDeVenda4);
//		Funcionario renato = em.find(Funcionario.class, 783383142560l);
//		Funcionario dani = em.find(Funcionario.class, 98765432101l);
//		Funcionario icaro = em.find(Funcionario.class, 12345678910l);
//
               
                
                
                
                //pontoDeVendaDAO.cadastrarFuncionarioAoPontoDeVenda(renato, pontoDeVenda1);
//		pontoDeVendaDAO.cadastrarFuncionarioAoPontoDeVenda(dani, pontoDeVenda2);
//
//		funcionario3.setRedeSocial("@danifarias");		
//		funcionarioDAO.atualizar(funcionario3);
//		funcionario3.setRedeSocial("@daniFarias");
//		funcionarioDAO.atualizar(funcionario3);
//		icaro.setDataNascimento(LocalDate.of(2000, 4, 10));
//		funcionarioDAO.atualizar(icaro);
		
		
		
		
		
//		pontoDeVendaDAO.cadastrarFuncionarioAoPontoDeVenda(dani, pontoDeVenda1);
//		pontoDeVendaDAO.cadastrarFuncionarioAoPontoDeVenda(dani, pontoDeVenda2);
//		pontoDeVendaDAO.cadastrarFuncionarioAoPontoDeVenda(dani, pontoDeVenda4);
		
		
		
		
//		Funcionario buscado = funcionarioDAO.buscaFuncionarioPeloCpf(12345678910l); //@DaniellaFarias
//		Funcionario buscado2 = funcionarioDAO.buscaFuncionarioPeloCpf(12332145689l);
//		
		
//		
//		PontoDeVenda pvBuscado = pontoDeVendaDAO.buscarPontoDeVenda(3L);
//		PontoDeVenda pvBuscado2 = pontoDeVendaDAO.buscarPontoDeVenda(1l);
////		
//		pontoDeVendaDAO.cadastrarFuncionarioAoPontoDeVenda(buscado, pvBuscado);
//		pontoDeVendaDAO.cadastrarFuncionarioAoPontoDeVenda(buscado2, pvBuscado2);
		
		
		
//		funcionarioDAO.removePeloCpf(98765432101l);
		
		em.getTransaction().commit();
		
		//flush commit rollback
		
	
		
		//PRODUTOS PV1
		
		//criarProdutosComPontoDeVenda(pv);
		//criarProdutosComPontoDeVenda(pv2);
		//criarProdutosComPontoDeVenda(pv3);
		//criarProdutosComPontoDeVenda(pv4);
		
		
		
		
		
		
		
	}
	
	public static void criarProdutosComPontoDeVenda(PontoDeVenda pv) {
		
		
		
		
	
	}
	
	
	
}

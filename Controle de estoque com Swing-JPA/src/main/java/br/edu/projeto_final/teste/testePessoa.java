package br.edu.projeto_final.teste;

import java.io.IOException;
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

public class testePessoa {
	
	public static void main(String[] args) throws IOException {
		
		EntityManager em = JPAutil.getEntityManager();
		
		
		PontoDeVendaDAO pontoDeVendaDAO = new PontoDeVendaDAO(em);
		FuncionarioDAO funcionarioDAO = new FuncionarioDAO(em);
		ProdutoDAO produtoDAO = new ProdutoDAO(em);
		
		
		//PONTO DE VENDA
		Endereco enderecoPontoDeVenda = new Endereco("53439370", "80", "D");
		Telefone telefonePontoDeVenda = new Telefone("81", "98080-8080");
		PontoDeVenda pv = new PontoDeVenda(enderecoPontoDeVenda, telefonePontoDeVenda, "pontodeVenda1@dominio.com");
		
		//PONTO DE VENDA
		Endereco enderecoPontoDeVenda2 = new Endereco("52580060", "110", "S/C");
		Telefone telefonePontoDeVenda2 = new Telefone("81", "98080-9090");
		PontoDeVenda pv2 = new PontoDeVenda(enderecoPontoDeVenda2, telefonePontoDeVenda2, "pontodeVenda2@dominio.com");
		
		//FUNCIONARIO
		Telefone telefone = new Telefone("81", "98080-8080");
		Endereco endereco = new Endereco("53250050", "80", "D");
		LocalDate dataNascimento = LocalDate.of(2000, 9, 5);
		Funcionario funcionario = new Funcionario("Jediel Santana", Sexo.MASCULINO, endereco, dataNascimento, "126377533485", telefone, "jediel@dominio.com", "@JedielSantana");
		
		//Produto
		
		PontoDeVenda pvbsc = em.find(PontoDeVenda.class, 2l);
		
		Produto produto = new Produto("Camiseta UNIDOS DO SAMBA", 7898080506044l, Categoria.Vestuario, Tipo.Camisa, Tamanho.P, Modelo.Masculino, Cor.Amarelo, 40, new BigDecimal("59.90"), pvbsc);
		
		em.getTransaction().begin();
		
		
		//produtoDAO.adicionaProdutoAoPontoDeVenda(produto, pvbsc);
		
		produtoDAO.remover(produto);
		
		
		
		
		//produtoDAO.removeProdutoDoPontoDeVenda(produto, pvbsc);
		
		
		//produtoDAO.removeProdutoDoPontoDeVenda(produto, pvbsc);
		
		//Produto produto2 = produtoDAO.buscarProdutoPorCodigoDeBarrasEPontoDeVenda(782383142550l, pvbsc);
		
		
		
		
		//Produto buscado = produtoDAO.buscarProdutoPorCodigoDeBarras(18383141550l);
		
		
		//pvbsc.removeProduto(buscado);
		
		
//		pv.adicionaFuncionario(funcionario);
//		pv.adicionaProduto(produto);
//		pv2.adicionaFuncionario(funcionario);
//		pv2.adicionaProduto(produto);
		
		
		
		
		
		//pontoDeVendaDAO.cadastrar(pv);
		//pontoDeVendaDAO.cadastrar(pv2);
		
//		funcionarioDAO.cadastrar(funcionario);
//		
		//produtoDAO.remover(produto);
		
//		
//		produtoDAO.cadastrar(produto);
//		produtoDAO.remover(produto);
		
		
		//produtoDAO.atualizar();		
		//Produto buscar = em.find(Produto.class, 12l);
		
		
		//produtoDAO.remover(buscar);
		//PontoDeVenda pvBsc = em.find(PontoDeVenda.class, 2l);
		
		//pvBsc.adicionaFuncionario(funcionario);		
		
		//pvBsc.adicionaProduto(produto);
		//pvBsc.removeProduto(produto);
		em.getTransaction().commit();
		
		
	}
	
	
}

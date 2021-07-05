package br.edu.projeto_final.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import br.edu.projeto_final.enums.Cor;
import br.edu.projeto_final.enums.Modelo;
import br.edu.projeto_final.enums.Tamanho;
import br.edu.projeto_final.exceptions.NaoEncontradoException;
import br.edu.projeto_final.exceptions.PontoDeVendaNuloException;
import br.edu.projeto_final.exceptions.ProdutoJaCadastradoException;
import br.edu.projeto_final.exceptions.EstoqueInsuficienteException;
import br.edu.projeto_final.modelo.PontoDeVenda;
import br.edu.projeto_final.modelo.Produto;



public class ProdutoDAO {
	
	
	private EntityManager em;
	
	public ProdutoDAO(EntityManager em) {
		this.em = em;
	}
	
	
	//ADICIONAR 
	
	public void adicionaProdutoAoPontoDeVenda(Produto produto, PontoDeVenda pv) {
		
		produto.setPontoDeVenda(pv);
		
		Long existe = existeProduto(produto);
		
		if(existe == -1) {
			
			pv.adicionaProduto(produto);
			
		} else {
			throw new ProdutoJaCadastradoException();
		}

	}
	
	public void cadastrar(Produto produto) {
		
		Long existe = existeProduto(produto);
		
		if(existe == -1) {
			em.persist(produto);
		} else {
			throw new ProdutoJaCadastradoException();
		} 
		
	}
	
	
	
	
	//REMOVER
	
	public void removeProdutoDoPontoDeVenda(Produto produto, PontoDeVenda pv) {
		produto.setPontoDeVenda(pv);
		
		Long existe = existeProduto(produto);
		
		if(existe != -1) {
			
			produto.setId(existe);
			pv.removeProduto(produto);	
			
		} else {
			throw new NaoEncontradoException("Produto não encontrado!");
		
		}
		
	}
	
	public void remover(Produto produto) {
		
		if(produto.getPontoDeVenda() == null) {
			throw new PontoDeVendaNuloException("Ponto de venda nulo!");
		} else {
			String jpql = "DELETE FROM Produto p WHERE p.codigoDeBarras = :codigoDeBarra AND p.tamanho = :tamanho AND p.cor = :cor AND p.modelo = :modelo AND p.pontoDeVenda.id = :id";
			
			produto.getPontoDeVenda().removeProduto(produto);	
			
			em.createQuery(jpql)
			.setParameter("codigoDeBarra", produto.getCodigoDeBarras())
			.setParameter("tamanho", produto.getTamanho())
			.setParameter("cor", produto.getCor())
			.setParameter("modelo", produto.getModelo())
			.setParameter("id", produto.getPontoDeVenda().getId())
			.executeUpdate();
			
		}
	}
	
        public void removerProdutoPorId(Long id){
            Produto produto = em.find(Produto.class, id);
            em.remove(produto);
        }
	
	//ATUALIZAR
	
	
	public void atualizar(Produto produto) {
            em.merge(produto);
	}
            
	
	//DAR BAIXA
        
        
        public void darBaixa(Long id, Integer qtd) throws EstoqueInsuficienteException {
            
            
            Produto produto = em.find(Produto.class, id);
            
            if(produto.getQuantidade() < qtd){
                throw new EstoqueInsuficienteException("ESTOQUE INSUFICIENTE!");
            }
            
            produto.setQuantidade(produto.getQuantidade() - qtd);
        }
	
	//Listagem

	public List<Produto> listarTodos(){
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> todosProdutosDeUmPontoDeVenda(String cep){
            String jpql = "SELECT p FROM Produto p JOIN PontoDeVenda pv ON p.pontoDeVenda = pv.id WHERE pv.endereco.CEP = :cep";
            
            return em.createQuery(jpql, Produto.class)
                    .setParameter("cep", cep)
                    .getResultList();
        }
	
	//METODOS BUSCA
	
	
	public Produto buscarProdutoPorPontoDeVenda(Produto produto, PontoDeVenda pv) {
		
		String jpql = "SELECT p FROM Produto p WHERE p.codigoDeBarras = :codigoDeBarra AND p.tamanho = :tamanho AND p.cor = :cor AND p.modelo = :modelo AND p.pontoDeVenda.id = :id";
		Produto produtoBuscado = null;
		try {
			produtoBuscado = em.createQuery(jpql, Produto.class).setParameter("codigoDeBarra", produto.getCodigoDeBarras())
			.setParameter("tamanho", produto.getTamanho())
			.setParameter("cor", produto.getCor())
			.setParameter("modelo", produto.getModelo())
			.setParameter("id", pv.getId())
			.getSingleResult();
			return produtoBuscado;
		} catch(NoResultException e) {
			throw new NaoEncontradoException("Produto n�o encontrado!");
		}
		
	}
	
        public Produto buscarProdutoPorId(Long id){
            return em.find(Produto.class, id);
        }
        
	public Produto buscarProduto(Produto produto) {
		String jpql = "SELECT p FROM Produto p WHERE p.codigoDeBarras = :codigoDeBarra AND p.tamanho = :tamanho AND p.cor = :cor AND p.modelo = :modelo AND p.pontoDeVenda.id = :id";
		Produto produtoBuscado = null;
		try {
			
			produtoBuscado = em.createQuery(jpql, Produto.class).setParameter("codigoDeBarra", produto.getCodigoDeBarras())
					.setParameter("tamanho", produto.getTamanho())
					.setParameter("cor", produto.getCor())
					.setParameter("modelo", produto.getModelo())
					.setParameter("id", produto.getPontoDeVenda().getId())
					.getSingleResult();
					return produtoBuscado;
		} catch (NoResultException e) {
			throw new NaoEncontradoException("Produto n�o encontrado!");
		}
		
	}
	
	
	public Produto buscaPorProduto(Long codigoDeBarra, Tamanho tamanho, Cor cor, Modelo modelo, PontoDeVenda pdv) {
		String jpql = "SELECT p FROM Produto p WHERE p.codigoDeBarras = :codigoDeBarra AND p.tamanho = :tamanho AND p.cor = :cor AND p.modelo = :modelo AND p.pontoDeVenda.id = :id";
		Produto produto = new Produto(codigoDeBarra, tamanho, cor, modelo, pdv);
		
		Produto produtoBuscado = null;
		try {
			
			produtoBuscado = em.createQuery(jpql, Produto.class)
					.setParameter("codigoDeBarra", produto.getCodigoDeBarras())
					.setParameter("tamanho", produto.getTamanho())
					.setParameter("cor", produto.getCor())
					.setParameter("modelo", produto.getModelo())
					.setParameter("id", pdv.getId())
					.getSingleResult();
					return produtoBuscado;
		} catch (NoResultException e) {
			throw new NaoEncontradoException("Produto n�o encontrado!");
		}
		
		
	}
	//EXISTE PRODUTO
	
        
        public boolean isCadastrado(Produto produto){
            
            Long existe = existeProduto(produto);
            
            if(existe != -1){
                return true;
            } else {
                return false;
            }
            
        }
        
	private Long existeProduto(Produto produto) {
		String jpql = "SELECT p FROM Produto p where p.codigoDeBarras = :codigo AND p.pontoDeVenda.id = :id AND p.modelo = :modelo AND p.cor = :cor AND p.tamanho = :tamanho AND p.tipo = :tipo";
		
		if(produto == null) {
			return -1l;
		}
		
		Produto produtoBanco;
		
		try {
			
			produtoBanco = em.createQuery(jpql, Produto.class)
					.setParameter("codigo", produto.getCodigoDeBarras())
					.setParameter("id", produto.getPontoDeVenda().getId())
					.setParameter("modelo", produto.getModelo())
					.setParameter("cor", produto.getCor())
					.setParameter("tamanho", produto.getTamanho())
					.setParameter("tipo", produto.getTipo())
					.getSingleResult();
			
			return produtoBanco.getId();
		
		} catch(NoResultException e) {
			
			return -1l;
		
		}
	}
	
}

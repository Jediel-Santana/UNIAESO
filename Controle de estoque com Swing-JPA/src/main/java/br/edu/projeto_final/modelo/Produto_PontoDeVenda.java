package br.edu.projeto_final.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Entity
//
//public class Produto_PontoDeVenda {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//	
//	private Long codigoDeBarras;
//	
//	private Integer quantidade;
//	
//	@ManyToOne
//	private Produto produto;
//	
//	@ManyToOne
//	private PontoDeVenda pontoDeVenda;
//	
//	
//	public Produto_PontoDeVenda() {
//	}
//	
//	public Produto_PontoDeVenda(Long codigoDeBarras, Integer quantidade, Produto produto, PontoDeVenda pontoDeVenda) {
//		this.codigoDeBarras = codigoDeBarras;
//		this.quantidade = quantidade;
//		this.produto = produto;
//		this.pontoDeVenda = pontoDeVenda;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Long getCodigoDeBarras() {
//		return codigoDeBarras;
//	}
//
//	public void setCodigoDeBarras(Long codigoDeBarras) {
//		this.codigoDeBarras = codigoDeBarras;
//	}
//
//	public Integer getQuantidade() {
//		return quantidade;
//	}
//
//	public void setQuantidade(Integer quantidade) {
//		this.quantidade = quantidade;
//	}
//
//	public Produto getProduto() {
//		return produto;
//	}
//
//	public void setProduto(Produto produto) {
//		this.produto = produto;
//	}
//
//	public PontoDeVenda getPontoDeVenda() {
//		return pontoDeVenda;
//	}
//
//	public void setPontoDeVenda(PontoDeVenda pontoDeVenda) {
//		this.pontoDeVenda = pontoDeVenda;
//	}
//	

	//pv
//	//	@OneToMany(mappedBy = "pontoDeVenda", cascade = CascadeType.ALL)
//	private List<Produto_PontoDeVenda> produto_PontoDeVenda = new ArrayList<Produto_PontoDeVenda>();
//	
//	
//	
//	
//	
//}

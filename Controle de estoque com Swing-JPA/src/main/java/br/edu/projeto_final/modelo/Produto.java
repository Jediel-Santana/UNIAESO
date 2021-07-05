package br.edu.projeto_final.modelo;

import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.sun.istack.NotNull;

import br.edu.projeto_final.enums.Categoria;
import br.edu.projeto_final.enums.Cor;
import br.edu.projeto_final.enums.Modelo;
import br.edu.projeto_final.enums.Tamanho;
import br.edu.projeto_final.enums.Tipo;


@Entity
@Table(name = "produtos")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String descricao;
	
	private Long codigoDeBarras;
	
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@Enumerated(EnumType.STRING)
	private Tamanho tamanho;
	
	@Enumerated(EnumType.STRING)
	private Modelo modelo;
	
	@Enumerated(EnumType.STRING)
	private Cor cor;
	
	private Integer quantidade;
	
	
	@ManyToOne
	private PontoDeVenda pontoDeVenda;
	
	@NotNull
	private BigDecimal preco;

	public Produto() {
	}

	public Produto(String descricao, Long codigoDeBarras, Categoria categoria, Tipo tipo, Tamanho tamanho,
			Modelo modelo, Cor cor, Integer quantidade, BigDecimal preco, PontoDeVenda pontoDeVenda) {
		this.descricao = descricao;
		this.codigoDeBarras = codigoDeBarras;
		this.categoria = categoria;
		this.tipo = tipo;
		this.tamanho = tamanho;
		this.modelo = modelo;
		this.cor = cor;
		this.quantidade = quantidade;
		this.preco = preco;
		this.pontoDeVenda = pontoDeVenda;
	}
	
	
	public Produto(Long codigoDeBarra, Tamanho tamanho, Cor cor, Modelo modelo, PontoDeVenda pdv) {
		this.codigoDeBarras = codigoDeBarra;
		this.tamanho = tamanho;
		this.cor = cor;
		this.modelo = modelo;
		this.pontoDeVenda = pdv;
	}
	
	public Long getCodigoDeBarras() {
		return codigoDeBarras;
	}

	public void setCodigoDeBarras(Long codigoDeBarras) {
		this.codigoDeBarras = codigoDeBarras;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Tamanho getTamanho() {
		return tamanho;
	}

	public void setTamanho(Tamanho tamanho) {
		this.tamanho = tamanho;
	}

	public Modelo getModelo() {
		return modelo;
	}

	public void setModelo(Modelo modelo) {
		this.modelo = modelo;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public PontoDeVenda getPontoDeVenda() {
		return pontoDeVenda;
	}

	public void setPontoDeVenda(PontoDeVenda pontoDeVenda) {
		this.pontoDeVenda = pontoDeVenda;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigoDeBarras == null) ? 0 : codigoDeBarras.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigoDeBarras == null) {
			if (other.codigoDeBarras != null)
				return false;
		} else if (!codigoDeBarras.equals(other.codigoDeBarras))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [id: " + id + ", descricao: " + descricao + ", codigoDeBarras: " + codigoDeBarras +  ", preco: " + preco + ", categoria: "
				+ categoria + ", tipo: " + tipo + ", tamanho: " + tamanho + ", modelo: " + modelo + ", cor: " + cor
				+ ", quantidade: " + quantidade + ", pontoDeVenda: " + pontoDeVenda + "]";
	}
	
	
	
}

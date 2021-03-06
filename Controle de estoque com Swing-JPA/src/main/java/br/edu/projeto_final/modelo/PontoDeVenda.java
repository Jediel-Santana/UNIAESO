package br.edu.projeto_final.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import br.edu.projeto_final.embutiveis.Endereco;
import br.edu.projeto_final.embutiveis.Telefone;
import javax.persistence.PreRemove;

@Entity
@Table(name = "pontos_de_vendas")
public class PontoDeVenda {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Embedded
    private Endereco endereco;
	
	@Embedded
	private Telefone telefone;
	
	private String email;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH }) 
	@JoinTable(name = "PontoDeVenda_Funcionarios", 
			joinColumns = @JoinColumn(name = "pontoDeVenda_id"),
			inverseJoinColumns = @JoinColumn(name = "funcionario_id")) 
	List<Funcionario> funcionarios = new ArrayList<Funcionario>();
	
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "pontoDeVenda", fetch = FetchType.LAZY)
	List<Produto> produtos = new ArrayList<Produto>();
	
	
	
	public PontoDeVenda() {
	}
	
	public PontoDeVenda(Endereco endereco, Telefone telefone, String email) {
		super();
		this.endereco = endereco;
		this.telefone = telefone;
		this.email = email;
	}
	
        
        @PreRemove
        public void preRemove(){
            produtos.removeAll(produtos);
            funcionarios.removeAll(funcionarios);
            
        }

        
        
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
        
        
        
        
	public void removerFuncionario(Funcionario funcionario) {
		funcionarios.remove(funcionario);
	}
	

	public void adicionaFuncionario(Funcionario funcionario) {
		
		boolean tem = funcionarios.contains(funcionario);
		
		if(!tem) {
			funcionarios.add(funcionario);
		}
		
		
	}
	
	

	public void adicionaProduto(Produto produto) {
		produto.setPontoDeVenda(this);
		produtos.add(produto);
	}
	
	public void removeProduto(Produto produto) {		
		produtos.remove(produto);
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}
	
	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public Long getId() {
		return id;
	}
	
	
	
	
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((endereco == null) ? 0 : endereco.hashCode());
		result = prime * result + ((funcionarios == null) ? 0 : funcionarios.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((produtos == null) ? 0 : produtos.hashCode());
		result = prime * result + ((telefone == null) ? 0 : telefone.hashCode());
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
		PontoDeVenda other = (PontoDeVenda) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (endereco == null) {
			if (other.endereco != null)
				return false;
		} else if (!endereco.equals(other.endereco))
			return false;
		if (funcionarios == null) {
			if (other.funcionarios != null)
				return false;
		} else if (!funcionarios.equals(other.funcionarios))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (produtos == null) {
			if (other.produtos != null)
				return false;
		} else if (!produtos.equals(other.produtos))
			return false;
		if (telefone == null) {
			if (other.telefone != null)
				return false;
		} else if (!telefone.equals(other.telefone))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "PontoDeVenda [id: " + id + ", endereco: " + endereco + ", telefone: " + telefone + ", email: " + email
				+ "]";
	}
	
	
	
}

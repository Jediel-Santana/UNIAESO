package edu.uniaeso.projeto.modelo;

import edu.uniaeso.projeto.embutiveis.Contato;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "empresas")
public class Empresa {
	
	
	private String nome;
	
	@Id
	private Long cnpj;
	
	
	@Embedded
	private Contato contato;
	
	private String email;
        
        @OneToMany(mappedBy = "empresa")
        private List<Ocorrencia> ocorrencias;
        
        
	public Empresa() {
	}
	
	public Empresa(String nome, Long cnpj, Contato contato, String email) {
		super();
		this.nome = nome;
		this.cnpj = cnpj;
		this.contato = new Contato(contato.getDdd(), contato.getNumero());
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getCnpj() {
		return cnpj;
	}

	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	public Contato getContato() {
		return contato;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getId() {
		return this.cnpj;
	}
	
	
	
}

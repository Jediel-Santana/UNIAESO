package edu.uniaeso.projeto.modelo;

import edu.uniaeso.projeto.embutiveis.Contato;
import edu.uniaeso.projeto.embutiveis.Sexo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
	
	
	@Id
	private Long cpf;

	private String nome;

	private LocalDate dataDeNascimento;

	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	
	@Embedded
	private Contato contato;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Ocorrencia> ocorrencias = new ArrayList<Ocorrencia>();
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Login login;
	
	public Usuario() {
	}

	public Usuario(String nome, Long cpf, LocalDate dataDeNascimento, Sexo sexo, Contato contato, Login login) {
		this.nome = nome;
		this.cpf = cpf;
		this.dataDeNascimento = dataDeNascimento;
		this.sexo = sexo;
		this.contato = contato;
		this.login = login;
		this.login.setUsuario(this);
	}

	public Long getCpf() {
		return cpf;
	}
	
	public void adicionarOcorrencia(Ocorrencia ocorrencia) {
		ocorrencias.add(ocorrencia);
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

	public LocalDate getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(LocalDate dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public List<Ocorrencia> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<Ocorrencia> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	@Override
	public String toString() {
		return "Usuario [nome: " + nome + ", cpf: " + cpf + ", dataDeNascimento: " + dataDeNascimento + ", sexo: " + sexo
				+ ", contato: " + contato + "]";
	}
	
	
}

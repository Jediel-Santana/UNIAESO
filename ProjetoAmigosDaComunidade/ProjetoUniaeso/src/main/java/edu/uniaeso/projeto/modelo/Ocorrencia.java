package edu.uniaeso.projeto.modelo;

import edu.uniaeso.projeto.embutiveis.Endereco;
import edu.uniaeso.projeto.embutiveis.Status;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import edu.uniaeso.projeto.util.QuantidadeDeOcorrencias;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "ocorrencias")
public class Ocorrencia {
	
	

	
	
	@Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigoOcorrencia;
	
	private LocalDate dataAbertura = LocalDate.now();
	
	@Embedded
	private Endereco endereco;
	
	@Enumerated(EnumType.STRING)
	private Status status = Status.EM_ANALISE;
	
	private LocalDate dataDaConclusao;
	
	private String descricao;
	
	private String documentos_anexos;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Empresa empresa;
	
	public Ocorrencia() {
	}

	public Ocorrencia(Endereco endereco, String descricao, String documentos_anexos, Usuario usuario, Empresa empresa) {
		
		
		this.endereco = endereco;
		this.descricao = descricao;
		this.documentos_anexos = documentos_anexos;
		this.usuario = usuario;
		this.empresa = empresa;
		
	}

	public Long getCodigoOcorrencia() {
		return codigoOcorrencia;
	}
	
	public void setCodigoOcorrencia(Long codigoOcorrencia) {
		this.codigoOcorrencia = codigoOcorrencia;
	}


	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public LocalDate getDataDaConclusao() {
		return dataDaConclusao;
	}

	public void setDataDaConclusao(LocalDate dataDaConclusao) {
		this.dataDaConclusao = dataDaConclusao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDocumentos_anexos() {
		return documentos_anexos;
	}

	public void setDocumentos_anexos(String documentos_anexos) {
		this.documentos_anexos = documentos_anexos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Long getId() {
		return this.codigoOcorrencia;
	}
	
	@Override
	public String toString() {
		return '[' + "ID_OCORRÊNCIA: " + codigoOcorrencia + ']';
	}
	
	
}

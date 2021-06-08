package edu.uniaeso.projeto.embutiveis;

import javax.persistence.Embeddable;

@Embeddable
public class Endereco {
	
	private String ruaOuAvenida;
	private String pontoDeReferencia;
	private Integer CEP;
	
	public Endereco() {
	}
	
	public Endereco(String ruaOuAvenida, String pontoDeReferencia, Integer cep) {
		this.ruaOuAvenida = ruaOuAvenida;
		this.pontoDeReferencia = pontoDeReferencia;
		this.CEP = cep;
	}
	
	public Endereco(String ruaOuAvenida, String pontoDeReferencia) {
		this.ruaOuAvenida = ruaOuAvenida;
		this.pontoDeReferencia = pontoDeReferencia;
	}
	

	public String getRuaOuAvenida() {
		return ruaOuAvenida;
	}

	public String getPontoDeReferencia() {
		return pontoDeReferencia;
	}

	public Integer getCEP() {
		return CEP;
	}
	
	
	
	
	


}

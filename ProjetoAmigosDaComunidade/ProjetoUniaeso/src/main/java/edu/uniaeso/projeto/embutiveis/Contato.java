package edu.uniaeso.projeto.embutiveis;

import javax.persistence.Embeddable;

@Embeddable
public class Contato {
	
	private Integer ddd;
	private Long numero;
	
	
	public Contato() {
	}

	public Contato(int ddd, Long numero) {
		this.ddd = ddd;
		this.numero = numero;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		return '[' + "(" + this.ddd + ")" + ' ' + this.numero  + ']';
	}
	
}

package br.edu.projeto_final.exceptions;

public class ProdutoJaCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = -7812886302930320466L;

	public ProdutoJaCadastradoException() {
		super("Produto ja cadastrado!");
	}
	
}

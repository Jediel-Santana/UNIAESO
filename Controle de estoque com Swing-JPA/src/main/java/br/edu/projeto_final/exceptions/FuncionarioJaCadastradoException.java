package br.edu.projeto_final.exceptions;

public class FuncionarioJaCadastradoException extends RuntimeException {
	
	private static final long serialVersionUID = -7812886302930320466L;

	public FuncionarioJaCadastradoException() {
		super("Funcionario ja cadastrado!");
	}
	
}

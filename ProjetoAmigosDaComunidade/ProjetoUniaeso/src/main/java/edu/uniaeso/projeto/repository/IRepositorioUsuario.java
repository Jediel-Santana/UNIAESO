package edu.uniaeso.projeto.repository;

import edu.uniaeso.projeto.modelo.Usuario;

public interface IRepositorioUsuario {
	
	
	public boolean cadastrar(Usuario usuario);
	
	public boolean remover(Usuario usuario);
	
	public boolean atualizar(Usuario usuario);
	
	public Usuario buscarUsuarioPorCpf(Long cpf);	
}

package edu.uniaeso.projeto.controller;

import javax.persistence.EntityManager;

import edu.uniaeso.projeto.dao.UsuarioDAO;
import edu.uniaeso.projeto.modelo.Usuario;
import edu.uniaeso.projeto.repository.IRepositorioUsuario;

public class ControllerUsuario {
		
	private UsuarioDAO usuarioDAO;
	
	
	public ControllerUsuario(EntityManager em) {
		usuarioDAO = new UsuarioDAO(em);
	}
	
	public void setEntityManager(EntityManager em) {
		//usuarioDAO.setEntityManager(em);
	}
	
	
	public boolean cadastrar(Usuario usuario) {
		return usuarioDAO.cadastrar(usuario);
	}
	public boolean remover(Usuario usuario) {
		return usuarioDAO.remover(usuario);
	}
	
	public boolean atualizar(Usuario usuario) {
		return usuarioDAO.atualizar(usuario);
	}
	
	public Usuario buscarUsuarioPorCpf(Long cpf) {
		return usuarioDAO.buscarUsuarioPorCpf(cpf);
	}
	
        
        public boolean isUsuarioCadastrado(Long cpf){
            
            return usuarioDAO.isUsuarioCadastrado(cpf);
            
        }
        
        
}

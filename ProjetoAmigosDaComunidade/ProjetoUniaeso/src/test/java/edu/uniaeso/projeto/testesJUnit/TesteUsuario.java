package edu.uniaeso.projeto.testesJUnit;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import edu.uniaeso.projeto.dao.UsuarioDAO;
import edu.uniaeso.projeto.dao.UsuarioDAO;
import edu.uniaeso.projeto.util.JPAutil;

public class TesteUsuario {

	
	private static EntityManager em;
	private UsuarioDAO usuarioDAO;
	
	
	
	@BeforeAll
	public void before() {
		em = JPAutil.getEntityManager();
		usuarioDAO = new UsuarioDAO(em);
	}
	
	//testes
	
	public void testaCadastroUsuario() {
		
		
		
		
	}
	
	
	
	@AfterAll
	public void after() {
		
	}
	
}

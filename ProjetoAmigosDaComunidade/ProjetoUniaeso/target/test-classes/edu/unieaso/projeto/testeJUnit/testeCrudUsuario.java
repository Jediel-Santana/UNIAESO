package edu.unieaso.projeto.testeJUnit;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import edu.uniaeso.projeto.dao.UsuarioDAO;
import edu.uniaeso.projeto.modelo.Sexo;
import edu.uniaeso.projeto.util.JPAutil;

public class testeCrudUsuario {
	
	
	private static EntityManager em;
	private UsuarioDAO dao;
	
	@BeforeAll
	public void before() {
		
		em = JPAutil.getEntityManager();
		dao = new UsuarioDAO(em);
		em.getTransaction().begin();
	}
	
	
	public void cadastrarUsuario() {
		
		Contato contatouser1 = new Contato(81,981710788l);
		Login login1 = new Login("jediel@hotmail.com", "123456");
		Usuario user = new Usuario("Jediel Santana", 12345678901l, LocalDate.of(1999, 9, 05), Sexo.Masculino, contatouser1, login1);
		
		
		dao.cadastrar(user);
		
		
	}
	
	
	
	
	
	
	@AfterAll
	public void after(){{
		
		em.getTransaction().rollback();
		em.close();
	}
	
	
	
}

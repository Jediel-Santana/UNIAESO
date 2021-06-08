package edu.uniaeso.projeto.testesJUnit;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import edu.uniaeso.projeto.dao.EmpresaDAO;
import edu.uniaeso.projeto.util.JPAutil;

public class TesteEmpresa {

	
	private static EntityManager em;
	private EmpresaDAO empresa;
	
	
	
	@BeforeAll
	public void before() {
		em = JPAutil.getEntityManager();
		empresa = new EmpresaDAO(em);
	}
	
	//testes
	
	
	
	
	
	@AfterAll
	public void after() {
		
	}
	
}

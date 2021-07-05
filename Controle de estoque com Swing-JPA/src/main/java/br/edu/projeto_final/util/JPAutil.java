package br.edu.projeto_final.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAutil {
		
	//teste
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("ProjetoRenato");
	
	
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	
}

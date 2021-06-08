package edu.uniaeso.projeto.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAutil {
	
	
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("projeto");
	
	
	public static EntityManager getEntityManager() {
		
		EntityManager em = emf.createEntityManager();
	
		return em;
	}
	
	
        
        public static void closeEMF(){
            emf.close();
        }
        
        
       
        
        
}

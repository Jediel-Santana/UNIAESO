/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniaeso.projeto.dao;

import edu.uniaeso.projeto.exceptions.NaoEncontrado;
import edu.uniaeso.projeto.modelo.Login;
import edu.uniaeso.projeto.modelo.Usuario;
import edu.uniaeso.projeto.util.QuantidadeDeOcorrencias;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author Jediel
 */
public class LoginDAO {
    
    private EntityManager em;
    
    
    public LoginDAO(EntityManager em) {
		this.em = em;
	
	}
	
    public void setEntityManager(EntityManager em) {
            if(em.isOpen()) {
                    em.close();
            }

            this.em = em;
    }
    
    
    public Usuario existeLogin(String email, String senha) throws NaoEncontrado{
        String jpql = "SELECT u FROM Usuario u JOIN Login l ON l.id = u.login.id WHERE l.email = :email AND l.senha = :senha";

        try{
            Usuario usuario = em.createQuery(jpql, Usuario.class)
                    .setParameter("email", email)
                    .setParameter("senha", senha)
                    .getSingleResult();
                    return usuario;
        
        } catch(NoResultException e){            
            throw new NaoEncontrado("Usuário não encontrado!");
        }
    }
    
    public boolean isEmailCadastrado(String email){
        String jpql = "SELECT l FROM Login l WHERE l.email = :email";
        
        try{
            Login login = em.createQuery(jpql, Login.class)
                    .setParameter("email", email)
                    .getSingleResult();
            if (login != null) return true;
            else return false;
            
        } catch(NoResultException e){
            return false;
        }
        
        
    }
        
    
    
}

package edu.uniaeso.projeto.dao;

import javax.persistence.EntityManager;

import edu.uniaeso.projeto.modelo.Usuario;
import edu.uniaeso.projeto.repository.IRepositorioUsuario;
import javax.persistence.NoResultException;

public class UsuarioDAO implements IRepositorioUsuario {

	
	private EntityManager em;
	
	public UsuarioDAO(EntityManager em) {
		this.em = em;
	}
	
	
	@Override
	public boolean cadastrar(Usuario usuario) {
			
			if(usuario != null) {
				em.persist(usuario);
				return true;
			} else {
				return false;
			}
			
		}


	@Override
	public boolean remover(Usuario usuario) {
		
			if(usuario != null) {
				em.remove(usuario);
				return true;
			} else {
				return false;
			}
		}


	@Override
	public boolean atualizar(Usuario usuario) {
			
			if(usuario != null) {
				em.merge(usuario);
				return true;
			} else {
				return false;
			}
		
		}


	@Override
	public Usuario buscarUsuarioPorCpf(Long cpf) {
		return em.find(Usuario.class, cpf);
	}

        public boolean isUsuarioCadastrado(Long cpf) {

            try{
                Usuario usuario = em.find(Usuario.class, cpf);

                if (usuario != null) return true;
                else return false;

            } catch(NoResultException e){
                return false;
            }

        
    }
	
	
        
        
        
}

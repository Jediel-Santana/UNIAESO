package br.edu.projeto_final.dao;

import br.edu.projeto_final.exceptions.FuncionarioJaCadastradoException;
import br.edu.projeto_final.exceptions.NaoEncontradoException;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.projeto_final.modelo.Funcionario;
import br.edu.projeto_final.modelo.PontoDeVenda;
import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;



public class FuncionarioDAO {
	
	
	private EntityManager em;
	
	public FuncionarioDAO(EntityManager em) {
		this.em = em;
	}
	
        
        
        
        
        //cadastrar 
	public void cadastrarFuncionarioAoPontoDeVenda(Funcionario funcionario, PontoDeVenda pv) {
            
            
            boolean isCadastrado = isCadastrado(funcionario.getCpf());
            
            try{
            if(funcionario != null && !isCadastrado) {
			em.persist(funcionario);
                        pv = em.merge(pv);
                        pv.adicionaFuncionario(funcionario);
            }
            }  catch(Exception e){
                throw new FuncionarioJaCadastradoException();
            }
            
            if(isCadastrado){
                throw new FuncionarioJaCadastradoException();
            }
        }
	
        
        //atualizar
	public void atualizar(Funcionario funcionario) {
		
		if(funcionario != null) {
//                        em.find(Funcionario.class, funcionario.getCpf());
			em.merge(funcionario);
		}
	
	}
	
	
        
        //buscar
	public Funcionario buscaFuncionarioPeloCpf(Long cpf) {
            Funcionario funcionario = em.find(Funcionario.class, cpf);
            
            if(funcionario == null){
                 throw new NaoEncontradoException("Funcionario nao encontrado!");
            }
            
            return funcionario;
            
        }
	
        public boolean isCadastrado(Long cpf){
            
            String jpql = "SELECT f FROM Funcionario f WHERE f.cpf = :cpf";
            
            try{
                Funcionario funcionario = em.createQuery(jpql, Funcionario.class)
                        .setParameter("cpf", cpf)
                        .getSingleResult();
                
                if(funcionario != null) return true;
                else return false;
                
            } catch(NoResultException e){
                return false;
            }
        }
	
	
	//remover
	public void removePeloCpf(Long cpf) {
                try{
                    Funcionario funcionario = em.find(Funcionario.class, cpf);
                    em.remove(funcionario);
                
                } catch(Exception e){
                    e.printStackTrace();
                }
	
                
        }
        
        
        public void remover(Funcionario funcionario){
            em.remove(funcionario);
        }
	
        
        
        //listar
	public List<Funcionario> todosFuncionarios(){
		String jpql = "SELECT f FROM Funcionario f";
		return em.createQuery(jpql, Funcionario.class).getResultList();
	}
	
        public List<PontoDeVenda> todosOsPontosDeVendaDeUmFuncionario(Funcionario funcionario){
            
           return em.find(Funcionario.class, funcionario.getCpf()).getPontosDeVenda();
            
        }
	
        public List<Funcionario> buscarTodosFuncionariosDeUmPontoPorCpf(String cep){
            
            String jpql = "SELECT f FROM Funcionario f JOIN FETCH f.pontosDeVenda pv WHERE pv.endereco.CEP = :cep";
            
            
            return em.createQuery(jpql, Funcionario.class)
                    .setParameter("cep", cep)
                    .getResultList();
            
        }
        
        
	
}

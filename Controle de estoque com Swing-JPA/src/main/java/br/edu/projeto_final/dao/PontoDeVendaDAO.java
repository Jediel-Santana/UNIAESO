package br.edu.projeto_final.dao;

import br.edu.projeto_final.exceptions.NaoEncontradoException;
import java.util.List;

import javax.persistence.EntityManager;

import br.edu.projeto_final.modelo.Funcionario;
import br.edu.projeto_final.modelo.PontoDeVenda;
import br.edu.projeto_final.vo.PontoDeVendaVo;
import javax.persistence.NoResultException;



public class PontoDeVendaDAO {
	
	
	private EntityManager em;
	
	public PontoDeVendaDAO(EntityManager em) {
		this.em = em;
	}
	
	public void cadastrar(PontoDeVenda pdv) {
		if(pdv != null) {
			em.persist(pdv);
		}
	}
	
	public void cadastrarFuncionarioAoPontoDeVenda(Funcionario funcionario, PontoDeVenda pv) {
            
           
            
            if(pv.getId() != null) {;
                    pv = em.merge(pv);
                    pv.adicionaFuncionario(funcionario);
		} else {
			throw new RuntimeException("ID do ponto de venda é nulo!");
		}
	}
	
	public void removerFuncionarioDoPontoDeVenda(Funcionario funcionario, PontoDeVenda pv) {
		pv.removerFuncionario(funcionario);
	}
	
        
        public void removerPorId(Long id){
            PontoDeVenda pv = em.find(PontoDeVenda.class, id);
            em.remove(pv);
        }
	
	public void atualizarPontoDeVenda(PontoDeVenda pv){ // P / XG TRANSIENT // MANAGED // DETACHED // REMOVED
            
            
            if(pv != null) {
			
                PontoDeVenda pvAt = em.find(PontoDeVenda.class, pv.getId());
                pv.setFuncionarios(pvAt.getFuncionarios());
                pv.setProdutos(pvAt.getProdutos());
                em.merge(pv);
		}
	}
	
	
	public PontoDeVenda buscarPontoDeVenda(Long id) {
		return em.find(PontoDeVenda.class, id);
	}
       
        public PontoDeVenda buscarPontoDeVendaPorCEP(String cep){
            String jpql = "SELECT p FROM PontoDeVenda p WHERE p.endereco.CEP = :cep";
            try{
                System.out.println("Buscando o cep: " + cep);
                return em.createQuery(jpql, PontoDeVenda.class)
                        .setParameter("cep", cep)
                        .getSingleResult();
            } catch(NoResultException e){
                throw new NaoEncontradoException("Ponto de Venda não encontrado!");
            }
        }
        
	
	
	public List<PontoDeVenda> listarTodos(){
		
		String jpql = "SELECT p FROM PontoDeVenda p";
		return em.createQuery(jpql, PontoDeVenda.class).getResultList();
	}
	
        
        public List<PontoDeVendaVo> BairroDosPontosDeVenda(){
            
            String jpql = "SELECT new br.edu.projeto_final.vo.PontoDeVendaVo (p.id, p.endereco.Bairro, p.endereco.CEP) FROM PontoDeVenda p";
            return em.createQuery(jpql, PontoDeVendaVo.class).getResultList();
        }

    public boolean isCadastrado(String cep) {
        
        String jpql = "SELECT p FROM PontoDeVenda p WHERE p.endereco.CEP = :cep";
        
        try{
            PontoDeVenda pontoDeVenda = em.createQuery(jpql, PontoDeVenda.class)
                    .setParameter("cep", cep)
                    .getSingleResult();
            if(pontoDeVenda != null) return true;
            else return false;
        
        } catch(NoResultException e){
            return false;
        }
    }
}

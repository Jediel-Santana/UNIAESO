package edu.uniaeso.projeto.dao;

import edu.uniaeso.projeto.Vo.TodasEmpresasVo;
import java.util.List;

import javax.persistence.EntityManager;

import edu.uniaeso.projeto.modelo.Empresa;
import edu.uniaeso.projeto.modelo.Ocorrencia;
import edu.uniaeso.projeto.modelo.Usuario;
import edu.uniaeso.projeto.repository.IRepositorioEmpresa;
import javax.persistence.criteria.CriteriaBuilder;

public class EmpresaDAO implements IRepositorioEmpresa {

	
	private EntityManager em;
	
	
	public EmpresaDAO(EntityManager em) {
		this.em = em;
	}
	
        public void setEntityManager(EntityManager em){
            this.em = em;
        }
	
	@Override
	public boolean cadastrar(Empresa empresa) {
			System.out.println("persistindo");
			if(empresa != null) {
				em.persist(empresa);
				return true;
			} else {
				return false;
			}
			
		}


	@Override
	public boolean remover(Empresa empresa) {
		
			if(empresa != null) {
				em.remove(empresa);
				return true;
			} else {
				return false;
			}
		}


	@Override
	public boolean atualizar(Empresa empresa) {
			
			if(empresa != null) {
				em.merge(empresa);
				return true;
			} else {
				return false;
			}
		
		}


	@Override
	public List<Empresa> todasEmpresas() {
		String jpql = "SELECT E from Empresa E";
		return em.createQuery(jpql, Empresa.class).getResultList();
	}

	@Override
	public Empresa buscaEmpresaPeloCnpj(Long cnpj) {
		return em.find(Empresa.class, cnpj);
	}
	
        public Long quantidadeDeEmpresas(){
            String jpql = "SELECT COUNT(e) FROM Empresa e";
            Long quantidade = em.createQuery(jpql, Long.class).getSingleResult();
            return quantidade;
        }
	
        public List<TodasEmpresasVo> TodasEmpresasComQuantidadeDeOcorrencias(){
            
            String jpql = "SELECT new edu.uniaeso.projeto.Vo.TodasEmpresasVo(e.nome, e.cnpj, e.email, COUNT(ocorrencia.codigoOcorrencia)) FROM Empresa e "
                    + "LEFT JOIN "
                    + "e.ocorrencias ocorrencia "
                    + "GROUP BY e.nome";
            
           
             
            return em.createQuery(jpql, TodasEmpresasVo.class).getResultList();
        }
        
        
        
	
}

package edu.uniaeso.projeto.dao;

import edu.uniaeso.projeto.Vo.OcorrenciaDoUsuarioVo;
import edu.uniaeso.projeto.Vo.OcorrenciaTabelaVo;
import java.util.List;

import javax.persistence.EntityManager;

import edu.uniaeso.projeto.modelo.Empresa;
import edu.uniaeso.projeto.modelo.Ocorrencia;
import edu.uniaeso.projeto.modelo.Usuario;
import edu.uniaeso.projeto.repository.IRepositorioOcorrencia;
import edu.uniaeso.projeto.util.QuantidadeDeOcorrencias;

public class OcorrenciaDAO implements IRepositorioOcorrencia {

	
	private EntityManager em;
	
	
	public OcorrenciaDAO(EntityManager em) {
		this.em = em;
		//Long quantidade = BuscaPorQuantidadeDeOcorrencias();
		//QuantidadeDeOcorrencias.setQuantidadeDeOcorrencias(quantidade);
	
	}
	
	public void setEntityManager(EntityManager em) {
		if(em.isOpen()) {
			em.close();
		}
		
		this.em = em;
	}
		

	@Override
	public boolean cadastrar(Ocorrencia ocorrencia) {
			
			if(ocorrencia != null) {
				em.persist(ocorrencia);
				return true;
			} else {
				return false;
			}
			
			
		}


	@Override
	public boolean remover(Ocorrencia ocorrencia) {
		
            if(ocorrencia != null) {
                    ocorrencia = em.merge(ocorrencia);
                    em.remove(ocorrencia);
                    return true;
            } else {
                    return false;
            }
    }
	
        
        
        
	public void removerOcorrenciaPorId(Long id) {
		Ocorrencia ocorrencia = em.find(Ocorrencia.class, id);
		em.remove(ocorrencia);
	}

	@Override
	public boolean atualizar(Ocorrencia ocorrencia) {
			
			if(ocorrencia != null) {
				em.merge(ocorrencia);
				return true;
			} else {
				return false;
			}
		
		}
        
        public Ocorrencia buscarOcorrenciaPorCodigo(Long codigo){
            return em.find(Ocorrencia.class, codigo);
        }
        
        
	@Override
	public List<Ocorrencia> buscarPorOcorrenciasDoUsuario(Usuario usuario) {
            String jpql = "SELECT o FROM Usuario u JOIN Ocorrencia o ON o.usuario = u.cpf";
            
            return em.createQuery(jpql, Ocorrencia.class).getResultList();
	}


	@Override
	public List<Ocorrencia> todasOcorrencias() {
            String jpql = "SELECT o FROM Ocorrencia o";
            
            return em.createQuery(jpql, Ocorrencia.class).getResultList();
	}


	@Override
	public List<Ocorrencia> todasOcorrenciasDaEmpresa(Empresa empresa) {
		return null;
	}
	
	private Long BuscaPorQuantidadeDeOcorrencias() {
		String jpql = "SELECT COUNT(O) FROM Ocorrencia O";
		Long resultado = em.createQuery(jpql, Long.class).getSingleResult();
		return resultado;
	}
        
        
        public List<OcorrenciaTabelaVo> todasOcorrenciasVo(){
            //(Long codigoOcorrencia, LocalDate dataAbertura, LocalDate dataConclusao, Status status, String descricao, String RuaOuAvenida, String nomeDaEmpresa)
            String jpql = "SELECT new edu.uniaeso.projeto.Vo.OcorrenciaTabelaVo(o.codigoOcorrencia, o.dataAbertura, o.status, o.descricao, o.endereco.ruaOuAvenida, e.nome) "
                    + "FROM Ocorrencia o JOIN o.empresa e ON o.empresa = e.cnpj";
            return em.createQuery(jpql, OcorrenciaTabelaVo.class).getResultList();
        }
        
        
        public List<OcorrenciaDoUsuarioVo> todasOcorrenciasDoUsuarioVo(Long cpf){
            
            //Long codOcorrencia, LocalDate dataAbertura, Status status, String descricao, Integer CEP, String ruaOuAvenida, String pontoDeReferencia, String nomeEmpresa
            String jpql = "SELECT new edu.uniaeso.projeto.Vo.OcorrenciaDoUsuarioVo(o.codigoOcorrencia, o.dataAbertura, o.status, o.descricao, o.endereco.CEP, o.endereco.ruaOuAvenida, o.endereco.pontoDeReferencia, e.nome) "
                    + "FROM Ocorrencia o JOIN o.empresa e ON o.empresa = e.cnpj WHERE o.usuario.cpf = :cpf";
            
            return em.createQuery(jpql, OcorrenciaDoUsuarioVo.class)
                    .setParameter("cpf", cpf)
                    .getResultList();
        } 
        
        
	
        
}

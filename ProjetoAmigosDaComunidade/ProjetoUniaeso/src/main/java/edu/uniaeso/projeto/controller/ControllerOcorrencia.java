package edu.uniaeso.projeto.controller;

import edu.uniaeso.projeto.Vo.OcorrenciaDoUsuarioVo;
import edu.uniaeso.projeto.Vo.OcorrenciaTabelaVo;
import java.util.List;

import javax.persistence.EntityManager;

import edu.uniaeso.projeto.dao.OcorrenciaDAO;
import edu.uniaeso.projeto.modelo.Empresa;
import edu.uniaeso.projeto.modelo.Ocorrencia;
import edu.uniaeso.projeto.modelo.Usuario;

public class ControllerOcorrencia {
		
	private OcorrenciaDAO ocorrenciaDAO;
	
	
	public ControllerOcorrencia(EntityManager em) {
		ocorrenciaDAO = new OcorrenciaDAO(em);
	}
	
	public void setEntityManager(EntityManager em) {
		ocorrenciaDAO.setEntityManager(em);
	}
	
	public boolean cadastrar(Ocorrencia ocorrencia) {
		return ocorrenciaDAO.cadastrar(ocorrencia);
	}
	public boolean remover(Ocorrencia ocorrencia) {
		return ocorrenciaDAO.remover(ocorrencia);
	}
	
        public void removerOcorrenciaPorId(Long id){
            ocorrenciaDAO.removerOcorrenciaPorId(id);
        }
        
	public boolean atualizar(Ocorrencia ocorrencia) {
		return ocorrenciaDAO.atualizar(ocorrencia);
	}
	
	public List<Ocorrencia> buscarPorOcorrenciasDoUsuario(Usuario usuario) {
		return ocorrenciaDAO.buscarPorOcorrenciasDoUsuario(usuario);
	}
        
        
        public Ocorrencia buscarOcorrenciaPorCodigo(Long codigo){
            return ocorrenciaDAO.buscarOcorrenciaPorCodigo(codigo);
        }

	public List<Ocorrencia> todasOcorrencias() {
		return ocorrenciaDAO.todasOcorrencias();
	}


	public List<Ocorrencia> todasOcorrenciasDaEmpresa(Empresa empresa) {
		return ocorrenciaDAO.todasOcorrenciasDaEmpresa(empresa);
	}
	
	public List<OcorrenciaTabelaVo> todasOcorrenciasVo(){
            return ocorrenciaDAO.todasOcorrenciasVo();
        }
        public List<OcorrenciaDoUsuarioVo> todasOcorrenciasDoUsuarioVo(Long cpf){
            return ocorrenciaDAO.todasOcorrenciasDoUsuarioVo(cpf);
        }
        
}

package edu.uniaeso.projeto.controller;

import edu.uniaeso.projeto.Vo.TodasEmpresasVo;
import java.util.List;

import javax.persistence.EntityManager;

import edu.uniaeso.projeto.dao.EmpresaDAO;
import edu.uniaeso.projeto.modelo.Empresa;

public class ControllerEmpresa {
		
	private EmpresaDAO empresaDAO;
	
	
	public ControllerEmpresa(EntityManager em) {
		empresaDAO = new EmpresaDAO(em);
	}
	
	public void setEntityManager(EntityManager em) {
		empresaDAO.setEntityManager(em);
	}
	
	public boolean cadastrar(Empresa empresa) {
		return empresaDAO.cadastrar(empresa);
	}


	public boolean remover(Empresa empresa) {
		return empresaDAO.remover(empresa);
	}
	
	public boolean atualizar(Empresa empresa) {
		return empresaDAO.atualizar(empresa);
	}
	
	
	public List<Empresa> todasEmpresas(){
		return empresaDAO.todasEmpresas();
	}
	
	public Empresa buscaEmpresaPeloCnpj(Long cnpj) {
		return empresaDAO.buscaEmpresaPeloCnpj(cnpj);
	}
        
        public Long quantidadeDeEmpresas(){
            return empresaDAO.quantidadeDeEmpresas();
        }
        
        public List<TodasEmpresasVo> TodasEmpresasComQuantidadeDeOcorrencias(){
            return empresaDAO.TodasEmpresasComQuantidadeDeOcorrencias();
        }
        
        
        

}

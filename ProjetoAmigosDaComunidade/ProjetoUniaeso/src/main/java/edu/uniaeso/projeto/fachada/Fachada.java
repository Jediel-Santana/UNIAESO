package edu.uniaeso.projeto.fachada;

import edu.uniaeso.projeto.Vo.OcorrenciaDoUsuarioVo;
import edu.uniaeso.projeto.Vo.OcorrenciaTabelaVo;
import edu.uniaeso.projeto.Vo.TodasEmpresasVo;
import java.util.List;

import javax.persistence.EntityManager;


import edu.uniaeso.projeto.controller.ControllerEmpresa;
import edu.uniaeso.projeto.controller.ControllerOcorrencia;
import edu.uniaeso.projeto.controller.ControllerUsuario;
import edu.uniaeso.projeto.modelo.Empresa;
import edu.uniaeso.projeto.modelo.Ocorrencia;
import edu.uniaeso.projeto.modelo.Usuario;

public class Fachada {
	
	
	private static Fachada fachada = new Fachada();
	
	
	private static EntityManager em = null;
	private static ControllerEmpresa controllerEmpresa;
	private static ControllerUsuario controllerUsuario;
	private static ControllerOcorrencia controllerOcorrencia;
        
	
	
	private Fachada() {
		
	}
	
	public static Fachada getFachadaInstance(EntityManager entityM) {	
            em = entityM;
            controllerEmpresa = new ControllerEmpresa(em);
            controllerUsuario =  new ControllerUsuario(em);
            controllerOcorrencia =   new ControllerOcorrencia(em);
            
            
            return fachada;
	}
	

	public void closeEntityManager() {
		
	}
	
	public EntityManager getEntityManager() {
		return this.em;
	}
	
	
	//EMPRESA
	
	public boolean cadastrarEmpresa(Empresa empresa) {
		return controllerEmpresa.cadastrar(empresa);
	}
	
	public boolean atualizarEmpresa(Empresa empresa) {
		return controllerEmpresa.atualizar(empresa);
	}
	
	public boolean removerEmpresa(Empresa empresa) {
		return controllerEmpresa.remover(empresa);
	}
	
	public List<Empresa> todasEmpresas(){
		return controllerEmpresa.todasEmpresas();
	}
	
	public Empresa buscaEmpresaPeloCnpj(Long cnpj) {
		return controllerEmpresa.buscaEmpresaPeloCnpj(cnpj);
	}
        public Long quantidadeDeEmpresas(){
            return controllerEmpresa.quantidadeDeEmpresas();
        }
	
        public List<TodasEmpresasVo> TodasEmpresasComQuantidadeDeOcorrencias(){
            return controllerEmpresa.TodasEmpresasComQuantidadeDeOcorrencias();
        }
        
	//USUARIO
	
	
	public boolean cadastarUsuario(Usuario usuario) {
		return controllerUsuario.cadastrar(usuario);
	}
	
	public boolean removerUsuario(Usuario usuario) {
		return controllerUsuario.remover(usuario);
	}

	public boolean atualizarUsuario(Usuario usuario) {
		return controllerUsuario.atualizar(usuario);
	}

	public Usuario buscarUsuarioPorCpf(Long cpf) {
		return controllerUsuario.buscarUsuarioPorCpf(cpf);
	}
	
        public boolean isUsuarioCadastrado(Long cpf){
            return controllerUsuario.isUsuarioCadastrado(cpf);
        }
	
	//OCORRENCIA
	
	
	public boolean cadastrarOcorrencia(Ocorrencia ocorrencia) {
		return controllerOcorrencia.cadastrar(ocorrencia);
	}

        public Ocorrencia buscarOcorrenciaPorCodigo(Long codigo){
            return controllerOcorrencia.buscarOcorrenciaPorCodigo(codigo);
        }
        
	public boolean removerOcorrencia(Ocorrencia ocorrencia) {
		ocorrencia = em.merge(ocorrencia);
		return controllerOcorrencia.remover(ocorrencia);
	}
        
        public void removerOcorrenciaPorId(Long id){
            controllerOcorrencia.removerOcorrenciaPorId(id);
        }

	public boolean atualizarOcorrencia(Ocorrencia ocorrencia) {
		return controllerOcorrencia.atualizar(ocorrencia);
	}

	public List<Ocorrencia> buscarPorOcorrenciasDoUsuario(Usuario usuario) {
		return controllerOcorrencia.buscarPorOcorrenciasDoUsuario(usuario);
	}
        
        public List<OcorrenciaDoUsuarioVo> todasOcorrenciasDoUsuarioVo(Long cpf){
            return controllerOcorrencia.todasOcorrenciasDoUsuarioVo(cpf);
        }

	public List<Ocorrencia> todasOcorrencias() {
		return controllerOcorrencia.todasOcorrencias();
	}
        
        public List<OcorrenciaTabelaVo> todasOcorrenciasVo(){
            return controllerOcorrencia.todasOcorrenciasVo();
        }

	public List<Ocorrencia> todasOcorrenciasDaEmpresa(Empresa empresa) {
		return controllerOcorrencia.todasOcorrenciasDaEmpresa(empresa);
	}
	
	
	
}

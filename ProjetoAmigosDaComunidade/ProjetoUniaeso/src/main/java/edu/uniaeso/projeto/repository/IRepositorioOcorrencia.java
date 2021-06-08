package edu.uniaeso.projeto.repository;

import java.util.List;

import edu.uniaeso.projeto.modelo.Empresa;
import edu.uniaeso.projeto.modelo.Ocorrencia;
import edu.uniaeso.projeto.modelo.Usuario;

public interface IRepositorioOcorrencia {
	
	
	public boolean cadastrar(Ocorrencia ocorrencia);
	
	public boolean remover(Ocorrencia ocorrencia);
	
	public boolean atualizar(Ocorrencia ocorrencia);
	
	public List<Ocorrencia> buscarPorOcorrenciasDoUsuario(Usuario usuario);
	
	public List<Ocorrencia> todasOcorrencias();
	
	public List<Ocorrencia> todasOcorrenciasDaEmpresa(Empresa empresa);
	
	
	}

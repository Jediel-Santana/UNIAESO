package edu.uniaeso.projeto.repository;

import java.util.List;

import edu.uniaeso.projeto.modelo.Empresa;

public interface IRepositorioEmpresa {
	
	
	public boolean cadastrar(Empresa empresa);
	
	public boolean remover(Empresa empresa);
	
	public boolean atualizar(Empresa empresa);
	
	public List<Empresa> todasEmpresas();
	
	public Empresa buscaEmpresaPeloCnpj(Long cpnj);
	
	
	
}

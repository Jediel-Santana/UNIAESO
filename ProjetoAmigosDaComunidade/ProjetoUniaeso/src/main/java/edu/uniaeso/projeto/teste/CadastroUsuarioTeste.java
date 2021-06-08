package edu.uniaeso.projeto.teste;



import java.time.LocalDate;

import javax.persistence.EntityManager;

import edu.uniaeso.projeto.Vo.OcorrenciaTabelaVo;
import edu.uniaeso.projeto.Vo.TodasEmpresasVo;
import edu.uniaeso.projeto.dao.EmpresaDAO;
import edu.uniaeso.projeto.dao.LoginDAO;
import edu.uniaeso.projeto.dao.OcorrenciaDAO;
import edu.uniaeso.projeto.dao.UsuarioDAO;
import edu.uniaeso.projeto.embutiveis.Contato;
import edu.uniaeso.projeto.modelo.Empresa;
import edu.uniaeso.projeto.embutiveis.Endereco;
import edu.uniaeso.projeto.modelo.Ocorrencia;
import edu.uniaeso.projeto.embutiveis.Sexo;
import edu.uniaeso.projeto.modelo.Usuario;
import edu.uniaeso.projeto.util.JPAutil;
import java.util.List;

public class CadastroUsuarioTeste {
	
	
	public static void main(String[] args) {
		
			EntityManager em = JPAutil.getEntityManager();
		
		//populaBanco();
			EmpresaDAO dao = new EmpresaDAO(em);
                
//            List<Ocorrencia> lista = dao.todasOcorrencias();


                
                List<TodasEmpresasVo> todas = dao.TodasEmpresasComQuantidadeDeOcorrencias();
                
                
                todas.forEach(p -> System.out.println(p));
                
		//Usuario usuario = em.find(Usuario.class, 1l);
		//Empresa empresa = em.find(Empresa.class, 1l);
		//Endereco enderecoProblema = new Endereco();
		
                //LoginDAO dao = new LoginDAO(em);
                
                
                //dao.existeLogin("jediel@hotmail.com", "123456");
                
		//Ocorrencia ocorrencia = new Ocorrencia();
		
		
		
		
	}

	private static void populaBanco() {
//		EntityManager em = JPAutil.getEntityManager();
//		
//		UsuarioDAO usuarioDAO = new UsuarioDAO(em);
//		EmpresaDAO empresaDAO = new EmpresaDAO(em);
//		
//		Contato contatoUsuario = new Contato(81,81710799l);
//		Usuario jediel = new Usuario("Jediel Santana", 12637757485l, LocalDate.of(2000, 9, 05), Sexo.Masculino, contatoUsuario);;
//		
//		
//		Contato contatoEmpresa = new Contato(81,34314535l);
//		Empresa CELPE = new Empresa("CELPE", 123666L, contatoEmpresa, "celpe@org.com.br");
//		
//		
//		em.getTransaction().begin();
//		
//		usuarioDAO.cadastrar(jediel);
//		empresaDAO.cadastrar(CELPE);
//		
//		em.flush();
	}
	
}

package edu.uniaeso.projeto.teste;

import java.time.LocalDate;

import javax.persistence.EntityManager;

import edu.uniaeso.projeto.dao.EmpresaDAO;
import edu.uniaeso.projeto.dao.OcorrenciaDAO;
import edu.uniaeso.projeto.dao.UsuarioDAO;
import edu.uniaeso.projeto.embutiveis.Contato;
import edu.uniaeso.projeto.modelo.Empresa;
import edu.uniaeso.projeto.embutiveis.Endereco;
import edu.uniaeso.projeto.modelo.Login;
import edu.uniaeso.projeto.modelo.Ocorrencia;
import edu.uniaeso.projeto.embutiveis.Sexo;
import edu.uniaeso.projeto.embutiveis.Status;
import edu.uniaeso.projeto.modelo.Usuario;
import edu.uniaeso.projeto.util.JPAutil;

public class cadastraBanco {
	
	
	public static void main(String[] args) {
		EntityManager em = JPAutil.getEntityManager();
		
		EmpresaDAO empresaDAO = new EmpresaDAO(em);
		UsuarioDAO usuarioDAO = new UsuarioDAO(em);
		EmpresaDAO empresaDAO2 = new EmpresaDAO(em);
		OcorrenciaDAO ocorrenciaDAO = new OcorrenciaDAO(em);
		
		Contato contatouser1 = new Contato(81,981710788l);
		Login login1 = new Login("jediel@hotmail.com", "123456");
		Usuario user1 = new Usuario("Jediel Santana", 12345678901l, LocalDate.of(1999, 9, 05), Sexo.Masculino, contatouser1, login1);
		
		Contato contatouser2 = new Contato(81,982896633l);
		Login login2 = new Login("Icaro@hotmail.com", "123456");
		Usuario user2 = new Usuario("Icaro Batalha", 12345678902l, LocalDate.of(2000, 10, 15), Sexo.Masculino, contatouser2, login2);
		
		Contato contatouser3 = new Contato(81,999245576l);
		Login login3 = new Login("Vinicius@hotmail.com", "123456");
		Usuario user3 = new Usuario("Vinicius Augusto", 12345678903l, LocalDate.of(1997, 4, 8), Sexo.Indefinido, contatouser3, login3);
		
		Contato contatouser4 = new Contato(81,998974556l);
		Login login4 = new Login("Daniella@hotmail.com", "123456");
		Usuario user4 = new Usuario("Daniella Farias", 12345678904l, LocalDate.of(1997, 7, 18), Sexo.Feminino, contatouser4, login4);
		
		Contato contatouser5 = new Contato(81,998974556l);
		Login login5 = new Login("Saulo@hotmail.com", "123456");
		Usuario user5 = new Usuario("Saulo Henrique", 12345678905l, LocalDate.of(1995, 10, 2), Sexo.Masculino, contatouser5, login5);
		
		
		//jpa e hibernate
		
//		Contato contatoEmpresa = new Contato(81,34314535l);
//		Empresa CELPE = new Empresa("CELPE", 12345678912341l, contatoEmpresa, "celpe@NEOENERGIA.org");
//		
//		Contato contatoEmpresa3 = new Contato(81,34314444l);
//		Empresa COMPESA = new Empresa("COMPESA", 12345678912342l, contatoEmpresa3, "compresa@NEOAGUA.org");	
//		Contato contatoEmpresa2 = new Contato(81,34478444l);
//		Empresa EMLURBE = new Empresa("EMLURBE", 12345678912343l, contatoEmpresa2, "emlurbe@INFRA.org");
//		
//		
//		
//		Empresa CELPE = em.find(Empresa.class, 12345678912341l);
//		Empresa COMPESA = em.find(Empresa.class, 12345678912342l);
//		Empresa EMLURB = em.find(Empresa.class, 12345678912343l);
//		
//		Usuario jediel = em.find(Usuario.class, 12345678901l);
//		Usuario icaro = em.find(Usuario.class, 12345678902l);
//		Usuario vinicius = em.find(Usuario.class, 12345678903l);
//		Usuario daniella = em.find(Usuario.class, 12345678904l);
//		Usuario saulo = em.find(Usuario.class, 12345678905l);
		
		
		
		//(Endereco endereco, String descricao, String documentos_anexos, Usuario usuario, Empresa empresa)
		
//		Endereco endereco1 = new Endereco("rua jose alexandre carvalho", "prox ao caldinho do zé", 53200500);
//		Ocorrencia oc1 = new Ocorrencia(endereco1, "BURACO NO MEIO DA AVENIDA", "documento-anexo", jediel, EMLURB);
//		
//		Endereco endereco2 = new Endereco("rua Santos dumond", "prox ao campo do ibura", 45455000);
//		Ocorrencia oc2 = new Ocorrencia(endereco2, "POSTE COM A LUZ QUEBRADA", "documento-anexo", icaro, CELPE);
//				
//		Endereco endereco3 = new Endereco("rua flora de assunção", "rua ao lado do arco-mix", 53270570);
//		Ocorrencia oc3 = new Ocorrencia(endereco1, "ESGOTO A CEÚ ABERTO", "documento-anexo", vinicius, COMPESA);
//		
//		Endereco endereco4 = new Endereco("rua aurora messías", "prox ao armazém coral", 53439370);
//		Ocorrencia oc4 = new Ocorrencia(endereco4, "CANO ESTOURADO NA RUA", "documento-anexo", daniella, COMPESA);
//		
//		Endereco endereco5 = new Endereco("rua sao jose belmonte", "prox bar da charque", 53416632);
//		Ocorrencia oc5 = new Ocorrencia(endereco5, "Poste com luz apagada", "documento-anexo", saulo, CELPE);
		
//		
//		
//		oc2.setStatus(Status.EM_ANALISE);
//		oc3.setStatus(Status.EM_ANDAMENTO);
//		oc4.setStatus(Status.FINALIZADO);
//		oc5.setStatus(Status.ABERTO);
//		
//		
		
		em.getTransaction().begin();
//		
//		usuarioDAO.cadastrar(user1);
//		usuarioDAO.cadastrar(user2);
//		usuarioDAO.cadastrar(user3);
//		usuarioDAO.cadastrar(user4);
//		usuarioDAO.cadastrar(user5);
		
//		
//		empresaDAO.cadastrar(CELPE);
//		empresaDAO.cadastrar(COMPESA);
//		empresaDAO.cadastrar(EMLURBE);
//		
		
//			ocorrenciaDAO.cadastrar(oc1);
//			ocorrenciaDAO.cadastrar(oc2);
//			ocorrenciaDAO.cadastrar(oc3);
//			ocorrenciaDAO.cadastrar(oc4);
//			ocorrenciaDAO.cadastrar(oc5);
		
		
		em.getTransaction().commit();
		
		
	}
	
	

	
}

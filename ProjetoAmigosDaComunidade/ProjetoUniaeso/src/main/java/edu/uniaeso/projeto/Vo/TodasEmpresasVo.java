/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniaeso.projeto.Vo;

/**
 *
 * @author Jediel
 */
public class TodasEmpresasVo {
    
    private String nome;
    private Long cnpj;
    private String email;
    private Long qtdOcorrencias;

    public TodasEmpresasVo(String nome, Long cnpj, String email, Long qtdOcorrencias) {
        this.nome = nome;
        this.cnpj = cnpj;
        //this.numero = numero;
        this.email = email;
        this.qtdOcorrencias = qtdOcorrencias;
    }

    public String getNome() {
        return nome;
    }

    public Long getCnpj() {
        return cnpj;
    }

    

    public String getEmail() {
        return email;
    }

    public Long getQtdOcorrencias() {
        return qtdOcorrencias;
    }

    @Override
    public String toString() {
        return "TodasEmpresasVo{" + "nome=" + nome + ", cnpj=" + cnpj + ", email=" + email + ", qtdOcorrencias=" + qtdOcorrencias + '}';
    }

    
    
    
    
    
    
    
}

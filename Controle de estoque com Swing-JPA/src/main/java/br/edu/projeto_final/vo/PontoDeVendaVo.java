/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.projeto_final.vo;


public class PontoDeVendaVo {
    
    
    private Long id;
    private String bairro;
    private String CEP;
    
    
    public PontoDeVendaVo(Long id, String bairro, String Cep) {
        this.id = id;
        this.bairro = bairro;
        this.CEP = Cep;
        
    }

    public Long getId() {
        return id;
    }

    public String getBairro() {
        return bairro;
    }
    
    public String getCEP() {
        return CEP;
    }
    
    
}

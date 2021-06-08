/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniaeso.projeto.Vo;

import edu.uniaeso.projeto.embutiveis.Status;
import java.time.LocalDate;

/**
 *
 * @author Jediel
 */
public class OcorrenciaDoUsuarioVo {
    
    
    
    private Long codOcorrencia;
    private LocalDate dataAbertura;
    private Status status;
    private String descricao;
    private Integer CEP;
    private String ruaOuAvenida;
    private String pontoDeReferencia;
    private String nomeEmpresa;

    public OcorrenciaDoUsuarioVo(Long codOcorrencia, LocalDate dataAbertura, Status status, String descricao, Integer CEP, String ruaOuAvenida, String pontoDeReferencia, String nomeEmpresa) {
        this.codOcorrencia = codOcorrencia;
        this.dataAbertura = dataAbertura;
        this.status = status;
        this.descricao = descricao;
        this.CEP = CEP;
        this.ruaOuAvenida = ruaOuAvenida;
        this.pontoDeReferencia = pontoDeReferencia;
        this.nomeEmpresa = nomeEmpresa;
    }

    public Long getCodOcorrencia() {
        return codOcorrencia;
    }

    public LocalDate getDataAbertura() {
        return dataAbertura;
    }

    public Status getStatus() {
        return status;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getCEP() {
        return CEP;
    }

    public String getRuaOuAvenida() {
        return ruaOuAvenida;
    }

    public String getPontoDeReferencia() {
        return pontoDeReferencia;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }
    
    
    
    
    
    
    
    
    
}

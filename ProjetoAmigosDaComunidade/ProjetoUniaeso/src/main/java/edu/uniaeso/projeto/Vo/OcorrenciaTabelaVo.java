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
public class OcorrenciaTabelaVo {
    
    
    private Long codigoOcorrencia;
    private LocalDate dataAbertura;
    private Status status;
    private String descricao;
    private String RuaOuAvenida;
    private String nomeDaEmpresa;

    public OcorrenciaTabelaVo(Long codigoOcorrencia, LocalDate dataAbertura, Status status, String descricao, String RuaOuAvenida, String nomeDaEmpresa) {
        this.codigoOcorrencia = codigoOcorrencia;
        this.dataAbertura = dataAbertura;
        this.status = status;
        this.descricao = descricao;
        this.RuaOuAvenida = RuaOuAvenida;
        this.nomeDaEmpresa = nomeDaEmpresa;
    }

    

    public Long getCodigoOcorrencia() {
        return codigoOcorrencia;
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

    public String getRuaOuAvenida() {
        return RuaOuAvenida;
    }

    public String getNomeDaEmpresa() {
        return nomeDaEmpresa;
    }

    @Override
    public String toString() {
        return "OcorrenciaTabelaVo{" + "codigoOcorrencia=" + codigoOcorrencia + ", dataAbertura=" + dataAbertura + ", dataConclusao="  + ", status=" + status + ", descricao=" + descricao + ", RuaOuAvenida=" + RuaOuAvenida + ", nomeDaEmpresa=" + nomeDaEmpresa + '}';
    }
    
    
    
    
}

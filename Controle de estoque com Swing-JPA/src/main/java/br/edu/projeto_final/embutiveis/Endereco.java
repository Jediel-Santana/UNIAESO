package br.edu.projeto_final.embutiveis;

import java.io.IOException;

import javax.persistence.Embeddable;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;

import br.edu.projeto_final.util.ViaCepUtil;

@Embeddable
public class Endereco {
	
	
	private String CEP;
	private String logradouro;
	private String Bairro;
	private String cidade;
	private String estado;
	
	private String num;
	private String complemento;
	
	
	public Endereco() {
	}

    public Endereco(String CEP, String logradouro, String Bairro, String cidade, String estado, String num, String complemento) {
        this.CEP = CEP;
        this.logradouro = logradouro;
        this.Bairro = Bairro;
        this.cidade = cidade;
        this.estado = estado;
        this.num = num;
        this.complemento = complemento;
    }


	public String getLogradouro() {
		return logradouro;
	}

	public String getBairro() {
		return Bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public String getNumero() {
		return num;
	}
	
	
	public String getComplemento() {
		return complemento;
	}

	public void setCEP(String cEP) {
		CEP = cEP;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public void setBairro(String bairro) {
		Bairro = bairro;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public void setNumero(String numero) {
		this.num = numero;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

    public String getCEP() {
        return CEP;
    }

    public String getEstado() {
        return estado;
    }

    
        
        
        
        
	@Override
	public String toString() {
		return "[CEP=" + CEP + ", rua=" + logradouro + ", Bairro=" + Bairro + ", cidade=" + cidade + ", estado="
				+ estado + ", num=" + num + ", complemento=" + complemento + "]";
	}
	
	
	
	
	
}

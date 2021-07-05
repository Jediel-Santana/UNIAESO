package br.edu.projeto_final.util;

import java.io.IOException;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;

public class ViaCepUtil {
	
	private static ViaCEPClient client = new ViaCEPClient();
	
	
	public ViaCepUtil() {
		
	}
	
	public ViaCEPEndereco getEndereco(String cep) throws IOException {
		return client.getEndereco(cep);
	}

	
}

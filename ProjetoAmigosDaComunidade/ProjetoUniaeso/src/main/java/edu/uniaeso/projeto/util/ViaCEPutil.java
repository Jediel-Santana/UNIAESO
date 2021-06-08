/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uniaeso.projeto.util;

import com.github.gilbertotorrezan.viacep.se.ViaCEPClient;
import com.github.gilbertotorrezan.viacep.shared.ViaCEPEndereco;
import java.io.IOException;

/**
 *
 * @author Jediel
 */
public class ViaCEPutil {
    
    private static ViaCEPClient client = new ViaCEPClient();
    
    
    public ViaCEPEndereco getEndereco(String cep) throws IOException{        
        return client.getEndereco(cep);        
    }
    
    
}

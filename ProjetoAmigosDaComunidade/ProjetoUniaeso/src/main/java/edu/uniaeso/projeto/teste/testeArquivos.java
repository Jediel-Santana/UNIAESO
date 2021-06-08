package edu.uniaeso.projeto.teste;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class testeArquivos {
	
	public static void main(String[] args) throws IOException {
		
		Path path = Paths.get("C:\\Users\\Jediel\\Documents\\HTML E CSS\\Produtos\\ola.txt");
		
		Files.write(path, "testando 123".getBytes());
	
		
	
	}
	
}

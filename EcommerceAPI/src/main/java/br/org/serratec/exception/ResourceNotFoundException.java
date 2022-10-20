package br.org.serratec.exception;

public class ResourceNotFoundException extends RuntimeException {

	public ResourceNotFoundException(String mensagem) {
		super(mensagem);
	}
	
}

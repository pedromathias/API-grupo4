package br.org.serratec.exception;

public class ResourceBadRequestException extends RuntimeException {
	public ResourceBadRequestException(String mensagem) {
		super(mensagem);
	}
}

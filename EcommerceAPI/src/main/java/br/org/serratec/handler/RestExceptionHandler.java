package br.org.serratec.handler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.org.serratec.model.error.MensagemError;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<String> erros = new ArrayList<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			erros.add(error.getField() + ": " + error.getDefaultMessage());
		}
		MensagemError erroResposta = new MensagemError(status.value(), "Existem Campos Inválidos, Confira o preechimento",
				LocalDateTime.now(), erros);
		return super.handleExceptionInternal(ex, erroResposta, headers, status, request);
	}
}
package br.gov.pe.sismepe.resources.exceptions;

import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import br.gov.pe.sismepe.services.exceptions.AuthorizationException;
import br.gov.pe.sismepe.services.exceptions.NegocioException;

@ControllerAdvice
public class CustomExceptionHandler {
	
	@ExceptionHandler({NegocioException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleNegocioException(NegocioException ex, WebRequest request) {
		List<FieldMessage> erros = Arrays.asList(new FieldMessage(ex.toString(), ex.getMessage()));	
		return new ResponseEntity<>(erros, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler({AccessDeniedException.class})
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	protected ResponseEntity<Object> handleAcessoNegado(AccessDeniedException ex, WebRequest request){
		List<FieldMessage> erros = Arrays.asList(new FieldMessage(ex.toString(), "Você não tem permissão para acessar esse recurso!"));	
		return new ResponseEntity<>(erros, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
	}

	
	@ExceptionHandler({AuthorizationException.class})
	@ResponseStatus(HttpStatus.FORBIDDEN)
	protected ResponseEntity<Object> handleAuthorizationException(AuthorizationException ex, WebRequest request){
		List<FieldMessage> erros = Arrays.asList(new FieldMessage(ex.toString(), ex.getMessage()));	
		return new ResponseEntity<>(erros, new HttpHeaders(), HttpStatus.FORBIDDEN);
	}
	
	@ExceptionHandler({ParseException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	protected ResponseEntity<Object> handleParseException(ParseException ex, WebRequest request){
		List<FieldMessage> erros = Arrays.asList(new FieldMessage(ex.toString(), ex.getMessage()));	
		return new ResponseEntity<>(erros, new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler({FileNotFoundException.class})
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected ResponseEntity<Object> handleAuthorizationException(FileNotFoundException ex, WebRequest request){
		List<FieldMessage> erros = Arrays.asList(new FieldMessage(ex.toString(), "Arquivo não encontrado!"));	
		return new ResponseEntity<>(erros, new HttpHeaders(), HttpStatus.NOT_FOUND);
	}

}

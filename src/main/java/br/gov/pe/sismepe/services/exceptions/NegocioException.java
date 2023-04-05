package br.gov.pe.sismepe.services.exceptions;
public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = -2827826421390600515L;

	public NegocioException(String mensagem) {
		super(mensagem);
	}
}

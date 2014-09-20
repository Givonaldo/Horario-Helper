package br.com.H2Helper.exception;

public class SalaJaCadastradaException extends H2Exception {

	private static final long serialVersionUID = 8745785810247081866L;

	public SalaJaCadastradaException(){
		super("Sala já cadastrada");
	}
	
}

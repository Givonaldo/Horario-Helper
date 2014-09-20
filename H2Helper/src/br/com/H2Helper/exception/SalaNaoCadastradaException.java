package br.com.H2Helper.exception;

public class SalaNaoCadastradaException extends H2Exception {

	private static final long serialVersionUID = -6245130669293819623L;

	public SalaNaoCadastradaException(){
		super("Sala não cadastrada");
	}
	
}

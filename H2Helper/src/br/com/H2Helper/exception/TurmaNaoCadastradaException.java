package br.com.H2Helper.exception;

public class TurmaNaoCadastradaException extends H2Exception {

	private static final long serialVersionUID = 664286800629778840L;

	public TurmaNaoCadastradaException(){
		super("Turma não cadastrada");
	}
	
}

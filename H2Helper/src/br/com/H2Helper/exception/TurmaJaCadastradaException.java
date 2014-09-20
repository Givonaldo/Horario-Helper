package br.com.H2Helper.exception;

public class TurmaJaCadastradaException extends H2Exception {

	private static final long serialVersionUID = 7115455031121317891L;

	public TurmaJaCadastradaException(){
		super("Turma já cadastrada");
	}
	
}

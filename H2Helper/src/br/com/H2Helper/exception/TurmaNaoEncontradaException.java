package br.com.H2Helper.exception;

/**
 * 
 * @author Gilvonaldo
 *
 */
public class TurmaNaoEncontradaException extends H2Exception {

	private static final long serialVersionUID = -3993649495170563952L;

	public TurmaNaoEncontradaException(){
		super("Turma não encontrada.");
	}
	
}

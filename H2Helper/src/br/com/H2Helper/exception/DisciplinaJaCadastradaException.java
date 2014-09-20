package br.com.H2Helper.exception;

/**
 * 
 * @author Gilvonaldo
 *
 */
public class DisciplinaJaCadastradaException extends H2Exception {

	private static final long serialVersionUID = -6789183610733527518L;

	public DisciplinaJaCadastradaException(){
		super("Disciplina já Cadastrada");
	}
}

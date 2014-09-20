package br.com.H2Helper.exception;

/**
 * 
 * @author Gilvonaldo
 *
 */
public class ProfessorNaoCadastradoException extends H2Exception {

	private static final long serialVersionUID = -2676237764441800982L;

	public ProfessorNaoCadastradoException(){
		super("Professor não Cadastrado");
	}
	
}

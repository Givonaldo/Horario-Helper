package br.com.H2Helper.exception;

/**
 * 
 * @author Gilvonaldo
 *
 */
public class ProfessorJaCadastradoException extends H2Exception {

	private static final long serialVersionUID = -5387020381584051530L;

	public ProfessorJaCadastradoException(){
		super("Professor já cadastrado");
	}
}

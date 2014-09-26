package br.com.H2Helper.exception;

/**
 * Classe que representa uma Exception que ser� lan�ada 
 * quando ouver a tentativa de cadastramento de um professor 
 * que j� est� cadastrado no sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti
 * @see H2Exception
 */
public class ProfessorJaCadastradoException extends H2Exception {

	private static final long serialVersionUID = -5387020381584051530L;

	public ProfessorJaCadastradoException(){
		super("Professor j� cadastrado(a)");
	}
}

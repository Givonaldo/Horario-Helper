package br.com.H2Helper.exception;

/**
 * Classe que representa uma Exception que será lançada 
 * quando ouver a tentativa de cadastramento de uma 
 * disciplina que já está cadastrada no sistema.
 *  
 * @author Gilvonaldo Alves da Siva Cavalcanti
 * @see H2Exception
 */
public class DisciplinaJaCadastradaException extends H2Exception {

	private static final long serialVersionUID = -6789183610733527518L;

	public DisciplinaJaCadastradaException(){
		super("Disciplina já Cadastrada");
	}
}

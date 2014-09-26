package br.com.H2Helper.exception;

/**
 * Classe que representa uma Exception que ser� lan�ada 
 * quando ouver a tentativa de cadastramento de um periodo 
 * que j� est� cadastrado no sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti
 * @see H2Exception
 */
public class PeriodoJaCadastradoException extends H2Exception {

	private static final long serialVersionUID = 1914005801450337818L;

	public PeriodoJaCadastradoException(){
		super("Periodo j� Cadastrado");
	}
}

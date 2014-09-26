package br.com.H2Helper.exception;

/**
 * Classe que representa um exception do sistema <b>H2Helper<b>, 
 * que ser� lan�ada quando ouver a tentativa de acessar uma turma 
 * n�o cadastrada no sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see H2Exception
 */
public class TurmaNaoEncontradaException extends H2Exception {

	private static final long serialVersionUID = -3993649495170563952L;

	public TurmaNaoEncontradaException(){
		super("Turma n�o encontrada.");
	}
	
}

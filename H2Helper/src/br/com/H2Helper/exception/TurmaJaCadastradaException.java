package br.com.H2Helper.exception;

/**
 * Classe que representa uma exception do Sistema <b>H2Helper</b> 
 * que ser� lan�ada quando ouver a tentativa de cadastramento de 
 * uma Turma que j� est� cadastrada no Sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see H2Exception
 */
public class TurmaJaCadastradaException extends H2Exception {

	private static final long serialVersionUID = 7115455031121317891L;

	public TurmaJaCadastradaException(){
		super("Turma j� cadastrada");
	}
	
}

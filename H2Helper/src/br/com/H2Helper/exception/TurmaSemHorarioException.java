package br.com.H2Helper.exception;

/**
 * Classe que representa uma exception do sistema <b>H2Helper</b>, 
 * que ser� lan�ada quando ouver a tentativa de Aloca��o de uma Turma 
 * que est� sm hor�rio.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see H2Exception
 */
public class TurmaSemHorarioException extends H2Exception {

	private static final long serialVersionUID = 2053888418634098142L;

	public TurmaSemHorarioException() {
		super("Turma sem hor�rio");
	}
}

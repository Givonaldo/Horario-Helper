package br.com.H2Helper.exception;

/**
 * Classe que representa uma exception do sistema <b>H2Helper</b>, 
 * que será lançada quando ouver a tentativa de Alocação de uma Turma 
 * que está sm horário.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see H2Exception
 */
public class TurmaSemHorarioException extends H2Exception {

	private static final long serialVersionUID = 2053888418634098142L;

	public TurmaSemHorarioException() {
		super("Turma sem horário");
	}
}

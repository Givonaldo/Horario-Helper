package br.com.H2Helper.exception;

/**
 * 
 * @author Gilvonaldo
 *
 */
public class TurmaSemHorarioException extends H2Exception {

	private static final long serialVersionUID = 2053888418634098142L;

	public TurmaSemHorarioException() {
		super("Turma sem horário");
	}
}

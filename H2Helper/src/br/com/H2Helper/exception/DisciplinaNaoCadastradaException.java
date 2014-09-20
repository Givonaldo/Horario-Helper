package br.com.H2Helper.exception;

/**
 * 
 * @author Gilvonaldo
 *
 */
public class DisciplinaNaoCadastradaException extends H2Exception {

	private static final long serialVersionUID = 6860290728754346050L;

	public DisciplinaNaoCadastradaException(){
		super("Disciplina não cadastrada");
	}
}

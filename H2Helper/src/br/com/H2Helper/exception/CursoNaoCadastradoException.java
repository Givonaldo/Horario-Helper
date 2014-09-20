package br.com.H2Helper.exception;

/**
 * 
 * @author Gilvonaldo
 *
 */
public class CursoNaoCadastradoException extends H2Exception {

	private static final long serialVersionUID = -4203580641734294912L;

	public CursoNaoCadastradoException(){
		super("Curso não cadastrado");
	}
	
}

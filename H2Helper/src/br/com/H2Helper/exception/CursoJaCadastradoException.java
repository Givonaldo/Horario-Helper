package br.com.H2Helper.exception;

/**
 * 
 * @author Gilvonaldo
 *
 */
public class CursoJaCadastradoException extends H2Exception {

	private static final long serialVersionUID = 975879796066820295L;

	public CursoJaCadastradoException(){
		super("Curso já cadastrado");
	}
}

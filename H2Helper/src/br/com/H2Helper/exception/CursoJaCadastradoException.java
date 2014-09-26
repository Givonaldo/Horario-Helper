package br.com.H2Helper.exception;

/**
 * Exception que ser� lan�ada quando ocorrer uma tentativa de 
 * cadastramento de um curso que j� foi cadastrado anteriormente.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti
 * @see H2Exception
 */
public class CursoJaCadastradoException extends H2Exception {

	private static final long serialVersionUID = 975879796066820295L;

	public CursoJaCadastradoException(){
		super("Curso j� cadastrado");
	}
}

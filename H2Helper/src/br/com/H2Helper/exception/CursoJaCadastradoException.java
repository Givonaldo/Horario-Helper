package br.com.H2Helper.exception;

/**
 * Exception que será lançada quando ocorrer uma tentativa de 
 * cadastramento de um curso que já foi cadastrado anteriormente.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti
 * @see H2Exception
 */
public class CursoJaCadastradoException extends H2Exception {

	private static final long serialVersionUID = 975879796066820295L;

	public CursoJaCadastradoException(){
		super("Curso já cadastrado");
	}
}

package br.com.H2Helper.exception;

/**
 * Exception que ser� lan�ada quando tiver a ocorrencia de 
 * acesso a um curso que n�o est� cadastrado no sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti
 * @see H2Exception
 */
public class CursoNaoCadastradoException extends H2Exception {

	private static final long serialVersionUID = -4203580641734294912L;

	public CursoNaoCadastradoException(){
		super("Curso n�o cadastrado");
	}
	
}

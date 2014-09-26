package br.com.H2Helper.exception;

/**
 * Classe que representa uma Exception que ser� lan�ada quando 
 * ouver uma tentativa de acesso a um objeto professor que n�o 
 * existe no Sistema. 
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see H2Exception
 */
public class ProfessorNaoCadastradoException extends H2Exception {

	private static final long serialVersionUID = -2676237764441800982L;

	public ProfessorNaoCadastradoException(){
		super("Professor n�o Cadastrado");
	}
	
}

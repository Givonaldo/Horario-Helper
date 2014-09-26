package br.com.H2Helper.exception;

/**
 * Excetion que ser� lan�ada quando os dados passados no 
 * par�metro for inv�lido.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti
 * @see H2Exception
 */
public class AtributoInvalidoException extends H2Exception {

	private static final long serialVersionUID = -9078266019062874002L;

	public AtributoInvalidoException(){
		super("Atributo Inv�lido");
	}
	
}

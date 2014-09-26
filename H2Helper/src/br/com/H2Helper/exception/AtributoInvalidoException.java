package br.com.H2Helper.exception;

/**
 * Excetion que será lançada quando os dados passados no 
 * parâmetro for inválido.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti
 * @see H2Exception
 */
public class AtributoInvalidoException extends H2Exception {

	private static final long serialVersionUID = -9078266019062874002L;

	public AtributoInvalidoException(){
		super("Atributo Inválido");
	}
	
}

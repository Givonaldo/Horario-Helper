package br.com.H2Helper.exception;

/**
 * Classe que representa uma Exception que será lançada quando 
 * ouver a tentativa de cadastramento de uma sala que já está 
 * cadastrada no sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see H2Exception
 */
public class SalaJaCadastradaException extends H2Exception {

	private static final long serialVersionUID = 8745785810247081866L;

	public SalaJaCadastradaException(){
		super("Sala já cadastrada");
	}
	
}

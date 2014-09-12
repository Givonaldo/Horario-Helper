package br.com.H2Helper.exception;

/**
 * Classe que representa a super classe de todas as 
 * exce��o lan�adas pela fachada do sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @version 1.0
 */
public class H2Exception extends Exception {
	
	private static final long serialVersionUID = 1L;

	public H2Exception(String mensagem){
		super(mensagem);
	}
	
}
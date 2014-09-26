package br.com.H2Helper.exception;

/**
 * Classe que representa a super classe de todas as 
 * exceção lançadas pela fachada do sistema <b>H2Helper</b>.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @version 1.0
 */
public class H2Exception extends Exception {

	private static final long serialVersionUID = -4600474116000904497L;

	public H2Exception(String mensagem){
		super(mensagem);
	}
	
}

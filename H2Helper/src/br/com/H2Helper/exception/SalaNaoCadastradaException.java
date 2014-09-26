package br.com.H2Helper.exception;

/**
 * Classe que representa uma Exception do sistema <B>H2Helper</B> 
 * que será lançada quando ouver a tentativa de acesso a uma Sala que 
 * não existe no Sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see H2Exception
 */
public class SalaNaoCadastradaException extends H2Exception {

	private static final long serialVersionUID = -6245130669293819623L;

	public SalaNaoCadastradaException(){
		super("Sala não cadastrada");
	}
	
}

package br.com.H2Helper.exception;

/**
 * Classe que representa uma exception que ser� lan�ada  
 * quando ouver uma tentativa de acesso a um periodo que 
 * n�o est� cadastrado no sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see H2Exception
 */
public class PeriodoNaoCadastradoException extends H2Exception {

	private static final long serialVersionUID = -2763885546770420540L;

	public PeriodoNaoCadastradoException(){
		super("Periodo n�o cadastrado");
	}
}

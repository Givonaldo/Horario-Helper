package br.com.H2Helper.exception;

/**
 * 
 * @author Gilvonaldo
 *
 */
public class PeriodoNaoCadastradoException extends H2Exception {

	private static final long serialVersionUID = -2763885546770420540L;

	public PeriodoNaoCadastradoException(){
		super("Periodo não Cadastrado");
	}
}

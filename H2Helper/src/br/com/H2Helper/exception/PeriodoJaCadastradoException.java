package br.com.H2Helper.exception;

/**
 * 
 * @author Gilvonaldo
 *
 */
public class PeriodoJaCadastradoException extends H2Exception {

	private static final long serialVersionUID = 1914005801450337818L;

	public PeriodoJaCadastradoException(){
		super("Periodo já Cadastrado");
	}
}

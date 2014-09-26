package br.com.H2Helper.exception;

/**
 * Classe que representa uma exception do sistema <b>H2Helper</b>, 
 * que será lançada quando ouver a tentativa de acessar uma turma 
 * que não está cadastrada no sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see H2Exception
 */
public class TurmaNaoCadastradaException extends H2Exception {

	private static final long serialVersionUID = 664286800629778840L;

	public TurmaNaoCadastradaException(){
		super("Turma não cadastrada");
	}
	
}

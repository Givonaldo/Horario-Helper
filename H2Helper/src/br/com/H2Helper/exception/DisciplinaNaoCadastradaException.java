package br.com.H2Helper.exception;

/**
 * Classe que representa uma Exception que será lançada 
 * quando ouver a tentativa de acessar uma disciplina que 
 * ainda não está cadastrada no sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti
 * @see H2Exception
 */
public class DisciplinaNaoCadastradaException extends H2Exception {

	private static final long serialVersionUID = 6860290728754346050L;

	public DisciplinaNaoCadastradaException(){
		super("Disciplina não cadastrada");
	}
}

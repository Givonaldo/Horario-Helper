package br.com.H2Helper.modelos;

/**
 * Classe que representa uma modelo de curso.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @version 1.0
 */
public class Curso {
	
	private String identificadorCurso, nome;
	
	public Curso(){
		
	}

	/**
	 * Metodo que retorna o identificador do curso.
	 * 
	 * @return identificadorCurso
	 */
	public String getIdentificadorCurso() {
		return identificadorCurso;
	}

	/**
	 * Metodo que seta o identificador do Curso.
	 *  
	 * @param identificadorCurso
	 * 		Identificador unico do curso.
	 */
	public void setIdentificadorCurso(String identificadorCurso) {
		this.identificadorCurso = identificadorCurso;
	}

	/**
	 * Metodo que retorna o nome do curso.
	 * 
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo que seta o valor para o nome do curso.
	 * 
	 * @param nome
	 * 		Nome do curso.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return getIdentificadorCurso()+" - "+getNome();
	}
	
}
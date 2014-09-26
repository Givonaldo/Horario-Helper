package br.com.H2Helper.modelos;

/**
 * Classe que representa um modelo de professor.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 */
public class Professor {

	private String idProfessor, nome;
	
	public Professor(){
		
	}

	/**
	 * Metodo que retorna o identificador do professor
	 * <b>(Matrícula)</b>.
	 * 
	 * @return idProfessor
	 */
	public String getIdProfessor() {
		return idProfessor;
	}

	/**
	 * Metodo que seta o identificador do professor.
	 * 
	 * @param idProfessor
	 * 		Identificador Ãºnico para o professor
	 */
	public void setIdProfessor(String idProfessor) {
		this.idProfessor = idProfessor;
	}

	/**
	 * Metodo que retorna o nome do professor.
	 * 
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Metodo que seta o nome do professor.
	 * 
	 * @param nome
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return getIdProfessor()+" - "+getNome();
	}
}

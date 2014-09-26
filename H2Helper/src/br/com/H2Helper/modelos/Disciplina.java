package br.com.H2Helper.modelos;

/**
 * Classe que representa um modelo para uma disciplina.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @version 1.0
 */
public class Disciplina {

	private String identificadorDisciplina,
	nomeDisciplina, identificadorCurso, identificadorperiodo;
	private int cargaHoraria;
	
	public Disciplina(){
		
	}

	/**
	 * Metodo que retorna o identificador da disciplina.
	 * 
	 * @return identificadorDisciplina
	 */
	public String getIdentificadorDisciplina() {
		return identificadorDisciplina;
	}

	/**
	 * Metodo que seta o identificador único para a disciplina.
	 * 
	 * @param identificadorDisciplina
	 * 		Identificador da dusciplina.
	 */
	public void setIdentificadorDisciplina(String identificadorDisciplina) {
		this.identificadorDisciplina = identificadorDisciplina;
	}

	/**
	 * Metodo que retorna o nome da disciplina.
	 * 
	 * @return nomeDisciplina
	 */
	public String getNomeDisciplina() {
		return nomeDisciplina;
	}

	/**
	 * Metodo que seta o nome da disciplina.
	 * @param nomeDisciplina
	 * 		Nome da disciplina.
	 */
	public void setNomeDisciplina(String nomeDisciplina) {
		this.nomeDisciplina = nomeDisciplina;
	}

	/**
	 * Metodo que retorna o identificador do curso ao qual 
	 * essa disciplina faz parte.
	 * 
	 * @return identificadorCurso
	 */
	public String getIdentificadorCurso() {
		return identificadorCurso;
	}

	/**
	 * Metodo que set o o identificador do curso ao qual 
	 * essa disciplina faz parte.
	 * 
	 * @param identificadorCurso
	 */
	public void setIdentificadorCurso(String identificadorCurso) {
		this.identificadorCurso = identificadorCurso;
	}

	/**
	 * Metodo que retorna o edentificador do periodo que está 
	 * associado a essa disciplina.
	 * 
	 * @return identificadorPeriodo
	 */
	public String getIdentificadorperiodo() {
		return identificadorperiodo;
	}

	/**
	 * Metodo que seta o identificador do periodo ao qual essa disciplina
	 * faz parte.
	 * 
	 * @param identificadorperiodo
	 */
	public void setIdentificadorperiodo(String identificadorperiodo) {
		this.identificadorperiodo = identificadorperiodo;
	}

	/**
	 * Metodo que retorna a carga horaria da disciplina.
	 * 
	 * @return cargaHoraria
	 */
	public int getCargaHoraria() {
		return cargaHoraria;
	}

	/**
	 * Metodo que seta a carga horaria da disciplina.
	 * 
	 * @param cargaHoraria
	 * 		Carga Horaria da disciplina no formato de inteiro.
	 */
	public void setCargaHoraria(int cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	@Override
	public String toString() {
		return getIdentificadorDisciplina()+" - "+getNomeDisciplina();
	}
}

package br.com.H2Helper.modelos;

/**
 * Classe que representa um modelo de um periodo.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 */
public class Periodo {

	private String identificadorPeriodo, idCurso;
	
	public Periodo(){
		
	}

	/**
	 * Metodo que retorna o identificador do periodo.
	 * 
	 * @return identificadorPeriodo
	 */
	public String getIdentificadorPeriodo() {
		return identificadorPeriodo;
	}

	/**
	 * Metodo que seta o identificador para o periodo.
	 * 
	 * @param identificadorPeriodo
	 */
	public void setIdentificadorPeriodo(String identificadorPeriodo) {
		this.identificadorPeriodo = identificadorPeriodo;
	}

	/**
	 * Metodo que retorna o identificador do curso ao qual esse
	 * periodo está associado.
	 * 
	 * @return idCurso
	 */
	public String getIdCurso() {
		return idCurso;
	}

	/**
	 * Metodo que seta o identificador do curso ao qual esse 
	 * periodo está associado.
	 * 
	 * @param idCurso
	 * 		Identificador do curso.
	 */
	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getIdentificadorPeriodo();
	}
}
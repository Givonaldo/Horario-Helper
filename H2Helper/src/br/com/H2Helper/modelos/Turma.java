package br.com.H2Helper.modelos;

/**
 * Classe que representa um modelo de turma.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 */
public class Turma {

	private String idTurma, idCurso, identificadorProfessor, identificadorDisciplina,
	identificadorSala, identificadorPeriodo, diaDaSemana, descricao;
	
	private int horaInicio, horaFim;
	
	public Turma(){
		
	}
		
	/**
	 * Metodo que retorna o identificador da turma.
	 * 
	 * @return idTurma
	 */
	public String getIdTurma() {
		return idTurma;
	}

	/**
	 * 
	 * @return descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * 
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Metodo que seta o identificador da turma.
	 * 
	 * @param idTurma
	 * 		Identificador da turma.
	 */
	public void setIdTurma(String idTurma) {
		this.idTurma = idTurma;
	}

	/**
	 * Metodo que retorna o identificador do curso ao qual essa turma 
	 * est· associada.
	 * 
	 * @return idCurso
	 */
	public String getIdCurso() {
		return idCurso;
	}

	/**
	 * Metodo que seta o identificador do curso ao qual essa turma 
	 * est√° associada.
	 * 
	 * @param idCurso
	 * 		Identificado do curso.
	 */
	public void setIdCurso(String idCurso) {
		this.idCurso = idCurso;
	}

	/**
	 * Metodo que retorna o identificador do professor.
	 * 
	 * @return identificadorProfessor
	 */
	public String getIdentificadorProfessor() {
		return identificadorProfessor;
	}

	/**
	 * Metodo que seta o identificador unico para o professor.
	 * 
	 * @param identificadorProfessor
	 */
	public void setIdentificadorProfessor(String identificadorProfessor) {
		this.identificadorProfessor = identificadorProfessor;
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
	 * Metodo que seta o identificador √∫nico para a disciplina associada
	 * a essa turma.
	 * 
	 * @param identificadorDisciplina
	 */
	public void setIdentificadorDisciplina(String identificadorDisciplina) {
		this.identificadorDisciplina = identificadorDisciplina;
	}

	/**
	 * Metodo que retorna o identificador da sala.
	 * 
	 * @return identificadorSala
	 */
	public String getIdentificadorSala() {
		return identificadorSala;
	}

	/**
	 * Metodo que seta o identificador da sala.
	 * 
	 * @param identificadorSala
	 * 		Identificador da sala.
	 */
	public void setIdentificadorSala(String identificadorSala) {
		this.identificadorSala = identificadorSala;
	}

	/**
	 * Metodo que retorna o identificador do periodo
	 * dessa turma.
	 * 
	 * @return identificadorPeriodo
	 */
	public String getIdentificadorPeriodo() {
		return identificadorPeriodo;
	}

	/**
	 * Metodo que retorna o identificador do periodo.
	 * 
	 * @param identificadorPeriodo
	 */
	public void setIdentificadorPeriodo(String identificadorPeriodo) {
		this.identificadorPeriodo = identificadorPeriodo;
	}

	/**
	 * 
	 * @return horaInicio
	 */
	public int getHoraInicio() {
		return horaInicio;
	}

	/**
	 * 
	 * @param horaInicio
	 */
	public void setHoraInicio(int horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * 
	 * @return horaFim
	 */
	public int getHoraFim() {
		return horaFim;
	}

	/**
	 * 
	 * @param horaFim
	 */
	public void setHoraFim(int horaFim) {
		this.horaFim = horaFim;
	}

	/**
	 * 
	 * @return diaDaSemana
	 */
	public String getDiaDaSemana() {
		return diaDaSemana;
	}

	/**
	 * 
	 * @param diaDaSemana
	 */
	public void setDiaDaSemana(String diaDaSemana) {
		this.diaDaSemana = diaDaSemana;
	}

	/**
	 * 
	 * @return String
	 */
	public String horario(){
		return getDiaDaSemana()+": "+getHoraInicio()+" ‡s "+getHoraFim();
	}
	
	@Override
	public String toString() {
		return "("+getIdTurma()+", "+getIdCurso()+", "+getIdentificadorProfessor()+", "
			+getIdentificadorDisciplina()+", "+getIdentificadorPeriodo()+", "+getIdentificadorSala()+")";
	}
}

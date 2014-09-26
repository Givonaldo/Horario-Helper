package br.com.H2Helper.dados;

import java.util.HashMap;

import br.com.H2Helper.modelos.Curso;
import br.com.H2Helper.modelos.Disciplina;
import br.com.H2Helper.modelos.Periodo;
import br.com.H2Helper.modelos.Professor;
import br.com.H2Helper.modelos.Sala;
import br.com.H2Helper.modelos.Turma;

/**
 * Classe onde será concentrado todos os dados relativos aos cadastros ocorridos
 * no sistema e que servirá como um concentrador de dados para realização da
 * persistencia em XML.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @version 1.0
 */
public class Dados {

	private HashMap<String, Disciplina> disciplinas = new HashMap<>();
	private HashMap<String, Professor> professores = new HashMap<>();
	private HashMap<String, Turma> turmas = new HashMap<>();
	private HashMap<String, Curso> cursos = new HashMap<>();
	private HashMap<String, Sala> salas = new HashMap<>();
	private HashMap<String, Periodo> periodo = new HashMap<>();

	private Persistencia persistencia;

	public Dados() {}

	/**
	 * Metodo que salva todas as alterações feitas no programa.
	 * 
	 * @param dados
	 *            classe do tipo <b>Dados</b> que será persistida.
	 */
	public void persistencia(Dados dados) {
		Persistencia armazenar = Persistencia.getInstance();
		armazenar.salvar(dados);
	}

	/**
	 * Metodo que Retorna um Mapa de <b>Disciplinas</b>.
	 *
	 * @return disciplinas
	 */
	public HashMap<String, Disciplina> getDisciplinas() {
		return disciplinas;
	}

	/**
	 * Metodo que seta um Mapa de <b>Disciplinas</b>.
	 * 
	 * @param disciplinas
	 *            Mapa de Disciplinas.
	 */
	public void setDisciplinas(HashMap<String, Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

	/**
	 * Metodo que Retorna um Mapa de <b>Professores</b>.
	 * 
	 * @return professores
	 */
	public HashMap<String, Professor> getProfessores() {
		return professores;
	}

	/**
	 * Metodo que seta um Mapa de <b>Professores</b>.
	 * 
	 * @param professores
	 *            Mapa de Professores.
	 */
	public void setProfessores(HashMap<String, Professor> professores) {
		this.professores = professores;
	}

	/**
	 * Metodo que Retorna um Mapa de <b>Turmas</b>.
	 * 
	 * @return turmas
	 */
	public HashMap<String, Turma> getTurmas() {
		return turmas;
	}

	/**
	 * Metodo que seta um Mapa de <b>Turmas</b>.
	 * 
	 * @param turmas
	 */
	public void setTurmas(HashMap<String, Turma> turmas) {
		this.turmas = turmas;
	}

	/**
	 * Metodo que seta um Mapa de <b>Cursos</b>.
	 * 
	 * @return cursos
	 */
	public HashMap<String, Curso> getCursos() {
		return cursos;
	}

	/**
	 * Metodo que Retorna um Mapa de <b>Cursos</b>.
	 * 
	 * @param cursos
	 *            Mapa de Cursos.
	 */
	public void setCursos(HashMap<String, Curso> cursos) {
		this.cursos = cursos;
	}

	/**
	 * Metodo que Retorna um Mapa de <b>Salas</b>.
	 * 
	 * @return salas
	 */
	public HashMap<String, Sala> getSalas() {
		return salas;
	}

	/**
	 * Metodo que seta um Mapa de <b>Salas</b>.
	 * 
	 * @param salas
	 *            Mapa de salas
	 */
	public void setSalas(HashMap<String, Sala> salas) {
		this.salas = salas;
	}

	/**
	 * Metodo que Retorna uma instancia da classe <b>Persistencia</b>.
	 * 
	 * @return persistencia
	 */
	public Persistencia getPersistencia() {
		return persistencia;
	}

	/**
	 * Metodo que seta um objeto <b>Persistencia</b>.
	 * 
	 * @param persistencia
	 *            objeto do tipo persistencia.
	 */
	public void setPersistencia(Persistencia persistencia) {
		this.persistencia = persistencia;
	}

	/**
	 * Metodo que Retorna um Mapa de <b>Periodos</b>.
	 * 
	 * @return periodo
	 */
	public HashMap<String, Periodo> getPeriodo() {
		return periodo;
	}

	/**
	 * Metodo que seta um Mapa de <b>Periodos</b>.
	 * 
	 * @param periodo
	 *            mapa de periodos.
	 */
	public void setPeriodo(HashMap<String, Periodo> periodo) {
		this.periodo = periodo;
	}

}

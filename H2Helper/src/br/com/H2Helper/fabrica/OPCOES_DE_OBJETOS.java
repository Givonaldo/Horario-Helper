package br.com.H2Helper.fabrica;

/**
 * Enumeração com todas as opções de tipos para facilitar a criação de objetos
 * concretos.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @version 1.0
 */
public enum OPCOES_DE_OBJETOS {

	DISCIPLINA("Disciplina"), PERIODO("Periodo"), SALA("Sala"), PROFESSOR("Professor"), 
	TURMA("Turma"), CURSO("Curso"),
	PROXY_CURSO("Proxy_Curso"), PROXY_DISCIPLINA("Proxy_Disciplina"), PROXY_PROFESSOR("Proxy_Professor"), PROXY_TURMA("Proxy_Turma"), 
	PROXY_PERIODO("Proxy_Periodo"), PROXY_SALA("Proxy_Sala"),PROXY_HORARIO("Proxy_Horario"),
	COMMAND_ADD_SALA("Add_Sala"), COMMAND_ALTERA_SALA("Altera_Sala"), COMMAND_REMOVE_SALA("Remove_Sala"), 
	COMMAND_ADD_TURMA("Add_Turma"), COMMAND_ALTERA_TURMA("Altera_Turma"), COMMAND_REMOVE_TURMA("Remove_Turma"), 
	COMMAND_ADD_DISCIPLINA("Add_Disciplina"), COMMAND_ALTERA_DISCIPLINA("Altera_Disciplina"), COMMAND_REMOVE_DISCIPLINA("Remove_Disciplina"),
	COMMAND_ADD_PROFESSOR("Add_Professor"), COMMAND_ALTERA_PROFESSOR("Altera_Professor"), COMMAND_REMOVE_PROFESSOR("Remove_Professor"), 
	COMMAND_ADD_CURSO("Add_Curso"), COMMAND_ALTERA_CURSO("Altera_Curso"), COMMAND_REMOVE_CURSO("Remove_Curso"), 
	COMMAND_ADD_PERIODO("Add_Periodo"), COMMAND_ALTERA_PERIODO("Altera_Periodo"), COMMAND_REMOVE_PERIODO("Remove_Periodo"),
	COMMAND_ALOCA_TURMA("Aloca_Turma");
	
	private String titulo;

	OPCOES_DE_OBJETOS(String titulo) {
		this.titulo = titulo;
	}

	/**
	 * 
	 * @return titulo
	 */
	public String getTitulo() {
		return this.titulo;
	}

}

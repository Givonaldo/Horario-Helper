package br.com.H2Helper.gerenciador;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.commands.commandCurso.CommandAdicionaCurso;
import br.com.H2Helper.commands.commandCurso.CommandAlteraCurso;
import br.com.H2Helper.commands.commandCurso.CommandRemoveCurso;
import br.com.H2Helper.commands.commandDisciplina.CommandAdicionaDisciplinaAoPeriodo;
import br.com.H2Helper.commands.commandDisciplina.CommandAlteraDiscilina;
import br.com.H2Helper.commands.commandDisciplina.CommandRemoveDisciplina;
import br.com.H2Helper.commands.commandPeriodo.CommandAdicionaPeriodo;
import br.com.H2Helper.commands.commandPeriodo.CommandRemovePeriodo;
import br.com.H2Helper.commands.commandProfessor.CommandAdicionaProfessor;
import br.com.H2Helper.commands.commandProfessor.CommandAlteraProfessor;
import br.com.H2Helper.commands.commandProfessor.CommandRemoveProfessor;
import br.com.H2Helper.commands.commandSala.CommandAdicionaSala;
import br.com.H2Helper.commands.commandSala.CommandAlteraSala;
import br.com.H2Helper.commands.commandSala.CommandRemoveSala;
import br.com.H2Helper.commands.commandTurma.CommandAdicionaTurma;
import br.com.H2Helper.commands.commandTurma.CommandAlteraTurma;
import br.com.H2Helper.commands.commandTurma.CommandRemoveTurma;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.fabrica.FabricaCommand;
import br.com.H2Helper.fabrica.FabricaIF;
import br.com.H2Helper.fabrica.OPCOES_DE_OBJETOS;

/**
 * Classe gerenciadora, que funciona como um ponto de delegação das ações 
 * realizadas pelo sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @version 1.0
 */
public class Gerenciador {

	private CommandIF addProfessor, alteraProfessor, removeProfessor, addDisciplina, alteraDisciplina
						, removeDisciplina, addSala, alteraSala, removeSala, addPeriodo, removePeriodo
						, addTurma, alteraTurma, removeTurma, addCurso, alteraCurso, removeCurso;
	private FabricaIF fabrica = new FabricaCommand();
	private Dados dados;
	private Persistencia persistencia = Persistencia.getInstance();
	
	public Gerenciador(){
		
		addProfessor = (CommandAdicionaProfessor) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_ADD_PROFESSOR);
		alteraProfessor = (CommandAlteraProfessor) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_ALTERA_PROFESSOR);
		removeProfessor = (CommandRemoveProfessor) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_REMOVE_PROFESSOR);
		addDisciplina = (CommandAdicionaDisciplinaAoPeriodo) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_ADD_DISCIPLINA);
		alteraDisciplina = (CommandAlteraDiscilina) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_ALTERA_DISCIPLINA);
		removeDisciplina = (CommandRemoveDisciplina) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_REMOVE_DISCIPLINA);		
		addSala = (CommandAdicionaSala) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_ADD_SALA); 
		alteraSala = (CommandAlteraSala) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_ALTERA_SALA);
		removeSala = (CommandRemoveSala) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_REMOVE_SALA);				
		addPeriodo = (CommandAdicionaPeriodo) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_ADD_PERIODO);
		removePeriodo = (CommandRemovePeriodo) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_REMOVE_PERIODO);		
		addTurma = (CommandAdicionaTurma) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_ADD_TURMA);
		alteraTurma = (CommandAlteraTurma) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_ALTERA_TURMA);
		removeTurma = (CommandRemoveTurma) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_REMOVE_TURMA);
		addCurso = (CommandAdicionaCurso) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_ADD_CURSO);
		alteraCurso = (CommandAlteraCurso) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_ALTERA_CURSO);
		removeCurso = (CommandRemoveCurso) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_REMOVE_CURSO);
		dados = (Dados) persistencia.load();
	}
	
	/**
	 * Metodo que delega a responsabilidade da criação de um professor para, um comando de 
	 * criação de professor.
	 * 
	 * @param idProfessor
	 * 		Identificador do professor a ser criado.
	 * @param nome
	 * 		Nome do professor a ser criado.
	 */
	public void addProfessor(String idProfessor, String nome) {
		
		addProfessor.execute(idProfessor, nome);
	}

	/**
	 * Metodo que delega a responsabilidade de alteração de um professor 
	 * cadastrado no sistema, para um comando de alteração de professor. 
	 * 
	 * @param idProfessor
	 * 		Identificador do professor que será alterado.	
	 * @param novoNome
	 * 		Novo valor para o nome do professor.
	 */
	public void alteraProfessor(String idProfessor, String novoNome) {
		
		alteraProfessor.execute(idProfessor, novoNome);
	}

	/**
	 * Metodo que delega a responsabilidade de remoção de um professor para um 
	 * comando de remoção de professsores.
	 * 
	 * @param matricula
	 * 		Identificador do professor que se deseja remover.
	 */
	public void removeProfessor(String matricula) {
		
		removeProfessor.execute(matricula);
	}

	/**
	 * Metodo que retorna um professor no formato toString.
	 * 
	 * @param identificador
	 * @return Professor
	 */
	public String getProfessor(String identificador) {
		
		return dados.getProfessores().get(identificador).toString();
	}

	/**
	 * Metodo que delega a responsabilidade de criação de uma nova disciplina para um 
	 * comando de criação de disciplina.
	 * 
	 * @param identificadorDisciplina
	 * 			Indentificador ínico para uma disciplina, será formado pelo identificadorDisciplina 
	 * 			e o identificadorCurso.
	 * @param nomeDisciplina
	 * 			Nome que descreve a disciplina.
	 * @param cargaHoraria
	 * 			Carga horaria no formato de interiros.
	 * @param identificadorCurso
	 * 			Identificador do curso ao qual essa disciplina pertence.
	 * @param identificadorperiodo
	 * 			Identificador do periodo ao qual essa disciplina pertence.
	 */
	public void addDisciplinaAoPeriodo(String identificadorDisciplina,
			String nomeDisciplina, int cargaHoraria, String identificadorCurso,
			String identificadorperiodo) {
		
		addDisciplina.execute(identificadorDisciplina, nomeDisciplina, cargaHoraria, 
				identificadorCurso, identificadorperiodo);
	}

	/**
	 * Metodo que delega a responsabilidade de alteração de uma determinada disciplina 
	 * para um comando de alteração de disciplina.
	 * 
	 * @param idCurso
	 * 		Identificador do curso ao qual essa disciplina pertence.
	 * @param sigla
	 * 		Sigla da disciplina.
	 * @param atributo
	 * 		O atributo que se deseja alterar
	 * @param novoValor
	 * 		O novo valor.
	 * 
	 */
	public void alteraDisciplina(String idCurso, String sigla, String atributo,
			String novoValor) {
		
		alteraDisciplina.execute(idCurso, sigla, atributo, novoValor);
	}

	/**
	 * Metodo que delega a responsabilidade de remoção de uma disciplina para 
	 * um comando de remoção de disciplina.
	 * 
	 * @param idCurso
	 * 		Identificador do curso ao qual essa disciplina pertence.
	 * @param idDisciplina
	 * 		Identificador da disciplina que se deseja excluir.
	 */
	public void removeDisciplina(String idCurso, String idDisciplina) {
		
		removeDisciplina.execute(idCurso, idDisciplina);
	}

	/**
	 * Metodo que retorna uma disciplina no formato toString.
	 * 
	 * @param idCurso
	 * 		Identificador do curso ao qual essa disciplina pertence.
	 * @param idDisciplina
	 * 		Identificador da disciplina.
	 * @return Disciplina.
	 */
	public String getDisciplina(String idCurso, String idDisciplina) {
		
		return dados.getDisciplinas().get(idCurso+" - "+idDisciplina).toString();
	}

	/**
	 * Metodo que delega a responsabilidade de criação de uma nova 
	 * sala para um comando de criação de salas.
	 *  
	 * @param idSala
	 * 		Identificador da sala.
	 * @param bloco
	 * 		Identificador do bloco.
	 */
	public void addSala(String idSala, String bloco) {
		
		addSala.execute(idSala, bloco);
	}

	/**
	 * Metodo que delega a responsabilidade de alteração de uma 
	 * determinada sala, para o comando de alteração de salas.
	 * 
	 * @param idSala
	 * 		Identificador da sala que se deseja alterar.
	 * @param novoBloco
	 * 		Novo valor para o bloco dessa sala.
	 */
	public void alteraSala(String idSala, String novoBloco) {
		
		alteraSala.execute(idSala, novoBloco);
	}

	/**
	 * Metodo que delega a responsabilidade de remoção de uma sala
	 * para um comando de remoção de salas.
	 * 
	 * @param idSala
	 * 		Identificador da sala que se deseja remover.
	 */
	public void removeSala(String idSala) {
		
		removeSala.execute(idSala);
	}

	/**
	 * Metodo que delega a responsabilidade de criação de um novo periodo
	 * para uma classe commando de criação de periodos.
	 *  
	 * @param identificadorPeriodo
	 * 		Identificador unico do periodo a ser criado.
	 * @param idCurso
	 * 		Identificador do curso ao qual esse periodo será associado.
	 */
	public void addPeriodo(String identificadorPeriodo, String idCurso) {
		
		addPeriodo.execute(identificadorPeriodo, idCurso);
	}
	
	/**
	 * Metodo que delega a responsabilidade de remoção de um determinado 
	 * periodo para um comando de remoção de periodos.
	 * 
	 * @param idCurso
	 * 		Identificador do curso ao qual esse periodo está associado.
	 * @param nomePeriodo
	 * 		Nome do periodo a ser removido.
	 */
	public void removePeriodo(String idCurso, String nomePeriodo) {
		
		removePeriodo.execute(idCurso, nomePeriodo);
	}

	/**
	 * Metodo que delega a responsabilidade de criação de uma nova turma 
	 * para um comando de criação de turmas <b>(CommandAdiciionaTurma)</b>.
	 * 
	 * @param idTurma
	 * 		Identificador inico da turma a ser criada.
	 * @param idCurso
	 * 		Identificador do curso ao qual essa turma pertence.
	 * @param identificadorProfessor
	 * 		Identificador do professor.
	 * @param identificadorDisciplina
	 * 		Identificador da disciplia
	 * @param identificadorSala
	 * 		Identificador da sala
	 * @param identificadorPeriodo
	 * 		Identificador do periodo ao qual essa turma pertence.
	 */
	public void addTurma(String idTurma, String idCurso,
			String identificadorProfessor, String identificadorDisciplina,
			String identificadorSala, String identificadorPeriodo) {
		
		addTurma.execute(idTurma, idCurso, identificadorProfessor, identificadorDisciplina, identificadorSala
				, identificadorPeriodo);
	}
	
	/**
	 * Metodo que delega a responsabilidade de alteração de uma 
	 * turma para um comando de alteração de turmas <b>(CommandAlteraTurma)</b>. 
	 * @param idTurma
	 * 		Identificador da turma a ser alterada.
	 * @param campo
	 * 		Atributo que sofrerar a alteração.
	 * @param novoValor
	 * 		Novo valor que será substituido.
	 */
	public void alteraTurma(String idTurma, String campo, String novoValor) {
		
		alteraTurma.execute(idTurma, campo, novoValor);
	}

	/**
	 * Metodo que delega a responsabilidade de remoção de uma determinada 
	 * turma para um comando de remoção de turmas <b>(CommandRemoveTurma)</b>.
	 * @param idTurma
	 * 		Identificador da turma a ser removida.
	 */
	public void removerTurma(String idTurma) {
		
		removeTurma.execute(idTurma);
	}

	/**
	 * Metodo que retorna uma turma no formato toString
	 * 
	 * @param idTurma
	 * 		Identificador da turma.
	 * @return Turma
	 */
	public String getTurma(String idTurma) {
		
		return dados.getTurmas().get(idTurma).toString();
	}

	/**
	 * Metodo que delega a responsabilidade de criação de um novo
	 * curso para um comando de criação de cursos <b>(CommandAdicionaCurso)</b>.
	 * @param identificadorCurso
	 * 		Identificador único do curso a ser criado.
	 * @param nome
	 * 		Nome do curso.
	 */
	public void addCurso(String identificadorCurso, String nome) {

		addCurso.execute(identificadorCurso, nome);
	}

	/**
	 * Metodo que delega a responsabilidade de alteração de um determinado curso para 
	 * um comando de alteração de cursos <b>(CommandAlteraCurso)</b>.
	 * 
	 * @param identificador
	 * 		Identificador do curso a ser alterado.
	 * @param novoValor
	 * 		Novo valor para o nome do curso.
	 */
	public void alterarCurso(String identificador, String novoValor) {
		
		alteraCurso.execute(identificador, novoValor);
	}
	
	/**
	 * Metodo que delega a responsabilidade de remoção de um determinado 
	 * curso para um comando de remoção de cursos <b>(CommandRemoveCurso)</b>. 
	 * 
	 * @param identificador
	 * 		Identificador do curso a ser removido.
	 */
	public void removeCurso(String identificador) {
		
		removeCurso.execute(identificador);
	}

	/**
	 * Metodo que retorna um curso no formato toString
	 * 
	 * @param idCurso
	 * 		Identificador do curso.
	 * @return Curso.
	 */
	public String getCurso(String idCurso) {
		
		return dados.getCursos().get(idCurso).toString();
	}

	/**
	 * Metodo que retorna uma sala no formato toString.
	 * @param idSala
	 * 		Identificador da sala.
	 * @return Sala
	 */
	public String getSala(String idSala) {
		
		return dados.getSalas().get(idSala).toString();
	}

	/**
	 * Metodo que retorna um periodo no formato toString.
	 * 
	 * @param idPeriodo
	 * 		Identificador do periodo.
	 * @param idCurso
	 * 		Identificador do curso ao qual esse periodo esta associado.
	 * @return Periodo
	 */
	public String getPeriodo(String idPeriodo, String idCurso) {
		
		String atributo = idPeriodo +" - "+ idCurso;
		return dados.getPeriodo().get(atributo).toString();
	}
}

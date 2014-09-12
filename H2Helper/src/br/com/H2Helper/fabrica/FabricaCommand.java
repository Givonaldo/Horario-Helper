package br.com.H2Helper.fabrica;

import br.com.H2Helper.commands.commandCurso.CommandAdicionaCurso;
import br.com.H2Helper.commands.commandCurso.CommandAlteraCurso;
import br.com.H2Helper.commands.commandCurso.CommandRemoveCurso;
import br.com.H2Helper.commands.commandDisciplina.CommandAdicionaDisciplinaAoPeriodo;
import br.com.H2Helper.commands.commandDisciplina.CommandAlteraDiscilina;
import br.com.H2Helper.commands.commandDisciplina.CommandRemoveDisciplina;
import br.com.H2Helper.commands.commandHorario.CommandAlocaTurmaAoHorario;
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

/**
 * Classe onde foi utilizado o padrão de projeto Method Factory, a fim de
 * fornecer um metodo para criação de objetos concretos.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see FabricaIF
 */
public class FabricaCommand implements FabricaIF {

	@Override
	public Object getObject(OPCOES_DE_OBJETOS tipo) {

		if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_ADD_PROFESSOR)) {
			return new CommandAdicionaProfessor();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_ALTERA_PROFESSOR)) {
			return new CommandAlteraProfessor();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_REMOVE_PROFESSOR)) {
			return new CommandRemoveProfessor();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_ADD_DISCIPLINA)) {
			return new CommandAdicionaDisciplinaAoPeriodo();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_ALTERA_DISCIPLINA)) {
			return new CommandAlteraDiscilina();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_REMOVE_DISCIPLINA)) {
			return new CommandRemoveDisciplina();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_ADD_CURSO)) {
			return new CommandAdicionaCurso();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_ALTERA_CURSO)) {
			return new CommandAlteraCurso();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_REMOVE_CURSO)) {
			return new CommandRemoveCurso();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_ADD_TURMA)) {
			return new CommandAdicionaTurma();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_ALTERA_TURMA)) {
			return new CommandAlteraTurma();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_REMOVE_TURMA)) {
			return new CommandRemoveTurma();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_ADD_SALA)) {
			return new CommandAdicionaSala();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_ADD_PERIODO)) {
			return new CommandAdicionaPeriodo();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_REMOVE_PERIODO)) {
			return new CommandRemovePeriodo();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_REMOVE_SALA)) {
			return new CommandRemoveSala();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_ALTERA_SALA)) {
			return new CommandAlteraSala();
		}else if (tipo.equals(OPCOES_DE_OBJETOS.COMMAND_ALOCA_TURMA)){
			return new CommandAlocaTurmaAoHorario();
		}
		return null;
	}

}

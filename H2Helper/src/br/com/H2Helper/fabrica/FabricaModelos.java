package br.com.H2Helper.fabrica;

import br.com.H2Helper.modelos.Curso;
import br.com.H2Helper.modelos.Disciplina;
import br.com.H2Helper.modelos.Periodo;
import br.com.H2Helper.modelos.Professor;
import br.com.H2Helper.modelos.Sala;
import br.com.H2Helper.modelos.Turma;

/**
 * Classe que representa uma fabrica de modelos, ela é responsável por criar as
 * classes dos tipos: Professor, Disciplina, Turma, Periodo e Sala.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @version 1.0
 * @see FabricaIF
 */
public class FabricaModelos implements FabricaIF {

	@Override
	public Object getObject(OPCOES_DE_OBJETOS tipo) {
		if (tipo.equals(OPCOES_DE_OBJETOS.PROFESSOR)) {
			return new Professor();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.DISCIPLINA)) {
			return new Disciplina();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.SALA)) {
			return new Sala();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.PERIODO)) {
			return new Periodo();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.CURSO)) {
			return new Curso();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.TURMA)) {
			return new Turma();
		} else {
			return null;
		}
	}

}
package br.com.H2Helper.commands.commandProfessor;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.fabrica.FabricaIF;
import br.com.H2Helper.fabrica.FabricaModelos;
import br.com.H2Helper.fabrica.OPCOES_DE_OBJETOS;
import br.com.H2Helper.modelos.Professor;

/**
 * Classe onde foi utilizado o padrão de projeto Command e que representa o
 * comando de criação de um novo professor.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see CommandIF
 */
public class CommandAdicionaProfessor implements CommandIF {

	private Persistencia persistencia;
	private Professor professor;
	private FabricaIF fabrica = new FabricaModelos();
	private Dados dados;

	public CommandAdicionaProfessor() {
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
		professor = (Professor) fabrica.getObject(OPCOES_DE_OBJETOS.PROFESSOR);
	}

	@Override
	public void execute(Object... atributos) {

		professor.setIdProfessor((String) atributos[0]);
		professor.setNome((String) atributos[1]);
		dados.getProfessores().put((String) atributos[0], professor);
		dados.persistencia(dados);
	}

}

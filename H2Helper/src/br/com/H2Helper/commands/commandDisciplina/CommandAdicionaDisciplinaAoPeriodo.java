package br.com.H2Helper.commands.commandDisciplina;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.fabrica.FabricaIF;
import br.com.H2Helper.fabrica.FabricaModelos;
import br.com.H2Helper.fabrica.OPCOES_DE_OBJETOS;
import br.com.H2Helper.modelos.Disciplina;

/**
 * Classe onde foi utilizado o padrão Command e que representa o comando de
 * criação de uma nova disciplina.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti
 * @see CommandIF
 */
public class CommandAdicionaDisciplinaAoPeriodo implements CommandIF {

	private FabricaIF fabrica = new FabricaModelos();
	private Disciplina disciplina;
	private Persistencia persistencia;
	private Dados dados;

	public CommandAdicionaDisciplinaAoPeriodo() {

		persistencia = Persistencia.getInstance();
		disciplina = (Disciplina) fabrica
				.getObject(OPCOES_DE_OBJETOS.DISCIPLINA);
		dados = (Dados) persistencia.load();
	}

	@Override
	public void execute(Object... atributos) {

		String identificador = (String)atributos[3]+" - "+(String)atributos[0];
		disciplina.setIdentificadorDisciplina(identificador);
		disciplina.setNomeDisciplina((String) atributos[1]);
		disciplina.setCargaHoraria(((int) atributos[2]));
		disciplina.setIdentificadorCurso((String) atributos[3]);
		disciplina.setIdentificadorperiodo((String) atributos[4]);
		
		dados.getDisciplinas().put(identificador, disciplina);
		
		dados.persistencia(dados);
	}

}

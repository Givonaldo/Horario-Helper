package br.com.H2Helper.commands.commandTurma;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.fabrica.FabricaIF;
import br.com.H2Helper.fabrica.FabricaModelos;
import br.com.H2Helper.fabrica.OPCOES_DE_OBJETOS;
import br.com.H2Helper.modelos.Turma;

/**
 * Classe onde foi utilizado o padrção de projeto Command e que representa o 
 * comando de criação de uma nova Turma.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see CommandIF
 */
public class CommandAdicionaTurma implements CommandIF {

	private Dados dados;
	private Persistencia persistencia;
	private Turma turma;
	private FabricaIF fabrica = new FabricaModelos();
	
	public CommandAdicionaTurma(){
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
		turma = (Turma) fabrica.getObject(OPCOES_DE_OBJETOS.TURMA);
	}
	
	@Override
	public void execute(Object... atributos) {
		
		turma.setIdTurma((String)atributos[0]);
		turma.setIdCurso((String)atributos[1]);
		turma.setIdentificadorProfessor((String)atributos[2]);
		turma.setIdentificadorDisciplina((String)atributos[3]);
		turma.setIdentificadorSala((String)atributos[4]);
		turma.setIdentificadorPeriodo((String) atributos[5]);
		
		dados.getTurmas().put((String)atributos[0], turma);
		dados.persistencia(dados);
	}


}

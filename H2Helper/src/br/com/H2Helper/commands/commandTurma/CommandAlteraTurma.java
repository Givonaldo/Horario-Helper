package br.com.H2Helper.commands.commandTurma;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;

/**
 * Classe onde foi utilizado o padrão de projeto Command e que representa o 
 * comando de alteração de uma turma previamente cadastrada no sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see CommandIF
 */
public class CommandAlteraTurma implements CommandIF {

	private static Dados dados;
	private Persistencia persistencia;
	
	public CommandAlteraTurma(){
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
	}
	
	@Override
	public void execute(Object... atributos) {
		
		if (((String)atributos[1]).equals("Nome")){
			dados.getTurmas().get((String)atributos[0]).setIdTurma((String)atributos[2]);
		}else if (((String)atributos[1]).equals("Professor")){
			dados.getTurmas().get((String)atributos[0]).setIdentificadorProfessor((String)atributos[2]);
		}else if (((String)atributos[1]).equals("Disciplina")){
			dados.getTurmas().get((String)atributos[0]).setIdentificadorDisciplina((String)atributos[2]);
		}else if (((String)atributos[1]).equals("Sala")){
			dados.getTurmas().get((String)atributos[0]).setIdentificadorSala((String)atributos[2]);
		}else if (((String)atributos[1]).equals("Periodo")){
			dados.getTurmas().get((String)atributos[0]).setIdentificadorPeriodo((String)atributos[2]);
		}
		dados.persistencia(dados);
	}
	
}

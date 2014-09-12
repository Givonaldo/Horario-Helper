package br.com.H2Helper.commands.commandTurma;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;

/**
 * Classe onde foi utilizado o padrão de projeto Command e que representa o 
 * comando de remoção de uma turma.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see CommandIF
 */
public class CommandRemoveTurma implements CommandIF {

	private Dados dados;
	private Persistencia persistencia;
	
	public CommandRemoveTurma() {
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
	}
	
	@Override
	public void execute(Object... atributos) {
		
		dados.getTurmas().remove((String)atributos[0]);
		dados.persistencia(dados);
	}

}

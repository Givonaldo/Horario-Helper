package br.com.H2Helper.commands.commandDisciplina;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;

/**
 * Classe onde foi utilizado o padrão Command e que representa o 
 * comando de remoção de uma determinada disciplina.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti
 * @see CommandIF
 */
public class CommandRemoveDisciplina implements CommandIF {

	private Dados dados;
	private Persistencia persistencia;
	
	public CommandRemoveDisciplina() {
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
	}
	
	@Override
	public void execute(Object... atributos) {
		
		dados.getDisciplinas().remove((String)atributos[0]+" - "+atributos[1]);
		dados.persistencia(dados);
	}

	

}

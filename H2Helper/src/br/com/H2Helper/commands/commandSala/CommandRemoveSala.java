package br.com.H2Helper.commands.commandSala;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;

/**
 * Classe onde foi utilizado o padr�o Command e que representa o 
 * comando de remo��o de uma sala.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see CommandIF
 */
public class CommandRemoveSala implements CommandIF {

	private Dados dados;
	private Persistencia persistencia;
	
	public CommandRemoveSala(){
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
	}
	
	@Override
	public void execute(Object... atributos) {
		
		dados.getSalas().remove((String)atributos[0]);
		dados.persistencia(dados);
	}

}

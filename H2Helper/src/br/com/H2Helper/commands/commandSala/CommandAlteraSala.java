package br.com.H2Helper.commands.commandSala;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;

/**
 * Classe onde foi utilizado o padrão Command e que representa o 
 * comando de alteração do Bloco de uma sala.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see CommandIF
 */
public class CommandAlteraSala implements CommandIF {

	private Dados dados;
	private Persistencia persistencia;
	
	public CommandAlteraSala() {
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
	}
	
	@Override
	public void execute(Object... atributos) {
		
		dados.getSalas().get((String)atributos[0]).setBloco((String)atributos[1]);
		dados.persistencia(dados);
	}
	
}

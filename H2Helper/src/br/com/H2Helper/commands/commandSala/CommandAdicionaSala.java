package br.com.H2Helper.commands.commandSala;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.fabrica.FabricaIF;
import br.com.H2Helper.fabrica.FabricaModelos;
import br.com.H2Helper.fabrica.OPCOES_DE_OBJETOS;
import br.com.H2Helper.modelos.Sala;

/**
 * Classe onde foi utilizado o padrão Command e que representa o 
 * comando de criação de uma nova Sala.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see CommandIF
 */
public class CommandAdicionaSala implements CommandIF {

	private Dados dados;
	private Persistencia persistencia;
	private Sala sala;
	private FabricaIF fabrica = new FabricaModelos(); 
	
	public CommandAdicionaSala(){
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
		sala = (Sala) fabrica.getObject(OPCOES_DE_OBJETOS.SALA);
	}
	
	@Override
	public void execute(Object... atributos) {
		
		sala.setIdSala((String)atributos[0]);
		sala.setBloco((String)atributos[1]);
		dados.getSalas().put((String)atributos[0], sala);
		dados.persistencia(dados);
	}

	
}

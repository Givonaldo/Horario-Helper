package br.com.H2Helper.commands.commandProfessor;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;

/**
 * Classe onde foi utilizado o padrão Command e que representa o 
 * comando de remoção de um determinado professor.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see CommandIF
 */
public class CommandRemoveProfessor implements CommandIF {

	private Dados dados;
	private Persistencia persistencia;
	
	public CommandRemoveProfessor(){
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
	}
	
	@Override
	public void execute(Object... atributos) {
		
		dados.getProfessores().remove(atributos[0]);
		dados.persistencia(dados);
	}
}

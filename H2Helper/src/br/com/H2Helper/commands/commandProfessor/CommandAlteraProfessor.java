package br.com.H2Helper.commands.commandProfessor;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;

/**
 * Classe onde foi utilizado o padrão Command e que representa o 
 * comando de alteração de um determinado professor.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see CommandIF
 */
public class CommandAlteraProfessor implements CommandIF{

	private Persistencia persistencia;
	private Dados dados;
	
	public CommandAlteraProfessor(){
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
	}
	
	@Override
	public void execute(Object... atributos) {
		
		dados.getProfessores().get(atributos[0]).setNome((String)atributos[1]);
		dados.persistencia(dados);
	}

}

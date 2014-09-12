package br.com.H2Helper.commands.commandHorario;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;

/**
 * Classe command que realiza os comandos de persistencia 
 * dos dados que envolve alocação de Horario.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see CommandIF
 */
public class CommandAlocaTurmaAoHorario implements CommandIF {

	private Dados dados;
	private Persistencia persistencia;

	public CommandAlocaTurmaAoHorario() {
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
	}
	
	@Override
	public void execute(Object... atributos) {

		dados.getTurmas().get((String) atributos[0]).setDiaDaSemana((String) atributos[1]);
		dados.getTurmas().get((String)atributos[0]).setHoraInicio((int)atributos[2]);
		dados.getTurmas().get((String)atributos[0]).setHoraFim((int)atributos[3]);
		dados.persistencia(dados);
	}

	
}

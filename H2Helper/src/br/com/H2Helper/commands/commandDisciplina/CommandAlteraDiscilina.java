package br.com.H2Helper.commands.commandDisciplina;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;

/**
 * Classe onde foi utilizado o padrão Command e que representa o 
 * comando de alteração de uma disciplina.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti
 * @see CommandIF 
 */
public class CommandAlteraDiscilina implements CommandIF {

	private Dados dados;
	private Persistencia persistencia;
	private String identificador;
	
	public CommandAlteraDiscilina() {
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
	}
	
	@Override
	public void execute(Object... atributos) {

		identificador = atributos[0]+" - "+atributos[1];
		
		if ((atributos[2]).equals("Nome")){
			dados.getDisciplinas().get(identificador).setNomeDisciplina((String)atributos[3]);
		}else if ((atributos[2]).equals("Carga Horaria")) {
			dados.getDisciplinas().get(identificador).setCargaHoraria(Integer.parseInt((String)atributos[3]));
		}else if ((atributos[2]).equals("Identificador Curso")){
			dados.getDisciplinas().get(identificador).setIdentificadorCurso((String)atributos[3]);
		}else if ((atributos[2]).equals("Identificador Periodo")){
			dados.getDisciplinas().get(identificador).setIdentificadorperiodo((String)atributos[3]);
		}
		dados.persistencia(dados);		
	}

}

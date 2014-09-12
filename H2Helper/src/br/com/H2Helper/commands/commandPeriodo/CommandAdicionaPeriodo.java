package br.com.H2Helper.commands.commandPeriodo;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.fabrica.FabricaIF;
import br.com.H2Helper.fabrica.FabricaModelos;
import br.com.H2Helper.fabrica.OPCOES_DE_OBJETOS;
import br.com.H2Helper.modelos.Periodo;

/**
 * Classe onde foi utilizado o padrão de projeto Command e 
 * que representa o comando de criação de um novo periodo.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see CommandIF
 */
public class CommandAdicionaPeriodo implements CommandIF {

	private Dados dados;
	private Persistencia persistencia;
	private String chave;
	private Periodo periodo;
	private FabricaIF fabrica = new FabricaModelos();
	
	public CommandAdicionaPeriodo(){
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
		periodo = (Periodo) fabrica.getObject(OPCOES_DE_OBJETOS.PERIODO);
	}
	
	@Override
	public void execute(Object... atributos) {
		
		chave = (String)atributos[0]+" - "+(String)atributos[1];
		periodo.setIdentificadorPeriodo((String)atributos[0]);
		periodo.setIdCurso((String)atributos[1]);
		dados.getPeriodo().put(chave, periodo);
		dados.persistencia(dados);
	}
	
}

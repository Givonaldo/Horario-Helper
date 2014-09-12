package br.com.H2Helper.commands.commandPeriodo;

import java.util.Set;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;

/**
 * Classe onde foi utilizado o padrão Command e que representa o 
 * comando de remoção de um determinado periodo.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see CommandIF
 */
public class CommandRemovePeriodo implements CommandIF {

	private Dados dados;
	private Persistencia persistencia;
	private Set<String> chaves;
	
	public CommandRemovePeriodo(){
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
		chaves = dados.getTurmas().keySet();
	}
	
	@Override
	public void execute(Object... atributos) {
		
		String chave = (String)atributos[1] +" - "+ (String)atributos[0];
		removeTurmasAssociadasAoPeriodo(chave);
		dados.getPeriodo().remove(chave);
		dados.persistencia(dados);
	}
	
	/** Metodo que realiza a remoção de todas as turmas que estão associadas 
	 * ao periodo que será excluido.
	 * 
	 * @param chavePeriodo
	 * 		identificador do periodo que será excluido
	 */
	public void removeTurmasAssociadasAoPeriodo(String chavePeriodo){
		
		for (String chave : chaves) {
			if (dados.getTurmas().get(chave).getIdentificadorPeriodo()
					.equals(dados.getPeriodo().get(chavePeriodo).getIdentificadorPeriodo())){
				dados.getTurmas().remove(chave);
			}
		}
		dados.persistencia(dados);
	}

}

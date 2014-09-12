package br.com.H2Helper.commands;

import br.com.H2Helper.gerenciador.Gerenciador;

/**
 * Interface "Super Tipo" onde foi utilizado o padr�o de 
 * projeto command que permite parametrizar outros objetos 
 * com diferentes solicita��es, facilitando as realiza��es 
 * dos comandos necess�rios do sistema.
 *  
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see Gerenciador
 */
public interface CommandIF {
	
	/** 
	 * Metodo que ser� implementado por todas as classes que 
	 * implementar essa interface, a fim de fornecer um metodo 
	 * com o n�mero de dados passados no parametro generico para  
	 * a execu��o dos comando. 
	 * 
	 * @param atributos
	 * 		Representa os dados necess�rios para a 
	 * 		execu��o do comando.
	 */
	public void execute(Object... atributos);
		
}

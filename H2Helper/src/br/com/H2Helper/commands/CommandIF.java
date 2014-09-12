package br.com.H2Helper.commands;

import br.com.H2Helper.gerenciador.Gerenciador;

/**
 * Interface "Super Tipo" onde foi utilizado o padrão de 
 * projeto command que permite parametrizar outros objetos 
 * com diferentes solicitações, facilitando as realizações 
 * dos comandos necessários do sistema.
 *  
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see Gerenciador
 */
public interface CommandIF {
	
	/** 
	 * Metodo que será implementado por todas as classes que 
	 * implementar essa interface, a fim de fornecer um metodo 
	 * com o número de dados passados no parametro generico para  
	 * a execução dos comando. 
	 * 
	 * @param atributos
	 * 		Representa os dados necessários para a 
	 * 		execução do comando.
	 */
	public void execute(Object... atributos);
		
}

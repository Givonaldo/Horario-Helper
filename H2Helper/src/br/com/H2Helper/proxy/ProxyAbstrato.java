package br.com.H2Helper.proxy;

/**
 * Classe abstrata do padr�o de projeto estrutural proxy, ela 
 * fornece metodos comuns para todos as subclasses proxy. 
 *  
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @version 1.0
 */
public abstract class ProxyAbstrato {

	public ProxyAbstrato(){
		
	}
	
	/**
	 * Metodo que verifica se o dado passado no par�metro � 
	 * nulo ou vazio.
	 *  
	 * @param atributo
	 * 		Dado a ser verificado se � nulo ou vazio.
	 * @return boolean
	 */
	public boolean verificaNuloOuVazio(String atributo){
		if (!atributo.matches(RECURSOS.VALIDA_ATRIBUTO.getTitulo()) || 
				atributo == null){
			return false;
		}else {
			return true;
		}
	}
	
	/**
	 * Metodo abstrato que verifica se um determinado objeto j� encontra-se
	 * cadastrado no sistema.
	 * 
	 * @param parametro
	 * 		Identificador do objeto que se est� procurando.
	 * @return boolean
	 */
	public abstract boolean verificaExistencia(String parametro);
	
	/**
	 * Metodo abstrato que verifica se os dados passados no parametros
	 * s�o v�lidos.
	 *  
	 * @param atributo
	 * 		Conjunto de parametro que ser� verificados. 
	 * @return boolean
	 */
	public abstract boolean verificaAtributo(String... atributo);
	
}

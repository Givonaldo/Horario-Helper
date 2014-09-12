package br.com.H2Helper.fabrica;

/**
 * Interface super tipo onde foi utilizado o padrão de projeto Abstract Factory
 * a fim de fornecer uma interface comum para uma familia de fabricas agrupadas.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 */
public interface FabricaIF {

	/**
	 * Metodo Method Factory, que será imlementados por todas as classe que
	 * fizerem parte da familia de fabricas Abstract Factory.
	 * 
	 * @param fabrica
	 * @return Fabrica
	 */
	public abstract Object getObject(OPCOES_DE_OBJETOS fabrica);

}

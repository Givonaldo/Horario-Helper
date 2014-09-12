package br.com.H2Helper.proxy;

/**
 * Enumeração com todas as opções de validação de dados
 * do sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @version 1.0
 */
public enum RECURSOS {

	VALIDA_MATRICULA("[0-9]{1,}"),
	VALIDA_PERIODO("([1-9]{1})|([0-9]{4}\\.[1-9]{1})"),
	VALIDA_NOME("(([A-Za-z]{1,})*(\\D{1,}))"),
	VALIDA_BLOCO("[A-Z]{1}"),
	VALIDA_SIGLA_PERIODO("[1-9]{1,}+.+[1-9]{1}"), 
	VALIDA_SIGLA("([^\\S]*((\\w){1,}))"),
	VALIDA_ATRIBUTO("((([^\\S])*(\\.){1,})|([A-Za-z]{1,})([^\\S]{1,})*(\\D{1,})|([^\\S]*((\\w){1,6})))"), 
	VALIDA_NOME_SALA("(((?i)Sala)*([\\s])*([\\d]{1,})|(([A-Za-z]{1,})*(\\D{1,})))|(([A-Za-z]{1,})*(\\D{1,})*([1-9]{1,}))");
	
	private String titulo;
	
	RECURSOS(String titulo){
		this.titulo = titulo;
	}
	
	/**
	 * Retorna o titulo da Enumeração.
	 * 
	 * @return titulo
	 */
	public String getTitulo(){
		return this.titulo;
	}
	
}

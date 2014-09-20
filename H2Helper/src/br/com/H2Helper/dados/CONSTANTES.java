package br.com.H2Helper.dados;

/**
 * Enumeração que fornece um caminho relativo para a realização 
 * da persistencia dos dados cadastrados no programa atravÃ©s de XML.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @version 1.0
 */
public enum CONSTANTES {

	CAMINHO(System.getProperty("user.dir") + System.getProperty("file.separator") 
			+ "src"+System.getProperty("file.separator")+"br"+System.getProperty("file.separator")+
			"com"+System.getProperty("file.separator")+"H2Helper"+System.getProperty("file.separator")+"dados" + System.getProperty("file.separator")), 
			ARQUIVO_UNICO("Arquivo_Unico.xml");
	
	
	private String titulo;
	CONSTANTES(String titulo){
		this.titulo = titulo;
	}
	
	public String getTitulo(){
		return this.titulo;
	}
	
}

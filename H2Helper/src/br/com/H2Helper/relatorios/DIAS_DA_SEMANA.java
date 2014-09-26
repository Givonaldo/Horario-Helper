package br.com.H2Helper.relatorios;

/**
 * Enumera��o que contem todos os dias da semana, para facilitar a
 * padroniza��o das Strings dentro do sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @version 1.0
 */
public enum DIAS_DA_SEMANA {

	SEGUNDA_FEIRA("Segunda"), TERCA_FEIRA("Ter�a"), QUARTA_FEIRA("Quarta"), QUINTA_FEIRA("Quinta"), SEXTA_FEIRA("Sexta");
	
	
	String descricao;
	DIAS_DA_SEMANA(String titulo) {
		this.descricao = titulo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}

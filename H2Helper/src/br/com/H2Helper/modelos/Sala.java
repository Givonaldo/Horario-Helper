package br.com.H2Helper.modelos;

/**
 * Classe que representa um modelo de sala.
 *  
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 */
public class Sala {

	private String idSala, bloco;
	
	public Sala(){
		
	}

	/**
	 * Metodo que retorna o identificador da sala.
	 * 
	 * @return idSala
	 */
	public String getIdSala() {
		return idSala;
	}

	/**
	 * Metodo que seta um identificador para a sala.
	 * 
	 * @param idSala
	 */
	public void setIdSala(String idSala) {
		this.idSala = idSala;
	}

	/**
	 * Metodo que retorna o bloco ao qual essa sala está 
	 * localizada.
	 * 
	 * @return bloco
	 */
	public String getBloco() {
		return bloco;
	}

	/**
	 * Metodo que seta o bloco onde essa sala está localizada.
	 * 
	 * @param bloco
	 * 		Letra do bloco onde a sala está localizada.
	 */
	public void setBloco(String bloco) {
		this.bloco = bloco;
	}
		
	@Override
	public String toString() {
		return getIdSala()+" - "+getBloco();
	}
}

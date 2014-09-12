package br.com.H2Helper.dados;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Classe respons�vel pelo armazenamento dos dados que ser� feito atravez do
 * XML. nela foi utilizado o padr�o singlinton.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @version 1.0
 */
public class Persistencia {

	private static Persistencia armazena = null;

	private Persistencia() {

	}

	/**
	 * Metodo que verifica se o objeto Armazena j� foi instanciado se n�o ele
	 * retorna uma instancia dessa classe.
	 * 
	 * @return {@link Persistencia}
	 */
	public static Persistencia getInstance() {
		if (armazena == null) {
			return new Persistencia();
		}
		return armazena;
	}

	/**
	 * Metodo que recebe como par�metro o nome do Arquivo XML que deseja salvar
	 * e o objeto que vai ser salvo.
	 * 
	 * @param arquivo
	 * @param objeto
	 */
	public void salvar(Object objeto) {

		try {
			FileOutputStream os = new FileOutputStream(
					Constantes.ARQUIVO_UNICO.getTitulo());
			XMLEncoder encoder = new XMLEncoder(os);
			encoder.writeObject(objeto);
			encoder.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que recumpera todos os dados salvos em XML e retorna esses dados
	 * atrav�s de um Object.
	 * 
	 * @return obj
	 */
	public Object load() {

		try {

			FileInputStream os = new FileInputStream(Constantes.ARQUIVO_UNICO.getTitulo());
			XMLDecoder decoder = new XMLDecoder(os);
			decoder.close();
			Object result = decoder.readObject();
			return result;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}

}

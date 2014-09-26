package br.com.H2Helper.proxy;

import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.exception.AtributoInvalidoException;
import br.com.H2Helper.exception.H2Exception;
import br.com.H2Helper.exception.SalaJaCadastradaException;
import br.com.H2Helper.exception.SalaNaoAlteradaException;
import br.com.H2Helper.exception.SalaNaoCadastradaException;
import br.com.H2Helper.gerenciador.Gerenciador;

/**
 * Classe onde foi usado o padrão de projeto Proxy e o 
 * singleton. A fim de fornecer uma camada de verificação 
 * dos dados fornecidos pela fachada do sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see ProxyAbstrato
 */
public class ProxySala extends ProxyAbstrato {
	
	private static ProxySala proxy = null;
	private Dados dados;
	private Persistencia persistencia;
	private Gerenciador gerenciador;
	
	private ProxySala(){
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
		gerenciador = new Gerenciador();
	}

	/**
	 * Metodo singleton 
	 * @return {@link ProxySala}
	 */
	public static ProxySala getInstance(){
		if (proxy == null){
			return new ProxySala();
		}else {
			return proxy;
		}
	}
	
	/**
	 * Metodo que realiza as verificações dos dados.
	 *  
	 * @param idSala
	 * @param bloco
	 * @throws H2Exception
	 */
	public void addSala(String idSala, String bloco) throws H2Exception {
		
		if (!verificaAtributo(idSala, bloco)){
			throw new AtributoInvalidoException();
		}
		//if (verificaExistencia(idSala)){
			//throw new SalaJaCadastradaException();
		//}
		else {
			gerenciador.addSala(idSala, bloco);
		}
	}

	/**
	 * Metodo que realiza as verificações dos dados passados no parâmetro do mesmo.
	 * 
	 * @param idSala
	 * @param novoBloco
	 * @throws H2Exception
	 */
	public void alteraSala(String idSala, String novoBloco) throws H2Exception {
		
		if (idSala == null || idSala.equals("") || novoBloco == null || novoBloco.equals("")){
			throw new SalaNaoAlteradaException();
		}else if (!verificaExistencia(idSala)){
			throw new SalaNaoCadastradaException();
		}else {
			gerenciador.alteraSala(idSala, novoBloco);
		}
	}
	
	/**
	 * Metodo que realiza as verificações dos dados passados no parâmetro do mesmo.
	 * 
	 * @param idSala
	 * @return
	 * @throws H2Exception
	 */
	public String getSala(String idSala) throws H2Exception {
		
		if (idSala == null || idSala.equals("")){
			throw new AtributoInvalidoException();
		}else if (!verificaExistencia(idSala)) {
			throw new SalaNaoCadastradaException();
		}else {
			return gerenciador.getSala(idSala);
		}
	}

	/**
	 * Metodo que realiza as verificações dos dados passados no parâmetro do mesmo.
     *	
	 * @param idSala
	 * @throws H2Exception
	 */
	public void removeSala(String idSala) throws H2Exception {
		
		if (idSala == null || !verificaNuloOuVazio(idSala)){
			throw new AtributoInvalidoException();
		}else if (!verificaExistencia(idSala)){
			throw new SalaNaoCadastradaException();
		}else {
			gerenciador.removeSala(idSala);
		}
	}

	@Override
	public boolean verificaExistencia(String parametro) {
		
		if (dados.getSalas().containsKey(parametro)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean verificaAtributo(String... atributo) {
		
		if (atributo[0] != null && atributo[1] != null && !atributo[0].equals("") &&
				!atributo[1].equals("")){
			return true;
		}else {
			return false;
		}
	}	
}
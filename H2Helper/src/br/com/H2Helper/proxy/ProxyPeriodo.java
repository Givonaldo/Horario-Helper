package br.com.H2Helper.proxy;

import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.exception.H2Exception;
import br.com.H2Helper.gerenciador.Gerenciador;

/**
 * 
 * Classe onde foi usado o padrão de projeto Proxy e o 
 * singleton. A fim de fornecer uma camada de verificação 
 * dos dados fornecidos pela fachada do sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see ProxyAbstrato
 */
public class ProxyPeriodo extends ProxyAbstrato {
	
	private static ProxyPeriodo proxy = null;
	private Dados dados;
	private Persistencia persistencia;
	private Gerenciador gerenciador;
	
	private ProxyPeriodo() {
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
		gerenciador = new Gerenciador();
	}
	
	/**
	 * Metodo singleton que retorna uma instancia dessa classe
	 * caso ela não tenha sido instanciada.
	 * 
	 * @return {@link ProxyPeriodo}
	 */
	public static ProxyPeriodo getInstance(){
		if (proxy == null){
			return new ProxyPeriodo();
		}else {
			return proxy;
		}
	}
	
	/**
	 * Metodo que realiza as verificações dos dados passados no parâmetro 
	 * do metodo e chama o metodo addPeriodo da classe gerenciador.
	 * 
	 * @param identificadorPeriodo
	 * @param idCurso
	 * @throws H2Exception
	 */
	public void addPeriodo(String identificadorPeriodo, String idCurso) throws H2Exception {
		
		String atributo = identificadorPeriodo +" - "+ idCurso;
		if (!verificaAtributo(identificadorPeriodo, idCurso)){
			throw new H2Exception("Atributo inválido");
		}else if (verificaExistencia(atributo)){
			throw new H2Exception("Periodo já Cadastrado");
		}else {
			gerenciador.addPeriodo(identificadorPeriodo, idCurso);
		}
	}
	
	/**
	 * Metodo que realiza as verificações dos dados passados no 
	 * parâmetro e delega a responsabilidade de remoção de um 
	 * periodo para um command.
	 * 
	 * @param idCurso
	 * @param nomePeriodo
	 * @throws H2Exception
	 */
	public void removePeriodo(String idCurso, String nomePeriodo)
			throws H2Exception {
		
		String atributo = nomePeriodo +" - "+ idCurso;
		if (!verificaAtributo(nomePeriodo, idCurso)){
			throw new H2Exception("Atributo inválido");
		}else if (!verificaExistencia(atributo)){
			throw new H2Exception("Periodo não Cadastrado");
		}else {
			gerenciador.removePeriodo(idCurso, nomePeriodo);
		}
	}

	/**
	 * 
	 * @param idPeriodo
	 * @param idCurso
	 * @return
	 * @throws H2Exception
	 */
	public String getPeriodo(String idPeriodo, String idCurso) throws H2Exception {
		
		String atributo = idPeriodo +" - "+ idCurso;
		if (!verificaAtributo(idPeriodo, idCurso)){
			throw new H2Exception("Atributo inválido");
		}else if (!verificaExistencia(atributo)){
			throw new H2Exception("Periodo não Cadastrado");
		}else{
			return gerenciador.getPeriodo(idPeriodo, idCurso);
		}
	}
	
	@Override
	public boolean verificaExistencia(String parametro) {
		
		if (dados.getPeriodo().containsKey(parametro)){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public boolean verificaAtributo(String... atributo) {
		
		if (atributo[0] != null && atributo[1] != null & atributo[0].matches(RECURSOS.VALIDA_SIGLA_PERIODO.getTitulo()) && 
				atributo[1].matches(RECURSOS.VALIDA_SIGLA.getTitulo())){
			return true;
		}else {
			return false;
		}
	}
}

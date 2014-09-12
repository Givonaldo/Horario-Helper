package br.com.H2Helper.proxy;

import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.exception.H2Exception;
import br.com.H2Helper.gerenciador.Gerenciador;

/**
 * Classe onde foi usado o padr�o de projeto Proxy e o 
 * singleton. A fim de fornecer uma camada de verifica��o 
 * dos dados fornecidos pela fachada do sistema.
 *  
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see ProxyAbstrato
 */
public class ProxyCurso extends ProxyAbstrato {

	private static ProxyCurso proxy = null;
	private Persistencia persistencia;
	private Dados dados;
	private Gerenciador gerenciador;
	
	private ProxyCurso(){
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
		gerenciador = new Gerenciador();
	}
	
	/**
	 * Metodo singleton.
	 * 
	 * @return ProxyCurso
	 */
	public static ProxyCurso getInstance(){
		if (proxy == null){
			return new ProxyCurso();
		}else {
			return proxy;
		}
	}
		
	/**
	 * Metodo que realiza a verifica��o dos dados passados no par�metro 
	 * e delega a responsabilidade de cria��o do curso para um Command 
	 * de cria��o de cursos.
	 *  
	 * @param identificadorCurso
	 * @param nome
	 * @throws H2Exception
	 */
	public void addCurso(String identificadorCurso, String nome) throws H2Exception {
		
		if (identificadorCurso == null || nome == null || !verificaAtributo(identificadorCurso, nome)){
			throw new H2Exception("Atributo inv�lido");
		}else if (verificaExistencia(identificadorCurso)){
			throw new H2Exception("Curso j� cadastrado");
		}else {
			gerenciador.addCurso(identificadorCurso, nome);
		}
	}
	
	/**
	 * Metodo que realiza as verifica��es dos dados passados no par�metro 
	 * e delega a responsabilidade de altera��o de um curso para um 
	 * command de altera��o de cursos.
	 * 
	 * @param identificador
	 * @param novoValor
	 * @throws H2Exception
	 */
	public void alterarCurso(String identificador, String novoValor)
			throws H2Exception {
		
		if (identificador == null || novoValor == null || !verificaAtributo(identificador, novoValor)){
			throw new H2Exception("Atributo inv�lido");
		}else if (!verificaExistencia(identificador)){
			throw new H2Exception("Curso n�o cadastrado");
		}else {
			gerenciador.alterarCurso(identificador, novoValor);
		}
	}

	/**
	 * Metodo que verifica os dados passados no par�metro e 
	 * delega a responsabilidade de remo��o do curso para 
	 * um command de remo��o de cursos.
	 * 
	 * @param identificador
	 * @throws H2Exception
	 */
	public void removeCurso(String identificador) throws H2Exception {
		
		if (identificador == null || !verificaNuloOuVazio(identificador)){
			throw new H2Exception("Atributo inv�lido");
		}else if (!verificaExistencia(identificador)){
			throw new H2Exception("Curso n�o cadastrado");
		}else {
			gerenciador.removeCurso(identificador);
		}
	}

	/**
	 * Metodo que verifica os dados passados no par�metro e 
	 * retorna um Curso no formato toString.
	 * 
	 * @param idCurso
	 * @return
	 * @throws H2Exception
	 */
	public String getCurso(String idCurso) throws H2Exception {
		
		if (idCurso == null || !verificaNuloOuVazio(idCurso)){
			throw new H2Exception("Atributo inv�lido");
		}else if (!verificaExistencia(idCurso)){
			throw new H2Exception("Curso n�o cadastrado");
		}else {
			return gerenciador.getCurso(idCurso);
		}
	}

	@Override
	public boolean verificaExistencia(String parametro) {
		
		if (dados.getCursos().containsKey(parametro)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean verificaAtributo(String... atributo) {
		
		if (atributo[0].matches(RECURSOS.VALIDA_SIGLA.getTitulo()) && atributo[1].matches(RECURSOS.VALIDA_ATRIBUTO.getTitulo()) &&
				atributo[0] != null & atributo[1] != null) {
			return true;
		}else{
			return false;
		}
	}
}

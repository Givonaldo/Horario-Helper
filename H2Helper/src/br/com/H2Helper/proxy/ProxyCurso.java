package br.com.H2Helper.proxy;

import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.exception.AtributoInvalidoException;
import br.com.H2Helper.exception.CursoJaCadastradoException;
import br.com.H2Helper.exception.CursoNaoCadastradoException;
import br.com.H2Helper.exception.H2Exception;
import br.com.H2Helper.gerenciador.Gerenciador;

/**
 * Classe onde foi usado o padrão de projeto Proxy e o 
 * singleton. A fim de fornecer uma camada de verificação 
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
	 * Metodo que realiza a verificação dos dados passados no parâmetro 
	 * e delega a responsabilidade de criação do curso para um Command 
	 * de criação de cursos.
	 *  
	 * @param identificadorCurso
	 * @param nome
	 * @throws H2Exception
	 */
	public void addCurso(String identificadorCurso, String nome) throws H2Exception {
		
		if (identificadorCurso == null || nome == null || !verificaAtributo(identificadorCurso, nome)){
			throw new AtributoInvalidoException();
		}
	//	if (verificaExistencia(identificadorCurso)){
	//		throw new CursoJaCadastradoException();
	//	}
		else {
			gerenciador.addCurso(identificadorCurso, nome);
		}
	}
	
	/**
	 * Metodo que realiza as verificaçães dos dados passados no parâmetro 
	 * e delega a responsabilidade de alteração de um curso para um 
	 * command de alteração de cursos.
	 * 
	 * @param identificador
	 * @param novoValor
	 * @throws H2Exception
	 */
	public void alterarCurso(String identificador, String novoValor)
			throws H2Exception {
		
		if (identificador == null || novoValor == null || !verificaAtributo(identificador, novoValor)){
			throw new AtributoInvalidoException();
		}else if (!verificaExistencia(identificador)){
			throw new CursoNaoCadastradoException();
		}else {
			gerenciador.alterarCurso(identificador, novoValor);
		}
	}

	/**
	 * Metodo que verifica os dados passados no parâmetro e 
	 * delega a responsabilidade de remoção do curso para 
	 * um command de remoção de cursos.
	 * 
	 * @param identificador
	 * @throws H2Exception
	 */
	public void removeCurso(String identificador) throws H2Exception {
		
		if (identificador == null || !verificaNuloOuVazio(identificador)){
			throw new AtributoInvalidoException();
		}else if (!verificaExistencia(identificador)){
			throw new CursoNaoCadastradoException();
		}else {
			gerenciador.removeCurso(identificador);
		}
	}

	/**
	 * Metodo que verifica os dados passados no parâmetro e 
	 * retorna um Curso no formato toString.
	 * 
	 * @param idCurso
	 * @return
	 * @throws H2Exception
	 */
	public String getCurso(String idCurso) throws H2Exception {
		
		if (idCurso == null || !verificaNuloOuVazio(idCurso)){
			throw new AtributoInvalidoException();
		}else if (!verificaExistencia(idCurso)){
			throw new CursoNaoCadastradoException();
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

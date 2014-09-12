package br.com.H2Helper.proxy;

import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.exception.H2Exception;
import br.com.H2Helper.gerenciador.Gerenciador;

/**
 * Classe onde foi usado o padr√£o de projeto Proxy e o 
 * singleton. A fim de fornecer uma camada de verifica√ß√£o 
 * dos dados fornecidos pela fachada do sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see ProxyAbstrato
 */
public class ProxyTurma extends ProxyAbstrato {

	private static ProxyTurma proxy = null;
	private Dados dados;
	private Persistencia persistencia;
	private Gerenciador gerenciador;

	private ProxyTurma() {

		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
		gerenciador = new Gerenciador();
	}

	/**
	 * Metodo singleton.
	 * 
	 * @return
	 */
	public static ProxyTurma getInstance() {

		if (proxy == null) {
			return new ProxyTurma();
		} else {
			return proxy;
		}
	}

	/**
	 * Metodo que realiza as verifica√ß√µes dos dados passados no par√¢metro do mesmo.
     *
	 * @param idTurma
	 * @param idCurso
	 * @param identificadorProfessor
	 * @param identificadorDisciplina
	 * @param identificadorSala
	 * @param identificadorPeriodo
	 * @throws H2Exception
	 */
	public void addTurma(String idTurma, String idCurso,
			String identificadorProfessor, String identificadorDisciplina,
			String identificadorSala, String identificadorPeriodo)
			throws H2Exception {

		if (idTurma == null || idCurso == null || identificadorProfessor == null || 
				identificadorDisciplina == null || identificadorSala == null || identificadorPeriodo == null || 
				!verificaAtributo(idTurma, idCurso, identificadorProfessor,	identificadorDisciplina, identificadorSala,
				identificadorPeriodo)) {
			throw new H2Exception("Atributo inv·lido");
		} else if (verificaExistencia(idTurma)) {
			throw new H2Exception("Turma j· cadastrada");
		} else {
			gerenciador.addTurma(idTurma, idCurso, identificadorProfessor,
					identificadorDisciplina, identificadorSala,
					identificadorPeriodo);

		}
	}

	/**
	 * Metodo que realiza as verifica√ß√µes dos dados passados no par√¢metro do mesmo.
	 *  
	 * @param idTurma
	 * @param campo
	 * @param novoValor
	 * @throws H2Exception
	 */
	public void alterarTurma(String idTurma, String campo, String novoValor)
			throws H2Exception {

		if (idTurma == null || campo == null || novoValor == null || !verificaAtributo(idTurma, campo, novoValor)) {
			throw new H2Exception("Atributo inv·lido");
		} else if (!verificaExistencia(idTurma)) {
			throw new H2Exception("Turma n„o cadastrada");
		} else {
			gerenciador.alteraTurma(idTurma, campo, novoValor);
		}
	}

	/**
	 * Metodo que realiza as verifica√ß√µes dos dados passados no par√¢metro do mesmo.
	 * 
	 * @param idTurma
	 * @throws H2Exception
	 */
	public void removerTurma(String idTurma) throws H2Exception {

		if (idTurma == null || !verificaNuloOuVazio(idTurma)) {
			throw new H2Exception("Atributo inv·lido");
		} else if (!verificaExistencia(idTurma)) {
			throw new H2Exception("Turma n„o cadastrada");
		} else {
			gerenciador.removerTurma(idTurma);
		}
	}

	/**
	 * Metodo que realiza as verifica√ß√µes dos dados passados no par√¢metro do mesmo.
	 * 
	 * @param idTurma
	 * @return
	 * @throws H2Exception
	 */
	public String getTurma(String idTurma) throws H2Exception {

		if (idTurma == null || !verificaNuloOuVazio(idTurma)) {
			throw new H2Exception("Atributo inv·lido");
		} else if (!verificaExistencia(idTurma)) {
			throw new H2Exception("Turma n„o cadastrada");
		} else {
			return gerenciador.getTurma(idTurma);
		}
	}

	@Override
	public boolean verificaExistencia(String parametro) {

		if (dados.getTurmas().containsKey(parametro)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean verificaAtributo(String... atributo) {

		switch (atributo.length) {
		case 3: {
			if (atributo[0].matches(RECURSOS.VALIDA_ATRIBUTO.getTitulo())
					&& atributo[1]
							.matches(RECURSOS.VALIDA_ATRIBUTO.getTitulo())
					&& atributo[2]
							.matches(RECURSOS.VALIDA_ATRIBUTO.getTitulo())) {
				return true;
			} else {
				return false;
			}
		}
		case 6: {
			if (atributo[0].matches(RECURSOS.VALIDA_SIGLA.getTitulo())
					&& atributo[1]
							.matches(RECURSOS.VALIDA_SIGLA.getTitulo())
					&& atributo[2]
							.matches(RECURSOS.VALIDA_MATRICULA.getTitulo())
					&& atributo[3]
							.matches(RECURSOS.VALIDA_SIGLA.getTitulo())
					&& atributo[4]
							.matches(RECURSOS.VALIDA_SIGLA.getTitulo())
					&& atributo[5]
							.matches(RECURSOS.VALIDA_SIGLA_PERIODO.getTitulo())) {
				return true;
			} else {
				return false;
			}
		}
		}
		return false;
	}
}

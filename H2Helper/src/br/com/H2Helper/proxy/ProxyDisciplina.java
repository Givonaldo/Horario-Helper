package br.com.H2Helper.proxy;

import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.exception.AtributoInvalidoException;
import br.com.H2Helper.exception.DisciplinaJaCadastradaException;
import br.com.H2Helper.exception.DisciplinaNaoCadastradaException;
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
public class ProxyDisciplina extends ProxyAbstrato {

	private static ProxyDisciplina proxy = null;
	private Gerenciador gerenciador;
	private Dados dados;
	private Persistencia persistencia;

	private ProxyDisciplina() {

		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
		gerenciador = new Gerenciador();
	}

	/**
	 * Metodo singleton
	 *  
	 * @return ProxyDisciplina
	 */
	public static ProxyDisciplina getInstance() {

		if (proxy == null) {
			return new ProxyDisciplina();
		} else {
			return proxy;
		}
	}

	/**
	 * Metodo que realiza a verificação dos dados passados no parâmetro e 
	 * delega a responsabilidade de criação de uma disciplina para um command
	 * de criação de disciplinas.
	 * 
	 * @param identificadorDisciplina
	 * @param nomeDisciplina
	 * @param cargaHoraria
	 * @param identificadorCurso
	 * @param identificadorperiodo
	 * @throws H2Exception
	 */
	public void addDisciplinaAoPeriodo(String identificadorDisciplina,
			String nomeDisciplina, int cargaHoraria, String identificadorCurso,
			String identificadorperiodo) throws H2Exception {
		
		String parametro = identificadorCurso + " - " + identificadorDisciplina;
		if (identificadorCurso == null || nomeDisciplina == null || cargaHoraria == 0 || identificadorDisciplina == null || 
				identificadorperiodo == null || !verificaAtributo(identificadorDisciplina, nomeDisciplina, identificadorCurso, 
						identificadorperiodo)) {
			throw new AtributoInvalidoException();
		} else if (verificaExistencia(parametro)) {
			throw new DisciplinaJaCadastradaException();
		} else {
			gerenciador.addDisciplinaAoPeriodo(identificadorDisciplina,
					nomeDisciplina, cargaHoraria, identificadorCurso,
					identificadorperiodo);
		}
	}

	/**
	 * Metodo que realiza as verificações dos dados passados no parâmetro e 
	 * delega a responsabilidade de alteração de uma disciplina para um 
	 * command de alteração de disciplinas.
	 *  
	 * @param idCurso
	 * @param sigla
	 * @param atributo
	 * @param novoValor
	 * @throws H2Exception
	 */
	public void alteraDisciplina(String idCurso, String sigla, String atributo,
			String novoValor) throws H2Exception {

		String parametro = idCurso + " - " + sigla;
		if (idCurso == null || sigla == null || atributo == null || 
				novoValor == null || !idCurso.matches(RECURSOS.VALIDA_SIGLA.getTitulo()) || !sigla.matches(RECURSOS.VALIDA_SIGLA.getTitulo())
				|| !atributo.matches(RECURSOS.VALIDA_NOME.getTitulo())) {
			throw new AtributoInvalidoException();
		} else if (!verificaExistencia(parametro)) {
			throw new DisciplinaNaoCadastradaException();
		} else {
			gerenciador.alteraDisciplina(idCurso, sigla, atributo, novoValor);
		}
	}

	/**
	 * Metodo que verifica os dados passados no parâmetro do metodo 
	 * e delega a responsabilidade de remoção de disciplinas para 
	 * um command de remoção de disciplinas.
	 * 
	 * @param idCurso
	 * @param idDisciplina
	 * @throws H2Exception
	 */
	public void removeDisciplina(String idCurso, String idDisciplina)
			throws H2Exception {

		String parametro = idCurso + " - " + idDisciplina;
		if (idCurso == null || idDisciplina == null || !idCurso.matches(RECURSOS.VALIDA_ATRIBUTO.getTitulo()) || 
				!idDisciplina.matches(RECURSOS.VALIDA_ATRIBUTO.getTitulo())){
			throw new AtributoInvalidoException();
		}else if (!verificaExistencia(parametro)) {
			throw new DisciplinaNaoCadastradaException();
		} else {
			gerenciador.removeDisciplina(idCurso, idDisciplina);
		}
	}

	/**
	 * Metodo que verifica os dados passados no parâmetro e 
	 * atribui a responsabilidade de retorno dos dados desejados 
	 * para o gerenciador.
	 * 
	 * @param idCurso
	 * @param idDisciplina
	 * @return toString de Disciplina.
	 * @throws H2Exception
	 */
	public String getDisciplina(String idCurso, String idDisciplina)
			throws H2Exception {

		String parametro = idCurso + " - " + idDisciplina;
		if (idCurso == null || idDisciplina == null || !idCurso.matches(RECURSOS.VALIDA_ATRIBUTO.getTitulo()) || 
				!idDisciplina.matches(RECURSOS.VALIDA_ATRIBUTO.getTitulo())){
			throw new AtributoInvalidoException();
		}else if (!verificaExistencia(parametro)) {
			throw new DisciplinaNaoCadastradaException();
		} else {
			return gerenciador.getDisciplina(idCurso, idDisciplina);
		}
	}

	@Override
	public boolean verificaExistencia(String parametro) {

		if (dados.getDisciplinas().containsKey(parametro)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean verificaAtributo(String... atributo) {
		
		if (atributo[0].matches(RECURSOS.VALIDA_SIGLA.getTitulo()) && atributo[1].matches(RECURSOS.VALIDA_NOME.getTitulo())
			&& atributo[2].matches(RECURSOS.VALIDA_SIGLA.getTitulo()) && atributo[3].matches(RECURSOS.VALIDA_SIGLA_PERIODO.getTitulo())
			&& atributo[0] != null && atributo[1] != null && atributo[2] != null && atributo[3] != null){
			return true;
		}else {
			return false;
		}
	}
}

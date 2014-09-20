package br.com.H2Helper.proxy;

import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.exception.AtributoInvalidoException;
import br.com.H2Helper.exception.CursoNaoCadastradoException;
import br.com.H2Helper.exception.DisciplinaNaoCadastradaException;
import br.com.H2Helper.exception.H2Exception;
import br.com.H2Helper.exception.PeriodoNaoCadastradoException;
import br.com.H2Helper.exception.ProfessorNaoCadastradoException;
import br.com.H2Helper.exception.SalaNaoCadastradaException;
import br.com.H2Helper.exception.TurmaJaCadastradaException;
import br.com.H2Helper.exception.TurmaNaoCadastradaException;
import br.com.H2Helper.gerenciador.Gerenciador;

/**
 * Classe onde foi usado o padrão de projeto Proxy e o 
 * singleton. A fim de fornecer uma camada de verificação 
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
	 * Metodo que realiza as verificações dos dados passados no parâmetro do mesmo.
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
			throw new AtributoInvalidoException();
		} else if (verificaExistencia(idTurma)) {
			throw new TurmaJaCadastradaException();
		} else if (verificacaoDeExistenciaDosIdentificadores(idCurso, identificadorProfessor, identificadorDisciplina, identificadorSala, identificadorPeriodo)){
			gerenciador.addTurma(idTurma, idCurso, identificadorProfessor, identificadorDisciplina, identificadorSala,
					identificadorPeriodo);
		}
	}

	/**
	 * Metodo que realiza as verificações dos dados passados no parâmetro do mesmo.
	 *  
	 * @param idTurma
	 * @param campo
	 * @param novoValor
	 * @throws H2Exception
	 */
	public void alterarTurma(String idTurma, String campo, String novoValor)
			throws H2Exception {

		if (idTurma == null || campo == null || novoValor == null || !verificaAtributo(idTurma, campo, novoValor)) {
			throw new AtributoInvalidoException();
		} else if (!verificaExistencia(idTurma)) {
			throw new TurmaNaoCadastradaException();
		} else {
			gerenciador.alteraTurma(idTurma, campo, novoValor);
		}
	}

	
	/**
	 * 
	 * @param descricao
	 */
	public void setDescricaoTurma(String idTurma, String descricao) throws H2Exception {
		
		if (descricao == null || !descricao.matches(RECURSOS.VALIDA_ATRIBUTO.getTitulo()) || 
				idTurma == null || !idTurma.matches(RECURSOS.VALIDA_ATRIBUTO.getTitulo())){
			throw new AtributoInvalidoException();
		}else if (!verificaExistencia(idTurma)){
			throw new TurmaNaoCadastradaException();
		}else {
			gerenciador.setDescricaoTurma(idTurma, descricao);
		}
	}
	
	/**
	 * Metodo que realiza as verificações dos dados passados no parâmetro do mesmo.
	 * 
	 * @param idTurma
	 * @throws H2Exception
	 */
	public void removerTurma(String idTurma) throws H2Exception {

		if (idTurma == null || !verificaNuloOuVazio(idTurma)) {
			throw new AtributoInvalidoException();
		} else if (!verificaExistencia(idTurma)) {
			throw new TurmaNaoCadastradaException();
		} else {
			gerenciador.removerTurma(idTurma);
		}
	}

	/**
	 * Metodo que realiza as verificações dos dados passados no parâmetro do mesmo.
	 * 
	 * @param idTurma
	 * @return
	 * @throws H2Exception
	 */
	public String getTurma(String idTurma) throws H2Exception {

		if (idTurma == null || idTurma.isEmpty()) {
			throw new AtributoInvalidoException();
		} else if (!verificaExistencia(idTurma)) {
			throw new TurmaNaoCadastradaException();
		} else {
			return gerenciador.getTurma(idTurma);
		}
	}
	
	/**
	 * 
	 * @param idCurso
	 * @param idProfessor
	 * @param idDisciplina
	 * @param idSala
	 * @param idPeriodo
	 * @return
	 * @throws H2Exception
	 */
	public boolean verificacaoDeExistenciaDosIdentificadores(String idCurso, String idProfessor, String idDisciplina, String idSala,
			String idPeriodo) throws H2Exception {
		
		if (!verificaExistenciaCurso(idCurso)){
			throw new CursoNaoCadastradoException();
		}else if (!verificaExistenciaProfessor(idProfessor)){
			throw new ProfessorNaoCadastradoException();
		}else if (!verificaExistenciaDisciplina(idDisciplina, idCurso)){
			throw new DisciplinaNaoCadastradaException();
		}else if (!verificaExistenciaSala(idSala)){
			throw new SalaNaoCadastradaException();
		}else if (!verificaExistenciaPeriodo(idPeriodo, idCurso)){
			throw new PeriodoNaoCadastradoException();
		}else {
			return true;
		}
		 
	}
	
	/**
	 * 
	 * @param parametro
	 * @return
	 */
	public boolean verificaExistenciaCurso(String parametro) {
		
		if (dados.getCursos().containsKey(parametro)){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 
	 * @param parametro
	 * @return
	 */
	public boolean verificaExistenciaProfessor(String parametro) {

		if (dados.getProfessores().containsKey(parametro)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 
	 * @param parametro
	 * @return
	 */
	public boolean verificaExistenciaDisciplina(String identificadorDisciplina, String idCurso) {

		String parametro = idCurso + " - " + identificadorDisciplina;
		if (dados.getDisciplinas().containsKey(parametro)) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * 
	 * @param parametro
	 * @return
	 */
	public boolean verificaExistenciaSala(String parametro) {
		
		if (dados.getSalas().containsKey(parametro)){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @param parametro
	 * @return
	 */
	public boolean verificaExistenciaPeriodo(String idPeriodo, String idCurso) {
		
		String atributo = idPeriodo +" - "+ idCurso;
		if (dados.getPeriodo().containsKey(atributo)){
			return true;
		}else{
			return false;
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
					&& atributo[1].matches(RECURSOS.VALIDA_ATRIBUTO.getTitulo())
					&& atributo[2].matches(RECURSOS.VALIDA_ATRIBUTO.getTitulo())) {
				return true;
			} else {
				return false;
			}
		}
		case 6: {
			if (atributo[0].matches(RECURSOS.VALIDA_SIGLA.getTitulo())
					&& atributo[1].matches(RECURSOS.VALIDA_SIGLA.getTitulo())
					&& atributo[2].matches(RECURSOS.VALIDA_MATRICULA.getTitulo())
					&& atributo[3].matches(RECURSOS.VALIDA_SIGLA.getTitulo())
					&& atributo[4].matches(RECURSOS.VALIDA_SIGLA.getTitulo())
					&& atributo[5].matches(RECURSOS.VALIDA_SIGLA_PERIODO.getTitulo())) {
				return true;
			} else {
				return false;
			}
		}
		}
		return false;
	}
	
	
}

package br.com.H2Helper.fachada;

import br.com.H2Helper.exception.H2Exception;
import br.com.H2Helper.fabrica.FabricaProxy;
import br.com.H2Helper.fabrica.OPCOES_DE_OBJETOS;
import br.com.H2Helper.proxy.ProxyCurso;
import br.com.H2Helper.proxy.ProxyDisciplina;
import br.com.H2Helper.proxy.ProxyHorario;
import br.com.H2Helper.proxy.ProxyPeriodo;
import br.com.H2Helper.proxy.ProxyProfessor;
import br.com.H2Helper.proxy.ProxySala;
import br.com.H2Helper.proxy.ProxyTurma;

/**
 * Classe onde foi utilizado o pardrão de projeto Fachada com o objetivo de
 * fornecer um ponto unico de acesso ao sistema, facilitando assim a interação
 * com as funcionalidades do sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @version 1.0
 */
public class FachadaH2 implements FachadaIF {

	private FabricaProxy fabricaDeGerenciadores;
	private ProxyProfessor proxyProfessor;
	private ProxyDisciplina proxyDisciplina;
	private ProxyCurso proxyCurso;
	private ProxyPeriodo proxyPeriodo;
	private ProxySala proxySala;
	private ProxyTurma proxyTurma;
	private ProxyHorario proxyHorario;
	
	public FachadaH2() {
		fabricaDeGerenciadores = new FabricaProxy();
	}

	@Override
	public void addProfessor(String idProfessor, String nome)
			throws H2Exception {
		proxyProfessor = (ProxyProfessor) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_PROFESSOR);
		proxyProfessor.addProfessor(idProfessor, nome);
	}

	@Override
	public void alteraProfessor(String idProfessor, String novoNome)
			throws H2Exception {
		proxyProfessor = (ProxyProfessor) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_PROFESSOR);
		proxyProfessor.alteraProfessor(idProfessor, novoNome);
	}

	@Override
	public void removeProfessor(String matricula) throws H2Exception {
		proxyProfessor = (ProxyProfessor) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_PROFESSOR);
		proxyProfessor.removeProfessor(matricula);
	}

	@Override
	public String getProfessor(String identificador) throws H2Exception {
		proxyProfessor = (ProxyProfessor) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_PROFESSOR);
		return proxyProfessor.getProfessor(identificador);
	}

	@Override
	public void addDisciplinaAoPeriodo(String identificadorDisciplina,
			String nomeDisciplina, int cargaHoraria, String identificadorCurso,
			String identificadorperiodo) throws H2Exception {
		proxyDisciplina = (ProxyDisciplina) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_DISCIPLINA);
		proxyDisciplina.addDisciplinaAoPeriodo(identificadorDisciplina,
				nomeDisciplina, cargaHoraria, identificadorCurso,
				identificadorperiodo);

	}

	@Override
	public void alteraDisciplina(String idCurso, String sigla, String atributo,
			String novoValor) throws H2Exception {
		proxyDisciplina = (ProxyDisciplina) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_DISCIPLINA);
		proxyDisciplina.alteraDisciplina(idCurso, sigla, atributo, novoValor);
	}

	@Override
	public void removeDisciplina(String idCurso, String idDisciplina)
			throws H2Exception {
		proxyDisciplina = (ProxyDisciplina) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_DISCIPLINA);
		proxyDisciplina.removeDisciplina(idCurso, idDisciplina);
	}

	@Override
	public String getDisciplina(String idCurso, String idDisciplina)
			throws H2Exception {
		proxyDisciplina = (ProxyDisciplina) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_DISCIPLINA);
		return proxyDisciplina.getDisciplina(idCurso, idDisciplina);
	}

	@Override
	public void addSala(String idSala, String bloco) throws H2Exception {
		proxySala = (ProxySala) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_SALA);
		proxySala.addSala(idSala, bloco);
	}

	@Override
	public void alteraSala(String idSala, String novoBloco) throws H2Exception {

		proxySala = (ProxySala) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_SALA);
		proxySala.alteraSala(idSala, novoBloco);

	}

	@Override
	public void removeSala(String idSala) throws H2Exception {

		proxySala = (ProxySala) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_SALA);
		proxySala.removeSala(idSala);
	}

	@Override
	public void addPeriodo(String identificadorPeriodo, String idCurso)
			throws H2Exception {

		proxyPeriodo = (ProxyPeriodo) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_PERIODO);
		proxyPeriodo.addPeriodo(identificadorPeriodo, idCurso);
	}

	@Override
	public void removePeriodo(String idCurso, String nomePeriodo)
			throws H2Exception {
		proxyPeriodo = (ProxyPeriodo) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_PERIODO);
		proxyPeriodo.removePeriodo(idCurso, nomePeriodo);
	}

	@Override
	public void addTurma(String idTurma, String idCurso,
			String identificadorProfessor, String identificadorDisciplina,
			String identificadorSala, String identificadorPeriodo)
			throws H2Exception {
		proxyTurma = (ProxyTurma) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_TURMA);
		proxyTurma.addTurma(idTurma, idCurso, identificadorProfessor,
				identificadorDisciplina, identificadorSala,
				identificadorPeriodo);

	}

	@Override
	public void alteraTurma(String idTurma, String campo, String novoValor)
			throws H2Exception {
		proxyTurma = (ProxyTurma) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_TURMA);
		proxyTurma.alterarTurma(idTurma, campo, novoValor);
	}

	@Override
	public void removerTurma(String idTurma) throws H2Exception {

		proxyTurma = (ProxyTurma) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_TURMA);
		proxyTurma.removerTurma(idTurma);

	}

	@Override
	public String getTurma(String idTurma) throws H2Exception {
		proxyTurma = (ProxyTurma) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_TURMA);
		return proxyTurma.getTurma(idTurma);
	}

	@Override
	public void addCurso(String identificadorCurso, String nome)
			throws H2Exception {

		proxyCurso = (ProxyCurso) fabricaDeGerenciadores.getObject(OPCOES_DE_OBJETOS.PROXY_CURSO);
		proxyCurso.addCurso(identificadorCurso, nome);
	}

	@Override
	public void alterarCurso(String identificador, String novoValor)
			throws H2Exception {

		proxyCurso = (ProxyCurso) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_CURSO);
		proxyCurso.alterarCurso(identificador, novoValor);

	}

	@Override
	public void removeCurso(String identificador) throws H2Exception {

		proxyCurso = (ProxyCurso) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_CURSO);
		proxyCurso.removeCurso(identificador);

	}

	@Override
	public String getCurso(String idCurso) throws H2Exception {
		proxyCurso = (ProxyCurso) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_CURSO);
		return proxyCurso.getCurso(idCurso);
	}

	@Override
	public String getSala(String idSala) throws H2Exception {
		proxySala = (ProxySala) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_SALA);
		return proxySala.getSala(idSala);
	}

	@Override
	public String getPeriodo(String idPeriodo, String idCurso)
			throws H2Exception {
		proxyPeriodo = (ProxyPeriodo) fabricaDeGerenciadores
				.getObject(OPCOES_DE_OBJETOS.PROXY_PERIODO);
		return proxyPeriodo.getPeriodo(idPeriodo, idCurso);
	}

	@Override
	public String alocaTurmaAoHorario(String idTurma, String diaDaSemana,
			int horaInicio, int horafim) throws H2Exception {
		
		proxyHorario = (ProxyHorario) fabricaDeGerenciadores.getObject(OPCOES_DE_OBJETOS.PROXY_HORARIO);		
		return proxyHorario.alocaTurmaAoHorario(idTurma, diaDaSemana, horaInicio, horafim);
	}

	@Override
	public String desalocaTurmaDoHorario(String idTurma, String diaDaSemana,
			int horaInicio, int horaFim) throws H2Exception {
		
		proxyHorario = (ProxyHorario) fabricaDeGerenciadores.getObject(OPCOES_DE_OBJETOS.PROXY_HORARIO);
		return proxyHorario.desalocaTurmaDoHorario(idTurma, diaDaSemana, horaInicio, horaFim);
	}

	@Override
	public String getHorario(String idTurma) throws H2Exception {
		
		proxyHorario = (ProxyHorario) fabricaDeGerenciadores.getObject(OPCOES_DE_OBJETOS.PROXY_HORARIO);
		return proxyHorario.getHorario(idTurma);
	}

	@Override
	public String getTurmas(String diaDaSemana, int horaInicio, int horaFim)
			throws H2Exception {
		
		proxyHorario = (ProxyHorario) fabricaDeGerenciadores.getObject(OPCOES_DE_OBJETOS.PROXY_HORARIO);
		return proxyHorario.getTurmas(diaDaSemana, horaInicio, horaFim);
	}

	@Override
	public String salvaHorario(String nomeDoArquivo) {
		
		proxyHorario = (ProxyHorario) fabricaDeGerenciadores.getObject(OPCOES_DE_OBJETOS.PROXY_HORARIO);
		return proxyHorario.salvaHorario(nomeDoArquivo);
	}

}

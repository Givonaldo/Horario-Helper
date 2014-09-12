package br.com.H2Helper.proxy;

import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.exception.H2Exception;
import br.com.H2Helper.gerenciador.GerenciadorDeHorarios;

/**
 * Classe Proxe de Horario, ela funciona como uma barreira logo 
 * apos a fachada do programa, ela realiza todas as verificações 
 * nescessárias dos dados, isso garante que nenhum dado inconsistente
 * passe para as classes gerenciadoras.
 *  
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see ProxyAbstrato
 */
public class ProxyHorario extends ProxyAbstrato {

	private static ProxyHorario proxy = null;
	private GerenciadorDeHorarios gerenciador;
	private Dados dados;
	private Persistencia persistencia;
	
	private ProxyHorario(){
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
		gerenciador = new GerenciadorDeHorarios();
	}

	/**
	 * Metodo singleton
	 *  
	 * @return {@link ProxyHorario}
	 */
	public static ProxyHorario getInstance() {

		if (proxy == null) {
			return new ProxyHorario();
		} else {
			return proxy;
		}
	}

	/**
	 * metodo que realiza a verificação dos dados passados no parâmetro e 
	 * delega a responsabilidade de alocação de uma turma para o gerenciador 
	 * de horario. 
	 * 
	 * @param idTurma
	 * @param diaDaSemana
	 * @param horaInicio
	 * @param horafim
	 * @return
	 * @throws H2Exception
	 */
	public String alocaTurmaAoHorario(String idTurma, String diaDaSemana,
			int horaInicio, int horafim) throws H2Exception {
		
		if (verificaAtributo(idTurma, diaDaSemana) || horaInicio < 0 || 
				horafim < 0 || horafim - horaInicio < 0){
			throw new H2Exception("Atributo Inválido");
		}else if (!verificaExistencia(idTurma)){
			throw new H2Exception("Turma não encontrada.");
		}else {
			return gerenciador.alocaTurmaAoHorario(idTurma, diaDaSemana, horaInicio, horafim);
		}
	}
	
	/**
	 * Metodo que verifica os dados passados no parâmetro e 
	 * delega a responsabilidade de desalocação de um horario 
	 * para a classe gerenciadora de horarios.
	 * 
	 * @param idTurma
	 * @param diaDaSemana
	 * @param horaInicio
	 * @param horaFim
	 * @return
	 * @throws H2Exception
	 */
	public String desalocaTurmaDoHorario(String idTurma, String diaDaSemana,
			int horaInicio, int horaFim) throws H2Exception {
		
		if (!verificaAtributo(idTurma, diaDaSemana) || horaInicio > 24 || horaInicio < 0 || 
				horaFim > 24 || horaFim < 0 || horaFim - horaInicio < 0){
			throw new H2Exception("Atributo Inválido");
		}else {
			return gerenciador.desalocaTurmaDoHorario(idTurma, diaDaSemana, horaInicio, horaFim);
		}
	}

	/**
	 * Metodo que retorna um horario.
	 * 
	 * @param idTurma
	 * @return horario.
	 * @throws H2Exception
	 */
	public String getHorario(String idTurma) throws H2Exception{
		
		if (idTurma.isEmpty()){
			throw new H2Exception("Atributo Inválido");
		}else if (!verificaExistencia(idTurma)){
			throw new H2Exception("Turma sem horário");
		}else {
			return gerenciador.getHorario(idTurma);
		}
	}

	/**
	 * Metodo que retorna as turmas que estão alocadas em determinado 
	 * horario.
	 * 
	 * @param diaDaSemana
	 * @param horaInicio
	 * @param horaFim
	 * @return 
	 * @throws H2Exception
	 */
	public String getTurmas(String diaDaSemana, int horaInicio, int horaFim)
			throws H2Exception {
		if (horaInicio <= 0 || horaFim < 0 || diaDaSemana.isEmpty()){
			throw new H2Exception("Atributo inválido");
		}
		return gerenciador.getTurmas(diaDaSemana, horaInicio, horaFim);
	}

	
	@Override
	public boolean verificaExistencia(String parametro) {
		
		if (dados.getTurmas().containsKey(parametro)){
			return true;
		}else {
			return false;
		}
	}

	@Override
	public boolean verificaAtributo(String... atributo) {

		if (atributo[0].isEmpty() || atributo[1].isEmpty()){
			return false;
		}
		return true;
	}
	
	/**
	 * Metodo que salva o horario em um arquivo com o nome passado 
	 * no parâmetro, caso já exista algum arquivo com o mesmo nome, 
	 * será substituido pelo arquivo criado.
	 * 
	 * @param nomeDoArquivo
	 * @return 
	 * @throws H2Exception
	 */
	public String salvaHorario(String nomeDoArquivo) {
		
		return null;
	}

}

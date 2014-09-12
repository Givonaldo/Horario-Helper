package br.com.H2Helper.gerenciador;

import java.util.Set;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.commands.commandHorario.CommandAlocaTurmaAoHorario;
import br.com.H2Helper.commands.commandTurma.CommandRemoveTurma;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.fabrica.FabricaCommand;
import br.com.H2Helper.fabrica.FabricaIF;
import br.com.H2Helper.fabrica.OPCOES_DE_OBJETOS;

/**
 * Classe que realiza a manipulação e gerenciamento de todos
 * os dados referentes ao horario, ela funciona como um 
 * centralizador de gerenciamento dos dados. Ela delega 
 * a responsabilidade de realização de persistencia e remoção 
 * de turmas e horarios aos commands especificos para a realização 
 * desse tipo de operação. 
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @version 1.0
 */
public class GerenciadorDeHorarios {

	private Dados dados;
	private Persistencia persistencia;
	private FabricaIF fabrica = new FabricaCommand();
	private CommandAlocaTurmaAoHorario command;
	private Set<String> chaves;
	private String descricaoDoChoqueDeHorario = "Choque com: ";
	private int quantidadeDeChoques = 0;
	private String turmasAlocadas = "Turmas Alocadas: ";
	private CommandIF commandRemoveTurmas;
	
	public GerenciadorDeHorarios() {

		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
		command = (CommandAlocaTurmaAoHorario) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_ALOCA_TURMA);
		commandRemoveTurmas = (CommandRemoveTurma) fabrica.getObject(OPCOES_DE_OBJETOS.COMMAND_REMOVE_TURMA);
		chaves = dados.getTurmas().keySet();
	}

	/**
	 * Metodo que realiza a alocação de turma.
	 * 
	 * @param idTurma
	 * @param diaDaSemana
	 * @param horaInicio
	 * @param horafim
	 * @return
	 */
	public String alocaTurmaAoHorario(String idTurma, String diaDaSemana,
			int horaInicio, int horafim) {
		
		verificaAlocacao(idTurma, diaDaSemana, horaInicio, horafim);
		if (quantidadeDeChoques == 0){
			return "OK";
		}else {
			return descricaoDoChoqueDeHorario;
		}
		
	}
	
	/**
	 * Remove a turma (e seus recursos) do horário passado como parâmetro.  e retorna 
	 * "ok" caso não haja nenhum dos recursos da turma já cadastrado naquele horário.
	 * 
	 * @param idTurma
	 * @param diaDaSemana
	 * @param horaInicio
	 * @param horaFim
	 * @return descricaoDoChoque.
	 */
	public String desalocaTurmaDoHorario(String idTurma, String diaDaSemana,
			int horaInicio, int horaFim) {

		for (String id : chaves) {
		
			verificaChoqueComProfessor(id, idTurma, horaInicio, horaFim);
			verificaChoqueComSala(id, idTurma, horaInicio, horaFim);
			
		}
		
		if (this.quantidadeDeChoques == 0){
			return "OK";
		}else {
			commandRemoveTurmas.execute(idTurma);
			return this.descricaoDoChoqueDeHorario;
		}
		
	}

	/**
	 * Metodo que retorna o horario da turma no formato String.
	 * 
	 * @param idTurma
	 * @return horario da turma.
	 */
	public String getHorario(String idTurma) {

		return dados.getTurmas().get(idTurma).horario();
	}

	/**
	 * Metodo que retorna todas as turmas que estão alocadas no dia e no 
	 * intervalo de (horaInicio e horaFim) da aula.
	 * 
	 * @param diaDaSemana
	 * @param horaInicio
	 * @param horaFim
	 * @return turmasAlocadas
	 */
	public String getTurmas(String diaDaSemana, int horaInicio, int horaFim) {

		for (String chave : chaves) {
			try{

				if (dados.getTurmas().get(chave).getDiaDaSemana().equals(diaDaSemana)){
					if (dados.getTurmas().get(chave).getHoraInicio() >= horaInicio && 
							dados.getTurmas().get(chave).getHoraInicio() <= horaFim || dados.getTurmas().get(chave).getHoraFim() >= horaInicio && 
							dados.getTurmas().get(chave).getHoraFim() <= horaFim){
	
						turmasAlocadas += dados.getTurmas().get(chave).getIdTurma()+ ", ";
					}
				}
			}catch(Exception e){}
		}
		return getTurmasAlocadas();
	}

	/**
	 * Metodo que verifica a ocorrencia de choques de horarios.
	 * 
	 * @param idTurma
	 * @param horaInicio
	 * @param horaFim
	 */
	public void verificaAlocacao(String idTurma, String diaDaSemana, int horaInicio, int horaFim) {

		for (String id : chaves) {
			try{
				if (dados.getTurmas().get(id).getDiaDaSemana().equals(diaDaSemana)) {
	
					verificaChoqueComProfessor(id, idTurma, horaInicio, horaFim);
					verificaChoqueComSala(id, idTurma, horaInicio, horaFim);
				} 
			}catch (Exception e){}
		}
		command.execute(idTurma, diaDaSemana, horaInicio, horaFim);
	}
	
	/**
	 * Metodo que verifica a ocorrencia de choques de horarios de 
	 * professor.
	 * 
	 * @param id
	 * @param idTurma
	 * @param horaInicio
	 * @param horaFim
	 */
	public void verificaChoqueComProfessor(String id, String idTurma, int horaInicio, int horaFim) {
		
		if (dados.getTurmas().get(id).getIdentificadorProfessor().equals(dados.getTurmas().get(idTurma).getIdentificadorProfessor())
				&& dados.getTurmas().get(idTurma).getHoraInicio() >= dados.getTurmas().get(id).getHoraInicio()
				&& dados.getTurmas().get(idTurma).getHoraInicio() <= dados.getTurmas().get(id).getHoraFim()
				|| dados.getTurmas().get(id).getIdentificadorProfessor().equals(dados.getTurmas().get(idTurma).getIdentificadorProfessor()) 
				&& dados.getTurmas().get(idTurma).getHoraFim() >= dados.getTurmas().get(id).getHoraInicio()
				&& dados.getTurmas().get(idTurma).getHoraFim() <= dados.getTurmas().get(id).getHoraFim()) {
			
			quantidadeDeChoques += 1;
			descricaoDoChoqueDeHorario += " - "+dados.getTurmas().get(id).getIdentificadorProfessor();
		}
	}
	
	/**
	 * Metodo que verifica a ocorrencia de choques de horarios com 
	 * o identificador da sala.
	 * 
	 * @param id
	 * @param idTurma
	 * @param horaInicio
	 * @param horaFim
	 */
	public void verificaChoqueComSala(String id, String idTurma, int horaInicio, int horaFim){
		
		if (dados.getTurmas().get(id).getIdentificadorSala().equals(dados.getTurmas().get(idTurma).getIdentificadorSala()) 
				&& dados.getTurmas().get(idTurma).getHoraInicio() >= dados.getTurmas().get(id).getHoraInicio()
				&& dados.getTurmas().get(idTurma).getHoraInicio() <= dados.getTurmas().get(id).getHoraFim()
				|| dados.getTurmas().get(id).getIdentificadorSala().equals(dados.getTurmas().get(idTurma).getIdentificadorSala()) && 
				dados.getTurmas().get(idTurma).getHoraFim() >= dados.getTurmas().get(id).getHoraInicio()
				&& dados.getTurmas().get(idTurma).getHoraFim() <= dados.getTurmas().get(id).getHoraFim()){
			
			quantidadeDeChoques += 1;
			descricaoDoChoqueDeHorario += " - "+dados.getTurmas().get(id).getIdentificadorSala();
		}
	}

	/**
	 * Metodo que retorna as turmas alocadas em 
	 * um determuinado periodo de tempo.
	 * 
	 * @return turmasAlocadas
	 */
	public String getTurmasAlocadas() {
		return turmasAlocadas;
	}
	
}

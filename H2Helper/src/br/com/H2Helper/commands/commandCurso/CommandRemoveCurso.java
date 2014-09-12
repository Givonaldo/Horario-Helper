package br.com.H2Helper.commands.commandCurso;

import java.util.Set;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.modelos.Curso;

/**
 * Classe onde foi utilizado o padr�o Command e que representa o 
 * comando de remo��o de um curso.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti
 * @see CommandIF
 */
public class CommandRemoveCurso implements CommandIF {

	private Dados dados;
	private Persistencia persistencia;
	private Set<String> chavesTurmas;
	private Set<String> chavesDisciplinas;
	private Curso curso;

	public CommandRemoveCurso() {

		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
		chavesTurmas = dados.getTurmas().keySet();
		chavesDisciplinas = dados.getDisciplinas().keySet();
	}

	@Override
	public void execute(Object... atributos) {

		dados.getCursos().remove((String) atributos[0]);
		removeTurmasAssociadasAoCurso((String)atributos[0]);
		dados.persistencia(dados);
	}

	/**
	 * Metodo que realiza a remo��o de todas as turmas associadas ao 
	 * curso que ser� removido. 
	 * 
	 * @param identificador
	 * 		Identificador(chave) do curso que ser� removido.
	 */
	public void removeTurmasAssociadasAoCurso(String identificador) {

		curso = (Curso) dados.getCursos().get(identificador);
		for (String chaveTurma : chavesTurmas) {
			if (dados.getTurmas().get(chaveTurma).getIdCurso()
					.equals(curso.getIdentificadorCurso())) {
				dados.getTurmas().remove(chaveTurma);
			}
		}
		dados.persistencia(dados);
	}

	/** Metodo que realiza a remo��o de todas as disciplinas que 
	 * est�o associadas ao curso que ser� removido.
	 * 
	 * @param identificador
	 * 			identificador(chave) do curso que ser� removido.
	 */
	public void removeDisciplinasAssosiadasAoCurso(String identificador){
	
		for (String chaveDisciplina : chavesDisciplinas) {
			if (dados.getDisciplinas().get(chaveDisciplina).getIdentificadorCurso()
					.equals(dados.getCursos().get(identificador))){
				dados.getDisciplinas().remove(chaveDisciplina);
			}
		}
		dados.persistencia(dados);
	}
}

package br.com.H2Helper.commands.commandCurso;

import br.com.H2Helper.commands.CommandIF;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.fabrica.FabricaIF;
import br.com.H2Helper.fabrica.FabricaModelos;
import br.com.H2Helper.fabrica.OPCOES_DE_OBJETOS;
import br.com.H2Helper.modelos.Curso;

/**
 * Classe onde foi utilizado o padrão Command e que representa o 
 * comando de criação de um novo curso.
 *  
 * @author Gilvonaldo Alves da Silva Cavalcanti
 * @see CommandIF
 */
public class CommandAdicionaCurso implements CommandIF {

	private Dados dados;
	private Persistencia persistencia;
	private Curso curso;
	private FabricaIF fabrica = new FabricaModelos(); 
	
	public CommandAdicionaCurso() {
		
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
		curso = (Curso) fabrica.getObject(OPCOES_DE_OBJETOS.CURSO);
	}
	
	@Override
	public void execute(Object... atributos) {
		
		curso.setIdentificadorCurso((String)atributos[0]);
		curso.setNome((String)atributos[1]);
		dados.getCursos().put((String)atributos[0], curso);
		dados.persistencia(dados);
	}

}

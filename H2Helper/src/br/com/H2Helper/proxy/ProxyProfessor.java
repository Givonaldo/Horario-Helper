package br.com.H2Helper.proxy;

import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import br.com.H2Helper.exception.AtributoInvalidoException;
import br.com.H2Helper.exception.H2Exception;
import br.com.H2Helper.exception.ProfessorJaCadastradoException;
import br.com.H2Helper.exception.ProfessorNaoCadastradoException;
import br.com.H2Helper.gerenciador.Gerenciador;

/**
 * 
 * Classe onde foi usado o padrão de projeto Proxy e o 
 * singleton. A fim de fornecer uma camada de verificação 
 * dos dados fornecidos pela fachada do sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see ProxyAbstrato 
 */
public class ProxyProfessor extends ProxyAbstrato {

	private static ProxyProfessor proxy = null;
	private Persistencia persistencia;
	private Dados dados;
	private Gerenciador gerenciador;

	private ProxyProfessor() {

		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
		gerenciador = new Gerenciador();
	}

	/**
	 * Metodo que retorna uma instancia dessa classe caso ela não 
	 * tenha sido instanciada.
	 * 
	 * @return {@link ProxyProfessor}
	 */
	public static ProxyProfessor getInstance() {
		if (proxy == null) {
			return new ProxyProfessor();
		} else {
			return proxy;
		}
	}

	/**
	 * Metodo que verifica os dados passados no parâmetro e delega a 
	 * responsabilidade de criação de um professor para um comando de 
	 * criação de professores.
	 * 
	 * @param idProfessor
	 * @param nome
	 * @throws H2Exception
	 */
	public void addProfessor(String idProfessor, String nome)
			throws H2Exception {

		if (idProfessor == null || nome == null || !verificaAtributo(idProfessor, nome)) {
			throw new AtributoInvalidoException();
		} 
		//if (verificaExistencia(idProfessor)){
			//throw new ProfessorJaCadastradoException();
		//}
		else{
			gerenciador.addProfessor(idProfessor, nome);
		}
	}

	/**
	 * Metodo que realiza as verificações dos dados.
	 * 
	 * @param idProfessor
	 * 			Identificador do professor.
	 * @param novoNome
	 * 			Novo nome do professor.
	 * @throws H2Exception
	 * 			Caso o parâmetro seja nulo ou inválido.
	 */
	public void alteraProfessor(String idProfessor, String novoNome)
			throws H2Exception {

		if (idProfessor == null || novoNome == null || !verificaAtributo(idProfessor, novoNome)) {
			throw new AtributoInvalidoException();
		}else if (!verificaExistencia(idProfessor)) {
			throw new ProfessorNaoCadastradoException();
		}  else {
			gerenciador.alteraProfessor(idProfessor, novoNome);
		}

	}

	/**
	 * Metodo que realiza as verificações dos dados.
	 * 
	 * @param matricula
	 * 		Representa um identificador unico para o professor.
	 * @throws H2Exception
	 */
	public void removeProfessor(String matricula) throws H2Exception {

		 if (matricula == null || !verificaNuloOuVazio(matricula)) {
			 throw new AtributoInvalidoException();
		 }else if (!verificaExistencia(matricula)) {
			throw new ProfessorNaoCadastradoException();
		}
		 gerenciador.removeProfessor(matricula);
	}


	/**
	 * Metodo que realiza as verificações dos dados.
	 *	
	 * @param identificador
	 * 		Identificador do professor.
	 * @return toString de professor 
	 * @throws H2Exception
	 */
	public String getProfessor(String identificador) throws H2Exception {
		
		if (identificador == null || !verificaNuloOuVazio(identificador)) {
			throw new AtributoInvalidoException();
		} else if (!verificaExistencia(identificador)) {
			throw new ProfessorNaoCadastradoException();
		}
		return gerenciador.getProfessor(identificador);
	}

	@Override
	public boolean verificaExistencia(String parametro) {

		if (dados.getProfessores().containsKey(parametro)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean verificaAtributo(String... atributo) {

		if (atributo[0] != null && atributo[1] != null
				& atributo[0].matches(RECURSOS.VALIDA_ATRIBUTO.getTitulo())
				&& atributo[1].matches(RECURSOS.VALIDA_ATRIBUTO.getTitulo())) {
			return true;
		} else {
			return false;
		}
	}
}

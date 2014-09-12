package br.com.H2Helper.fabrica;

import br.com.H2Helper.proxy.ProxyCurso;
import br.com.H2Helper.proxy.ProxyDisciplina;
import br.com.H2Helper.proxy.ProxyHorario;
import br.com.H2Helper.proxy.ProxyPeriodo;
import br.com.H2Helper.proxy.ProxyProfessor;
import br.com.H2Helper.proxy.ProxySala;
import br.com.H2Helper.proxy.ProxyTurma;

/**
 * Classe onde foi utilizado o padrão de projeto Method Factory, a fim de
 * fornecer um metodo para criação de objetos concretos.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see FabricaIF
 */
public class FabricaProxy implements FabricaIF {

	public FabricaProxy() {
	}

	@Override
	public Object getObject(OPCOES_DE_OBJETOS tipo) {

		if (tipo.equals(OPCOES_DE_OBJETOS.PROXY_PROFESSOR)) {
			return ProxyProfessor.getInstance();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.PROXY_DISCIPLINA)) {
			return ProxyDisciplina.getInstance();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.PROXY_CURSO)) {
			return ProxyCurso.getInstance();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.PROXY_TURMA)) {
			return ProxyTurma.getInstance();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.PROXY_PERIODO)) {
			return ProxyPeriodo.getInstance();
		} else if (tipo.equals(OPCOES_DE_OBJETOS.PROXY_SALA)) {
			return ProxySala.getInstance();
		}else if (tipo.equals(OPCOES_DE_OBJETOS.PROXY_HORARIO)){
			return ProxyHorario.getInstance();
		}else {
			return null;
		}
	}

}

package h2.ifpb.edu.br;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import br.com.H2Helper.exception.H2Exception;
import br.com.H2Helper.fachada.FachadaH2;
import br.com.H2Helper.fachada.FachadaIF;

public class H2FachadaTest {
	FachadaIF fachada;

	@Before
	public void setUp() throws Exception {
		fachada = new FachadaH2();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddNovoProfessor() throws H2Exception {
		fachada.addProfessor("idMirna", "Mirna");
		String prof = fachada.getProfessor("idMirna");
		assertEquals("idMirna - Mirna", prof);

		fachada.addProfessor("idZe", "Ze");
		String prof2 = fachada.getProfessor("idZe");
		assertEquals("idZe - Ze", prof2);
	}

	@Test
	public void testAddProfessorExistente() throws H2Exception {
		fachada.addProfessor("idMirna", "Mirna");
		fachada.addProfessor("idMirna", "Maria");
	}

	@Test
	public void testAddProfessorExistente2() {
		try {
			fachada.addProfessor("idZe", "Ze");
			fachada.addProfessor("idze", "Zezin");

		} catch (Exception e) {
			fail("Inserindo codigos diferentes");
		}

		String prof1 = "";
		try {
			prof1 = fachada.getProfessor("idZe");
		} catch (H2Exception e1) {
			e1.printStackTrace();
		}
		assertEquals("idZe - Ze", prof1);
		try {
			fachada.addProfessor("idze", "Zezinho");
		} catch (H2Exception e) {
			fail("Professor já cadastrado(a)");
		}
	}

	@Test
	public void testAlteraProfessor() {

		try {
			cadastraProfessores();
			fachada.alteraProfessor("idMirna", "Mirna Maia");
			assertEquals("idMirna - Mirna Maia", fachada.getProfessor("idMirna"));
		} catch (H2Exception e) {
			//fail("Professor não cadastrado(a)");
		}
	}

	@Test
	public void testParametrosInvalidos() {
		try {
			fachada.addCurso(null, "ze");
			fail("Parametro inválido");
		} catch (H2Exception e) {
		}

		try {
			fachada.addProfessor("", "ze");
			fail("Parametro inválido");
		} catch (H2Exception e) {
		}

		try {
			fachada.addSala("idSala", "");
			fail("Parametro inválido");
		} catch (H2Exception e) {
		}

		try {
			fachada.addPeriodo("", "");
			fail("Parametro inválido");
		} catch (H2Exception e) {
		}

		try {
			fachada.addPeriodo("2014.1", null);
			fail("Parametro inválido");
		} catch (H2Exception e) {
		}

	}

	@Test
	public void testRemoveProfessor() {

		try {
			cadastraProfessores();
			fachada.removeProfessor("idZe");
		} catch (H2Exception e) {
			fail("Professor não removido");
		}

		try {
			fachada.getProfessor("idZe");
			fail("Professor não encontrado pq acabou de ser removido :)");
		} catch (H2Exception e) {

		}

		try {
			fachada.removeProfessor("jao");
			fail("Professor não cadastrado");
		} catch (H2Exception e) {

		}
	}

	@Test
	public void testGetProfessor() {
		try {
			cadastraProfessores();
			String ze = fachada.getProfessor("idZe");
			assertEquals("idZe - Ze", ze);
			String mirna = fachada.getProfessor("idMirna");
			assertEquals("idMirna - Mirna", mirna);
		} catch (H2Exception e) {
			fail("Professor não cadastrado");
		}
	}

	@Test
	public void testAddCurso() {
		try {
			cadastraCursos();
			assertEquals("ADS - Análise e Desenvolvimento de Sistemas",
					fachada.getCurso("ADS"));
			assertEquals("TCE - Construção de Edifícios",
					fachada.getCurso("TCE"));
			assertEquals("ABC - Teste Curso", fachada.getCurso("ABC"));
		} catch (H2Exception e) {
			fail("Curso não cadastrado");
		}
	}

	@Test
	public void testAlterarCurso() {
		try {
			cadastraCursos();
			fachada.alterarCurso("ADS", "Análise de Sistemas");
			assertEquals("ADS - Análise de Sistemas", fachada.getCurso("ADS"));

			fachada.alterarCurso("TCE", "Construção");
			assertEquals("TCE - Construção", fachada.getCurso("TCE"));

			fachada.alterarCurso("ABC", "Curso ABC");
			assertEquals("ABC - Curso ABC", fachada.getCurso("ABC"));

		} catch (H2Exception e) {
			fail("Curso não cadastrado");
		}

		try {
			fachada.alterarCurso("", "NovoValor");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			fachada.alterarCurso(null, "NovoValor");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			fachada.alterarCurso("TCE", "");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			fachada.alterarCurso("ADS", null);
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

	}

	@Test
	public void testRemoveCurso() {
		try {
			cadastraCursos();
			fachada.removeCurso("ABC");
			fachada.getCurso("ABC");
			fail("Curso não cadastrado");
		} catch (H2Exception e) {
		}

		try {
			cadastraCursos();
			fachada.removeCurso("TCE");
			fachada.getCurso("TCE");
			fail("Curso não cadastrado");
		} catch (H2Exception e) {
		}

	}

	@Test
	public void testGetCurso() {
		try {
			fachada.getCurso("");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			fachada.getCurso(null);
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraCursos();
			String ads = fachada.getCurso("ADS");
			assertEquals("ADS - Análise e Desenvolvimento de Sistemas", ads);
		} catch (H2Exception e) {
			fail("Curso não cadastrado");
		}

		try {
			cadastraCursos();
			String tce = fachada.getCurso("TCE");
			assertEquals("TCE - Construção de Edifícios", tce);
		} catch (H2Exception e) {
			fail("Curso não cadastrado");
		}

		try {
			cadastraCursos();
			fachada.getCurso("ADSS");
			fail("Curso não cadastrado");
		} catch (H2Exception e) {
		}

	}

	@Test
	public void testAddPeriodo() {
		try {
			cadastraPeriodos();
		} catch (H2Exception e) {
			fail("Curso não cadastrado");
		}
	}

	@Test
	public void testPeriodoInvalido() {
		try {
			cadastraPeriodos();
			fachada.addPeriodo("2012.2", "");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraPeriodos();
			fachada.addPeriodo("2012.2", null);
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraPeriodos();
			fachada.addPeriodo("", "ADS");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraPeriodos();
			fachada.addPeriodo(null, "ADS");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}
	}

	@Test
	public void testPeriodoExistente() {
		try {
			cadastraPeriodos();
			fachada.addPeriodo("2012.1", "ADS");
			// DEVE MOSTRAR A SEGUINTE MENSAGEM DE ERRO:
			// fail("Periodo jÃ¡ cadastrado");
		} catch (H2Exception e) {
		}

	}

	@Test	
	public void testRemovePeriodo() {
		try {
			cadastraPeriodos();
			fachada.removePeriodo("ADS", "2012.1");

			fachada.removePeriodo("ADS", "2012.1");
			
			// OBS: DEVE RETORNAR ESTA EXCEï¿½ï¿½O:
			// fail("Periodo nÃ£o cadastrado");
			// VISTO QUE O PERï¿½ODO jÃ¡ FOI REMOVIDO ANTERIORMENTE
			// E NUMA SEGUNDA TENTATIVA DE REMOï¿½ï¿½O ELE nÃ£o
			// DEVE SER ENCONTRADO
			fail("Periodo não cadastrado");
		} catch (H2Exception e) {
			
			
		}

		try {
		cadastraPeriodos();
			fachada.removePeriodo("ABC", "2013.1");
			fachada.removePeriodo("ABC", "2013.1");			
			
			fail("Periodo não cadastrado");
		} catch (H2Exception e) {
			
		}

		try {
			cadastraPeriodos();
			fachada.removePeriodo("AAA", "2013.1");
			fachada.removePeriodo("AAA", "2013.1");
			
			
			fail("Periodo não cadastrado");
		} catch (H2Exception e) {
			
		}

		try {
			cadastraPeriodos();
			fachada.removePeriodo("TCE", "2015.2");
			// OBS: DEVE RETORNAR ESTA EXCEï¿½ï¿½O:
			// fail("Periodo nÃ£o cadastrado");
			fail("Periodo não cadastrado");
		} catch (H2Exception e) {
			
		}

	}

	@Test
	public void testGetPeriodo() {
		try {
			cadastraPeriodos();
			fachada.getPeriodo("", "ADS");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraPeriodos();
			fachada.getPeriodo(null, "ADS");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraPeriodos();
			fachada.getPeriodo("2012.2", "");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraPeriodos();
			fachada.getPeriodo("2012.2", null);
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}
	}

	@Test
	public void testAddDisciplina() {
		try {
			cadastraDisciplinas();
			assertEquals("ADS - PP - Padrões de Projeto",
					fachada.getDisciplina("ADS", "PP"));

			assertEquals("ADS - AA - Análise de Algoritmos",
					fachada.getDisciplina("ADS", "AA"));

		} catch (H2Exception e) {
			//e.printStackTrace();
		}

	}

	@Test
	public void testDisciplinaInvalida() {

		try {
			fachada.addDisciplinaAoPeriodo("", "Disciplina1", 100, "ADS",
					"2012.1");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			fachada.addDisciplinaAoPeriodo(null, "Disciplina1", 100, "ADS",
					"2012.1");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			fachada.addDisciplinaAoPeriodo("AP", "Disciplina1", 100, null,
					"2012.1");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			fachada.addDisciplinaAoPeriodo("AP", "Disciplina1", 100, "",
					"2012.1");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			fachada.addDisciplinaAoPeriodo("AP", "", 100, "ADS", "2012.1");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			fachada.addDisciplinaAoPeriodo("AP", null, 100, "ADS", "2012.1");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

	}

	@Test
	public void testAlteraDisciplina() {
		try {
			cadastraDisciplinas();
			assertEquals("MB - Matemática Básica",
					fachada.getDisciplina("ADS", "MB"));
			fachada.alteraDisciplina("ADS", "MB", "Nome", "Mat Bas");
			assertEquals("MB - Mat Bas", fachada.getDisciplina("ADS", "MB"));
		} catch (H2Exception e) {
			e.printStackTrace();
		}

		try {
			cadastraDisciplinas();
			assertEquals("PP - Padrões de Projeto",
					fachada.getDisciplina("ADS", "PP"));
			fachada.alteraDisciplina("ADS", "PP", "Nome", "Padrões");
			assertEquals("PP - Padrões", fachada.getDisciplina("ADS", "PP"));
		} catch (H2Exception e) {
			e.printStackTrace();
		}

		try {
			cadastraDisciplinas();
			fachada.alteraDisciplina("ADS", "AA", "CargaHoraria", "20");
			assertEquals("AA - Análise de Algoritmos",
					fachada.getDisciplina("ADS", "AA"));
			
		} catch (H2Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testRemoveDisciplina() {

		try {
			cadastraDisciplinas();
			fachada.removeDisciplina("", "MAC");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraDisciplinas();
			fachada.removeDisciplina(null, "MAC");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraDisciplinas();
			fachada.removeDisciplina("TCE", "");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraDisciplinas();
			fachada.removeDisciplina("TCE", null);
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraDisciplinas();
			fachada.removeDisciplina("TCE", "MC");
			fachada.getDisciplina("TCE", "MC");
			fail("Disciplina não encontrada");
		} catch (H2Exception e) {
		}

		try {
			cadastraDisciplinas();
			fachada.removeDisciplina("ADS", "TESTE");
			// DEVE MOSTRAR A MENSAGEM DE ERRO:
			// fail("Disciplina nÃ£o encontrada");
		} catch (H2Exception e) {
		}

	}

	@Test
	public void testGetDisciplina() {
		try {
			cadastraDisciplinas();
			fachada.getDisciplina("", "PP");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraDisciplinas();
			fachada.getDisciplina(null, "PP");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraDisciplinas();
			fachada.getDisciplina("ADS", "");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraDisciplinas();
			fachada.getDisciplina("ADS", null);
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

	}

	@Test
	public void testAddSala() throws H2Exception {

		try {
			cadastraSalas();
			assertEquals("idSala1 - Bloco da Sala 1",
					fachada.getSala("idSala1"));
			assertEquals("idSala2 - Bloco da Sala 2",
					fachada.getSala("idSala2"));
		} catch (H2Exception e) {
			fail("Sala nao cadastrada");
		}

	}

	@Test
	public void testAlteraSala() throws H2Exception {
		try {
			cadastraSalas();
			fachada.alteraSala("idSala1", "Novo Bloco da Sala 1");

			assertEquals("idSala1 - Novo Bloco da Sala 1",
					fachada.getSala("idSala1"));

			fachada.alteraSala("idSala2", "Novo Bloco da Sala 2");

			assertEquals("idSala2 - Novo Bloco da Sala 2",
					fachada.getSala("idSala2"));
		} catch (H2Exception e) {
			
			fail("Sala não alterada");
		}

	}

	@Test
	public void testRemoveSala() {
		try {
			cadastraSalas();
			fachada.removeSala("idSala1");

			fachada.getSala("idSala1");
			fail("Sala nao cadastrada");
		} catch (H2Exception e) {
		}
	}

	@Test
	public void testGetSala() {
		try {
			cadastraSalas();
			fachada.getSala("");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraSalas();
			fachada.getSala(null);
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}
	}

	@Test
	public void testAddTurma() throws H2Exception {
		try {
			cadastraTurmas();
			fail("Disciplina não cadastrada");
		} catch (H2Exception e) {
		}
	}

	@Test
	public void testAddTurmaInvalida() {
		try {
			cadastraCursos();
			cadastraProfessores();
			cadastraDisciplinas();
			cadastraSalas();
			cadastraPeriodos();

			fachada.addTurma("", "ADS", "idMirna", "PP", "idSala1", "2012.1");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraCursos();
			cadastraProfessores();
			cadastraDisciplinas();
			cadastraSalas();
			cadastraPeriodos();

			fachada.addTurma("TurmaTeste", "", "idMirna", "PP", "idSala1",
					"2012.1");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraCursos();
			cadastraProfessores();
			cadastraDisciplinas();
			cadastraSalas();
			cadastraPeriodos();

			fachada.addTurma("TurmaTeste", "ADS", null, "PP", "idSala1",
					"2012.1");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraCursos();
			cadastraProfessores();
			cadastraDisciplinas();
			cadastraSalas();
			cadastraPeriodos();

			fachada.addTurma("TurmaTeste", "ADS", "idZe", null, "idSala1",
					"2012.1");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraCursos();
			cadastraProfessores();
			cadastraDisciplinas();
			cadastraSalas();
			cadastraPeriodos();

			fachada.addTurma("TurmaTeste", "ADS", "idZe", "AA", "", "2012.1");
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

		try {
			cadastraCursos();
			cadastraProfessores();
			cadastraDisciplinas();
			cadastraSalas();
			cadastraPeriodos();

			fachada.addTurma("TurmaTeste", "ADS", "idZe", "AA", "idSala1", null);
			fail("Atributo inválido");
		} catch (H2Exception e) {
		}

	}

//	@Test
//	public void testAlteraTurma() {
//		try {
//			cadastraTurmas();
//			fachada.alteraTurma("", campo, novoValor)
//			
//		} catch (H2Exception e) {
//			fail("Not yet implemented");
//		}
//
//	}

	// @Test
	// public void testRemoverTurma() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testGetTurma() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testObject() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testGetClass() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testHashCode() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testEquals() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testClone() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testToString() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testNotify() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testNotifyAll() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testWaitLong() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testWaitLongInt() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testWait() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testFinalize() {
	// fail("Not yet implemented");
	// }
	//

	private void cadastraProfessores() throws H2Exception {
		fachada.addProfessor("idMirna", "Mirna");
		fachada.addProfessor("idJoao", "Joao");
		fachada.addProfessor("idZe", "Ze");
	}

	private void cadastraSalas() throws H2Exception {
		fachada.addSala("idSala1", "Bloco da Sala 1");
		fachada.addSala("idSala2", "Bloco da Sala 2");
		fachada.addSala("idSala3", "Bloco da Sala 3");
	}

	private void cadastraCursos() throws H2Exception {
		fachada.addCurso("ADS", "Análise e Desenvolvimento de Sistemas");
		fachada.addCurso("TCE", "Construção de Edifícios");
		fachada.addCurso("ABC", "Teste Curso");
	}

	private void cadastraPeriodos() throws H2Exception {
		cadastraCursos();
		fachada.addPeriodo("2012.1", "ADS");
		fachada.addPeriodo("2012.2", "TCE");
		fachada.addPeriodo("2013.1", "ABC");
		fachada.addPeriodo("2013.1", "ADS");
		fachada.addPeriodo("2013.2", "ADS");
		fachada.addPeriodo("2014.1", "TCE");
		
	}

	private void cadastraDisciplinas() throws H2Exception {
		cadastraCursos();
		cadastraPeriodos();
		fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS",
				"2012.1");
		fachada.addDisciplinaAoPeriodo("MB", "Matemática Básica", 100, "ADS",
				"2012.1");
		fachada.addDisciplinaAoPeriodo("AA", "Análise de Algoritmos", 97,
				"ADS", "2013.1");
		fachada.addDisciplinaAoPeriodo("MC", "Metodologia Científica", 100,
				"TCE", "2014.1");
	}

	private void cadastraTurmas() throws H2Exception {
		cadastraCursos();
		cadastraProfessores();
		cadastraPeriodos();
		cadastraDisciplinas();
		cadastraSalas();
		fachada.addTurma("Turma1", "ADS", "idMirna", "PP", "idSala1", "2012.1");
		fachada.addTurma("Turma2", "TCE", "idJoao", "MC", "idSala2", "2012.2");
		fachada.addTurma("Turma3", "ADS", "idZe", "AA", "idSala3", "2013.1");
	}
}

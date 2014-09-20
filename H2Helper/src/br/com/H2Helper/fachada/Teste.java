package br.com.H2Helper.fachada;

import java.io.IOException;

import com.itextpdf.text.DocumentException;

import br.com.H2Helper.exception.H2Exception;
import br.com.H2Helper.relatorios.ArvoreDeDiretorios;
import br.com.H2Helper.relatorios.Relatorio;

public class Teste {

	public static void main(String[] args) throws IOException, DocumentException {

		
		FachadaH2 fachada = new FachadaH2();
		
		try {
			
			fachada.addCurso("ADS", "Análise e Desenvolvimento de Sistemas");
			fachada.addCurso("TCE", "Tecnologia em Construção de Edificios");
			
			fachada.addProfessor("56856", "Mirna Maia");
			fachada.addProfessor("09654", "Hugo Feitosa");
			fachada.addProfessor("99999", "Henrique Cunha");
			
			fachada.addDisciplinaAoPeriodo("PP", "Padrões de Projeto", 100, "ADS", "2014.1");
			fachada.addDisciplinaAoPeriodo("IJ", "Tijolo", 100, "TCE", "2014.1");
			fachada.addDisciplinaAoPeriodo("BD", "Banco de Dados", 100, "ADS", "2014.2");
			
			fachada.addSala("lab_9", "D");
			fachada.addSala("lab_1", "B");
			fachada.addSala("lab_2", "A");
			
			fachada.addPeriodo("2014.1", "ADS");
			fachada.addPeriodo("2011.2", "TCE");
			fachada.addPeriodo("2000.2", "ADS");
			
			System.out.println(fachada.getDisciplina("ADS", "PP"));
			System.out.println(fachada.getDisciplina("ADS", "BD"));
			System.out.println(fachada.getDisciplina("TCE", "IJ"));
			
			
			
			fachada.addTurma("ADS_2014", "ADS", "56856", "PP", "lab_9", "2014.1");
			fachada.addTurma("TCE_2011", "TCE", "09654", "IJ", "lab_2", "2011.2");
			fachada.addTurma("ADS_2000", "ADS", "99999", "BD", "lab_1", "2000.2");
			
			
			
			System.out.println(fachada.getTurma("ADS_2014"));
			
			
			fachada.alocaTurmaAoHorario("ADS_2014", "Segunda", 12, 14);
			fachada.alocaTurmaAoHorario("TCE_2011", "Terça", 12, 14);
			fachada.alocaTurmaAoHorario("ADS_2000", "Segunda", 10, 11);
			
			
			System.out.println(fachada.getHorario("ADS_2000"));
			System.out.println(fachada.getHorario("TCE_2011"));
			
			Relatorio relatorio = new Relatorio();
			
			ArvoreDeDiretorios arvore = new ArvoreDeDiretorios(null, true);
			
			relatorio.createPdf(arvore.getCaminhoSelecionado()+"Relatorio.pdf", "Primeiro Periodo", "2013.1","1º Periodo", "ADS");
			
			
			
		} catch (H2Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		new ArvoreDeDiretorios(null, true).setVisible(true);
		
	}

}

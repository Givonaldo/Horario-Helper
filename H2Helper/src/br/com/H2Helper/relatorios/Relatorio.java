package br.com.H2Helper.relatorios;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import br.com.H2Helper.dados.Dados;
import br.com.H2Helper.dados.Persistencia;
import com.adobe.acrobat.Viewer;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 *
 */
public class Relatorio {

	private String caminhoImg = (System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+System.getProperty("file.separator"));
	private Dados dados;
	private Persistencia persistencia;
	private Set<String> chaves;
	private ArrayList<DIAS_DA_SEMANA> diasDaSemana;
	private PdfPTable tabela;
	
	public Relatorio(){

		diasDaSemana = new ArrayList<>();
		dados = new Dados();
		tabela = new PdfPTable(5);
		diasDaSemana.add(DIAS_DA_SEMANA.SEGUNDA_FEIRA);
		diasDaSemana.add(DIAS_DA_SEMANA.TERCA_FEIRA);
		diasDaSemana.add(DIAS_DA_SEMANA.QUARTA_FEIRA);
		diasDaSemana.add(DIAS_DA_SEMANA.QUINTA_FEIRA);
		diasDaSemana.add(DIAS_DA_SEMANA.SEXTA_FEIRA);
		persistencia = Persistencia.getInstance();
		dados = (Dados) persistencia.load();
		chaves = dados.getTurmas().keySet();
	}

	/**
	 * Metodo que Cria o documento PDF.
	 * 
	 * @param filename  
	 *          O nome do caminho onde o PDF será criado.
	 * @throws DocumentException	
	 * 			Caso ocorra algum erro na criação do documento PDF.
	 * @throws IOException
	 * 			Caso o arquivo não tenha sido encontrado.
	 */
	public void createPdf(String filename, String opcoes, String periodo,
			String descricao, String siglaCurso) throws IOException,
			DocumentException {

		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.open();
		Image imagem = Image.getInstance(caminhoImg + "logoIFPB.jpg");
		document.add(imagem);
		Paragraph paragrafo = new Paragraph("Instituto Federal de Educação Ciência e Tecnoliga PARAÍBA");
		paragrafo.setAlignment(Element.ALIGN_CENTER);
		document.add(paragrafo);
		tabela(document, periodo, descricao, siglaCurso);
		document.add(this.tabela);
		document.close();
		JOptionPane.showMessageDialog(null, "PDF Gerado com sucesso.");
		// Chamada do metodo que exibe o PDF.
		exibirPDF(filename);
	}

	/**
	 * Metodo que cria a tabela de objetos e adiciona os objetos no arquivo PDF.
	 *
	 * @return a PdfPTable
	 * @throws DocumentException
	 * 
	 */
	public void tabela(Document doc, String periodo, String descricao,
			String siglaCurso) throws DocumentException {

		// Concatenação para a formação do nome da grade.
		Paragraph paragrafo = new Paragraph("Horário - "+siglaCurso.toUpperCase()+" "+ periodo);
		paragrafo.setAlignment(Element.ALIGN_CENTER);

		doc.add(paragrafo);
		Paragraph paragrafo1 = new Paragraph(" ");
		doc.add(paragrafo1);
		int cont = 0;
		
		Paragraph paragrafo2 = new Paragraph(descricao.toUpperCase());
		paragrafo2.setAlignment(Element.ALIGN_LEFT);
		doc.add(paragrafo2);
		Paragraph paragrafoVazio = new Paragraph(" ");
		doc.add(paragrafoVazio);
		
		
		tabela.setWidthPercentage(500 / 5.23f);
		tabela.setWidths(new int[] { 1, 1, 1, 1, 1 });
		PdfPCell cell;
		
		cell = new PdfPCell(new Phrase("Segunda-Feira"));
		cell.setColspan(cont);
		tabela.addCell(cell);
		tabela.addCell("Terça-Feira");
		tabela.addCell("Quarta-Feira");
		tabela.addCell("Quinta-Feira");
		tabela.addCell("Sexta-Feira");

		preencheTabela(descricao, tabela);
		
		doc.add(this.tabela);
		
	}

	/**
	 * Metodo que realiza o preenchimento do horário referente ao dia passado no parâmetro
	 *  do metodo.
	 *  
	 * @param dia
	 * 		Dia da semana que se deseja preencher na tabela.
	 * @param tabela
	 * 		Tabela que representará a grade de horarios.
	 * @param descricao
	 * 		A descrição da turma, ex.: 1º Periodo... 
	 */
	public void preencheDias(DIAS_DA_SEMANA dia, PdfPTable tabela, String descricao){
		
		
		for (String id : chaves) {
			
			if (dados.getTurmas().get(id).getDiaDaSemana().equals(dia) && 
					dados.getTurmas().get(id).getDescricao().equals(descricao)){
				
				tabela.addCell(dados.getDisciplinas().get(dados.getTurmas().get(id).getIdentificadorDisciplina()).getIdentificadorDisciplina() 
						+" de "+dados.getTurmas().get(id).getHoraInicio()+" às "+dados.getTurmas().get(id).getHoraFim());
				chaves.remove(id);
				break;
			}
			
		}
		
	}
	
	public void preencheTabela(String descricao, PdfPTable tabela){
		
		while(chaves.size() != 0){
			
			for (DIAS_DA_SEMANA dia : diasDaSemana) {
				preencheDias(dia, tabela, descricao);
			}
		}
	}
	
	/**
	 * Metodo que exibe o PDF atraves da biblioteca Acrobat do Java beans.
	 *
	 * @param filename 
	 */
	public void exibirPDF(String filename) {
		try {

			JFrame frame = new JFrame("Grade de Horários - IFPB");
			frame.setLayout(new BorderLayout());
			Viewer viewer = new Viewer();
			frame.add(viewer, BorderLayout.CENTER);
			FileInputStream input = new FileInputStream(new File(filename));
			viewer.setDocumentInputStream(input);
			viewer.setProperty("Default_Zoom_Type", "FitPage");
			viewer.setProperty("Default_Magnification", "100");
			viewer.zoomTo(2.0);
			viewer.activate();
			frame.setSize(700, 500);
			frame.pack();
			frame.setDefaultCloseOperation(1);
			frame.setVisible(true);
			Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();
			frame.setSize(tela.width, tela.height);

		} catch (Exception e) {
			// erro
		}
	}
	
	
	/**
	public static void main(String[] args) {

		// #====> TESTE <====# 
		
		Relatorio r = new Relatorio();
		ArvoreDeDiretorios arvore = new ArvoreDeDiretorios(null, true);

		try {

			r.createPdf(arvore.getCaminhoSelecionado()+ System.getProperty("file.separator")
				+ "Relatorio.pdf", "Primeiro Periodo", "2013.1","1º Periodo", "ADS");
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (DocumentException e) {
			
			e.printStackTrace();
		}

	}*/
}

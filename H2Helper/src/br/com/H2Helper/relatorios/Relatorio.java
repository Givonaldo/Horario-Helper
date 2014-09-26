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
 * Classe que gera um documento PDF, apartir dos dados cadastrados 
 * no sistema.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @see ArvoreDeDiretorios
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
	public void createPdf(String filename, String siglaCurso, String periodo) throws IOException,
			DocumentException {
		
		Document document = new Document();
		PdfWriter.getInstance(document, new FileOutputStream(filename));
		document.open();
		Image imagem = Image.getInstance(caminhoImg + "logoIFPB.jpg");
		document.add(imagem);
		Paragraph paragrafo = new Paragraph("Instituto Federal de Educação Ciência e Tecnoliga PARAÍBA");
		paragrafo.setAlignment(Element.ALIGN_CENTER);
		document.add(paragrafo);
		
		addDescricaoNaTabela(document, siglaCurso);
		//tabela(document,  descricao, siglaCurso);

		//document.add(this.tabela);
		document.close();
		JOptionPane.showMessageDialog(null, "PDF Gerado com sucesso.");
		// Chamada do metodo que exibe o PDF.
		//exibirPDF(filename);
	}

	/**
	 * Metodo que percorre todas as descrições dos Objetos Turmas, cadastrados 
	 * no sistema, essa descrição servirá como uma Grade de Horário.
	 * 
	 */
	public void addDescricaoNaTabela(Document doc, String siglaCurso){
		
		for (String idTurma : chaves) {
			
			try {
				String descricao = dados.getTurmas().get(idTurma).getDescricao();
				if (descricao != null){
					
					tabela(doc, descricao, siglaCurso);
				}
			}catch (Exception e){}
		}
	}
	
	/**
	 * Metodo que cria a tabela de objetos e adiciona os objetos no arquivo PDF.
	 *
	 * @return a PdfPTable
	 * @throws DocumentException
	 * 
	 */
	public void tabela(Document doc, String descricao,
			String siglaCurso) throws DocumentException {

		// Concatenação para a formação do nome da grade.
		Paragraph paragrafo = new Paragraph("Horário - "+siglaCurso.toUpperCase()+" "+ descricao);
		paragrafo.setAlignment(Element.ALIGN_CENTER);

		doc.add(paragrafo);
		Paragraph paragrafo1 = new Paragraph(" ");
		doc.add(paragrafo1);
	
		Paragraph paragrafo2 = new Paragraph(descricao.toUpperCase());
		paragrafo2.setAlignment(Element.ALIGN_LEFT);
		doc.add(paragrafo2);
		Paragraph paragrafoVazio = new Paragraph(" ");
		doc.add(paragrafoVazio);
		
		preencheTabela(descricao, doc);
		
		doc.add(paragrafoVazio);
		doc.add(this.tabela);
	}

	/**
	 * Metodo que preenche a tabela com os dias da semana correspondente ao
	 * horario, e chama os metodos de preenchimento dos dias da semana.
	 * 
	 * @param descricao
	 * 		String que representará a descrição de cada periodo.
	 * @throws DocumentException
	 * 		Caso a API ITextPdf não consiga criar um Arquivo do tipo Document. 
	 */
	public void preencheTabela(String descricao, Document doc) throws DocumentException{
	
		int cont = 0;
		tabela = new PdfPTable(5);
		tabela.setWidthPercentage(500 / 5.23f);
		tabela.setWidths(new int[] { 1, 1, 1, 1, 1 });
		PdfPCell cell1 = new PdfPCell(new Phrase("Segunda-Feira"));
		cell1.setColspan(0);
		tabela.addCell(cell1);
		PdfPCell cell2 = new PdfPCell(new Phrase("Terça-Feira"));
		cell2.setColspan(0);
		tabela.addCell(cell2);
		PdfPCell cell3 = new PdfPCell(new Phrase("Quarta-Feira"));
		cell3.setColspan(0);
		tabela.addCell(cell3);
		PdfPCell cell4 = new PdfPCell(new Phrase("Quinta-Feira"));
		cell4.setColspan(0);
		tabela.addCell(cell4);
		PdfPCell cell5 = new PdfPCell(new Phrase("Sexta-Feira"));
		cell5.setColspan(0);
		tabela.addCell(cell5);
		
		while(chaves.size() != 0){
			cont++;
			for (DIAS_DA_SEMANA dia : diasDaSemana) {
				preencheDias(dia.getDescricao(), descricao, doc);
			}
			
			if (cont == chaves.size())
					break;
			this.tabela = null;
		}
		
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
	 * @throws DocumentException 
	 * 		Caso a API ITextPdf não consiga criar um Arquivo do tipo Document.
	 */
	public void preencheDias(String dia, String descricao, Document doc) throws DocumentException{
		
		for (String id : chaves) {
			try{
			if (dados.getTurmas().get(id).getDiaDaSemana().equals(dia) && dados.getTurmas().get(id).getDescricao().equals(descricao)){
				
				String identificador = dados.getTurmas().get(id).getIdentificadorDisciplina();		
				PdfPCell celula = new PdfPCell(new Phrase(identificador+" de "+dados.getTurmas().get(id).getHoraInicio()+" às "+dados.getTurmas().get(id).getHoraFim()));
				celula.setColspan(0);
				tabela.addCell(celula);
				chaves.remove(0);
				break;
				
			}else if (dados.getTurmas().get(id).getDiaDaSemana() != dia || dados.getTurmas().get(id).getDescricao() != descricao){
		
				PdfPCell celula1 = new PdfPCell(new Phrase(" "));
				celula1.setColspan(0);
				tabela.addCell(celula1);
				break;
			}
			}catch (Exception e){}
		}
		
		doc.add(this.tabela);

	}
	
	/**
	 * Metodo que exibe o PDF atraves da biblioteca Acrobat do Java beans.
	 *
	 * @param filename 
	 * 		URL + Nome do Arquivo que será exibido 
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
	
}

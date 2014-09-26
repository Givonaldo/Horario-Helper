package br.com.H2Helper.relatorios;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import javax.swing.JButton;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Classe que representa uma Ávore de diretórios com opções de caminhos
 * para o armazenamento do relatório.
 *  
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 * @version 1.0
 */
public class ArvoreDeDiretorios extends JDialog {

	private static final long serialVersionUID = -1239177161119419993L;
	private JTree fileTree;
	private SistemaDeModelo1 fileSystemModel;
	private JTextArea fileDetailsTextArea = new JTextArea();
	private String caminhoSelecionado, arquivo;
	private String caminhoImg = (System.getProperty("user.dir")+System.getProperty("file.separator")+"img"+System.getProperty("file.separator"));
	private String caminho = System.getProperty("user.home")+System.getProperty("file.separator");
	
	public ArvoreDeDiretorios(java.awt.Frame parent, boolean modal) {

		super(parent, modal);
		setTitle("Salvar Como ");
		setComponentes();
	}

	/**
	 * Metodo que adiciona todos os componentes da árvore de 
	 * diretórios.
	 * 
	 */
	public void setComponentes() {

		fileDetailsTextArea.setEditable(true);
		fileDetailsTextArea.setLayout(new GridBagLayout());
		fileDetailsTextArea.setSize(200, 330);
		ImageIcon imagem = new ImageIcon(caminhoImg + "download.jpg");
		JLabel label = new JLabel("");
		label.setIcon(imagem);
		fileDetailsTextArea.add(label);
		fileSystemModel = new SistemaDeModelo1(new File(caminho));
		fileTree = new JTree(fileSystemModel);
		fileTree.setEditable(true);
		fileTree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent event) {
				File file = (File) fileTree.getLastSelectedPathComponent();
				fileDetailsTextArea.setText(getFileDetails(file));
			}
		});
		getContentPane().setLayout(null);
		JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,true, new JScrollPane(fileTree), new JScrollPane(fileDetailsTextArea));
		splitPane.setDividerLocation(260);
		splitPane.setBounds(0, 0, 600, 400);
		splitPane.setOneTouchExpandable(true);
		getContentPane().add(splitPane);
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(471, 412, 117, 25);
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		getContentPane().add(btnCancelar);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(345, 412, 117, 25);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				setCaminhoSelecionado(getArquivo());
				dispose();
			}
		});
		getContentPane().add(btnOk);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setSize(630, 480);
		setLocationRelativeTo(null);
		
	}

	/**
	 * Metodo que imprime todos os detalhes do diretorio 
	 * selecionado.
	 * 
	 * @param file
	 * 		URL do diretório que foi selecionado.
	 * @return String
	 * 		uma String com todos os detalhes do caminho 
	 * 		escolhido. 
	 */
	private String getFileDetails(File file) {
		
		if (file == null)
			return "";

		setArquivo(file.getPath());
		StringBuffer buffer = new StringBuffer();
		buffer.append("Name: " + file.getName() + "\n");
		buffer.append("Path: " + file.getPath() + "\n");
		buffer.append("Tamanho: " + file.length() + "\n");
		return buffer.toString();
	}

	/**
	 * 
	 * @param arquivo
	 */
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	/**
	 * 
	 * @return arquivo
	 */
	public String getArquivo() {
		return arquivo;
	}

	/**
	 * 
	 * @param caminhoSelecionado
	 */
	public void setCaminhoSelecionado(String caminhoSelecionado) {
		this.caminhoSelecionado = caminhoSelecionado;
	}

	/**
	 * 
	 * @return caminhoSelecionado
	 */
	public String getCaminhoSelecionado() {
		return caminhoSelecionado+System.getProperty("file.separator");
	}

}

/**
 * Classe interna que representará um modelo de Diretórios 
 * que será adicionado em um Vector e exibido como opção 
 * de escolha para o usuário para caminho de relatórios.
 * 
 * @author Gilvonaldo Alves da Silva Cavalcanti.
 *  @version 1.0
 */
class SistemaDeModelo1 implements TreeModel {

	private File raiz;
	private Vector<TreeModelListener> ouvinte = new Vector<TreeModelListener>();

	/**
	 * 
	 * @param rootDirectory
	 */
	public SistemaDeModelo1(File rootDirectory) {
		raiz = rootDirectory;
	}

	/**
	 * 
	 */
	public Object getRoot() {
		return raiz;
	}

	/**
	 * 
	 */
	public Object getChild(Object parent, int index) {
		File directory = (File) parent;
		String[] children = directory.list();
		return new Arvore(directory, children[index]);
	}

	/**
	 * 
	 */
	public int getChildCount(Object parent) {
		File file = (File) parent;
		if (file.isDirectory()) {
			String[] fileList = file.list();
			if (fileList != null)
				return file.list().length;
		}
		return 0;
	}

	/**
	 * 
	 */
	public boolean isLeaf(Object node) {
		File file = (File) node;
		return file.isFile();
	}

	/**
	 * 
	 */
	public int getIndexOfChild(Object parent, Object child) {
		File directory = (File) parent;
		File file = (File) child;
		String[] children = directory.list();
		for (int i = 0; i < children.length; i++) {
			if (file.getName().equals(children[i])) {
				return i;
			}
		}
		return -1;

	}

	/**
	 * 
	 */
	public void valueForPathChanged(TreePath path, Object value) {

		File oldFile = (File) path.getLastPathComponent();
		String fileParentPath = oldFile.getParent();
		String newFileName = (String) value;
		File targetFile = new File(fileParentPath, newFileName);
		oldFile.renameTo(targetFile);
		File parent = new File(fileParentPath);
		int[] changedChildrenIndices = { getIndexOfChild(parent, targetFile) };
		Object[] changedChildren = { targetFile };
		fireTreeNodesChanged(path.getParentPath(), changedChildrenIndices,
				changedChildren);

	}

	/**
	 * Metodo que realiza a adição de todos os caminho em um vetor 
	 * enquanto ouver proximo "filho" de diretorios para adicionar. 
	 * @param parentPath
	 * @param indices
	 * @param children
	 */
	private void fireTreeNodesChanged(TreePath parentPath, int[] indices,
			Object[] children) {

		TreeModelEvent event = new TreeModelEvent(this, parentPath, indices,
				children);
		Iterator<TreeModelListener> iterator = ouvinte.iterator();
		TreeModelListener listener = null;
		while (iterator.hasNext()) {
			listener = (TreeModelListener) iterator.next();
			listener.treeNodesChanged(event);
		}
	}

	/**
	 * 
	 */
	public void addTreeModelListener(TreeModelListener listener) {
		ouvinte.add(listener);
	}

	/**
	 * 
	 */
	public void removeTreeModelListener(TreeModelListener listener) {
		ouvinte.remove(listener);
	}

	/**
	 * Super classe que representará a Arvore de diretórios.
	 * 
	 * @author Gilvonaldo Alves da Silva Cavalcanti.
	 */
	private class Arvore extends File {
		
		private static final long serialVersionUID = 7263330561245112800L;

		public Arvore(File parent, String child) {
			super(parent, child);
		}

		public String toString() {
			return getName();
		}
	}
}

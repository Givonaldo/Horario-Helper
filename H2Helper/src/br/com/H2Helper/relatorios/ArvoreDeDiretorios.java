package br.com.H2Helper.relatorios;

import java.io.File;
import java.util.Iterator;
import java.util.Vector;

import javax.swing.JDialog;
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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author Gilvonaldo
 *
 */
public class ArvoreDeDiretorios extends JDialog {

	private static final long serialVersionUID = -1239177161119419993L;
	private JTree fileTree;
	private SistemaDeModelo1 fileSystemModel;
	private JTextArea fileDetailsTextArea = new JTextArea();
	private String caminhoSelecionado, arquivo;

	public ArvoreDeDiretorios(java.awt.Frame parent, boolean modal) {

		super(parent, modal);
		setTitle("Salvar Como ");
		setComponentes();
	}

	public void setComponentes() {

		// retorna uma String com o caminho de todos os arquivos até a
		// raiz do sistema.
		String caminho = System.getProperty("user.home")+System.getProperty("file.separator");

		fileDetailsTextArea.setEditable(true);
		fileDetailsTextArea.setSize(200, 600);

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
		setVisible(true);

	}

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

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setCaminhoSelecionado(String caminhoSelecionado) {
		this.caminhoSelecionado = caminhoSelecionado;
	}

	public String getCaminhoSelecionado() {
		return caminhoSelecionado+System.getProperty("file.separator");
	}

}


class SistemaDeModelo1 implements TreeModel {

	private File raiz;
	private Vector ouvinte = new Vector();

	public SistemaDeModelo1(File rootDirectory) {
		raiz = rootDirectory;
	}

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
	 * 
	 * @param parentPath
	 * @param indices
	 * @param children
	 */
	private void fireTreeNodesChanged(TreePath parentPath, int[] indices,
			Object[] children) {

		TreeModelEvent event = new TreeModelEvent(this, parentPath, indices,
				children);
		Iterator iterator = ouvinte.iterator();
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
	 * 
	 * @author Gilvonaldo
	 *
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

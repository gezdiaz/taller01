package frsf.isi.died.tp.app.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Enumeration;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;

import frsf.isi.died.tp.app.controller.LibroController;
import frsf.isi.died.tp.app.controller.VideoController;
import frsf.isi.died.tp.estructuras.arbolContenido.ArbolContenido;
import frsf.isi.died.tp.estructuras.arbolContenido.TipoNodo;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class ArbolContenidoPanel {

	private JFrame ventana;
	
	public ArbolContenidoPanel(JFrame ventana) {
		this.ventana = ventana;
	}
	
	public void mostrarArbolContenido(MaterialCapacitacion mat) {
		JPanel panel = new JPanel(new BorderLayout());
		DefaultMutableTreeNode raiz = new DefaultMutableTreeNode(mat.getContenido());
		agregarNodos(raiz, mat.getContenido());
		DefaultTreeModel treeModel = new DefaultTreeModel(raiz);
		JTree arbol = new JTree(treeModel);
		JScrollPane scroll = new JScrollPane(arbol);
		JFrame interna = new JFrame((mat.esLibro()?"Libro":"Video")+": "+mat.getTitulo());
		JPopupMenu menu = new JPopupMenu();
		JMenuItem menuItem;
//		JButton boton;
//		
//		boton = new JButton("Agregar Contenido");
//		boton.addActionListener(a -> {
//			try {
//				DefaultMutableTreeNode seleccionado = (DefaultMutableTreeNode)arbol.getLastSelectedPathComponent();
//				ArbolContenido contenido = (ArbolContenido)seleccionado.getUserObject();
//				agregarContenido(contenido, arbol, seleccionado);
//			}catch(Exception e) {
//				JOptionPane.showConfirmDialog(ventana, "Por favor seleccione un elemento del árbol para agregar hijos.", "Seleccione un elemento",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
//			}
//		});
		
		menuItem = new JMenuItem("Agregar Hijo");
		menuItem.addActionListener(a -> {
			try {
				DefaultMutableTreeNode seleccionado = (DefaultMutableTreeNode)arbol.getLastSelectedPathComponent();
				ArbolContenido contenido = (ArbolContenido)seleccionado.getUserObject();
				agregarContenido(contenido, arbol, seleccionado, interna);
				System.out.print("En el material: ");
				mat.getContenido().imprimirArbol("");
				System.out.print("Raiz: ");
				((ArbolContenido)raiz.getUserObject()).imprimirArbol("");;
			}catch(Exception e) {
				JOptionPane.showConfirmDialog(interna, "Por favor seleccione un elemento del árbol para agregar hijos.", "Seleccione un elemento",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Eliminar Contenido");
		menuItem.addActionListener(a -> {
			try {
				DefaultMutableTreeNode seleccionado = (DefaultMutableTreeNode)arbol.getLastSelectedPathComponent();
				ArbolContenido contenido = (ArbolContenido)seleccionado.getUserObject();
				if(contenido.getTipo().equals(TipoNodo.TITULO)) {
					JOptionPane.showConfirmDialog(interna, "No puede eliminar el Título", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}else {
					if(JOptionPane.showConfirmDialog(ventana, "¿Está seguro que desea eliminar el contenido seleccionado y todos sus hijos?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0) {
						treeModel.removeNodeFromParent(seleccionado);
						mat.getContenido().eliminarNodo(contenido);
					}
				}
				
			}catch(Exception e) {
				JOptionPane.showConfirmDialog(interna, "Por favor seleccione un elemento del árbol para eliminar.", "Seleccione un elemento",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Salir");
		menuItem.addActionListener(a -> interna.dispose());
		menu.add(menuItem);
		
		arbol.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent event) {
            	if(event.getButton() == MouseEvent.BUTTON3 && event.getClickCount() == 1 && !event.isConsumed()) {
            		event.consume();
            		menu.show(interna, event.getX(), event.getY()-10);
            	}

			}
		});
		
//		panel.add(boton,BorderLayout.SOUTH);
		panel.add(scroll, BorderLayout.CENTER);
		interna.setContentPane(panel);
		interna.pack();
		interna.setSize(new Dimension(400, 400));
		interna.setLocationRelativeTo(ventana);
		interna.setVisible(true);
		interna.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
	}

	private void agregarContenido(ArbolContenido contenido, JTree arbol, DefaultMutableTreeNode padre, JFrame ventanaArbol) {
		JDialog nueva = new JDialog();
		JPanel panelAgregar = new JPanel(new GridBagLayout());
		TipoNodo[] array = {TipoNodo.METADATO, TipoNodo.AUTOR, TipoNodo.SECCION,
				TipoNodo.PARRAFO, TipoNodo.CAPITULO, TipoNodo.EDITORIAL, TipoNodo.RESUMEN,
				TipoNodo.PALABRA_CLAVE};
		JComboBox<TipoNodo> tipo = new JComboBox<>(array);
		JTextField valor = new JTextField(20);
		JLabel label;
		JButton boton;
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.insets = new Insets(5, 5, 5, 5);
		cons.gridheight=1;
		cons.gridwidth=1;
		cons.gridx=0;
		cons.gridy=0;
		label = new JLabel("Valor: ");
		panelAgregar.add(label,cons);
		
		cons.gridx=1;
		panelAgregar.add(valor);
		
		cons.gridx=0;
		cons.gridy=1;
		label = new JLabel("Tipo: ");
		panelAgregar.add(label, cons);
		
		cons.gridx=1;
		cons.fill=GridBagConstraints.BOTH;
		panelAgregar.add(tipo, cons);
		
		cons.gridx=0;
		cons.gridy=2;
		cons.fill=GridBagConstraints.NONE;
		cons.anchor=GridBagConstraints.WEST;
		boton = new JButton("Cancelar");
		boton.addActionListener(a -> {
			nueva.dispose();
		});
		panelAgregar.add(boton, cons);
		
		cons.gridx=1;
		cons.anchor = GridBagConstraints.EAST;
		boton = new JButton("Aceptar");
		boton.addActionListener(a -> {
			if(valor.getText().isEmpty()) {
				JOptionPane.showConfirmDialog(ventanaArbol, "Por favor ingrese un valor para agregar al contenido", "Ingrese un valor",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}else {
				if(((ArbolContenido)padre.getUserObject()).getTipo() == TipoNodo.TITULO 
						&& ((TipoNodo)tipo.getSelectedItem() == TipoNodo.METADATO 
							|| (TipoNodo)tipo.getSelectedItem() == TipoNodo.RESUMEN
							|| (TipoNodo)tipo.getSelectedItem() == TipoNodo.CAPITULO)) {
					ArbolContenido hijo = new ArbolContenido(valor.getText(), (TipoNodo)tipo.getSelectedItem()); 
					contenido.addHijo(hijo);
					padre.add(new DefaultMutableTreeNode(hijo));
					arbol.updateUI();
					nueva.dispose();
					System.out.print("En la ventanita: ");
					contenido.imprimirArbol("");
				}else {
					if(((ArbolContenido)padre.getUserObject()).getTipo() == TipoNodo.TITULO) {
						JOptionPane.showConfirmDialog(ventanaArbol, "Los hijos de título solo pueden ser: METADATO, RESUMEN o CAPÍTULO", "Error en tipo de contenido",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}else {
						ArbolContenido hijo = new ArbolContenido(valor.getText(), (TipoNodo)tipo.getSelectedItem()); 
						contenido.addHijo(hijo);
						padre.add(new DefaultMutableTreeNode(hijo));
						arbol.updateUI();
						nueva.dispose();
						System.out.print("En la ventanita: ");
						contenido.imprimirArbol("");
					}
				}
				
			}
		});
		panelAgregar.add(boton, cons);
//		
//		ventanaArbol.add(nueva);
		nueva.setContentPane(panelAgregar);
		nueva.pack();
		nueva.setVisible(true);
		nueva.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
	}

	private void agregarNodos(DefaultMutableTreeNode padre, ArbolContenido contenido) {
		for(ArbolContenido nodo: contenido.getHijos()) {
			DefaultMutableTreeNode hijo = new DefaultMutableTreeNode(nodo);
			padre.add(hijo);
			agregarNodos(hijo, nodo);
		}
		
	}
	
}

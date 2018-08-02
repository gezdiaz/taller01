package frsf.isi.died.tp.app.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.WindowConstants;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

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
		JFrame interna = new JFrame("Contenido");
		JButton boton;
		
		boton = new JButton("Agregar");
		boton.addActionListener(a -> {
			try {
				ArbolContenido contenido = (ArbolContenido)((DefaultMutableTreeNode)arbol.getLastSelectedPathComponent()).getUserObject();
				agregarContenido(contenido);
			}catch(Exception e) {
				JOptionPane.showConfirmDialog(ventana, "Por favor seleccione un elemento del árbol para agregar hijos.", "Seleccione un elemento",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		});
		panel.add(boton,BorderLayout.SOUTH);
		scroll.setPreferredSize(new Dimension(100, 200));
		panel.add(scroll, BorderLayout.CENTER);
		interna.setContentPane(panel);
		interna.pack();
		interna.setLocationRelativeTo(ventana);
		interna.setVisible(true);
		interna.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);		
	}

	private void agregarContenido(ArbolContenido contenido) {
		JFrame nueva = new JFrame("Agregar Contenido a: "+contenido.toString());
		JPanel panel = new JPanel(new GridBagLayout());
		TipoNodo[] array = {TipoNodo.TITULO, TipoNodo.METADATO, TipoNodo.AUTOR, TipoNodo.SECCION,
				TipoNodo.PARRAFO, TipoNodo.CAPITULO, TipoNodo.EDITORIAL, TipoNodo.RESUMEN,
				TipoNodo.PALABRA_CLAVE};
		JComboBox<TipoNodo> tipo = new JComboBox<>(array);
		JTextField valor = new JTextField(20);
		JLabel label;
		JButton boton;
		GridBagConstraints cons = new GridBagConstraints();
		
		cons.gridheight=1;
		cons.gridwidth=1;
		cons.gridx=0;
		cons.gridy=0;
		label = new JLabel("Valor: ");
		panel.add(label,cons);
		
		cons.gridx=1;
		panel.add(valor);
		
		cons.gridx=0;
		cons.gridy=1;
		label = new JLabel("Tipo: ");
		panel.add(label, cons);
		
		cons.gridx=1;
		panel.add(tipo, cons);
		
		cons.gridx=0;
		cons.gridy=2;
		boton = new JButton("Cancelar");
		boton.addActionListener(a -> {
			nueva.dispose();
		});
		panel.add(boton, cons);
		
		cons.gridx=1;
		boton = new JButton("Aceptar");
		boton.addActionListener(a -> {
			if(valor.getText().isEmpty()) {
				JOptionPane.showConfirmDialog(ventana, "Por favor ingrese un valor para agregar al contenido", "Ingrese un valor",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}else {
				contenido.addHijo(new ArbolContenido(valor.getText(), (TipoNodo)tipo.getSelectedItem()));
			}
		});
		panel.add(boton, cons);
		
		nueva.setContentPane(panel);
		nueva.pack();
		nueva.setLocationRelativeTo(ventana);
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

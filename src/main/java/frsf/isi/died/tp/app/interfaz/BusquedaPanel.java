package frsf.isi.died.tp.app.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import org.junit.experimental.theories.Theories;

import frsf.isi.died.tp.app.interfaz.tabla.*;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.Video;

public class BusquedaPanel {
	
	private static JScrollPane scrollPane;
	private static JTable tabla;
	private static LibroTablaModelo tableModel;
	
	public static void busqueda(JFrame ventana) {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		JLabel label;
		JButton boton;
		JTextField tTitulo = new JTextField(20), tCalificacion = new JTextField(20),
		tTema = new JTextField(20), tFecha = new JTextField(20);
		JComboBox<TipoOrdenamiento> combo;
		
		label = new JLabel("Búsqueda");
		constraints.gridx=0;
		constraints.gridy=0;
		panel.add(label, constraints);
		
		label = new JLabel("Criterio de búsqueda:");
		constraints.gridy=3;
		panel.add(label,constraints);
		
		label = new JLabel("Título:");
		constraints.gridy=5;
		panel.add(label,constraints);
		
		constraints.gridx=2;
		panel.add(tTitulo, constraints);
		
		label = new JLabel("Calificación:");
		constraints.gridx=0;
		constraints.gridy=7;
		panel.add(label, constraints);
		
		constraints.gridx=2;
		panel.add(tCalificacion, constraints);
		
		label = new JLabel("Tema:");
		constraints.gridx=0;
		constraints.gridy=9;
		panel.add(label,constraints);
		
		constraints.gridx=2;
		panel.add(tTema, constraints);
		
		label = new JLabel("Rangos de fecha de publicación:");
		constraints.gridx=0;
		constraints.gridy=11;
		panel.add(label, constraints);
		
		constraints.gridx=2;
		panel.add(tFecha, constraints);
		
		label = new JLabel("Criterio de ordenamiento:");
		constraints.gridx=0;
		constraints.gridy=13;
		panel.add(label, constraints);
		
		combo = new JComboBox<TipoOrdenamiento>();
		combo.addItem(TipoOrdenamiento.TITULO);
		combo.addItem(TipoOrdenamiento.PRECIO);
		combo.addItem(TipoOrdenamiento.FECHA);
		combo.addItem(TipoOrdenamiento.CALIFICACION);
		combo.addItem(TipoOrdenamiento.RELEVANCIA);
		constraints.gridx=2;
		panel.add(combo, constraints);
		
		boton = new JButton("Cancelar");
		constraints.gridx=0;
		constraints.gridy=15;
		boton.addActionListener( a -> Principal.mostrarInterfaz(ventana));
		panel.add(boton, constraints);
		
		boton = new JButton("Buscar");
		constraints.gridx=3;
//		boton.addActionListener( );
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800, 600);
		
        ventana.setVisible(true);
	}

	public static void mostrarMaterialesTablaReLoca(JFrame ventana) {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gridConst = new GridBagConstraints();
		GridBagConstraints constraints = new GridBagConstraints();
		tableModel = new LibroTablaModelo();
		JButton btnAceptar = new JButton("Agregar otro");
//		LISTA DEMO DE LIBROS
		ArrayList<Libro> libros = new ArrayList<Libro>();
		Libro l1= new Libro(1, "Libro1", 10.0, 20.0, 154);libros.add(l1);
		Libro l2= new Libro(2, "Libro2", 20.0, 24.0, 361);libros.add(l2);
		Libro l3= new Libro(3, "Libro3", 15.0, 18.0, 108);libros.add(l3);
		Libro l4= new Libro(4, "Libro4", 30.0, 16.0, 250);libros.add(l4);
		Libro l5= new Libro(5, "Libro5", 24.0, 32.0, 545);libros.add(l5);
		Libro l6= new Libro(6, "Libro6", 28.0, 54.0, 302);libros.add(l6);
		
//		TITULO VENTANA
		JLabel lblTitulo = new JLabel("Lista de libros");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 7;
		constraints.gridheight = 2;
		constraints.weighty = 0.3;
		panel.add(lblTitulo,constraints);
		
		
		
		
		tableModel.setLibros(libros);
		tabla = new JTable(tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=7;	
		gridConst.gridy=2;
		gridConst.weighty=0.7;
		gridConst.weightx=1.0;		
		panel.add(scrollPane, gridConst);
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800, 600);
		
        ventana.setVisible(true);
	}
	

}

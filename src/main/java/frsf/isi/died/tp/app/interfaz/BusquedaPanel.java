package frsf.isi.died.tp.app.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.*;

import org.junit.experimental.theories.Theories;

import frsf.isi.died.tp.app.interfaz.tabla.*;
import frsf.isi.died.tp.modelo.BibliotecaABB;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Video;

public class BusquedaPanel {
	
	private static JScrollPane scrollPane;
	private static JTable tabla;
	private static BusquedaTablaModelo tableModel;
	
	public static void busqueda(JFrame ventana) {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		JLabel label;
		JButton boton;
		JTextField tTitulo = new JTextField(20), tCalificacion1 = new JTextField(10), 
				tCalificacion2 = new JTextField(10), tTema = new JTextField(20), 
				tFecha1 = new JTextField(10), tFecha2 = new JTextField(10);
		JComboBox<TipoOrdenamiento> combo;
		ArrayList<MaterialCapacitacion> materiales = new ArrayList<MaterialCapacitacion>();
		
		materiales.add( new Libro( 1, "Libro1", 10.0, 20.0, 154));
		materiales.add( new Libro( 2, "Libro2", 20.0, 24.0, 361));
		materiales.add( new Libro( 3, "Libro3", 15.0, 18.0, 108));
		materiales.add( new Libro( 4, "Libro4", 30.0, 16.0, 250));
		materiales.add( new Libro( 5, "Libro5", 24.0, 32.0, 545));
		materiales.add( new Libro( 6, "Libro6", 28.0, 54.0, 302));
		materiales.add( new Video( 7, "Video7", 28.0, 360));
		materiales.add( new Video( 8, "Video8", 15.0, 625));
		materiales.add( new Video( 9, "Video9", 30.0, 145));
		materiales.add( new Video( 10, "Video10", 45.0, 38));
		materiales.add( new Video( 11, "Video11", 12.0, 60));
		
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
		panel.add(tCalificacion1, constraints);
		constraints.gridx=3;
		panel.add(tCalificacion2, constraints);
		
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
		panel.add(tFecha1, constraints);
		constraints.gridx=3;
		panel.add(tFecha2, constraints);
		
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
		boton.addActionListener( a -> {
			ArrayList<MaterialCapacitacion> filtrados = new ArrayList<MaterialCapacitacion>();
			filtrados = materiales;
			Integer c1, c2;
			Date fecha1, fecha2;
				try {
					if(!tTitulo.getText().isEmpty()) 
						filtrados.removeIf(material -> !material.getTitulo().contains(tTitulo.getText()) );
					if(!tCalificacion1.getText().isEmpty() && !tCalificacion2.getText().isEmpty()) {
						c1 = Integer.parseInt(tCalificacion1.getText());
						c2 = Integer.parseInt(tCalificacion2.getText());
						filtrados.removeIf(material -> material.getCalificacion()<c1 || material.getCalificacion()>c2);
					}
					if(!tFecha1.getText().isEmpty() && !tFecha2.getText().isEmpty()) {
						fecha1 = (new SimpleDateFormat("dd/MM/yyyy")).parse(tFecha1.getText());
						fecha2 = (new SimpleDateFormat("dd/MM/yyyy")).parse(tFecha2.getText());
						filtrados.removeIf(material -> material.getFechaPublicacion().getTime()<fecha1.getTime()	
														|| material.getFechaPublicacion().getTime()>fecha2.getTime());
					}
					
//					for(MaterialCapacitacion mat: filtrados) {
//						if(!tTitulo.getText().isEmpty()) {
//							if(!mat.getTitulo().contains(tTitulo.getText())) {
//								filtrados.remove(mat);
//							}
//						}else if(!tCalificacion1.getText().isEmpty() && !tCalificacion2.getText().isEmpty()) {
//							if(mat.getCalificacion()<c1 || mat.getCalificacion()>c2) {
//								filtrados.remove(mat);
//							}
//						}else if(!tTema.getText().isEmpty()) {
//							if(!mat.getTema().equals(tTema.getText())) {
//								filtrados.remove(mat);
//							}
//						}else if(!tFecha1.getText().isEmpty() && !tFecha2.getText().isEmpty()) {
//							if(mat.getFechaPublicacion().getTime()<fecha1.getTime()	|| mat.getFechaPublicacion().getTime()>fecha2.getTime()) { 
//								filtrados.remove(mat);
//							}
//						}
//					}
					TipoOrdenamiento tipo = (TipoOrdenamiento) combo.getSelectedItem();
					BibliotecaABB ordenados = new BibliotecaABB();
					for(MaterialCapacitacion mat: filtrados)	ordenados.agregar(mat);
					switch(tipo) {
					case TITULO:
						ordenados.ordenarPorTitulo();
						break;
					case PRECIO:
						ordenados.ordenarPorPrecio();
						break;
					case CALIFICACION:
						ordenados.ordenarPorCalificacion();
						break;
					case FECHA:
						ordenados.ordenarPorFecha();
						break;
					case RELEVANCIA:
						ordenados.ordenarPorRelevancia();
						break;
					}
					filtrados.removeAll(filtrados);
					filtrados.addAll(ordenados.materiales());
					BusquedaPanel.mostrarMaterialesTablaReLoca(ventana, filtrados);
				}catch(NumberFormatException nfex) {
					System.out.println("Puso otra cosa en un campo numérico");
					JOptionPane.showConfirmDialog(ventana, "El campo calificación debe llevar un número entre 1 y 100.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);	
					//nfex.printStackTrace();
				}catch(ParseException pex) {
					System.out.println("La fecha está mal escrita");
					JOptionPane.showConfirmDialog(ventana, "La fecha debe ser escrita con formato dd/mm/aaaa", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			
			
		});
		
		panel.add(boton,constraints);
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800, 600);
		
        ventana.setVisible(true);
	}

	public static void mostrarMaterialesTablaReLoca(JFrame ventana, ArrayList<MaterialCapacitacion> materiales) {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gridConst = new GridBagConstraints();
		GridBagConstraints constraints = new GridBagConstraints();
		tableModel = new BusquedaTablaModelo();
		JButton btnAceptar = new JButton("Agregar otro");
//		LISTA DEMO DE LIBROS
		
		
//		ACA SE FILTRA
		
		
		
//		TITULO VENTANA
		JLabel lblTitulo = new JLabel("Lista de materiales");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 7;
		constraints.gridheight = 2;
		constraints.weighty = 0.3;
		panel.add(lblTitulo,constraints);
		
		
		
		
		tableModel.setMateriales(materiales);
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

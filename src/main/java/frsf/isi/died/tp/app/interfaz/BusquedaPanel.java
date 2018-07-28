package frsf.isi.died.tp.app.interfaz;

import java.awt.Font;
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
		JTextField tTitulo = new JTextField(23), tCalificacion1 = new JTextField(8), 
				tCalificacion2 = new JTextField(8), tTema = new JTextField(23), 
				tFecha1 = new JTextField(8), tFecha2 = new JTextField(8);
		JComboBox<TipoOrdenamiento> combo;
//		variable temporal para simular la busqueda
		ArrayList<MaterialCapacitacion> materiales = new ArrayList<MaterialCapacitacion>();
		materiales.add( new Libro( 1, "Libro1", 10.0, 20.0, 154));
		materiales.add( new Libro( 2, "Java", 20.0, 24.0, 361));
		materiales.add( new Libro( 3, "Python", 15.0, 18.0, 108));
		materiales.add( new Libro( 4, "C", 30.0, 16.0, 250));
		materiales.add( new Libro( 5, "hola", 24.0, 32.0, 545));
		materiales.add( new Libro( 6, "Libro6", 28.0, 54.0, 302));
		materiales.add( new Video( 7, "El escape del paralítico", 28.0, 360));
		materiales.add( new Video( 8, "El regreso", 15.0, 625));
		materiales.add( new Video( 9, "Java", 30.0, 145));
		materiales.add( new Video( 10, "Eclipse", 45.0, 38));
		materiales.add( new Video( 11, "Video11", 12.0, 60));
//		TODO deberia agregar todos los materiales que hay en el almacenamiento
		
		label = new JLabel("Búsqueda");	
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridheight=1;
		constraints.gridwidth=5;
		constraints.anchor=GridBagConstraints.NORTH;
		label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 40));
		panel.add(label,constraints);

		label = new JLabel("Criterio de búsqueda:");
		constraints.gridx=0;
		constraints.gridy=3;
		constraints.gridwidth=1;
		label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 15));
		panel.add(label,constraints);
		
		label = new JLabel("Título:");
		constraints.gridx=0;
		constraints.gridy=5;
		constraints.gridwidth=1;
		constraints.anchor = GridBagConstraints.WEST;
		panel.add(label,constraints);
		
		constraints.gridx=2;
		constraints.gridy=5;
		constraints.gridwidth=3;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(tTitulo, constraints);
		
		
		label = new JLabel("Calificación desde:");
		constraints.gridx=0;
		constraints.gridy=7;
		constraints.gridwidth=1;
		constraints.anchor = GridBagConstraints.WEST;
		panel.add(label, constraints);
		
		constraints.gridx=2;
		constraints.gridy=7;
		constraints.gridwidth=1;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(tCalificacion1, constraints);
		
		label = new JLabel(" hasta: ");
		constraints.gridx=3;
		constraints.gridy=7;
		constraints.gridwidth=1;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(label, constraints);
		
		constraints.gridx=4;
		constraints.gridy=7;
		constraints.gridwidth=1;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(tCalificacion2, constraints);
		
		
		label = new JLabel("Tema:");
		constraints.gridx=0;
		constraints.gridy=9;
		constraints.gridwidth=1;
		constraints.anchor = GridBagConstraints.WEST;
		panel.add(label,constraints);
		
		constraints.gridx=2;
		constraints.gridy=9;
		constraints.gridwidth=3;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(tTema, constraints);
		
		
		label = new JLabel("Rangos de fecha de publicación:");
		constraints.gridx=0;
		constraints.gridy=11;
		constraints.gridwidth=1;
		constraints.anchor = GridBagConstraints.WEST;
		panel.add(label, constraints);
		
		constraints.gridx=2;
		constraints.gridy=11;
		constraints.gridwidth=1;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(tFecha1, constraints);
		
		label = new JLabel(" hasta: ");
		constraints.gridx=3;
		constraints.gridy=11;
		constraints.gridwidth=1;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(label, constraints);
		
		constraints.gridx=4;
		constraints.gridy=11;
		constraints.gridwidth=1;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(tFecha2, constraints);
		
		label = new JLabel("Criterio de ordenamiento:");
		constraints.gridx=0;
		constraints.gridy=13;
		constraints.gridwidth=1;
		constraints.anchor = GridBagConstraints.WEST;
		panel.add(label, constraints);
		
		combo = new JComboBox<TipoOrdenamiento>();
		combo.addItem(TipoOrdenamiento.TITULO);
		combo.addItem(TipoOrdenamiento.PRECIO);
		combo.addItem(TipoOrdenamiento.FECHA);
		combo.addItem(TipoOrdenamiento.CALIFICACION);
		combo.addItem(TipoOrdenamiento.RELEVANCIA);
		constraints.gridx=2;
		constraints.gridwidth=3;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(combo, constraints);
		
		boton = new JButton("Cancelar");
		constraints.gridx=0;
		constraints.gridy=15;
		constraints.gridwidth=1;
		boton.addActionListener( a -> Principal.mostrarInterfaz(ventana));
		panel.add(boton, constraints);
		
		boton = new JButton("Buscar");
		constraints.gridx=3;
		boton.addActionListener( a -> {
			ArrayList<MaterialCapacitacion> filtrados = new ArrayList<MaterialCapacitacion>();
			String titulo = null;
			Integer califMenor = null, califMayor = null;
			Date fechaMenor = null, fechaMayor = null;
			BibliotecaABB biblioteca = new BibliotecaABB();
			biblioteca.agregar(materiales);

				try {
					if(!tTitulo.getText().isEmpty()) 
						titulo=tTitulo.getText();
					if(!tCalificacion1.getText().isEmpty() && !tCalificacion2.getText().isEmpty()) {
						califMenor = Integer.parseInt(tCalificacion1.getText());
						califMayor = Integer.parseInt(tCalificacion2.getText());
					}
					if(!tFecha1.getText().isEmpty() && !tFecha2.getText().isEmpty()) {
						fechaMenor = (new SimpleDateFormat("dd/MM/yyyy")).parse(tFecha1.getText());
						fechaMayor = (new SimpleDateFormat("dd/MM/yyyy")).parse(tFecha2.getText());
					}

					filtrados = biblioteca.buscar(titulo, califMenor, califMayor, fechaMenor, fechaMayor);
					
					TipoOrdenamiento tipo = (TipoOrdenamiento) combo.getSelectedItem();
					BibliotecaABB ordenados = new BibliotecaABB();
					ordenados.agregar(filtrados);
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
					if(ordenados.materiales().isEmpty()) {
						JOptionPane.showConfirmDialog(ventana, "No se ha encontrado ninún material que coincida con los criterios de búsqueda.","No se encontró material", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					}else {
						mostrarMaterialesTabla(ventana, (ArrayList<MaterialCapacitacion>) ordenados.materiales());
					}
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

	public static void mostrarMaterialesTabla(JFrame ventana, ArrayList<MaterialCapacitacion> materiales) {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gridConst = new GridBagConstraints();
		GridBagConstraints constraints = new GridBagConstraints();
		tableModel = new BusquedaTablaModelo();
		JButton boton;
		
		
//		TITULO VENTANA
		JLabel lblTitulo = new JLabel("Lista de materiales");
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 7;
		constraints.gridheight = 2;
		constraints.weighty = 0.25;
		constraints.anchor=GridBagConstraints.NORTH;
		lblTitulo.setFont(new Font(lblTitulo.getFont().getName(), lblTitulo.getFont().getStyle(), 20));
		panel.add(lblTitulo,constraints);
		
		
//		TABLA
		tableModel.setMateriales(materiales);
		tabla = new JTable(tableModel);
		tabla.setFillsViewportHeight(true);
		scrollPane= new JScrollPane(tabla);
		
		gridConst.gridx=0;
		gridConst.gridwidth=2;	
		gridConst.gridy=2;
		gridConst.weighty=0.5;
		gridConst.weightx=1;
		gridConst.anchor = GridBagConstraints.CENTER;
		gridConst.fill=GridBagConstraints.HORIZONTAL;
		panel.add(scrollPane, gridConst);
		
		
//		BOTONES
		boton = new JButton("Inicio");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.weighty = 0.25;
		constraints.anchor = GridBagConstraints.WEST;
		boton.addActionListener( a -> Principal.mostrarInterfaz(ventana));
		panel.add(boton,constraints);
		
		boton = new JButton("Atrás");
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.weighty = 0.25;
		constraints.anchor = GridBagConstraints.EAST;
		boton.addActionListener( a -> busqueda(ventana));
		panel.add(boton,constraints);
		
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800, 600);
		
        ventana.setVisible(true);
	}
	

}

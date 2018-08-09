package frsf.isi.died.tp.app.interfaz;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import frsf.isi.died.tp.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.tp.app.interfaz.tabla.*;
import frsf.isi.died.tp.modelo.BibliotecaABB;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class BusquedaPanel {
	
	private MaterialCapacitacionDao dao;
	private JFrame ventana;
	private ListaDeseosPanel listaPanel;
	private RelacionesPanel relacionesPanel;
	private ArbolContenidoPanel arbolContenido;
	private BusquedaContenidoPanel busquedaContenido;

	
	
	public BusquedaPanel(MaterialCapacitacionDao dao, JFrame ventana) {
		this.dao = dao;
		this.ventana = ventana;
		this.relacionesPanel = new RelacionesPanel(dao,ventana);
		this.arbolContenido = new ArbolContenidoPanel(ventana);
		this.busquedaContenido = new BusquedaContenidoPanel(ventana, this, dao);
	}
	
//	public void setGrafoPanel(GrafoPanel grafoPanel) {
//		this.grafoPanel= grafoPanel;
//	}
	
	public void setListaPanel(ListaDeseosPanel listaPanel) {
		this.listaPanel= listaPanel;
	}
	
	public void setRelacionesPanel(RelacionesPanel relacionesPanel) {
		this.relacionesPanel = relacionesPanel;
	}
	
	public void busqueda() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		JLabel label;
		JButton boton;
		JTextField tTitulo = new JTextField(23), tCalificacion1 = new JTextField(8), 
				tCalificacion2 = new JTextField(8), tTema = new JTextField(23), 
				tFecha1 = new JTextField(8), tFecha2 = new JTextField(8);
		JComboBox<TipoOrdenamiento> combo;
//		variable temporal para simular la busqueda
//		ArrayList<MaterialCapacitacion> materiales = (ArrayList<MaterialCapacitacion>)dao.listaMateriales(); // new ArrayList<MaterialCapacitacion>();
//		try {
//			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
//			materiales.add( new Libro( 1, "Libro1", 10.0, 20.0, 154, formatoFecha.parse("05/02/2010"), Relevancia.MEDIA));
//			materiales.add( new Libro( 2, "Java", 20.0, 24.0, 361, formatoFecha.parse("05/02/2005"), Relevancia.ALTA));
//			materiales.add( new Libro( 3, "Python", 15.0, 18.0, 108, formatoFecha.parse("05/04/2010"), Relevancia.BAJA));
//			materiales.add( new Libro( 4, "C", 30.0, 16.0, 250, formatoFecha.parse("10/02/2010"), Relevancia.ALTA));
//			materiales.add( new Libro( 5, "hola", 24.0, 32.0, 545, formatoFecha.parse("05/02/2018"), Relevancia.BAJA));
//			materiales.add( new Libro( 6, "Libro6", 28.0, 54.0, 302, formatoFecha.parse("05/12/2010"), Relevancia.MEDIA));
//			materiales.add( new Video( 7, "El escape del paralítico", 28.0, 360, formatoFecha.parse("15/12/2000"), Relevancia.MEDIA));
//			materiales.add( new Video( 8, "El regreso", 15.0, 625, formatoFecha.parse("25/12/2010"), Relevancia.BAJA));
//			materiales.add( new Video( 9, "Java", 30.0, 145, formatoFecha.parse("23/02/2014"), Relevancia.ALTA));
//			materiales.add( new Video( 10, "Eclipse", 45.0, 38, formatoFecha.parse("27/03/2017"), Relevancia.BAJA));
//			materiales.add( new Video( 11, "Video11", 12.0, 60, formatoFecha.parse("14/01/2016"), Relevancia.MEDIA));
//		} catch (ParseException e) {
//			
//			e.printStackTrace();
//		}
		
		
		constraints.insets=new Insets(5, 5, 5, 5);
		label = new JLabel("Búsqueda");	
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridheight=1;
		constraints.gridwidth=4;
		constraints.anchor=GridBagConstraints.NORTH;
		label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 40));
		panel.add(label,constraints);

		label = new JLabel("Criterios de búsqueda:");
		constraints.fill=GridBagConstraints.HORIZONTAL;
		constraints.anchor=GridBagConstraints.CENTER;
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
		
		constraints.gridx=1;
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
		
		constraints.gridx=1;
		constraints.gridy=7;
		constraints.gridwidth=1;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(tCalificacion1, constraints);
		
		label = new JLabel(" hasta: ");
		constraints.gridx=2;
		constraints.gridy=7;
		constraints.gridwidth=1;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(label, constraints);
		
		constraints.gridx=3;
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
		
		constraints.gridx=1;
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
		
		constraints.gridx=1;
		constraints.gridy=11;
		constraints.gridwidth=1;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(tFecha1, constraints);
		
		label = new JLabel(" hasta: ");
		constraints.gridx=2;
		constraints.gridy=11;
		constraints.gridwidth=1;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(label, constraints);
		
		constraints.gridx=3;
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
		constraints.gridx=1;
		constraints.gridwidth=3;
		constraints.anchor = GridBagConstraints.CENTER;
		panel.add(combo, constraints);
		
		boton = new JButton("Cancelar");
		constraints.gridx=0;
		constraints.gridy=15;
		constraints.gridwidth=1;
		constraints.fill = GridBagConstraints.NONE;
		constraints.anchor = GridBagConstraints.WEST;
		boton.addActionListener( a -> Principal.mostrarInterfaz());
		panel.add(boton, constraints);
		
		boton = new JButton("Bucar por contenido");
		constraints.gridx=1;
		constraints.gridwidth=2;
		constraints.anchor = GridBagConstraints.CENTER;
		boton.addActionListener(a -> busquedaContenido.buscarPorContenido());
		panel.add(boton, constraints);
		
		boton = new JButton("Buscar");
		constraints.gridx=3;
		constraints.gridwidth=1;
		constraints.anchor = GridBagConstraints.EAST;
		boton.addActionListener( a -> {
			ArrayList<MaterialCapacitacion> filtrados = new ArrayList<MaterialCapacitacion>();
			String titulo = null, tema = null;
			Integer califMenor = null, califMayor = null;
			Date fechaMenor = null, fechaMayor = null;
			BibliotecaABB biblioteca = new BibliotecaABB();
			ArrayList<MaterialCapacitacion> materiales = (ArrayList<MaterialCapacitacion>)dao.listaMateriales();
			biblioteca.agregar(materiales);

				try {
					if(!tTitulo.getText().isEmpty()) 
						titulo=tTitulo.getText();
					if(!tCalificacion1.getText().isEmpty() && !tCalificacion2.getText().isEmpty()) {
						califMenor = Integer.parseInt(tCalificacion1.getText());
						califMayor = Integer.parseInt(tCalificacion2.getText());
					}
					if(!tTema.getText().isEmpty())
						tema=tTema.getText();
					if(!tFecha1.getText().isEmpty() && !tFecha2.getText().isEmpty()) {
						fechaMenor = (new SimpleDateFormat("dd/MM/yyyy")).parse(tFecha1.getText());
						fechaMayor = (new SimpleDateFormat("dd/MM/yyyy")).parse(tFecha2.getText());
					}

					filtrados = biblioteca.buscar(titulo, califMenor, califMayor, tema, fechaMenor, fechaMayor);
					
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
						JOptionPane.showConfirmDialog(ventana, "No se ha encontrado ningún material que coincida con los criterios de búsqueda.","No se encontró material", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					}else {
						mostrarMaterialesTabla((ArrayList<MaterialCapacitacion>) ordenados.materiales());
					}
				}catch(NumberFormatException nfex) {
					System.out.println("Puso otra cosa en un campo numérico");
					JOptionPane.showConfirmDialog(ventana, "El campo CALIFICACIÓN debe llevar un número entre 1 y 100.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);	
					//nfex.printStackTrace();
				}catch(ParseException pex) {
					System.out.println("La fecha está mal escrita");
					JOptionPane.showConfirmDialog(ventana, "La fecha debe ser escrita con formato dd/MM/aaaa", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			
			
		});
		panel.add(boton,constraints);
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800, 600);
		
        ventana.setVisible(true);
	}

	protected void mostrarMaterialesTabla(ArrayList<MaterialCapacitacion> materiales) {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gridConst = new GridBagConstraints();
		GridBagConstraints constraints = new GridBagConstraints();
		MaterialesTablaModelo tableModel = new MaterialesTablaModelo();
		JScrollPane scrollPane;
		JTable tabla;
		JButton boton;
		
		
//		TITULO VENTANA
		JLabel lblTitulo = new JLabel("Resultado de la búsqueda: ");
		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridwidth = 5;
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
		gridConst.gridwidth=5;	
		gridConst.gridy=2;
		gridConst.weighty=0.5;
		gridConst.weightx=1;
		gridConst.anchor = GridBagConstraints.CENTER;
		gridConst.fill=GridBagConstraints.HORIZONTAL;
		panel.add(scrollPane, gridConst);
		
		
//		BOTONES
		boton = new JButton("Inicio");
		constraints.gridwidth = 1;
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.weightx = 0.25;
		constraints.weighty = 0.25;
		constraints.anchor = GridBagConstraints.CENTER;
		boton.addActionListener( a -> Principal.mostrarInterfaz());
		panel.add(boton,constraints);
		
		boton = new JButton("Atrás");
		constraints.gridwidth = 1;
		constraints.gridx = 4;
		constraints.gridy = 3;
		constraints.weightx = 0.25;
		constraints.weighty = 0.25;
		constraints.anchor = GridBagConstraints.CENTER;
		boton.addActionListener( a -> busqueda());
		panel.add(boton,constraints);
		
		boton = new JButton("Agregar a la lista de deseos");
		constraints.gridwidth = 1;
		constraints.gridx=1;
		constraints.weightx = 0.25;
		constraints.anchor=GridBagConstraints.CENTER;
		boton.addActionListener(a -> {
			if(tabla.getSelectedRow()==-1) {
				JOptionPane.showConfirmDialog(ventana, "Por favor seleccione un material de la tabla.", "Seleccione un material", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}else {
				MaterialCapacitacion material = tableModel.getMateriales().get(tabla.getSelectedRow());
				listaPanel.getController().agregar(material);
				if(JOptionPane.showConfirmDialog(ventana, "Se agregó el "+(material.esLibro()? "Libro": "Video")+": "+material.getTitulo()+" a la lista de deseos. \n ¿Desea ver la lista de deseados?",
						"Agregado a lista de deseados", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE) ==0) {
					listaPanel.mostrarLista();
				}
			}
			
		});
		panel.add(boton, constraints);
		
		boton = new JButton("Agregar relación");
		constraints.gridwidth = 1;
		constraints.gridx=2;
		constraints.weightx = 0.25;
		constraints.anchor=GridBagConstraints.CENTER;
		boton.addActionListener(a -> {
			if(tabla.getSelectedRow()==-1) {
				JOptionPane.showConfirmDialog(ventana, "Por favor seleccione un material de la tabla.", "Seleccione un material",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}else {
				MaterialCapacitacion material = tableModel.getMateriales().get(tabla.getSelectedRow());
				relacionesPanel.armarRelacionesPanel(material);
			}
		});
		panel.add(boton, constraints);
		
		boton = new JButton("Ver Contenido");
		constraints.gridwidth = 1;
		constraints.gridx=3;
		constraints.weightx = 0.25;
		constraints.anchor=GridBagConstraints.CENTER;
		boton.addActionListener(a ->{
			if(tabla.getSelectedRow()==-1) {
				JOptionPane.showConfirmDialog(ventana, "Por favor seleccione un material de la tabla.", "Seleccione un material",  JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}else {
				MaterialCapacitacion material = tableModel.getMateriales().get(tabla.getSelectedRow());
				arbolContenido.mostrarArbolContenido(material);
			}
		});
		panel.add(boton, constraints);
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800, 600);
		
        ventana.setVisible(true);
	}
	

}

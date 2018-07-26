package frsf.isi.died.tp.app.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale.LanguageRange;

import javax.swing.*;

import frsf.isi.died.tp.app.controller.LibroController;
import frsf.isi.died.tp.app.controller.VideoController;
import frsf.isi.died.tp.modelo.productos.Relevancia;
import frsf.isi.died.tp.modelo.productos.Video;

public class ABMVideo {
	
	public static void agregarVideo(JFrame ventana) {
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		JLabel errorID = new JLabel(), errorTitulo = new JLabel(), errorCosto = new JLabel(), errorDuracion = new JLabel(), 
				errorFecha = new JLabel(), encabezado = new JLabel("Agregar Nuevo Video");
		JTextField tID = new JTextField(20), tTitulo = new JTextField(), tCosto = new JTextField(),
				tDuracion = new JTextField(), tFecha = new JTextField();
		JButton aceptar = new JButton("Aceptar"), cancelar = new JButton("Cancelar");
		JComboBox<Relevancia> lRelevancia = new JComboBox<Relevancia>();		
		
		constraints.insets=new Insets(5, 5, 5, 5);
		
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridheight=1;
		constraints.gridwidth=4;
		constraints.anchor=GridBagConstraints.NORTH;
		encabezado.setFont(new Font(encabezado.getFont().getName(), encabezado.getFont().getStyle(), 40));
		panel.add(encabezado,constraints);
		constraints.fill=GridBagConstraints.HORIZONTAL;
		constraints.anchor=GridBagConstraints.CENTER;
		
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridheight=1;
		constraints.gridwidth=2;
		panel.add(new JLabel("ID: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridwidth=1;
		panel.add(tID, constraints);
		
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.gridwidth=2;
		panel.add(new JLabel("Título: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=2;
		constraints.gridwidth=1;
		panel.add(tTitulo, constraints);
		
		constraints.gridx=0;
		constraints.gridy=3;
		constraints.gridwidth=2;
		panel.add(new JLabel("Costo: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=3;
		constraints.gridwidth=1;
		panel.add(tCosto, constraints);
		
		constraints.gridx=0;
		constraints.gridy=4;
		constraints.gridwidth=2;
		panel.add(new JLabel("Duracion: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=4;
		constraints.gridwidth=1;
		panel.add(tDuracion, constraints);
		
		constraints.gridx=0;
		constraints.gridy=5;
		constraints.gridwidth=2;
		panel.add(new JLabel("Fecha de Publicación: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=5;
		constraints.gridwidth=1;
		panel.add(tFecha, constraints);
		
		constraints.gridx=0;
		constraints.gridy=6;
		constraints.gridwidth=2;
		panel.add(new JLabel("Relevancia: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=6;
		constraints.gridwidth=1;
		lRelevancia.addItem(Relevancia.BAJA);
		lRelevancia.addItem(Relevancia.MEDIA);
		lRelevancia.addItem(Relevancia.ALTA);
		lRelevancia.setSelectedItem(Relevancia.MEDIA);
		panel.add(lRelevancia, constraints);
		
		constraints.gridx=0;
		constraints.gridy=7;
		cancelar.addActionListener(a -> Principal.mostrarInterfaz(ventana));
		panel.add(cancelar, constraints);
				
		constraints.gridx=3;
		constraints.gridy=7;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.WEST;
		aceptar.addActionListener(e -> {
			Integer id;
			String titulo;
			Double costo = 0.0;
			Integer duracion = 0;
			Date fechaPublicacion = Calendar.getInstance().getTime();
			Relevancia relev;
			
			errorID.setText("");
			errorTitulo.setText("");
			errorCosto.setText("");
			errorDuracion.setText("");
			errorFecha.setText("");
			try {
				if(tID.getText().isEmpty()){
					System.out.println("El ID no puede ser vacio");
					errorID.setText("Debe ingresar un ID");
					return;
				}else {
					id = Integer.parseInt(tID.getText());
				}
				if(tTitulo.getText().isEmpty()) {
					System.out.println("El título no puede ser vacío");
					errorTitulo.setText("Debe ingresar un título");
					return;
				}else {
					titulo = tTitulo.getText();
				}
				if(tCosto.getText().isEmpty()) {
					errorCosto.setText("Debe ingresar un costo");
					return;
				}else {
					costo = Double.parseDouble(tCosto.getText());
				}
				if(tDuracion.getText().isEmpty()) {
					errorDuracion.setText("Debe ingresar una duracion");
					return;
				}else{
					duracion = Integer.parseInt(tDuracion.getText());
				}
				if(tFecha.getText().isEmpty()) {
					errorFecha.setText("Debe ingresar una fecha de publicación");
					return;
				}else{
					fechaPublicacion = (new SimpleDateFormat("dd/MM/yyyy")).parse(tFecha.getText());
					if((fechaPublicacion.getTime() - Calendar.getInstance().getTime().getTime()) > 0) {
						System.out.println("Puso una fecha futura");
						JOptionPane.showConfirmDialog(ventana, "La fecha ingresada es futura", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				relev = (Relevancia)lRelevancia.getSelectedItem();
				
				if(JOptionPane.showConfirmDialog(ventana, "¿Está seguro que desea guardar el nuevo viedo con los datos ingresados?","Confirmacion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
					VideoController.agregarVideo(id, titulo, costo, duracion, fechaPublicacion, relev);
					tID.setText("");tTitulo.setText("");tCosto.setText("");
					tDuracion.setText("");
					tFecha.setText("");	
					lRelevancia.setSelectedItem(Relevancia.MEDIA);
				}
				
			}catch(NumberFormatException nfex) {
				System.out.println("Puso otra cosa en un campo numérico");
				JOptionPane.showConfirmDialog(ventana, "Los campos ID, Costo, Precio Compra y Páginas deben ser numéricos.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);	
				//nfex.printStackTrace();
			}catch(ParseException pex) {
				System.out.println("La fecha está mal escrita");
				JOptionPane.showConfirmDialog(ventana, "La fecha debe ser escrita con formato dd/mm/aaaa", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
			
		});
		panel.add(aceptar, constraints);
		
		constraints.gridx=3;
		constraints.gridy=1;
		errorID.setPreferredSize(new Dimension(230, 16));
		errorID.setForeground(Color.red);
		panel.add(errorID,constraints);

		constraints.gridy=2;
		errorTitulo.setPreferredSize(new Dimension(230, 16));
		errorTitulo.setForeground(Color.red);
		panel.add(errorTitulo,constraints);
		
		constraints.gridy=3;
		errorCosto.setPreferredSize(new Dimension(230, 16));
		errorCosto.setForeground(Color.red);
		panel.add(errorCosto,constraints);
		
		constraints.gridy=4;
		errorDuracion.setPreferredSize(new Dimension(230, 16));
		errorDuracion.setForeground(Color.red);
		panel.add(errorDuracion,constraints);
		
		constraints.gridy=5;
		errorFecha.setPreferredSize(new Dimension(230, 16));
		errorFecha.setForeground(Color.red);
		panel.add(errorFecha,constraints);
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800, 600);
		
        ventana.setVisible(true);
		
	}
	
	public static void editarVideo(JFrame ventana) {
		
		JPanel panel = new JPanel();
		JLabel encabezado = new JLabel("Editar Video"), errorID = new JLabel();
		JTextField tID = new JTextField(20);
		JButton buscar = new JButton("Buscar"), cancelar = new JButton("Cancelar");
		GridBagConstraints constraints = new GridBagConstraints();
		
		panel.setLayout(new GridBagLayout());
		constraints.insets=new Insets(5, 5, 5, 5);
		
		constraints.anchor=GridBagConstraints.NORTH;
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridheight=1;
		constraints.gridwidth=3;
		encabezado.setFont(new Font(encabezado.getFont().getFontName(), encabezado.getFont().getStyle(), 40));
		panel.add(encabezado, constraints);
		
		constraints.anchor=GridBagConstraints.CENTER;
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridheight=1;
		constraints.gridwidth=2;
		panel.add(new JLabel("Ingrese el ID de video a editar: "), constraints);
		
		constraints.gridwidth=1;
		constraints.gridx=2;
		constraints.gridy=1;
		panel.add(tID,constraints);
		
		constraints.gridx=0;
		constraints.gridy=2;
		cancelar.addActionListener(a -> Principal.mostrarInterfaz(ventana));
		panel.add(cancelar,constraints);
		
		constraints.gridx=3;
		buscar.addActionListener(a -> {
			try {
				if(tID.getText().isEmpty()) {
					errorID.setText("Debe ingresar un ID");
				}else {
					//buscar video con id Integer.parseInt(tID.getText());
					//creo un nuevo libro para simular la busqueda
					Video nuevo = VideoController.buscarVideo(Integer.parseInt(tID.getText()));
					System.out.println("Video a editar: "+nuevo);
					edicionVideo(nuevo, ventana);
				}
			}catch(Exception e) {
				
			}
		});
		panel.add(buscar,constraints);
		
		constraints.gridx=3;
		constraints.gridy=1;
		errorID.setPreferredSize(new Dimension(230, 16));
		errorID.setForeground(Color.red);
		panel.add(errorID,constraints);
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800, 600);
		ventana.setVisible(true);
	}

	private static void edicionVideo(Video video, JFrame ventana) {
		JPanel panel = new JPanel();
		JLabel encabezado = new JLabel("Editar video"), errorID = new JLabel(), errorTitulo = new JLabel(),
				errorCosto = new JLabel(), errorDuracion = new JLabel(), errorFecha = new JLabel();
		JTextField tID = new JTextField(20), tTitulo = new JTextField(20), tCosto = new JTextField(20),
				tDuracion = new JTextField(20), tFecha = new JTextField(20);
		JButton aceptar = new JButton("Aceptar"), cancelar = new JButton("Cancelar");
		GridBagConstraints constraints = new GridBagConstraints();
		JComboBox<Relevancia> lRelevancia = new JComboBox<Relevancia>();
		
		panel.setLayout(new GridBagLayout());
		constraints.insets=new Insets(5, 5, 5, 5);
		
		constraints.anchor=GridBagConstraints.NORTH;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridheight=1;
		constraints.gridwidth=3;
		encabezado.setFont(new Font(encabezado.getFont().getFontName(), encabezado.getFont().getStyle(), 40));
		panel.add(encabezado, constraints);
		
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridheight=1;
		constraints.gridwidth=2;
		panel.add(new JLabel("ID: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=1;
		constraints.gridwidth=1;
		constraints.gridwidth=1;
		tID.setEditable(false);
		panel.add(tID, constraints);
		
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.gridwidth=2;
		panel.add(new JLabel("Título: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=2;
		constraints.gridwidth=1;
		panel.add(tTitulo, constraints);
		
		constraints.gridx=0;
		constraints.gridy=3;
		constraints.gridwidth=2;
		panel.add(new JLabel("Costo: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=3;
		constraints.gridwidth=1;
		panel.add(tCosto, constraints);
		
		constraints.gridx=0;
		constraints.gridy=4;
		constraints.gridwidth=2;
		panel.add(new JLabel("Duración: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=4;
		constraints.gridwidth=1;
		panel.add(tDuracion, constraints);
		
		constraints.gridx=0;
		constraints.gridy=5;
		constraints.gridwidth=2;
		panel.add(new JLabel("Fecha de Publicación: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=5;
		constraints.gridwidth=1;
		panel.add(tFecha, constraints);
		
		constraints.gridx=0;
		constraints.gridy=6;
		constraints.gridwidth=2;
		panel.add(new JLabel("Relevancia: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=6;
		constraints.gridwidth=1;
		lRelevancia.addItem(Relevancia.BAJA);
		lRelevancia.addItem(Relevancia.MEDIA);
		lRelevancia.addItem(Relevancia.ALTA);
		panel.add(lRelevancia, constraints);
		
		constraints.gridx=0;
		constraints.gridy=7;
		cancelar.addActionListener(a -> Principal.mostrarInterfaz(ventana));
		panel.add(cancelar, constraints);
		
		constraints.gridx=3;
		constraints.gridy=7;
		constraints.fill=GridBagConstraints.NONE;
		aceptar.addActionListener(a -> {
			System.out.println("Pide confrimacion y guarda los cambios.");

			Integer id;
			String titulo;
			Double costo = 0.0;
			Integer duracion = 0;
			Date fechaPublicacion = Calendar.getInstance().getTime();
			Relevancia relevancia;
			
			errorID.setText("");
			errorTitulo.setText("");
			errorCosto.setText("");
			errorDuracion.setText("");
			errorFecha.setText("");
			try {
				if(tID.getText().isEmpty()){
					System.out.println("El ID no puede ser vacio");
					errorID.setText("Debe ingresar un ID");
					return;
				}else {
					id = Integer.parseInt(tID.getText());
				}
				if(tTitulo.getText().isEmpty()) {
					System.out.println("El título no puede ser vacío");
					errorTitulo.setText("Debe ingresar un título");
					return;
				}else {
					titulo = tTitulo.getText();
				}
				if(tCosto.getText().isEmpty()) {
					errorCosto.setText("Debe ingresar un costo");
					return;
				}else {
					costo = Double.parseDouble(tCosto.getText());
				}
				if(tDuracion.getText().isEmpty()) {
					errorDuracion.setText("Debe ingresar una cantidad de páginas");
					return;
				}else{
					duracion = Integer.parseInt(tDuracion.getText());
				}
				if(tFecha.getText().isEmpty()) {
					errorFecha.setText("Debe ingresar una fecha de publicación");
					return;
				}else{
					fechaPublicacion = (new SimpleDateFormat("dd/MM/yyyy")).parse(tFecha.getText());
					if((fechaPublicacion.getTime() - Calendar.getInstance().getTime().getTime()) > 0) {
						System.out.println("Puso una fecha futura");
						JOptionPane.showConfirmDialog(ventana, "La fecha ingresada es futura", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				
				relevancia = (Relevancia)lRelevancia.getSelectedItem();
			
				if(JOptionPane.showConfirmDialog(ventana, "¿Está seguro que desea modificar el libro con los datos ingresados?", "Confirmar edición", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
					VideoController.editarVideo(video, titulo, costo, duracion, fechaPublicacion, relevancia);
					editarVideo(ventana);
				}
				
			}catch(NumberFormatException nfex) {
				System.out.println("Puso otra cosa en un campo numérico");
				JOptionPane.showConfirmDialog(ventana, "Los campos ID, Costo, Precio Compra y Páginas deben ser numéricos.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);	
				//nfex.printStackTrace();
			}catch(ParseException pex) {
				System.out.println("La fecha está mal escrita");
				JOptionPane.showConfirmDialog(ventana, "La fecha debe ser escrita con formato dd/mm/aaaa", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		});
		panel.add(aceptar,constraints);
		
		constraints.gridx=3;
		constraints.gridy=1;
		errorID.setPreferredSize(new Dimension(230, 16));
		errorID.setForeground(Color.red);
		panel.add(errorID,constraints);

		constraints.gridy=2;
		errorTitulo.setPreferredSize(new Dimension(230, 16));
		errorTitulo.setForeground(Color.red);
		panel.add(errorTitulo,constraints);
		
		constraints.gridy=3;
		errorCosto.setPreferredSize(new Dimension(230, 16));
		errorCosto.setForeground(Color.red);
		panel.add(errorCosto,constraints);
		
		constraints.gridy=4;
		errorDuracion.setPreferredSize(new Dimension(230, 16));
		errorDuracion.setForeground(Color.red);
		panel.add(errorDuracion,constraints);
		
		
		constraints.gridy=5;
		errorFecha.setPreferredSize(new Dimension(230, 16));
		errorFecha.setForeground(Color.red);
		panel.add(errorFecha,constraints);
		
		//muestro en los campos de texto los datos anteriores
		tID.setText(video.getId().toString());
		tTitulo.setText(video.getTitulo());
		tCosto.setText(video.getCosto().toString());
		tDuracion.setText(video.getDuracion().toString());
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		tFecha.setText(formato.format(video.getFechaPublicacion()));
		lRelevancia.setSelectedItem(video.getRelevancia());
		
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800,600);
		ventana.setVisible(true);
	}

	public static void eliminarVideo(JFrame ventana) {
		
		JPanel panel = new JPanel();
		JLabel encabezado = new JLabel("ELiminar Video"), errorID = new JLabel();
		JTextField tID = new JTextField(20);
		JButton buscar = new JButton("Buscar"), cancelar = new JButton("Cancelar");
		GridBagConstraints constraints = new GridBagConstraints();
		
		panel.setLayout(new GridBagLayout());
		constraints.insets=new Insets(5, 5, 5, 5);
		
		constraints.anchor=GridBagConstraints.NORTH;
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridheight=1;
		constraints.gridwidth=3;
		encabezado.setFont(new Font(encabezado.getFont().getFontName(), encabezado.getFont().getStyle(), 40));
		panel.add(encabezado, constraints);
		
		constraints.anchor=GridBagConstraints.CENTER;
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridheight=1;
		constraints.gridwidth=2;
		panel.add(new JLabel("Ingrese el ID del video a eliminar: "), constraints);
		
		constraints.gridwidth=1;
		constraints.gridx=2;
		constraints.gridy=1;
		panel.add(tID,constraints);
		
		constraints.gridx=0;
		constraints.gridy=2;
		cancelar.addActionListener(a -> Principal.mostrarInterfaz(ventana));
		panel.add(cancelar,constraints);
		
		constraints.gridx=3;
		buscar.addActionListener(a -> {
			try {
				if(tID.getText().isEmpty()) {
					errorID.setText("Debe ingresar un ID");
				}else {
					//buscar video con id Integer.parseInt(tID.getText());
					//creo un nuevo video para simular la busqueda
					Video encontrado = VideoController.buscarVideo(Integer.parseInt(tID.getText()));
					System.out.println("Video a eliminar: "+encontrado);
					eliminacionVideo(encontrado,ventana);
				}
			}catch(Exception e) {
				
			}
		});
		panel.add(buscar,constraints);
		
		constraints.gridx=3;
		constraints.gridy=1;
		errorID.setPreferredSize(new Dimension(230, 16));
		errorID.setForeground(Color.red);
		panel.add(errorID,constraints);
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800, 600);
		ventana.setVisible(true);
		
	}

	private static void eliminacionVideo(Video video, JFrame ventana) {
		
		JPanel panel = new JPanel();
		JLabel encabezado = new JLabel("Eliminar Libro"), errorID = new JLabel(), errorTitulo = new JLabel(),
				errorCosto = new JLabel(), errorPrecio = new JLabel(), errorPaginas = new JLabel(), 
				errorFecha = new JLabel();
		JTextField tID = new JTextField(20), tTitulo = new JTextField(20), tCosto = new JTextField(20),
					tPrecio = new JTextField(20), tPaginas = new JTextField(20), tFecha = new JTextField(20),
					lRelevancia = new JTextField(20);
		JButton eliminar = new JButton("Eliminar"), cancelar = new JButton("Cancelar");
		GridBagConstraints constraints = new GridBagConstraints();
		
		
		panel.setLayout(new GridBagLayout());
		constraints.insets=new Insets(5, 5, 5, 5);
		
		constraints.anchor=GridBagConstraints.NORTH;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridheight=1;
		constraints.gridwidth=3;
		encabezado.setFont(new Font(encabezado.getFont().getFontName(), encabezado.getFont().getStyle(), 40));
		panel.add(encabezado, constraints);
		
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridheight=1;
		constraints.gridwidth=2;
		panel.add(new JLabel("Video seleccionado: "),constraints);
		
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.gridheight=1;
		constraints.gridwidth=2;
		panel.add(new JLabel("ID: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.gridwidth=1;
		tID.setText(video.getId().toString());
		tID.setEditable(false);
		panel.add(tID, constraints);
		
		constraints.gridx=0;
		constraints.gridy=3;
		constraints.gridwidth=2;
		panel.add(new JLabel("Título: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=3;
		constraints.gridwidth=1;
		tTitulo.setText(video.getTitulo());
		tTitulo.setEditable(false);
		panel.add(tTitulo, constraints);
		
		constraints.gridx=0;
		constraints.gridy=4;
		constraints.gridwidth=2;
		panel.add(new JLabel("Costo: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=4;
		constraints.gridwidth=1;
		tCosto.setText(video.getCosto().toString());
		tCosto.setEditable(false);
		panel.add(tCosto, constraints);
		
			
		constraints.gridx=0;
		constraints.gridy=5;
		constraints.gridwidth=2;
		panel.add(new JLabel("Duracion: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=5;
		constraints.gridwidth=1;
		tPaginas.setText(video.getDuracion().toString());
		tPaginas.setEditable(false);
		panel.add(tPaginas, constraints);
		
		constraints.gridx=0;
		constraints.gridy=6;
		constraints.gridwidth=2;
		panel.add(new JLabel("Fecha de Publicación: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=6;
		constraints.gridwidth=1;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		tFecha.setText(formato.format(video.getFechaPublicacion()));
		tFecha.setEditable(false);
		panel.add(tFecha, constraints);
		
		constraints.gridx=0;
		constraints.gridy=7;
		constraints.gridwidth=2;
		panel.add(new JLabel("Relevancia: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=7;
		constraints.gridwidth=1;
		lRelevancia.setText(video.getRelevancia().toString());
		lRelevancia.setEditable(false);
		panel.add(lRelevancia, constraints);
		
		constraints.gridx=0;
		constraints.gridy=9;
		cancelar.addActionListener(a -> ABMVideo.eliminarVideo(ventana));
		panel.add(cancelar, constraints);
		
		constraints.gridx=3;
		constraints.gridy=9;
		constraints.fill=GridBagConstraints.NONE;
		eliminar.addActionListener(a -> {
			System.out.println("Pide confrimacion y elimina el libro.");
			
			if(JOptionPane.showConfirmDialog(ventana, "¿Está seguro que desea eliminar el libro con los datos ingresados?", "Confirmar edición", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
				VideoController.eliminarVideo(video);
				eliminarVideo(ventana);
			}				
				
		});
		panel.add(eliminar,constraints);
		
		constraints.gridx=3;
		constraints.gridy=1;
		errorID.setPreferredSize(new Dimension(230, 16));
		errorID.setForeground(Color.red);
		panel.add(errorID,constraints);

		constraints.gridy=2;
		errorTitulo.setPreferredSize(new Dimension(230, 16));
		errorTitulo.setForeground(Color.red);
		panel.add(errorTitulo,constraints);
		
		constraints.gridy=3;
		errorCosto.setPreferredSize(new Dimension(230, 16));
		errorCosto.setForeground(Color.red);
		panel.add(errorCosto,constraints);
		
		constraints.gridy=4;
		errorPrecio.setPreferredSize(new Dimension(230, 16));
		errorPrecio.setForeground(Color.red);
		panel.add(errorPrecio,constraints);
		
		constraints.gridy=5;
		errorPaginas.setPreferredSize(new Dimension(230, 16));
		errorPaginas.setForeground(Color.red);
		panel.add(errorPaginas,constraints);
		
		constraints.gridy=6;
		errorFecha.setPreferredSize(new Dimension(230, 16));
		errorFecha.setForeground(Color.red);
		panel.add(errorFecha,constraints);
		
				
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800,600);
		ventana.setVisible(true);
	}

	
}

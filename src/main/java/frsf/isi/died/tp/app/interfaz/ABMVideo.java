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

import javax.swing.*;

import frsf.isi.died.tp.app.controller.LibroController;
import frsf.isi.died.tp.app.controller.VideoController;
import frsf.isi.died.tp.modelo.productos.Libro;
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
						Principal.mostrarDialogo("La fecha ingresada es futura", ventana);
						return;
					}
				}
				
				VideoController.agregarVideo(id, titulo, costo, duracion, fechaPublicacion);
				
			}catch(NumberFormatException nfex) {
				System.out.println("Puso otra cosa en un campo numérico");
				Principal.mostrarDialogo("Los Campos ID, Costo y Duracion solo permiten números", ventana);
				//nfex.printStackTrace();
			}catch(ParseException pex) {
				System.out.println("La fecha está mal escrita");
				Principal.mostrarDialogo("La fecha debe ser escrita con formato dd/mm/aaaa", ventana);
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
		cancelar.addActionListener(a -> {ventana.dispose(); Principal.main(null);});
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
				errorCosto = new JLabel(), errorSegundos = new JLabel(), errorFecha = new JLabel();
		JTextField tID = new JTextField(20), tTitulo = new JTextField(20), tCosto = new JTextField(20),
				tSegundos = new JTextField(20), tFecha = new JTextField(20);
		JButton aceptar = new JButton("Aceptar"), cancelar = new JButton("Cancelar");
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
		panel.add(new JLabel("Segundos: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=4;
		constraints.gridwidth=1;
		panel.add(tSegundos, constraints);
		
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
		cancelar.addActionListener(a -> {
			ventana.dispose();
			Principal.main(null);
		});
		panel.add(cancelar, constraints);
		
		constraints.gridx=3;
		constraints.gridy=6;
		constraints.fill=GridBagConstraints.NONE;
		aceptar.addActionListener(a -> {
			System.out.println("Pide confrimacion y guarda los cambios.");
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
		errorSegundos.setPreferredSize(new Dimension(230, 16));
		errorSegundos.setForeground(Color.red);
		panel.add(errorSegundos,constraints);
		
		
		constraints.gridy=5;
		errorFecha.setPreferredSize(new Dimension(230, 16));
		errorFecha.setForeground(Color.red);
		panel.add(errorFecha,constraints);
		
		//muestro en los campos de texto los datos anteriores
		tID.setText(video.getId().toString());
		tTitulo.setText(video.getTitulo());
		tCosto.setText(video.getCosto().toString());
		tSegundos.setText(video.getDuracion().toString());
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		tFecha.setText(formato.format(video.getFechaPublicacion()));
		
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800,600);
		ventana.setVisible(true);
	}
	
}

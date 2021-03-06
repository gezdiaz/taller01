package frsf.isi.died.tp.app.interfaz;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import frsf.isi.died.tp.app.controller.LibroController;
import frsf.isi.died.tp.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.tp.app.interfaz.tabla.LibroTablaModelo;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.Relevancia;

public class ABMLibro {
	
	private LibroController controller;
	private JFrame ventana;
	
	public ABMLibro(MaterialCapacitacionDao dao, JFrame ventana){
		this.controller = new LibroController(dao);
		this.ventana = ventana;
	}
	
	
	public void agregarLibro() {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		JLabel errorTitulo = new JLabel(), errorCosto = new JLabel(),
				errorPrecio = new JLabel(), errorPaginas = new JLabel(), 
				errorFecha = new JLabel(), encabezado = new JLabel("Agregar Nuevo Libro");
		JTextField tTitulo = new JTextField(20), tCosto = new JTextField(20),
					tPrecioCompra = new JTextField(20), tPaginas = new JTextField(20),
					tFecha = new JTextField(20), tTema = new JTextField(20);
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
		constraints.gridwidth=2;
		panel.add(new JLabel("T�tulo: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=1;
		constraints.gridwidth=1;
		panel.add(tTitulo, constraints);
		
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.gridwidth=2;
		panel.add(new JLabel("Costo: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=2;
		constraints.gridwidth=1;
		panel.add(tCosto, constraints);
		
		constraints.gridx=0;
		constraints.gridy=3;
		constraints.gridwidth=2;
		panel.add(new JLabel("Precio de Compra: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=3;
		constraints.gridwidth=1;
		panel.add(tPrecioCompra, constraints);
		
		constraints.gridx=0;
		constraints.gridy=4;
		constraints.gridwidth=2;
		panel.add(new JLabel("N� P�ginas: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=4;
		constraints.gridwidth=1;
		panel.add(tPaginas, constraints);
		
		constraints.gridx=0;
		constraints.gridy=5;
		constraints.gridwidth=2;
		panel.add(new JLabel("Fecha de Publicaci�n: "), constraints);
		
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
		constraints.gridwidth=2;
		panel.add(new JLabel("Tema: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=7;
		constraints.gridwidth=1;
		tTema.setText("Otros");
		panel.add(tTema, constraints);
		
		constraints.gridx=0;
		constraints.gridy=8;
		cancelar.addActionListener(a -> Principal.mostrarInterfaz());
		panel.add(cancelar, constraints);
				
		constraints.gridx=3;
		constraints.gridy=8;
		constraints.fill=GridBagConstraints.NONE;
		constraints.anchor=GridBagConstraints.WEST;
		aceptar.addActionListener(e -> {
			String titulo, tema;
			Double costo = null, precioCompra = null;
			Integer paginas = null;
			Date fechaPublicacion = Calendar.getInstance().getTime();
			Relevancia relevancia;
			
			errorTitulo.setText("");
			errorCosto.setText("");
			errorPrecio.setText("");
			errorPaginas.setText("");
			errorFecha.setText("");
			try {
				if(tTitulo.getText().isEmpty()) {
					errorTitulo.setText("Debe ingresar un t�tulo");
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
				if(tPrecioCompra.getText().isEmpty()) {
					errorPrecio.setText("Debe ingresar un precio de compra");
					return;
				}else{
					precioCompra = Double.parseDouble(tPrecioCompra.getText());
				}
				if(tPaginas.getText().isEmpty()) {
					errorPaginas.setText("Debe ingresar un n�mero de p�ginas");
					return;
				}else{
					paginas = Integer.parseInt(tPaginas.getText());
				}
				if(tFecha.getText().isEmpty()) {
					errorFecha.setText("Debe ingresar una fecha de publicaci�n");
					return;
				}else{
					fechaPublicacion = (new SimpleDateFormat("dd/MM/yyyy")).parse(tFecha.getText());
					if((fechaPublicacion.getTime() - Calendar.getInstance().getTime().getTime()) > 0) {
						JOptionPane.showConfirmDialog(ventana, "La fecha ingresada es futura", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if(tTema.getText().isEmpty()) {
					tema = "Otros";
				}else {
					tema = tTema.getText();
				}
				
				relevancia = (Relevancia)lRelevancia.getSelectedItem();
				
				if(JOptionPane.showConfirmDialog(ventana, "�Est� seguro que desea guardar el nuevo libro con los datos ingresados?","Confirmacion",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE)==0) {
					controller.agregarLibro(0, titulo, costo, precioCompra, paginas, fechaPublicacion, relevancia, tema);
//					Antes quedaba en la misma pantalla y borraba todos los campos de texto
//					tID.setText("");tTitulo.setText("");tCosto.setText("");
//					tPrecioCompra.setText("");tPaginas.setText("");
//					tFecha.setText("");		
//					lRelevancia.setSelectedItem(Relevancia.MEDIA);
//					Ahora muestra una tabla con todos los libros
					mostrarTabla();
				}
				
								
				
			}catch(NumberFormatException nfex) {
				if(costo == null) {
					JOptionPane.showConfirmDialog(ventana, "El campo Costo debe ser num�rico.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);	
				}else {
					if(precioCompra == null) {
						JOptionPane.showConfirmDialog(ventana, "El campo Precio de Compra debe ser num�rico.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);	
					}else {
						if(paginas == null) {
							JOptionPane.showConfirmDialog(ventana, "El campo N� P�ginas debe ser un n�mero entero.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);	
						}
					}
				}
			}catch(ParseException pex) {
				JOptionPane.showConfirmDialog(ventana, "La fecha debe ser escrita con formato dd/mm/aaaa", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
			
		});
		panel.add(aceptar, constraints);
		
		constraints.gridx=3;
		constraints.gridy=1;
		errorTitulo.setPreferredSize(new Dimension(230, 16));
		errorTitulo.setForeground(Color.red);
		panel.add(errorTitulo,constraints);
		
		constraints.gridy=2;
		errorCosto.setPreferredSize(new Dimension(230, 16));
		errorCosto.setForeground(Color.red);
		panel.add(errorCosto,constraints);
		
		constraints.gridy=3;
		errorPrecio.setPreferredSize(new Dimension(230, 16));
		errorPrecio.setForeground(Color.red);
		panel.add(errorPrecio,constraints);
		
		constraints.gridy=4;
		errorPaginas.setPreferredSize(new Dimension(230, 16));
		errorPaginas.setForeground(Color.red);
		panel.add(errorPaginas,constraints);
		
		constraints.gridy=5;
		errorFecha.setPreferredSize(new Dimension(230, 16));
		errorFecha.setForeground(Color.red);
		panel.add(errorFecha,constraints);
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800, 600);
		
        ventana.setVisible(true);
	}

	private void mostrarTabla() {
		JPanel panel = new JPanel(new GridBagLayout());
		LibroTablaModelo tableModel = new LibroTablaModelo();
		JTable tabla = new JTable(tableModel);
		ArrayList<Libro> libros = new ArrayList<Libro>();
		GridBagConstraints constraints = new GridBagConstraints();
		JButton volver = new JButton("Volver al inicio"), agregar = new JButton("Agregar otro");
		JScrollPane scroll = new JScrollPane(tabla);

//		libros = DAO.getTodosLibros();
		
//		try {
//			libros.add(new Libro(1, "Java", 5.4, 4.2, 100, (new SimpleDateFormat("dd/MM/yyyy")).parse("06/03/2009"), Relevancia.ALTA));
//			libros.add(new Libro(2, "Python", 10.4, 5.4, 200, (new SimpleDateFormat("dd/MM/yyyy")).parse("08/08/2008"), Relevancia.ALTA));
//			libros.add(new Libro(3, "C++", 45.5, 40.6, 500, (new SimpleDateFormat("dd/MM/yyyy")).parse("09/05/1997"), Relevancia.ALTA));
//			libros.add(new Libro(4, "Cobol", 5.0, 4.0, 50, (new SimpleDateFormat("dd/MM/yyyy")).parse("07/08/1977"), Relevancia.ALTA));
//			libros.add(new Libro(5, "Rubi", 140.89, 130.8, 1000, (new SimpleDateFormat("dd/MM/yyyy")).parse("15/10/2013"), Relevancia.ALTA));
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
		libros.addAll(controller.listaLibros());
				
		tableModel.setLibros(libros);
		
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.fill=GridBagConstraints.BOTH;
		constraints.gridheight=1;
		constraints.gridwidth=5;
		constraints.weightx=1;
		tabla.setFillsViewportHeight(true);
		panel.add(scroll, constraints);
		
		constraints.gridheight=1;
		constraints.gridwidth=1;
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		panel.add(new JLabel(""),constraints);
		
		constraints.gridx=1;
		constraints.gridy=1;
		constraints.fill=GridBagConstraints.NONE;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		constraints.weightx=0;
		constraints.anchor=GridBagConstraints.CENTER;
		volver.addActionListener(a -> Principal.mostrarInterfaz());
		panel.add(volver,constraints);
		
		constraints.gridheight=1;
		constraints.gridwidth=1;
		constraints.gridx=2;
		constraints.gridy=1;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		panel.add(new JLabel(""),constraints);
		
		constraints.gridx=3;
		constraints.anchor=GridBagConstraints.CENTER;
		constraints.gridy=1;
		constraints.fill=GridBagConstraints.NONE;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		constraints.weightx=0;
		constraints.anchor=GridBagConstraints.CENTER;
		agregar.addActionListener(a -> agregarLibro());
		panel.add(agregar,constraints);
		
		constraints.gridheight=1;
		constraints.gridwidth=1;
		constraints.gridx=4;
		constraints.gridy=1;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		constraints.weightx=0.5;
		panel.add(new JLabel(""),constraints);
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800,600);
		ventana.setVisible(true);
		
	}
	
	public void editarLibro() {
		
		JPanel panel = new JPanel();
		JLabel encabezado = new JLabel("Editar Libro"), errorID = new JLabel();
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
		panel.add(new JLabel("Ingrese el ID de libro a editar: "), constraints);
		
		constraints.gridwidth=1;
		constraints.gridx=2;
		constraints.gridy=1;
		panel.add(tID,constraints);
		
		constraints.gridx=0;
		constraints.gridy=2;
		cancelar.addActionListener(a -> Principal.mostrarInterfaz());
		panel.add(cancelar,constraints);
		
		constraints.gridx=3;
		buscar.addActionListener(a -> {
			try {
				if(tID.getText().isEmpty()) {
					errorID.setText("Debe ingresar un ID");
				}else {
					//buscar libro con id Integer.parseInt(tID.getText());
					//creo un nuevo libro para simular la busqueda
					Libro encontrado = controller.buscarLibro(Integer.parseInt(tID.getText()));
					if(encontrado == null) {
						JOptionPane.showConfirmDialog(ventana, "No se encontr� un libro con el id ingresado", "No existe Libro", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);	
					}else {
						edicionLibro(encontrado);
					}
					
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
	
	private void edicionLibro(Libro libro) {
		JPanel panel = new JPanel();
		JLabel encabezado = new JLabel("Editar Libro"), errorTitulo = new JLabel(),
				errorCosto = new JLabel(), errorPrecio = new JLabel(), errorPaginas = new JLabel(), 
				errorFecha = new JLabel();
		JTextField tID = new JTextField(20), tTitulo = new JTextField(20), tCosto = new JTextField(20),
				tPrecio = new JTextField(20), tPaginas = new JTextField(20),
				tFecha = new JTextField(20), tTema = new JTextField(20);
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
		tID.setText(libro.getId().toString());
		tID.setEditable(false);
		panel.add(tID, constraints);
		
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.gridwidth=2;
		panel.add(new JLabel("T�tulo: "), constraints);
		
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
		panel.add(new JLabel("Precio de Compra: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=4;
		constraints.gridwidth=1;
		panel.add(tPrecio, constraints);
		
		constraints.gridx=0;
		constraints.gridy=5;
		constraints.gridwidth=2;
		panel.add(new JLabel("N� P�ginas: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=5;
		constraints.gridwidth=1;
		panel.add(tPaginas, constraints);
		
		constraints.gridx=0;
		constraints.gridy=6;
		constraints.gridwidth=2;
		panel.add(new JLabel("Calificaci�n: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=6;
		constraints.gridwidth=1;
		JSlider sldrCalificacion = new JSlider(JSlider.HORIZONTAL,0,100,50);
		sldrCalificacion.setFont(new Font("Serif", Font.ITALIC, 15));
		sldrCalificacion.setMajorTickSpacing(20);
		sldrCalificacion.setMinorTickSpacing(10);
		sldrCalificacion.setPaintTicks(true);
		sldrCalificacion.setPaintLabels(true);
		panel.add(sldrCalificacion,constraints);
		
		constraints.gridx=0;
		constraints.gridy=7;
		constraints.gridwidth=2;
		panel.add(new JLabel("Fecha de Publicaci�n: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=7;
		constraints.gridwidth=1;
		panel.add(tFecha, constraints);
		
		constraints.gridx=0;
		constraints.gridy=8;
		constraints.gridwidth=2;
		panel.add(new JLabel("Relevancia: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=8;
		constraints.gridwidth=1;
		lRelevancia.addItem(Relevancia.BAJA);
		lRelevancia.addItem(Relevancia.MEDIA);
		lRelevancia.addItem(Relevancia.ALTA);
		panel.add(lRelevancia, constraints);
		
		constraints.gridx=0;
		constraints.gridy=9;
		constraints.gridwidth=2;
		panel.add(new JLabel("Tema: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=9;
		constraints.gridwidth=1;
		tTema.setText("Otros");
		panel.add(tTema, constraints);
		
		constraints.gridx=0;
		constraints.gridy=10;
		cancelar.addActionListener(a -> Principal.mostrarInterfaz());
		panel.add(cancelar, constraints);
		
		constraints.gridx=3;
		constraints.gridy=10;
		constraints.fill=GridBagConstraints.NONE;
		aceptar.addActionListener(a -> {			
			String titulo, tema;
			Double costo = null, precioCompra = null;
			Integer paginas = null;
			Date fechaPublicacion = Calendar.getInstance().getTime();
			Relevancia relevancia;
			
			errorTitulo.setText("");
			errorCosto.setText("");
			errorPrecio.setText("");
			errorPaginas.setText("");
			errorFecha.setText("");
			try {
				if(tTitulo.getText().isEmpty()) {
					errorTitulo.setText("Debe ingresar un t�tulo");
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
				if(tPrecio.getText().isEmpty()) {
					errorPrecio.setText("Debe ingresar un precio de compra");
					return;
				}else{
					precioCompra = Double.parseDouble(tPrecio.getText());
				}
				if(tPaginas.getText().isEmpty()) {
					errorPaginas.setText("Debe ingresar un n�mero de p�ginas");
					return;
				}else{
					paginas = Integer.parseInt(tPaginas.getText());
				}
				if(tFecha.getText().isEmpty()) {
					errorFecha.setText("Debe ingresar una fecha de publicaci�n");
					return;
				}else{
					fechaPublicacion = (new SimpleDateFormat("dd/MM/yyyy")).parse(tFecha.getText());
					if((fechaPublicacion.getTime() - Calendar.getInstance().getTime().getTime()) > 0) {
						JOptionPane.showConfirmDialog(ventana, "La fecha ingresada es futura", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				if(tTema.getText().isEmpty()) {
					tema = "Otros";
				}else {
					tema = tTema.getText();
				}
				
				relevancia = (Relevancia)lRelevancia.getSelectedItem();
			
				if(JOptionPane.showConfirmDialog(ventana, "�Est� seguro que desea modificar el libro con los datos ingresados?", "Confirmar edici�n", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
					controller.editarLibro(libro, titulo, costo, precioCompra, paginas, fechaPublicacion, relevancia, tema, sldrCalificacion.getValue());
					editarLibro();
				}
				
			}catch(NumberFormatException nfex) {
				if(costo == null) {
					JOptionPane.showConfirmDialog(ventana, "El campo Costo debe ser num�rico.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);	
				}else {
					if(precioCompra == null) {
						JOptionPane.showConfirmDialog(ventana, "El campo Precio de Compra debe ser num�rico.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);	
					}else {
						if(paginas == null) {
							JOptionPane.showConfirmDialog(ventana, "El campo N� P�ginas debe ser un n�mero entero.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);	
						}
					}
				}
			}catch(ParseException pex) {
				JOptionPane.showConfirmDialog(ventana, "La fecha debe ser escrita con formato dd/mm/aaaa", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
				
				
		});
		panel.add(aceptar,constraints);
		
		constraints.gridx=3;
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
		
		constraints.gridy=7;
		errorFecha.setPreferredSize(new Dimension(230, 16));
		errorFecha.setForeground(Color.red);
		panel.add(errorFecha,constraints);
		
		//muestro en los campos de texto los datos anteriores
		tID.setText(libro.getId().toString());
		tTitulo.setText(libro.getTitulo());
		tCosto.setText(libro.getCosto().toString());
		tPrecio.setText(libro.getPrecioCompra().toString());
		tPaginas.setText(libro.getPaginas().toString());
		sldrCalificacion.setValue(libro.getCalificacion());
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		tFecha.setText(formato.format(libro.getFechaPublicacion()));
		lRelevancia.setSelectedItem(libro.getRelevancia());
		tTema.setText(libro.getTema());
		
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800,600);
		ventana.setVisible(true);
	}

	public void eliminarLibro() {
		
		JPanel panel = new JPanel();
		JLabel encabezado = new JLabel("Eliminar Libro"), errorID = new JLabel();
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
		panel.add(new JLabel("Ingrese el ID de libro a eliminar: "), constraints);
		
		constraints.gridwidth=1;
		constraints.gridx=2;
		constraints.gridy=1;
		panel.add(tID,constraints);
		
		constraints.gridx=0;
		constraints.gridy=2;
		cancelar.addActionListener(a -> Principal.mostrarInterfaz());
		panel.add(cancelar,constraints);
		
		constraints.gridx=3;
		buscar.addActionListener(a -> {
			try {
				if(tID.getText().isEmpty()) {
					errorID.setText("Debe ingresar un ID");
				}else {
					//buscar libro con id Integer.parseInt(tID.getText());
					//creo un nuevo libro para simular la busqueda
					Libro encontrado = controller.buscarLibro(Integer.parseInt(tID.getText()));
					if(encontrado == null) {
						JOptionPane.showConfirmDialog(ventana, "No se encontr� un libro con el id ingresado", "No existe Libro", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);	
					}else {
						eliminacionLibro(encontrado);
					}
					
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

	private void eliminacionLibro(Libro libro) {
		
		JPanel panel = new JPanel();
		JLabel encabezado = new JLabel("Eliminar Libro"), errorID = new JLabel(), errorTitulo = new JLabel(),
				errorCosto = new JLabel(), errorPrecio = new JLabel(), errorPaginas = new JLabel(), 
				errorFecha = new JLabel();
		JTextField tID = new JTextField(20), tTitulo = new JTextField(20), tCosto = new JTextField(20),
					tPrecio = new JTextField(20), tPaginas = new JTextField(20), tFecha = new JTextField(20),
					lRelevancia = new JTextField(20), tTema = new JTextField(20);
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
		panel.add(new JLabel("Libro seleccionado: "),constraints);
		
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.gridheight=1;
		constraints.gridwidth=2;
		panel.add(new JLabel("ID: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=2;
		constraints.gridwidth=1;
		constraints.gridwidth=1;
		tID.setText(libro.getId().toString());
		tID.setEditable(false);
		panel.add(tID, constraints);
		
		constraints.gridx=0;
		constraints.gridy=3;
		constraints.gridwidth=2;
		panel.add(new JLabel("T�tulo: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=3;
		constraints.gridwidth=1;
		tTitulo.setText(libro.getTitulo());
		tTitulo.setEditable(false);
		panel.add(tTitulo, constraints);
		
		constraints.gridx=0;
		constraints.gridy=4;
		constraints.gridwidth=2;
		panel.add(new JLabel("Costo: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=4;
		constraints.gridwidth=1;
		tCosto.setText(libro.getCosto().toString());
		tCosto.setEditable(false);
		panel.add(tCosto, constraints);
		
		constraints.gridx=0;
		constraints.gridy=5;
		constraints.gridwidth=2;
		panel.add(new JLabel("Precio de Compra: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=5;
		constraints.gridwidth=1;
		tPrecio.setText(libro.getPrecioCompra().toString());
		tPrecio.setEditable(false);
		panel.add(tPrecio, constraints);
		
		constraints.gridx=0;
		constraints.gridy=6;
		constraints.gridwidth=2;
		panel.add(new JLabel("N� P�ginas: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=6;
		constraints.gridwidth=1;
		tPaginas.setText(libro.getPaginas().toString());
		tPaginas.setEditable(false);
		panel.add(tPaginas, constraints);
		
		constraints.gridx=0;
		constraints.gridy=7;
		constraints.gridwidth=2;
		panel.add(new JLabel("Fecha de Publicaci�n: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=7;
		constraints.gridwidth=1;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		tFecha.setText(formato.format(libro.getFechaPublicacion()));
		tFecha.setEditable(false);
		panel.add(tFecha, constraints);
		
		constraints.gridx=0;
		constraints.gridy=8;
		constraints.gridwidth=2;
		panel.add(new JLabel("Relevancia: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=8;
		constraints.gridwidth=1;
		lRelevancia.setText(libro.getRelevancia().toString());
		lRelevancia.setEditable(false);
		panel.add(lRelevancia, constraints);
		
		constraints.gridx=0;
		constraints.gridy=9;
		constraints.gridwidth=2;
		panel.add(new JLabel("Tema: "), constraints);
		
		constraints.gridx=2;
		constraints.gridy=9;
		constraints.gridwidth=1;
		tTema.setText(libro.getTema());
		tTema.setEditable(false);
		panel.add(tTema, constraints);
		
		constraints.gridx=0;
		constraints.gridy=10;
		cancelar.addActionListener(a -> this.eliminarLibro());
		panel.add(cancelar, constraints);
		
		constraints.gridx=3;
		constraints.gridy=10;
		constraints.fill=GridBagConstraints.NONE;
		eliminar.addActionListener(a -> {			
			if(JOptionPane.showConfirmDialog(ventana, "�Est� seguro que desea eliminar el libro con los datos ingresados?", "Confirmar eliminaci�n", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE)==0){
				controller.eliminarLibro(libro);
				eliminarLibro();
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

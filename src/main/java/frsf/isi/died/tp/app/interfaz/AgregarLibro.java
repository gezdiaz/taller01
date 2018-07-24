package frsf.isi.died.tp.app.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.*;

import frsf.isi.died.tp.app.controller.LibroController;

public class AgregarLibro {
	
	public static void mostrarInterface(JFrame ventana) {
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		JLabel eID = new JLabel("ID: "), eTitulo = new JLabel("Título:"), eCosto = new JLabel("Costo: "),
				ePrecioCompra = new JLabel("Precio Compra: "), ePaginas = new JLabel("Páginas: "), eFecha = new JLabel("Fecha Publicación");
		
		JTextField tID = new JTextField("Ingrese ID"), tTitulo = new JTextField("Ingrese título"), tCosto = new JTextField("Ingrese costo"),
					tPrecioCompra = new JTextField("Ingrese precio"), tPaginas = new JTextField("Ingrese Nº Páginas"), tFecha = new JTextField("dd/mm/aaaa");
		JButton aceptar = new JButton("Aceptar"), cancelar = new JButton("Cancelar");
				
		constraints.gridx=0;
		constraints.gridy=0;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		panel.add(eID, constraints);
		
		constraints.gridx=1;
		constraints.gridy=0;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		tID.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
							
			}
			@Override
			public void focusGained(FocusEvent e) {
				tID.setText("");
			}
		});
		panel.add(tID, constraints);
		
		constraints.gridx=0;
		constraints.gridy=1;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		panel.add(eTitulo, constraints);
		
		constraints.gridx=1;
		constraints.gridy=1;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		tTitulo.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
							
			}
			@Override
			public void focusGained(FocusEvent e) {
				tTitulo.setText("");
			}
		});
		panel.add(tTitulo, constraints);
		
		constraints.gridx=0;
		constraints.gridy=2;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		panel.add(eCosto, constraints);
		
		constraints.gridx=1;
		constraints.gridy=2;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		tCosto.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
							
			}
			@Override
			public void focusGained(FocusEvent e) {
				tCosto.setText("");
			}
		});
		panel.add(tCosto, constraints);
		
		constraints.gridx=0;
		constraints.gridy=3;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		panel.add(ePrecioCompra, constraints);
		
		constraints.gridx=1;
		constraints.gridy=3;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		tPrecioCompra.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
							
			}
			@Override
			public void focusGained(FocusEvent e) {
				tPrecioCompra.setText("");
			}
		});
		panel.add(tPrecioCompra, constraints);
		
		constraints.gridx=0;
		constraints.gridy=4;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		panel.add(ePaginas, constraints);
		
		constraints.gridx=1;
		constraints.gridy=4;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		tPaginas.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
							
			}
			@Override
			public void focusGained(FocusEvent e) {
				tPaginas.setText("");
			}
		});
		panel.add(tPaginas, constraints);
		
		constraints.gridx=0;
		constraints.gridy=5;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		panel.add(eFecha, constraints);
		
		constraints.gridx=1;
		constraints.gridy=5;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		tFecha.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
							
			}
			@Override
			public void focusGained(FocusEvent e) {
				tFecha.setText("");
			}
		});
		panel.add(tFecha, constraints);
		
		constraints.gridx=0;
		constraints.gridy=7;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		cancelar.addActionListener(a -> {
			
		});
		panel.add(cancelar, constraints);
		
		
		constraints.gridx=1;
		constraints.gridy=7;
		constraints.gridheight=1;
		constraints.gridwidth=1;
		aceptar.addActionListener(e -> {
			Integer id;
			String titulo;
			Double costo = 0.0, precioCompra = 0.0;
			Integer paginas = 0;
			Date fechaPublicacion = Calendar.getInstance().getTime();
			try {
				if(tID.getText().isEmpty()){
					System.out.println("El ID no puede ser vacio");
					return;
				}else {
					id = Integer.parseInt(tID.getText());
				}
				if(tTitulo.getText().isEmpty()) {
					System.out.println("El título no puede ser vacío");
					return;
				}else {
					titulo = tTitulo.getText();
				}
				if(!tCosto.getText().isEmpty()) {
					costo = Double.parseDouble(tCosto.getText());
				}
				if(!tPrecioCompra.getText().isEmpty()) {
					precioCompra = Double.parseDouble(tPrecioCompra.getText());
				}
				if(!tPaginas.getText().isEmpty()) {
					paginas = Integer.parseInt(tPaginas.getText());
				}
				if(!tFecha.getText().isEmpty()) {
					fechaPublicacion = (new SimpleDateFormat("dd/MM/yyyy")).parse(tFecha.getText());
				}
				
				LibroController.agregarLibro(id, titulo, costo, precioCompra, paginas, fechaPublicacion);
				
			}catch(Exception ex) {
				
			}
			
		});
		panel.add(aceptar, constraints);
		
		ventana.setContentPane(panel);
		ventana.pack();
        ventana.setSize(800, 600);
        ventana.setVisible(true);
	}
	
}

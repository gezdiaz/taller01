package frsf.isi.died.tp.app.interfaz;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import frsf.isi.died.tp.app.controller.ListaDeseosController;
import frsf.isi.died.tp.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.tp.app.interfaz.tabla.MaterialesTablaModelo;

public class ListaDeseosPanel {
	
	private ListaDeseosController controller;
	private BusquedaPanel busquedaPanel;
	private JFrame ventana;
	
	public ListaDeseosPanel(MaterialCapacitacionDao dao, JFrame ventana) {
		this.controller = new ListaDeseosController(dao);
		this.ventana = ventana;
	}
	
	public void setBusquedaPanel(BusquedaPanel busquedaPanel) {
		this.busquedaPanel = busquedaPanel;
	}

	public ListaDeseosController getController() {
		return controller;
	}
	
	public void mostrarLista() {
				
		JPanel panel = new JPanel(new GridBagLayout());
		MaterialesTablaModelo tableModel = new MaterialesTablaModelo();
		JTable tabla = new JTable(tableModel);
		GridBagConstraints constraints = new GridBagConstraints();
		JButton boton;
		
		JLabel lblTitulo = new JLabel("Lista de deseos: ");
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 4;
		constraints.gridheight = 1;
		constraints.weighty = 0.25;
		constraints.anchor=GridBagConstraints.NORTH;
		lblTitulo.setFont(new Font(lblTitulo.getFont().getName(), lblTitulo.getFont().getStyle(), 20));
		panel.add(lblTitulo,constraints);
		
		tableModel.setMateriales(controller.getLista());
		tabla = new JTable(tableModel);
		tabla.setFillsViewportHeight(true);
		JScrollPane scrollPane= new JScrollPane(tabla);
		
		constraints.gridx=0;
		constraints.gridwidth=4;	
		constraints.gridy=1;
		constraints.weighty=0.5;
		constraints.weightx=1;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill=GridBagConstraints.BOTH;
		panel.add(scrollPane, constraints);
		
		constraints.gridx = 0;
		constraints.gridy = 2;
		constraints.gridwidth=1;
		constraints.weighty = 0.25;
		constraints.weightx=0.75;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		constraints.weightx=0;
		JLabel label = new JLabel();
		label.setPreferredSize(new Dimension(35, 1));
		panel.add(label, constraints);
		
		boton = new JButton("Inicio");
		constraints.gridx = 1;
		constraints.gridy = 2;
		constraints.gridwidth=1;
		constraints.weighty = 0.25;
		constraints.weightx=0.25;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill=GridBagConstraints.NONE;
		constraints.weightx=0;
		boton.addActionListener( a -> Principal.mostrarInterfaz());
		panel.add(boton,constraints);
		
		constraints.gridx=2;
		constraints.weightx=0.75;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		panel.add(new JLabel(""), constraints);
		
		boton = new JButton("Agregar Materiales");
		constraints.gridx = 3;
		constraints.gridy = 2;
		constraints.weighty = 0.25;
		constraints.weightx=0.25;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill=GridBagConstraints.NONE;
		boton.addActionListener( a -> busquedaPanel.busqueda());
		panel.add(boton,constraints);
		
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800, 600);
		
        ventana.setVisible(true);
		
	}


}

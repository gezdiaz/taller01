package frsf.isi.died.tp.app.interfaz;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import frsf.isi.died.tp.app.controller.ListaDeseosController;
import frsf.isi.died.tp.app.interfaz.tabla.MaterialesTablaModelo;

public class ListaDeseosPanel {

	public static void mostrarLista(JFrame ventana, ListaDeseosController listaController) {
		
		if(listaController == null) listaController = new ListaDeseosController();
		
		JPanel panel = new JPanel(new GridBagLayout());
		MaterialesTablaModelo tableModel = new MaterialesTablaModelo();
		JTable tabla = new JTable(tableModel);
		GridBagConstraints constraints = new GridBagConstraints();
		JButton boton;
		
		JLabel lblTitulo = new JLabel("Lista de deseos: ");
		constraints.gridx = 1;
		constraints.gridy = 0;
		constraints.gridwidth = 7;
		constraints.gridheight = 1;
		constraints.weighty = 0.25;
		constraints.anchor=GridBagConstraints.NORTH;
		lblTitulo.setFont(new Font(lblTitulo.getFont().getName(), lblTitulo.getFont().getStyle(), 20));
		panel.add(lblTitulo,constraints);
		
		tableModel.setMateriales(listaController.getLista());
		tabla = new JTable(tableModel);
		tabla.setFillsViewportHeight(true);
		JScrollPane scrollPane= new JScrollPane(tabla);
		
		constraints.gridx=0;
		constraints.gridwidth=2;	
		constraints.gridy=2;
		constraints.weighty=0.5;
		constraints.weightx=1;
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.fill=GridBagConstraints.HORIZONTAL;
		panel.add(scrollPane, constraints);
		
		boton = new JButton("Inicio");
		constraints.gridx = 0;
		constraints.gridy = 3;
		constraints.weighty = 0.25;
		constraints.anchor = GridBagConstraints.WEST;
		constraints.fill=GridBagConstraints.NONE;
		constraints.weightx=0;
		boton.addActionListener( a -> Principal.mostrarInterfaz(ventana));
		panel.add(boton,constraints);
		
		boton = new JButton("Agregar Materiales");
		constraints.gridx = 1;
		constraints.gridy = 3;
		constraints.weighty = 0.25;
		constraints.anchor = GridBagConstraints.EAST;
		boton.addActionListener( a -> BusquedaPanel.busqueda(ventana));
		panel.add(boton,constraints);
		
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800, 600);
		
        ventana.setVisible(true);
		
	}

}

package frsf.isi.died.tp.app.interfaz;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import frsf.isi.died.tp.estructuras.arbolContenido.*;
import frsf.isi.died.tp.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.tp.app.interfaz.tabla.NodosTablaModelo;
import frsf.isi.died.tp.modelo.productos.*;

public class BusquedaContenidoPanel {

	private JFrame ventana;
	private BusquedaPanel busquedaPanel;
	private MaterialCapacitacionDao dao;
	private List<ArbolContenido> nodos;
	
	public BusquedaContenidoPanel(JFrame ventana, BusquedaPanel busquedaPanel, MaterialCapacitacionDao dao) {
		this.ventana = ventana;
		this.busquedaPanel = busquedaPanel;
		this.dao = dao;
		nodos = new ArrayList<ArbolContenido>();
	}
	
	public void buscarPorContenido() {
		
		JPanel panel = new JPanel(new GridBagLayout());
		JLabel label;
		JButton boton;
		JTextField valor = new JTextField(20);
		NodosTablaModelo tableModel = new NodosTablaModelo();
		JTable tabla;
		JScrollPane scroll;
		GridBagConstraints cons = new GridBagConstraints();
		TipoNodo[] array = {TipoNodo.TITULO, TipoNodo.METADATO, TipoNodo.AUTOR, TipoNodo.SECCION,
				TipoNodo.PARRAFO, TipoNodo.CAPITULO, TipoNodo.EDITORIAL, TipoNodo.RESUMEN,
				TipoNodo.PALABRA_CLAVE, TipoNodo.FECHA_PUBLICACION, TipoNodo.SITIO_WEB};
		JComboBox<TipoNodo> tipo = new JComboBox<>(array);
		
		tableModel.setNodos(nodos);
		tabla = new JTable(tableModel);
		tabla.setFillsViewportHeight(true);
		scroll = new JScrollPane(tabla);
		
		label = new JLabel("Búsqueda por contenido");
		label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 40));
		cons.insets = new Insets(5, 5, 5, 5);
		cons.gridheight=1;
		cons.gridwidth=3;
		cons.gridx=0;
		cons.gridy=0;
		panel.add(label, cons);
		
		label = new JLabel("Valor: ");
		cons.gridwidth=1;
		cons.gridx=0;
		cons.gridy=1;
		panel.add(label, cons);
		
		cons.gridx=1;
		cons.gridy=1;
		cons.fill = GridBagConstraints.HORIZONTAL;
		panel.add(valor, cons);
		
		label = new JLabel("Tipo: ");
		cons.gridx=0;
		cons.gridy=2;
		cons.fill = GridBagConstraints.NONE;
		panel.add(label, cons);
		
		cons.gridx=1;
		cons.gridy=2;
		panel.add(tipo, cons);
		
		boton = new JButton("Atrás");
		boton.addActionListener(a -> busquedaPanel.busqueda());
		cons.gridx=0;
		cons.gridy=6;
		cons.anchor=GridBagConstraints.WEST;
		panel.add(boton, cons);
		
		boton = new JButton("Eliminar Filtro");
		boton.addActionListener(a ->{
			if(tableModel.getNodos().isEmpty()) {
				JOptionPane.showConfirmDialog(ventana, "La lista de filtros está vacía.", "Seleccione un filtro", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}else {
				ArbolContenido nodoSeleccionado = null;
				if(tabla.getSelectedRow() >= 0) {
					nodoSeleccionado = tableModel.getNodos().get(tabla.getSelectedRow());
				}else {
					JOptionPane.showConfirmDialog(ventana, "Seleccione un filtro a eliminar de la lista a la derecha.", "Seleccione un filtro", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
				if(nodoSeleccionado != null) {
					nodos.remove(nodoSeleccionado);
					tableModel.getNodos().remove(nodoSeleccionado);
					tableModel.fireTableDataChanged();
				}
				
			}
		});
		cons.gridx=0;
		cons.gridy=3;
		cons.gridwidth=1;
		panel.add(boton, cons);
		
		boton = new JButton("Agregar Filtro");
		boton.addActionListener(a -> {
			if(valor.getText().isEmpty()) {
				JOptionPane.showConfirmDialog(ventana, "Debe ingresar un valor para el filtro.", "Ingrese un valor", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}else {
				tableModel.agregarNodo(new ArbolContenido(valor.getText(), (TipoNodo)tipo.getSelectedItem()));
				tableModel.fireTableDataChanged();
			}
		});
		cons.gridx=1;
		cons.gridy=3;
		cons.gridwidth=1;
		panel.add(boton, cons);
		
		boton = new JButton("Buscar");
		boton.addActionListener(a -> {
			ArrayList<MaterialCapacitacion> materiales = (ArrayList<MaterialCapacitacion>) dao.filtrarContenido(tableModel.getNodos());
			nodos.clear();
			busquedaPanel.mostrarMaterialesTabla(materiales);
		});
		
		label = new JLabel("");
		cons.gridx=0;
		cons.gridy=4;
		label.setPreferredSize(new Dimension(0, 15));
		panel.add(label, cons);
		label = new JLabel("");
		cons.gridx=0;
		cons.gridy=5;
		label.setPreferredSize(new Dimension(0, 15));
		panel.add(label, cons);
		
		cons.gridx=2;
		cons.gridy=6;
		cons.anchor=GridBagConstraints.EAST;
		cons.gridwidth=1;
		panel.add(boton, cons);
		
		label = new JLabel("Filtros seleccionados: ");
		cons.gridx=2;
		cons.gridy=1;
		cons.anchor = GridBagConstraints.WEST;
		panel.add(label, cons);
		
		cons.gridx=2;
		cons.gridy=2;
		cons.gridheight=4;
		cons.gridwidth=1;
		cons.fill=GridBagConstraints.BOTH;
		panel.add(scroll, cons);
		
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800, 600);
		
        ventana.setVisible(true);
	}
	
	
}

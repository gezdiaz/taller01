package frsf.isi.died.tp.app.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frsf.isi.died.tp.app.controller.GrafoController;
import frsf.isi.died.tp.app.controller.RelacionesController;
import frsf.isi.died.tp.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.tp.app.interfaz.grafo.ControlPanel;
import frsf.isi.died.tp.app.interfaz.grafo.GrafoPanel;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class RelacionesPanel {
	
	private RelacionesController controller;
	private JFrame ventana;
	
	public RelacionesPanel(MaterialCapacitacionDao dao, JFrame ventana) {
		this.controller = new RelacionesController(dao);
		this.ventana = ventana;
	}
	
	public void armarRelacionesPanel(MaterialCapacitacion material) {
		JPanel panel = new JPanel(new BorderLayout());
		ControlPanel controlPanel = new ControlPanel();
		GrafoPanel grafoPanel = new GrafoPanel(this.ventana);
		GrafoController grfController = new GrafoController(grafoPanel,controlPanel);
		grfController.setListaTema(material.getTema());
		grfController.crearVertice(400, 300, Color.RED, material);
		grafoPanel.setAuxiliarOrigen(grfController.buscarVertice(material));
		
		panel.add(new JLabel("Doble click: agregar nodo, Click secundario: ver opciones, Arrastrar y soltar: agregar arista."), BorderLayout.SOUTH);
		panel.add(grafoPanel, BorderLayout.CENTER);
		
		ventana.setContentPane(panel);
		ventana.pack();
		ventana.setSize(800, 600);
		
        ventana.setVisible(true);
	}

	public RelacionesController getController() {
		return this.controller;
	}
}

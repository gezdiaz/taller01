package frsf.isi.died.tp.app.interfaz;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import frsf.isi.died.tp.app.controller.GrafoController;
import frsf.isi.died.tp.app.controller.RelacionesController;
import frsf.isi.died.tp.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.tp.app.interfaz.grafo.ControlPanel;
import frsf.isi.died.tp.app.interfaz.grafo.GrafoPanel;
import frsf.isi.died.tp.app.interfaz.grafo.VerticeView;

public class RelacionesPanel {
	
	private RelacionesController controller;
	private JFrame ventana;
	
	public RelacionesPanel(MaterialCapacitacionDao dao, JFrame ventana) {
		this.controller = new RelacionesController(dao);
		this.ventana = ventana;
	}
	
	public void armarRelacionesPanel() {
//		JPanel panel = new JPanel(new GridBagLayout());
		ControlPanel controlPanel = new ControlPanel();
		GrafoPanel grafoPanel = new GrafoPanel(this.ventana);
		GrafoController grfController = new GrafoController(grafoPanel,controlPanel);
		
		
		ventana.setContentPane(grafoPanel);
		ventana.pack();
		ventana.setSize(800, 600);
		
        ventana.setVisible(true);
	}

	public RelacionesController getController() {
		return this.controller;
	}
}

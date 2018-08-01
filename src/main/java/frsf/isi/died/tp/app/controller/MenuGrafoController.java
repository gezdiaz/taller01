package frsf.isi.died.tp.app.controller;

import frsf.isi.died.tp.app.interfaz.grafo.GrafoPanel;
import frsf.isi.died.tp.app.interfaz.grafo.OpcionesPopUp;

public class MenuGrafoController {

	GrafoController grafoController;
	GrafoPanel grafoPanel;
	
	public MenuGrafoController(GrafoController grafoController, GrafoPanel grafoPanel) {
		this.grafoController = grafoController;
		this.grafoPanel = grafoPanel;
	}
	
	public void opcionPopUp(OpcionesPopUp op) {
		switch(op) {
		case BUSCAR_CAMINO: grafoPanel.buscarCamino(); break;
		case LISTA_TEMA: System.out.println("Mostrar Lista Tema");
		}
	}
	
}

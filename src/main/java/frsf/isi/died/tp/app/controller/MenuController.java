package frsf.isi.died.tp.app.controller;

import javax.swing.JFrame;

import frsf.isi.died.tp.app.interfaz.ABMLibro;
import frsf.isi.died.tp.app.interfaz.ABMVideo;
import frsf.isi.died.tp.app.interfaz.BusquedaPanel;

public class MenuController {

	public static void opcion(OpcionesMenu op, JFrame ventana) {
		switch(op) {
		case AGREGAR_LIBRO: ABMLibro.agregarLibro(ventana); break;
		case ELIMINAR_LIBRO: System.out.println("Abre interface de eliminar libro"); break;
		case EDITAR_LIBRO: ABMLibro.editarLibro(ventana); break;
		case AGREGAR_VIDEO: ABMVideo.agregarVideo(ventana);; break;
		case ELIMINAR_VIDEO: System.out.println("Abre interface de eliminar video"); break;
		case EDITAR_VIDEO: ABMVideo.editarVideo(ventana); break;
		case BUSQUEDA: BusquedaPanel.busqueda(ventana); break;
		case WHISLIST: System.out.println("Abre lista de deseos");
		}
	}
	
}

package frsf.isi.died.tp.app.controller;

import javax.swing.JFrame;

import frsf.isi.died.tp.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.tp.app.interfaz.ABMLibro;
import frsf.isi.died.tp.app.interfaz.ABMVideo;
import frsf.isi.died.tp.app.interfaz.BusquedaPanel;
import frsf.isi.died.tp.app.interfaz.ListaDeseosPanel;

public class MenuController {

	private ABMLibro interfazLibro;
	private ABMVideo interfazVideo;
	private BusquedaPanel busqueda;
	private ListaDeseosPanel listaDeseos;
	
	public MenuController(MaterialCapacitacionDao dao, JFrame ventana) {
		interfazLibro = new ABMLibro(dao, ventana);
		interfazVideo = new ABMVideo(dao, ventana);
		busqueda = new BusquedaPanel(dao, ventana);
		listaDeseos = new ListaDeseosPanel(dao, ventana);
		busqueda.setListaPanel(listaDeseos);
		listaDeseos.setBusquedaPanel(busqueda);
	}
	
	
	public void opcion(OpcionesMenu op, JFrame ventana) {
		switch(op) {
		case AGREGAR_LIBRO: interfazLibro.agregarLibro(); break;
		case ELIMINAR_LIBRO: interfazLibro.eliminarLibro(); break;
		case EDITAR_LIBRO: interfazLibro.editarLibro(); break;
		case AGREGAR_VIDEO: interfazVideo.agregarVideo(); break;
		case ELIMINAR_VIDEO: interfazVideo.eliminarVideo(); break;
		case EDITAR_VIDEO: interfazVideo.editarVideo(); break;
		case BUSQUEDA: busqueda.busqueda(); break;
		case WHISLIST: listaDeseos.mostrarLista(); break;
		}
	}
	
}

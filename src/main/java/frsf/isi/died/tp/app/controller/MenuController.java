package frsf.isi.died.tp.app.controller;

import javax.swing.JFrame;

import frsf.isi.died.tp.app.interfaz.AgregarLibro;

public class MenuController {

	public static void opcion(OpcionesMenu op, JFrame ventana) {
		switch(op) {
		case AGREGAR_LIBRO: AgregarLibro.mostrarInterface(ventana); break;
		case ELIMINAR_LIBRO: System.out.println("Abre interface de eliminar libro"); break;
		case EDITAR_LIBRO: System.out.println("Abre inerface para editar libro"); break;
		case AGREGAR_VIDEO: System.out.println("Abre interfaz de agregar video"); break;
		case ELIMINAR_VIDEO: System.out.println("Abre interface de eliminar video"); break;
		case EDITAR_VIDEO: System.out.println("Abre inerface para editar video"); break;
		case BUSQUEDA: System.out.println("Abre interfaz de busqueda"); break;
		case WHISLIST: System.out.println("Abre lista de deseos");
		}
	}
	
}

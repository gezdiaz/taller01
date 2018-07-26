package frsf.isi.died.tp.app.controller;

import java.util.Calendar;
import java.util.Date;

import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.Relevancia;

public class LibroController {
	
	public static Libro agregarLibro(Integer id, String titulo, Double costo, Double precioCompra, Integer paginas, Date fechaPublicacion, Relevancia relev) {
		Libro nuevo = new Libro(id, titulo, costo, precioCompra, paginas, fechaPublicacion, relev);
		System.out.println("Se creó el nuevo libro:"+nuevo);
		return nuevo;
	}

	public static Libro buscarLibro(int id) {
		//creo un nuevo libro para simular la busqueda
		//TODO implementar busqueda de libro
		Libro nuevo = new Libro(id, "Java", 56.4, 41.6, 100, Calendar.getInstance().getTime(), Relevancia.ALTA);
		return nuevo;
	}
	
}

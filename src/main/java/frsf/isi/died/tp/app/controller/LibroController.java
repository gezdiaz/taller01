package frsf.isi.died.tp.app.controller;

import java.util.Calendar;
import java.util.Date;

import frsf.isi.died.tp.modelo.productos.Libro;

public class LibroController {
	
	public static void agregarLibro(Integer id, String titulo, Double costo, Double precioCompra, Integer paginas, Date fechaPublicacion) {
		Libro nuevo = new Libro(id, titulo, costo, precioCompra, paginas, fechaPublicacion);
		System.out.println("Se creó el nuevo libro:"+nuevo);
	}

	public static Libro buscarLibro(int id) {
		//creo un nuevo libro para simular la busqueda
		//TODO implementar busqueda de libro
		Libro nuevo = new Libro(id, "Java", 56.4, 41.6, 100, Calendar.getInstance().getTime());
		return nuevo;
	}
	
}

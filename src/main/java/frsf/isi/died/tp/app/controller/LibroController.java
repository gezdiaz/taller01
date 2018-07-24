package frsf.isi.died.tp.app.controller;

import java.util.Date;

import frsf.isi.died.tp.modelo.productos.Libro;

public class LibroController {
	
	public static void agregarLibro(Integer id, String titulo, Double costo, Double precioCompra, Integer paginas, Date fechaPublicacion) {
		Libro nuevo = new Libro(id, titulo, costo, precioCompra, paginas, fechaPublicacion);
		System.out.println("Se creó el nuevo libro:"+nuevo);
	}
	
}

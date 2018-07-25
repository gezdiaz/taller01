package frsf.isi.died.tp.app.controller;

import java.util.Date;

import frsf.isi.died.tp.modelo.productos.Video;

public class VideoController {

	public static void agregarVideo(Integer id, String titulo, Double costo, Integer duracion, Date fechaPublicacion) {
		Video nuevo = new Video(id, titulo, costo, duracion, fechaPublicacion);
		System.out.println("Se creó el nuevo viedo: "+nuevo);
	}
	
}

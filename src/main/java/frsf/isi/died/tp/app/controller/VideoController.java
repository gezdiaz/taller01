package frsf.isi.died.tp.app.controller;

import java.util.Calendar;
import java.util.Date;

import frsf.isi.died.tp.modelo.productos.Relevancia;
import frsf.isi.died.tp.modelo.productos.Video;

public class VideoController {

	public static Video agregarVideo(Integer id, String titulo, Double costo, Integer duracion, Date fechaPublicacion, Relevancia relev) {
		Video nuevo = new Video(id, titulo, costo, duracion, fechaPublicacion, relev);
		System.out.println("Se creó el nuevo viedo: "+nuevo);
		return nuevo;
	}

	public static Video buscarVideo(int id) {
		Video existente = new Video(id,"La fuga del paralitico", 23.00, 234, Calendar.getInstance().getTime(), Relevancia.ALTA);
		return existente;
	}
	
}

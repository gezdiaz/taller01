package frsf.isi.died.tp.modelo.productos;

import java.util.Date;

public class Video extends MaterialCapacitacion{
	private static Double costoSegundo = 0.15;
	private Integer duracion;
	
	public Video() {
		//constructor vacio
	}
	public Video(Integer id, String titulo, Date fechaPublicacion) {
		this(id,titulo,0.0,0, fechaPublicacion);
	}
	public Video(Integer id, String titulo) {
		this(id,titulo,0.0,0);
	}
	public Video(Integer id, String titulo, Double costo, Integer duracion, Date fechaPublicacion) {
		super(id, titulo, costo, fechaPublicacion);
		this.duracion = duracion;
	}
	public Video(Integer id, String titulo, Double costo, Integer duracion) {
		super(id, titulo, costo);
		this.duracion = duracion;
	}
	public Boolean esVideo() {
		return true;
	}
	public Boolean esLibro() {
		return false;
	}
	public Double precio() {
		return costo + (costoSegundo*duracion);
	}
	
}

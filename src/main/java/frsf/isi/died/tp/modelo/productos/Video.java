package frsf.isi.died.tp.modelo.productos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Video extends MaterialCapacitacion{
	private static Double costoSegundo = 0.15;
	private Integer duracion;
	
	public Video() {
		//constructor vacio
	}
	
	public Video(Integer id, String titulo, Date fechaPublicacion) {
		this(id,titulo,0.0,0, fechaPublicacion, Relevancia.MEDIA);
	}
	
	public Video(Integer id, String titulo) {
		this(id,titulo,0.0,0);
	}
	
	public Video(Integer id, String titulo, Double costo, Integer duracion, Date fechaPublicacion, Relevancia relev) {
		super(id, titulo, costo, fechaPublicacion, relev);
		this.duracion = duracion;
	}
	
	public Video(Integer id, String titulo, Double costo, Integer duracion) {
		this(id, titulo, costo, duracion, Calendar.getInstance().getTime(), Relevancia.MEDIA);
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
	
	public Integer getDuracion() {
		return duracion;
	}
	
	public void setDuracion(Integer duracion) {
		this.duracion=duracion;
	}
	
	@Override
	public boolean equals(Object obj) {
		return obj instanceof Video && super.equals(obj) ;
	}
	
	@Override
	public List<String> asCsvRow() {
		List<String> lista = new ArrayList<String>();
		lista.add(this.id+"");
		lista.add("\""+this.titulo.toString()+"\"");
		lista.add(this.costo.toString());
		lista.add(this.duracion.toString());
		lista.add(this.fechaPublicacion.toString());
		lista.add(this.calificacion.toString());
		lista.add(this.relevancia.toString());
		lista.add(this.votantes.toString());
		return lista;
	}

	@Override
	public void loadFromStringRow(List<String> datos) {
		this.id =Integer.valueOf(datos.get(0));
		this.titulo = datos.get(1);
		this.costo =Double.valueOf(datos.get(2));
		this.duracion =Integer.valueOf(datos.get(3));
		this.fechaPublicacion=new Date();
		this.calificacion=Integer.valueOf(datos.get(5));
		this.relevancia=Relevancia.valueOf(datos.get(6));
		this.votantes=Integer.valueOf(datos.get(7));
	}
	
}

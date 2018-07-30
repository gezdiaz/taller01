package frsf.isi.died.tp.modelo.productos;

import java.text.SimpleDateFormat;
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
		this(id,titulo,0.0,0, fechaPublicacion, Relevancia.MEDIA, "Otros");
	}
	
	public Video(Integer id, String titulo) {
		this(id,titulo,0.0,0);
	}
	
	public Video(Integer id, String titulo, Double costo, Integer duracion, Date fechaPublicacion, Relevancia relev, String tema) {
		super(id, titulo, costo, fechaPublicacion, relev, tema);
		this.duracion = duracion;
	}
	
	public Video(Integer id, String titulo, Double costo, Integer duracion) {
		this(id, titulo, costo, duracion, Calendar.getInstance().getTime(), Relevancia.MEDIA, "Otros");
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
		lista.add((new SimpleDateFormat("dd/MM/yyyy")).format(this.fechaPublicacion));
		lista.add(this.calificacion.toString());
		lista.add(this.relevancia.toString());
		lista.add(this.votantes.toString());
		lista.add("\""+this.tema+"\"");
		return lista;
	}

	@Override
	public void loadFromStringRow(List<String> datos) {
		
		try {
			this.id = Integer.valueOf(datos.get(0));
			this.titulo = datos.get(1).substring(1, datos.get(1).length() - 1);
			this.costo = Double.valueOf(datos.get(2));
			this.duracion = Integer.valueOf(datos.get(3));
			this.fechaPublicacion = (new SimpleDateFormat("dd/MM/yyyy")).parse(datos.get(4));
			this.calificacion = Integer.valueOf(datos.get(5));
			this.relevancia = Relevancia.valueOf(datos.get(6));
			this.votantes = Integer.valueOf(datos.get(7));
			this.tema = datos.get(8).substring(1, datos.get(8).length() - 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.modelo.productos;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import frsf.isi.died.tp.util.Ordenable;

/**
 * Representa de manera abstracta los materiales de capacitación
 * 
 * @author mdominguez
 * 
 * nombres integrantes:
 * Diaz, Gaston
 * Vacca, Joaquin
 * Proyecto GitHub:
 * https://github.com/gezdiaz/taller01.git
 */
public abstract class MaterialCapacitacion implements Ordenable, Comparable<MaterialCapacitacion>{
	protected Integer id;
	private Integer calificacion;
	private Integer votantes;
	private Date fechaPublicacion;
	private Relevancia relevancia;
	
	public Date getFechaPublicacion() {
		return fechaPublicacion;
	}

	public void setFechaPublicacion(Date fechaPublicacion) {
		this.fechaPublicacion = fechaPublicacion;
	}

	public Relevancia getRelevancia() {
		return relevancia;
	}

	public void setRelevancia(Relevancia relevancia) {
		this.relevancia = relevancia;
	}

	public Integer getCalificacion() {
		return calificacion;
	}
	
	public void agregarCalificacion(Integer cal) {
		calificacion = ((calificacion * votantes) + cal) / (votantes + 1);
		votantes ++;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	/**
	 * Titulo del material
	 */
	protected String titulo;

	/**
	 * Costo básico que debe sumarse al precio por el mero hecho de publicarlo en el
	 * portal
	 */
	protected Double costo;
	

	/**
	 * Constructor por defecto
	 */
	public MaterialCapacitacion() {
		this(0,"en desarrollo",0.0, Calendar.getInstance().getTime(), Relevancia.MEDIA);
	}

	/**
	 * Constructor que recibe como argumento un ID y un Titulo
	 * 
	 * @param id
	 * @param titulo
	 */
	public MaterialCapacitacion(Integer id, String titulo, Date fechaPublicacion) {
		this(id,titulo,0.0, fechaPublicacion, Relevancia.MEDIA);
	}

	/**
	 * Constructor que recibe como argumento un ID y un costo
	 * 
	 * @param id
	 * @param titulo
	 */
	public MaterialCapacitacion(Integer id,String titulo, Double costo, Date fechaPublicacion, Relevancia relev) {
		this.id =id;
		this.titulo = titulo;
		this.costo = costo;
		this.fechaPublicacion = fechaPublicacion;
		this.relevancia = relev;
		this.calificacion = 0;
		this.votantes = 0;
	}


	//TODO 01 implementar los metodos getters y setters y escribir el javadoc
	// AYUDA: para implementar estos metodos usar un atajo del IDE 
	// elegir el menu "Source" --> "Generate getters y setters" y elegir alli que metodos generar.
	

	public MaterialCapacitacion(Integer id, String titulo, Double costo) {
		this(id,titulo,costo,Calendar.getInstance().getTime(), Relevancia.MEDIA);
	}

	/**
	 * El precio de un material se define según el tipo del material y toma como
	 * base el costo del mismo
	 * 
	 * @return
	 */
	public abstract Double precio();
	
	/**
	 * Retorna verdadero si es una instancia de libro, falso en caso contrario
	 * @return
	 */
	public abstract Boolean esLibro();

	/**
	 * Retorna verdadero si es una instancia de video, falso en caso contrario
	 * @return
	 */
	public abstract Boolean esVideo();
	
	//TODO 02: sobrescribir el metodo toString de la clase "Object"
	//	el método toString retorna un string que representa el material actual
	//  retornando el titulo, y el precio 	 * usando el formato : 
	// [Titulo: <titulo> ; Precio: <precio> ]
	
	public String toString() {
		return "[Titulo: "+titulo+"; Precio: "+this.precio()+"]";
	}
	
	// TODO 10: implementar Ordenable
	public final int valor() {
		return (this.precio()).intValue(); 
		/*Esto funciona porque, la clase MaterialCapaciatcion es abstracta,
		*entonces no puede tener instacias y nunca se va a llamar al m�todo abtracto precio(),
		*sino al implementado en las calses hijas que son concretas
		*/
		
	}
	@Override
	public boolean equals(Object otroMaterial) {
		if(otroMaterial.getClass() == this.getClass() ) {
			return ((MaterialCapacitacion) otroMaterial).getTitulo().equalsIgnoreCase(this.titulo);
		}
		return false;
	}

	@Override
	public int compareTo(MaterialCapacitacion otroMaterial) {
		// TODO Auto-generated method stub
		if(this.equals(otroMaterial)) {
			return this.precio().compareTo(otroMaterial.precio());
		}else {
			return this.titulo.compareTo(otroMaterial.getTitulo());
		}
	}
	

}

package frsf.isi.died.tp.app.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import frsf.isi.died.tp.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.tp.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.Relevancia;

public class LibroController {
	
	private MaterialCapacitacionDao dao;
	
	public LibroController(MaterialCapacitacionDao dao) {
		this.dao = dao;
	}
	
	public ArrayList<Libro> listaLibros(){
		return (ArrayList<Libro>) dao.listaLibros();
	}
	
	public Libro agregarLibro(Integer id, String titulo, Double costo, Double precioCompra, Integer paginas, Date fechaPublicacion, Relevancia relevancia, String tema) {
		Libro nuevo = new Libro(id, titulo, costo, precioCompra, paginas, fechaPublicacion, relevancia, tema);
		System.out.println("Se creó el nuevo libro:"+nuevo);
		//TODO guardar libro en almacenamiento
		dao.agregarLibro(nuevo);
		return nuevo;
	}

	public Libro buscarLibro(int id) {
		//creo un nuevo libro para simular la busqueda
		//TODO implementar busqueda de libro
		Libro nuevo ;//= new Libro(id, "Java", 56.4, 41.6, 100, Calendar.getInstance().getTime(), Relevancia.ALTA);
		//TODO buscar libro en el almacenamiento
		nuevo = (Libro)dao.findById(id);
		return nuevo;
	}

	public void editarLibro(Libro libro, String titulo, double costo, double precioCompra, int paginas,
			Date fechaPublicacion, Relevancia relevancia, String tema) {
		
		libro.setTitulo(titulo);
		libro.setCosto(costo);
		libro.setPrecioCompra(precioCompra);
		libro.setCosto(costo);
		libro.setFechaPublicacion(fechaPublicacion);
		libro.setRelevancia(relevancia);
		libro.setTema(tema);
		
		System.out.println("Se editó el libro: "+libro);
		//TODO editar libro en el almacenamiento persistente
	}

	public void eliminarLibro(Libro libro) {
		// TODO eliminar libro del almacenamiento
		System.out.println("Libro Eliminado: "+libro);
	}
	
}

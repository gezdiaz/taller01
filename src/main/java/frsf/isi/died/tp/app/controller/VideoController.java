package frsf.isi.died.tp.app.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import frsf.isi.died.tp.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.tp.modelo.productos.Relevancia;
import frsf.isi.died.tp.modelo.productos.Video;

public class VideoController {

	private MaterialCapacitacionDao dao;
	
	public VideoController(MaterialCapacitacionDao dao) {
		this.dao = dao;
	}
	
	public ArrayList<Video> listaVideos(){
		return (ArrayList<Video>) dao.listaVideos();
	}
	
	public Video agregarVideo(Integer id, String titulo, Double costo, Integer duracion, Date fechaPublicacion, Relevancia relev) {
		Video nuevo = new Video(id, titulo, costo, duracion, fechaPublicacion, relev);
		System.out.println("Se creó el nuevo viedo: "+nuevo);
		//TODO guardar video en almacenamiento
		dao.agregarVideo(nuevo);
		return nuevo;
	}

	public Video buscarVideo(int id) {
		Video existente = new Video(id,"La fuga del paralitico", 23.00, 234, Calendar.getInstance().getTime(), Relevancia.ALTA);
		//TODO buscar video en almacenamiento
//		existente = (Video)dao.findById(id);
		
		return existente;
	}
	
	public void editarVideo(Video video, String titulo, double costo, int duracion,
			Date fechaPublicacion, Relevancia relevancia) {
		
		video.setTitulo(titulo);
		video.setCosto(costo);
		video.setDuracion(duracion);
		video.setFechaPublicacion(fechaPublicacion);
		video.setRelevancia(relevancia);
		
		System.out.println("Video editado: "+video);
		//TODO actualizar video en almacenamiento
		
	}

	public void eliminarVideo(Video video) {
		// TODO eliminar video del almacenamiento
		System.out.println("Video eliminado: "+video);		
	}
	
}

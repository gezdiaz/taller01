package frsf.isi.died.tp.app.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import frsf.isi.died.tp.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
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
	
	public void agregarVideo(String titulo, Double costo, Integer duracion, Date fechaPublicacion, Relevancia relev, String tema) {
		Video nuevo = new Video(0, titulo, costo, duracion, fechaPublicacion, relev, tema);
		dao.agregarVideo(nuevo);
	}

	public Video buscarVideo(int id) {
		MaterialCapacitacion existente;
		existente = dao.findById(id);
		if(existente != null && existente.esVideo()) {
			return (Video)existente;
		}else {
			return null;
		}
		
	}
	
	public void editarVideo(Video video, String titulo, double costo, int duracion,
			Date fechaPublicacion, Relevancia relevancia, String tema) {

		video.setTitulo(titulo);
		video.setCosto(costo);
		video.setDuracion(duracion);
		video.setFechaPublicacion(fechaPublicacion);
		video.setRelevancia(relevancia);
		video.setTema(tema);
		
		dao.editarVideo(video.getId(), video);
		
	}

	public void eliminarVideo(Video video) {
		dao.eliminarVideo(video);		
	}
	
}

package frsf.isi.died.tp.app.dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import frsf.isi.died.tp.app.dao.util.CsvDatasource;
import frsf.isi.died.tp.estructuras.Grafo;
import frsf.isi.died.tp.estructuras.Vertice;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Video;

public class MaterialCapacitacionDaoDefault implements MaterialCapacitacionDao{

	private static Grafo<MaterialCapacitacion> GRAFO_MATERIAL  = new Grafo<MaterialCapacitacion>();
	private static Integer SECUENCIA_ID;
	
	private CsvDatasource dataSource;
	
	public MaterialCapacitacionDaoDefault() {
		dataSource = new CsvDatasource();
		if(GRAFO_MATERIAL.esVacio()) {
			cargarGrafo();
		}
		SECUENCIA_ID = maxID();
	}

	private Integer maxID() {
		List<MaterialCapacitacion> vertices = GRAFO_MATERIAL.listaVertices();
		Integer max = 0;
		
		for(MaterialCapacitacion ver: vertices) {
			if(ver.getId()>max) {
				max = ver.getId();
			}
		}
		
		return max;
	}

	private void cargarGrafo() {
		List<List<String>> libros = dataSource.readFile("libros.csv");
		for(List<String> filaLibro : libros) {
			Libro aux = new Libro();
			aux.loadFromStringRow(filaLibro);
			GRAFO_MATERIAL.addNodo(aux);
		}
		List<List<String>> videos= dataSource.readFile("videos.csv");
		for(List<String> filaVideo: videos) {
			Video aux = new Video();
			aux.loadFromStringRow(filaVideo);
			GRAFO_MATERIAL.addNodo(aux);
		}
		List<List<String>> aristas= dataSource.readFile("aristas.csv");
		for(List<String> filaArista: aristas) {
			MaterialCapacitacion n1 = this.findById(Integer.valueOf(filaArista.get(0)));
			MaterialCapacitacion n2 = this.findById(Integer.valueOf(filaArista.get(2)));
			GRAFO_MATERIAL.conectar(n1, n2);
		}
 	}
	
	@Override
	public void agregarLibro(Libro mat) {
		mat.setId(++SECUENCIA_ID);
		GRAFO_MATERIAL.addNodo(mat);	
		try {
			dataSource.agregarFilaAlFinal("libros.csv", mat);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void agregarVideo(Video mat) {
		mat.setId(++SECUENCIA_ID);
		GRAFO_MATERIAL.addNodo(mat);		
		try {
			dataSource.agregarFilaAlFinal("videos.csv", mat);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Libro> listaLibros() {
		List<Libro> libros = new ArrayList<>();
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.esLibro()) libros.add((Libro)mat); 
		}
		return libros;
	}

	@Override
	public List<Video> listaVideos() {
		List<Video> vids = new ArrayList<>();
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.esVideo()) vids.add((Video)mat); 
		}
		return vids;
	}

	@Override
	public List<MaterialCapacitacion> listaMateriales() {
		return GRAFO_MATERIAL.listaVertices();
	}

	@Override
	public MaterialCapacitacion findById(Integer id) {
		for(MaterialCapacitacion mat : GRAFO_MATERIAL.listaVertices()) {
			if(mat.getId().equals(id)) return mat;
		}
		return null;
	}

	@Override
	public List<MaterialCapacitacion> buscarCamino(Integer idOrigen, Integer idDestino, Integer saltos) {
		MaterialCapacitacion n1 = this.findById(idOrigen);
		MaterialCapacitacion n2 = this.findById(idDestino);
		List<MaterialCapacitacion> aux = GRAFO_MATERIAL.buscarCaminoNSaltos(n1, n2, saltos);
		if(aux!=null) return aux;
		return null;
	}

	@Override
	public void crearCamino(Integer idOrigen, Integer idDestino) {
		MaterialCapacitacion n1 = this.findById(idOrigen);
		MaterialCapacitacion n2 = this.findById(idDestino);
		GRAFO_MATERIAL.conectar(n1, n2);
		List<String> fila = new ArrayList<>();
		fila.add(n1.getId()+"");
		fila.add(n1.getTitulo());
		fila.add(n2.getId()+"");
		fila.add(n2.getTitulo());
		try {
			dataSource.agregarFilaAlFinal("aristas.csv", fila);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void eliminarLibro(Libro mat) {
		GRAFO_MATERIAL.eliminarNodo(mat);
		actualizarArchivoLibros();
		
	}
	
	@Override
	public void eliminarVideo(Video mat) {
		GRAFO_MATERIAL.eliminarNodo(mat);
		actualizarArchivoVideos();
		
	}

	@Override
	public void editarLibro(Integer id, Libro mat) {
		Libro anterior = (Libro)findById(id);
		GRAFO_MATERIAL.reemplazarNodo(anterior, mat);
		actualizarArchivoLibros();
	}

	@Override
	public void editarVideo(Integer id, Video mat) {
		Video anterior = (Video)findById(id);
		GRAFO_MATERIAL.reemplazarNodo(anterior, mat);
		actualizarArchivoVideos();		
	}

	private void actualizarArchivoLibros() {
		File archivoLibros = new File("libros.csv");
		archivoLibros.delete();
		for(Libro l: listaLibros()) {
			try {
				dataSource.agregarFilaAlFinal("libros.csv", l);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void actualizarArchivoVideos() {
		File archivoLibros = new File("videos.csv");
		archivoLibros.delete();
		for(Video l: listaVideos()) {
			try {
				dataSource.agregarFilaAlFinal("videos.csv", l);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public boolean existeArista(Integer idOrigen, Integer idDestino) {
		return GRAFO_MATERIAL.esAdyacente(this.findById(idOrigen),this.findById(idDestino) );
		
	}
	
	/*
	 * calcula el PR de cada material
	 */
	private Double calcularPR(MaterialCapacitacion mat) {
		Double resul = 1.0,sumatoria=0.0;
		if(!GRAFO_MATERIAL.getReferentes(mat).isEmpty()) {
			for(MaterialCapacitacion matRef : GRAFO_MATERIAL.getReferentes(mat)) {
				sumatoria = sumatoria + (double) (matRef.getPR()/GRAFO_MATERIAL.gradoSalida(matRef));
			}
			resul = 0.5+ (0.5 * sumatoria);
		}
		return resul;
	}

	/*
	 * Calcula el PR para el orden de la lista
	 */
	@Override
	public void setAllPR(List<MaterialCapacitacion> materiales) {

		ArrayList<Double> actualesPR = new ArrayList<Double>();
		Integer diferencia=0;
		while(diferencia<15) {
			for(MaterialCapacitacion mat: materiales) {
				actualesPR.add(this.calcularPR(mat));
			}
			for(int i=0; i<materiales.size(); i++) {
				materiales.get(i).setPR(actualesPR.get(i));
			}
			actualesPR.clear();
			diferencia++;
		}
	}

	public List<MaterialCapacitacion> actualizarPR(List<MaterialCapacitacion> materiales) {
		this.setAllPR(materiales);
		return materiales;
	}
	
}

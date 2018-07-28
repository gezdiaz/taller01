package frsf.isi.died.tp.app.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.PriorityQueue;

import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Relevancia;
import frsf.isi.died.tp.modelo.productos.Video;

public class ListaDeseosController {

	PriorityQueue<MaterialCapacitacion> listaDeseos;
	
	public ListaDeseosController() {
		//TODO debería buscar la lista de deseos en el almacenamiento
		listaDeseos = new PriorityQueue<MaterialCapacitacion>(10,((m1,m2) -> comparador(m1,m2)));
//		listaDeseos.addAll(DAO.getListaDeseos());
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
			listaDeseos.add(new Libro(4, "C", 30.0, 16.0, 250, formatoFecha.parse("10/02/2010"), Relevancia.ALTA));
			listaDeseos.add(new Libro(5, "hola", 24.0, 32.0, 545, formatoFecha.parse("05/02/2018"), Relevancia.BAJA));
			listaDeseos.add(new Libro(6, "Libro6", 28.0, 54.0, 302, formatoFecha.parse("05/12/2010"), Relevancia.MEDIA));
			listaDeseos.add(new Video(7, "El escape del paralítico", 28.0, 360, formatoFecha.parse("15/12/2000"),Relevancia.MEDIA));
			listaDeseos.add(new Video(8, "El regreso", 15.0, 625, formatoFecha.parse("25/12/2010"), Relevancia.BAJA));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static int comparador(MaterialCapacitacion mat1, MaterialCapacitacion mat2) {
		int res = 0;
		
		res = mat1.getRelevancia().compareTo(mat2.getRelevancia());
		if(res==0) {
			res = mat1.getCalificacion().compareTo(mat2.getCalificacion());
			if(res==0) {
				res = mat1.precio().compareTo(mat2.precio());
			}
		}
		
		return res;
	}
	
	public void agregar(MaterialCapacitacion mat) {
		listaDeseos.add(mat);
		System.out.println("Se agregó el material: "+mat);
		//TODO tiene que guaradar el nuevo material en la lista del almacenamiento
	}
	
	public ArrayList<MaterialCapacitacion> getLista(){
		ArrayList<MaterialCapacitacion> lista = new ArrayList<MaterialCapacitacion>();
		Integer tam = listaDeseos.size();
		
		for(int i=0; i<tam; i++) {
			lista.add(listaDeseos.poll());
		}
		listaDeseos.addAll(lista);		
		
		return lista;
	}
	
}

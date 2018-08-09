package frsf.isi.died.tp.app.controller;

import java.util.ArrayList;
import java.util.PriorityQueue;

import frsf.isi.died.tp.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class ListaDeseosController {

	PriorityQueue<MaterialCapacitacion> listaDeseos;
	
	public ListaDeseosController(MaterialCapacitacionDao dao) {
		//TODO debería buscar la lista de deseos en el almacenamiento
		listaDeseos = new PriorityQueue<MaterialCapacitacion>(10,((m1,m2) -> comparador(m1,m2)));

		
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

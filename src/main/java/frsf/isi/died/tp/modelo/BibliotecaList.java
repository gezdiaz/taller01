package frsf.isi.died.tp.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import frsf.isi.died.tp.modelo.productos.*;

public class BibliotecaList implements Biblioteca{

	private ArrayList<MaterialCapacitacion> materiales;
	
	public BibliotecaList() {
		// TODO Auto-generated constructor stub
		this.materiales = new ArrayList<MaterialCapacitacion>();
	}
	
	public void agregar(MaterialCapacitacion mat) {
		materiales.add(mat);
	}
	
	public Integer cantidadMateriales() {
		return materiales.size();
	}
	
	public Integer cantidadLibros() {
		Integer cant = 0;
		for(MaterialCapacitacion m: materiales) {
			if(m instanceof Libro) 
				cant++;
		}
		return cant;
	}
	
	public Integer cantidadVideos() {
		Integer cant = 0;
		for(MaterialCapacitacion m: materiales) {
			if(m instanceof Video) 
				cant++;
		}
		return cant;
	}

	@Override
	public Collection<MaterialCapacitacion> materiales() {
		return this.materiales;
	}

	@Override
	public void imprimir() {
		for(MaterialCapacitacion m: materiales) 
			System.out.println(m.toString());
	}

	@Override
	public void ordenarPorPrecio(Boolean b) {
		
		if(b) {
			Collections.sort(materiales, (m1, m2) -> m1.precio().compareTo(m2.precio()));
		}else {
			Collections.sort(materiales);
		}
		
	}
	
	
}

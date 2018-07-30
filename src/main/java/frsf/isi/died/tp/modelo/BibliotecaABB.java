package frsf.isi.died.tp.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import frsf.isi.died.tp.estructuras.Arbol;
import frsf.isi.died.tp.estructuras.ArbolBinarioBusqueda;
import frsf.isi.died.tp.estructuras.ArbolVacio;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class BibliotecaABB implements Biblioteca {

	private Arbol materiales;
	private Boolean flagOrdenarPorPrecio;
	private Comparator<MaterialCapacitacion> comparaTitulo= (mc1,mc2)-> mc1.getTitulo().compareTo(mc2.getTitulo());
	private Comparator<MaterialCapacitacion> comparaPrecio= (mc1,mc2)-> mc1.precio().intValue()- mc2.precio().intValue();
	
	public BibliotecaABB() {
		this.materiales = new ArbolVacio();
		flagOrdenarPorPrecio= false;
	}
	
	@Override
	public void agregar(MaterialCapacitacion material) {
		if(this.materiales.esVacio()) this.materiales = new ArbolBinarioBusqueda(material, comparaTitulo);
		else{
			if(materiales.tamanio()<10)this.materiales.add(material);
		}
	}

	@Override
	public Integer cantidadMateriales() {
		return materiales.tamanio();
	}

	@Override
	public Integer cantidadLibros() {
		return materiales.tamanioLibros();
	}

	@Override
	public Integer cantidadVideos() {
		return materiales.tamanioVideos();
	}

	@Override
	public Collection<MaterialCapacitacion> materiales() {
		// TODO RETORNAR LA LISTA DEL ARBOL ORDENADA ASCENDENTEMENTE		
		return materiales.inOrden();		
	}

	@Override
	public void imprimir() {
		materiales.imprimir();
	}

	@Override
	public void ordenarPorPrecio(Boolean b) {
		if((flagOrdenarPorPrecio && b) || (!flagOrdenarPorPrecio && !b ) ) {
			// no hago nada porque ya estaba ordenando por precio
			// y me pide que ordene por precio por lo tanto retorno
			return;
		}

		if(flagOrdenarPorPrecio && !b) {
			this.flagOrdenarPorPrecio = false;
			this.ordenarPorTitulo();
		}
		if(!flagOrdenarPorPrecio && b) {
			this.flagOrdenarPorPrecio= true;
			this.ordenarPorPrecio();
		}		
	}

	@Override
	public MaterialCapacitacion buscar(Integer precio) {
		// TODO Auto-generated method stub
		if(!flagOrdenarPorPrecio) this.ordenarPorPrecio(true);
		return this.materiales.buscar(precio);		
	}
	
	public Collection<MaterialCapacitacion> rango(Double costoMinimo,Double costoMax){
		if(!flagOrdenarPorPrecio) this.ordenarPorPrecio(true); 				
		return this.materiales.rango(costoMinimo, costoMax);
	}
	
	public void ordenarPorPrecio() {
		// Creo un nuevo arbol que ordena comparando por PRECIO.
		// Obtengo la lista del �rbol acutal.
		// Paso cada elemento de la lista al nuevo �rbol.
		// ahora el nuevo �rbol cuando lo recorra ordenado, mostrar� los 
		// datos ordenados por PRECIO la pr�xima vez que se invoque en 
		// BibliotecaABB el m�todo imprimir() o materiales()
		if(materiales.esVacio()) return; //Si no hay materiales no tengo que ordenar nada
		List<MaterialCapacitacion> lista = materiales.inOrden();
		Arbol nuevoArbol = new ArbolBinarioBusqueda((m1,m2)->m1.precio().compareTo(m2.precio()));
		for(MaterialCapacitacion m: lista) {
			nuevoArbol.add(m);
		}
		materiales = nuevoArbol;
		
	}
	
	public void ordenarPorTitulo() {
		// Creo un nuevo arbol que ordena comparando por titulo.
		// Obtengo la lista del �rbol acutal.
		// Paso cada elemento de la lista al nuevo �rbol.
		// ahora el nuevo �rbol cuando lo recorra ordenado, mostrar� los 
		// datos ordenados por titulo la pr�xima vez que se invoque en 
		// BibliotecaABB el m�todo imprimir() o materiales()
		if(materiales.esVacio()) return; //Si no hay materiales no tengo que ordenar nada
		List<MaterialCapacitacion> lista = materiales.inOrden();
		Arbol nuevoArbol = new ArbolBinarioBusqueda((m1,m2)->m1.getTitulo().compareTo(m2.getTitulo()));
		for(MaterialCapacitacion m: lista) {
			nuevoArbol.add(m);
		}
		materiales = nuevoArbol;
	}
	
	public void ordenarPorFecha() {
		if(materiales.esVacio()) return; //Si no hay materiales no tengo que ordenar nada
		List<MaterialCapacitacion> lista = materiales.inOrden();
		Arbol nuevoArbol = new ArbolBinarioBusqueda((m1,m2)->m1.getFechaPublicacion().compareTo(m2.getFechaPublicacion()));
		for(MaterialCapacitacion m: lista) {
			nuevoArbol.add(m);
		}
		materiales = nuevoArbol;
	}
	
	public void ordenarPorCalificacion() {	
		if(materiales.esVacio()) return; //Si no hay materiales no tengo que ordenar nada
		List<MaterialCapacitacion> lista = materiales.inOrden();
		Arbol nuevoArbol = new ArbolBinarioBusqueda((m1,m2)->m1.getCalificacion().compareTo(m2.getCalificacion()));
		for(MaterialCapacitacion m: lista) {
			nuevoArbol.add(m);
		}
		materiales = nuevoArbol;
	}
	
	public void ordenarPorRelevancia() {
		if(materiales.esVacio()) return; //Si no hay materiales no tengo que ordenar nada
		List<MaterialCapacitacion> lista = materiales.inOrden();
		Arbol nuevoArbol = new ArbolBinarioBusqueda((m1,m2)->m1.getRelevancia().compareTo(m2.getRelevancia()));
		for(MaterialCapacitacion m: lista) {
			nuevoArbol.add(m);
		}
		materiales = nuevoArbol;
	}

	public void agregar(ArrayList<MaterialCapacitacion> materiales) {
		for(MaterialCapacitacion material: materiales) {
			this.agregar(material);
		}		
	}
	
	public ArrayList<MaterialCapacitacion> buscar(String titulo, Integer califMenor, Integer califMayor,String tema, Date fechaMenor, Date fechaMayor){
		ArrayList<MaterialCapacitacion> lista = (ArrayList<MaterialCapacitacion>) this.materiales.inOrden();
		
		if(titulo != null) {
			lista.removeIf(mat -> !mat.getTitulo().toLowerCase().contains(titulo.toLowerCase()));
		}
		if(califMenor != null && califMayor != null) {
			lista.removeIf(mat -> mat.getCalificacion() < califMenor || mat.getCalificacion() > califMayor);
		}
		if(tema != null) {
			lista.removeIf(mat -> !mat.getTema().equalsIgnoreCase(tema.toLowerCase()));
		}
		if(fechaMenor != null && fechaMayor != null) {
			lista.removeIf(mat -> mat.getFechaPublicacion().getTime() < fechaMenor.getTime() || mat.getFechaPublicacion().getTime() > fechaMayor.getTime());
		}
		
		return lista;
	}

}

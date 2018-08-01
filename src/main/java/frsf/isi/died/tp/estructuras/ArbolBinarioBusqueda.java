package frsf.isi.died.tp.estructuras;

import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ArbolBinarioBusqueda extends ArbolBinario {

	private MaterialCapacitacion valor;
	private ArbolBinario izquierdo;
	private ArbolBinario derecho;
	private Comparator<MaterialCapacitacion> comparador;

	public ArbolBinarioBusqueda(Comparator<MaterialCapacitacion> comparardor) {
		this(null,comparardor);
	}
	

	public ArbolBinarioBusqueda(MaterialCapacitacion e,Comparator<MaterialCapacitacion> comparardor) {
		this.izquierdo = new ArbolVacio();
		this.derecho = new ArbolVacio();
		this.valor = e;
		this.comparador = comparardor;
	}

	@Override
	public boolean esVacio() {
		return false;
	}

	@Override
	public ArbolBinario izquierdo() {
		return this.izquierdo;
	}

	@Override
	public ArbolBinario derecho() {
		return this.derecho;
	}

	@Override
	public boolean contiene(MaterialCapacitacion unValor) {
		if (this.valor.equals(unValor))
			return true;
		return this.izquierdo.contiene(unValor) || this.derecho.contiene(unValor);
	}

	@Override
	public boolean equals(ArbolBinario unArbol) {
		return unArbol instanceof ArbolBinarioBusqueda && this.valor.equals(((ArbolBinarioBusqueda) unArbol).valor)
				&& this.izquierdo.equals(unArbol.izquierdo()) && this.derecho.equals(unArbol.derecho());
	}

	@Override
	public Integer profundidad() {		
		if(this.valor==null) return 0;
		return 1 + Math.max(this.izquierdo.profundidad(), this.derecho.profundidad());
	}

	@Override
	public void add(MaterialCapacitacion mat) {
		if (this.valor == null) {
			this.valor = mat;
			return;
		} else {
			if (this.comparador.compare(mat, this.valor) > 0) {
				if (this.derecho.esVacio())
					this.derecho = new ArbolBinarioBusqueda(mat,this.comparador);
				else
					this.derecho.add(mat);
			} else {
				if (this.izquierdo.esVacio())
					this.izquierdo = new ArbolBinarioBusqueda(mat,this.comparador);
				else
					this.izquierdo.add(mat);
			}
		}
	}
	
	@Override
	public void imprimir() {
		this.inOrden().stream().forEach(System.out::println);
	}

	@Override
	public Integer tamanio() {
		return this.izquierdo().tamanio()+this.derecho().tamanio()+1;
	}

	@Override
	public Integer tamanioLibros() {
		if(valor.esLibro())
			return this.izquierdo().tamanioLibros()+this.derecho().tamanioLibros()+1;
		else
			return this.izquierdo().tamanioLibros()+this.derecho().tamanioLibros();
	}

	@Override
	public Integer tamanioVideos() {
		if(valor.esVideo())
			return this.izquierdo().tamanioVideos()+this.derecho().tamanioVideos()+1;
		else
			return this.izquierdo().tamanioVideos()+this.derecho().tamanioVideos();
	}

	@Override
	public List<MaterialCapacitacion> preOrden() {
		List<MaterialCapacitacion> lista = new ArrayList<MaterialCapacitacion>();
		lista.add(valor);
		lista.addAll(this.izquierdo().preOrden());
		lista.addAll(this.derecho().preOrden());
		
		return lista;
	}

	@Override
	public List<MaterialCapacitacion> inOrden() {
		List<MaterialCapacitacion> lista = new ArrayList<MaterialCapacitacion>();
		lista.addAll(this.izquierdo().inOrden());
		lista.add(valor);
		lista.addAll(this.derecho().inOrden());

		return lista;
	}

	@Override
	public List<MaterialCapacitacion> postOrden() {
		List<MaterialCapacitacion> lista = new ArrayList<MaterialCapacitacion>();
		lista.addAll(this.izquierdo().postOrden());
		lista.addAll(this.derecho().postOrden());
		lista.add(valor);

		return lista;
	}
	
	@Override
	public MaterialCapacitacion buscar(Integer precioBuscado) {
		int comparacion =this.valor.precio().intValue() - precioBuscado.intValue(); 
		
		if(comparacion == 0)
			return this.valor;
		else 
			if(comparacion < 0)
				return this.derecho().buscar(precioBuscado);
			else
				return this.izquierdo().buscar(precioBuscado);
	}

	@Override
	public List<MaterialCapacitacion> rango(Double precioMin, Double precioMax) {
		List<MaterialCapacitacion> lista = new ArrayList<MaterialCapacitacion>();
		int comparacionPrecioMin =this.valor.precio().compareTo(precioMin);
		int comparacionPrecioMax =this.valor.precio().compareTo(precioMax);
	
		if(comparacionPrecioMax <= 0 && comparacionPrecioMin >= 0) {
			lista.addAll(this.izquierdo().rango(precioMin, precioMax));
			lista.add(valor);
			lista.addAll(this.derecho().rango(precioMin, precioMax));
		}else {
			if(comparacionPrecioMax > 0) 
				lista.addAll(this.izquierdo().rango(precioMin, precioMax));
			else
				lista.addAll(this.derecho().rango(precioMin, precioMax));
		}
		
		return lista;
	}


}

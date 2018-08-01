package frsf.isi.died.tp.estructuras;

import java.util.List;

public class ArbolNArio<T> extends Arbol<T> {

	private NodoNario<T> nodo;
	
	public ArbolNArio(T valor, TipoNodo tipo) {
		this.nodo = new NodoNario<T>(valor, tipo);
	}
	
	public T getValor() {
		return nodo.getValor();
	}

	@Override
	public Boolean esVacio() {
		return false;
	}

	@Override
	public List<ArbolNArio<T>> getHijos() {
		// TODO Auto-generated method stub
		return nodo.getHijos();
	}
	
}

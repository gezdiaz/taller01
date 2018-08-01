package frsf.isi.died.tp.estructuras;

import java.util.List;

public abstract class Arbol<T> {

	
	
	public abstract Boolean esVacio();
	public abstract List<ArbolNArio<T>> getHijos();
	
}

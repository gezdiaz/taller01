package frsf.isi.died.tp.estructuras;

import java.util.ArrayList;
import java.util.List;

public class ArbolNVacio<T> extends Arbol<T> {

	@Override
	public Boolean esVacio() {
		return true;
	}

	@Override
	public List<ArbolNArio<T>> getHijos() {
		// TODO Auto-generated method stub
		return new ArrayList<ArbolNArio<T>>();
	}

}

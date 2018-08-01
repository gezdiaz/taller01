package frsf.isi.died.tp.estructuras;

import java.util.List;

public class NodoNario<T> {
	
	private T valor;
	private TipoNodo tipo;
	private List<ArbolNArio<T>> hijos;
	
	public NodoNario(T valor, TipoNodo tipo) {
		this.valor = valor;
		this.tipo = tipo;
	}

	public T getValor() {
		return valor;
	}

	public void setValor(T valor) {
		this.valor = valor;
	}

	public TipoNodo getTipo() {
		return tipo;
	}

	public void setTipo(TipoNodo tipo) {
		this.tipo = tipo;
	}
	
	public List<ArbolNArio<T>> getHijos(){
		return hijos;
	}
}

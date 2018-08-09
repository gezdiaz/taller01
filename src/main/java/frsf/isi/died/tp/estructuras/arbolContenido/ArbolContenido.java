package frsf.isi.died.tp.estructuras.arbolContenido;

import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.ARG_IN;

public class ArbolContenido {

	private String valor;
	private TipoNodo tipo;
	private List<ArbolContenido> hijos;
	
	public ArbolContenido(String valor, TipoNodo tipo) {
		this.valor = valor;
		this.tipo = tipo;
		this.hijos = new ArrayList<ArbolContenido>();
	}	
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public TipoNodo getTipo() {
		return tipo;
	}
	public void setTipo(TipoNodo tipo) {
		this.tipo = tipo;
	}
	public void addHijo(ArbolContenido hijo) {
		this.hijos.add(hijo);
	}
	public List<ArbolContenido> getHijos() {
		return hijos;
	}
	@Override
	public String toString() {
		return tipo.toString()+": "+valor;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ArbolContenido other = (ArbolContenido) obj;
		if (tipo != other.tipo)
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}
	public void eliminarNodo(ArbolContenido nodo) {
		if(hijos.contains(nodo)) {
			hijos.remove(nodo);
		}else {
			for(ArbolContenido h: hijos) {
				h.eliminarNodo(nodo);
			}
		}
	}
	public void imprimirArbol(String tab) {
		System.out.println(tab+this.toString());
		for(ArbolContenido h: hijos) {
			h.imprimirArbol(tab+"\t");
		}
	}
	public Integer cantHijosDirectosTipo(TipoNodo t) {
		Integer cant = 0;
		for(ArbolContenido h: hijos) {
			if(h.getTipo().equals(t)) {
				cant++;
			}
		}
		return cant;
	}
	public Boolean contieneNodo(String valor, TipoNodo tipo) {
		Boolean contiene = false;
		
		if(this.valor.equals(valor) && this.tipo.equals(tipo)) {
			contiene = true;
		}else {
			for(ArbolContenido h: this.hijos) {
				if(h.contieneNodo(valor, tipo)) {
					contiene = true;
					break;
				}
			}
		}
		
		return contiene;
	}
	public Boolean contieneTodos(List<ArbolContenido> nodos) {
		Boolean res = true;
		
		for(ArbolContenido n: nodos) {
			if(!this.contieneNodo(n.getValor(), n.getTipo())) {
				res=false;
			}
		}
		
		return res;
	}
	
}

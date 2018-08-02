package frsf.isi.died.tp.estructuras.arbolContenido;

import java.util.ArrayList;
import java.util.List;

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
	
	
	
	
}

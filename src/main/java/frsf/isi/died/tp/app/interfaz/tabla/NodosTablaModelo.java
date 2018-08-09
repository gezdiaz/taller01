package frsf.isi.died.tp.app.interfaz.tabla;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import frsf.isi.died.tp.estructuras.arbolContenido.*;

public class NodosTablaModelo extends AbstractTableModel {

	private List<ArbolContenido> nodos;
	private String[] columnas = {"Valor", "Tipo contenido"};
	
	
	public List<ArbolContenido> getNodos(){
		return nodos;
	}
	public void setNodos(List<ArbolContenido> nodos) {
		this.nodos = nodos;
	}
	public void agregarNodo(ArbolContenido nodo) {
		nodos.add(nodo);
	}
	
	@Override
	public String getColumnName(int indice) {
		return this.columnas[indice];
	}
	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public int getRowCount() {
		return nodos.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object valor = null;
		switch (columnIndex) {
		case 0:
			valor = nodos.get(rowIndex).getValor();
			break;
		case 1:
			valor = nodos.get(rowIndex).getTipo();
			break;
		default:
			System.out.println("Indice fuera de rango");
			valor = "S/D";
			break;
		}
		
		return valor;
	}

}

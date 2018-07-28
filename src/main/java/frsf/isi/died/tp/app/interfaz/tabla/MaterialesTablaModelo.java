package frsf.isi.died.tp.app.interfaz.tabla;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import frsf.isi.died.tp.modelo.productos.*;

public class MaterialesTablaModelo extends AbstractTableModel {

	private List<MaterialCapacitacion> materiales;
	private String[] columnas = {"ID","Tipo","Título","Costo publicación","Precio suscripción",
			"Fecha publicación","Calificación","Relevancia"};

	@Override
	public String getColumnName(int indice) {
		return this.columnas[indice];
	}
	
	public List<MaterialCapacitacion> getLibros() {
		return materiales;
	}

	public void setMateriales(List<MaterialCapacitacion> materiales) {
		this.materiales = materiales;
	}
	
	public List<MaterialCapacitacion> getMateriales(){
		return this.materiales;
	}

	@Override
	public int getRowCount() {
		return materiales.size();
	}

	@Override
	public int getColumnCount() {
		return columnas.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object valor = null;
		switch (columnIndex) {
		case 0:
			valor = this.materiales.get(rowIndex).getId();
			break;
		case 1:
			valor = this.materiales.get(rowIndex).esLibro()?"Libro":"Video";
			break;
		case 2:
			valor = this.materiales.get(rowIndex).getTitulo();
			break;
		case 3:
			valor = this.materiales.get(rowIndex).getCosto();
			break;
		case 4:
			valor = this.materiales.get(rowIndex).precio();
			break;
		case 5:
			valor = (new SimpleDateFormat("dd/MM/yyyy")).format(this.materiales.get(rowIndex).getFechaPublicacion());
			break;
		case 6:
			valor = this.materiales.get(rowIndex).getCalificacion();
			break;
		case 7:
			valor = this.materiales.get(rowIndex).getRelevancia();
			break;
		default:
			System.out.println("Indice fuera de rango");
			valor = "S/D";
			break;
		}
		return valor;
	}
}

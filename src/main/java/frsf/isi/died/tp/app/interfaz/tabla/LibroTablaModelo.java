package frsf.isi.died.tp.app.interfaz.tabla;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import frsf.isi.died.tp.modelo.productos.Libro;

public class LibroTablaModelo extends AbstractTableModel {


	private List<Libro> libros;
	private String[] columnas = {"ID","Titulo","Precio Compra","Costo publicacion",
			"Paginas","Precio Suscripción","Fecha publicación","Calificación","Relevancia"};
	
	
	@Override
	public String getColumnName(int indice) {
		return this.columnas[indice];
	}
	
	public List<Libro> getLibros() {
		return libros;
	}

	public void setLibros(List<Libro> libros) {
		this.libros = libros;
	}

	@Override
	public int getRowCount() {
		return libros.size();
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
			valor = this.libros.get(rowIndex).getId();
			break;
		case 1:
			valor = this.libros.get(rowIndex).getTitulo();
			break;
		case 2:
			valor = this.libros.get(rowIndex).getPrecioCompra();
			break;
		case 3:
			valor = this.libros.get(rowIndex).getCosto();
			break;
		case 4:
			valor = this.libros.get(rowIndex).getPaginas();
			break;
		case 5:
			valor = this.libros.get(rowIndex).precio();
			break;
		case 6:
			valor = this.libros.get(rowIndex).getFechaPublicacion();
			break;
		case 7:
			valor = this.libros.get(rowIndex).getCalificacion();
			break;
		case 8:
			valor = this.libros.get(rowIndex).getRelevancia();
			break;
		default:
			System.out.println("Indice fuera de rango");
			valor = "S/D";
			break;
		}
		return valor;
	}
}

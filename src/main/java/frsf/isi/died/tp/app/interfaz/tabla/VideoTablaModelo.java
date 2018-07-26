package frsf.isi.died.tp.app.interfaz.tabla;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import frsf.isi.died.tp.modelo.productos.Video;

public class VideoTablaModelo extends AbstractTableModel {


	private List<Video> videos;
	private String[] columnas = {"ID","Titulo","Costo publicacion","Duración",
			"Fecha publicación","Calificación","Relevancia"};
	
	
	@Override
	public String getColumnName(int indice) {
		return this.columnas[indice];
	}
	
	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> Videos) {
		this.videos = videos;
	}

	@Override
	public int getRowCount() {
		return videos.size();
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
			valor = this.videos.get(rowIndex).getId();
			break;
		case 1:
			valor = this.videos.get(rowIndex).getTitulo();
			break;
		case 2:
			valor = this.videos.get(rowIndex).getCosto();
			break;
		case 3:
			valor = this.videos.get(rowIndex).getDuracion();
			break;
		case 4:
			valor = this.videos.get(rowIndex).getFechaPublicacion();
			break;
		case 5:
			valor = this.videos.get(rowIndex).getCalificacion();
			break;
		case 6:
			valor = this.videos.get(rowIndex).getRelevancia();
			break;
		default:
			System.out.println("Indice fuera de rango");
			valor = "S/D";
			break;
		}
		return valor;
	}
}

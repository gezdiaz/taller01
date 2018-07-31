package frsf.isi.died.tp.app.controller;

import java.util.List;

import frsf.isi.died.tp.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.tp.modelo.productos.*;

public class RelacionesController {
	private List<MaterialCapacitacion> materiales;
	private List<MaterialCapacitacion> matMismoTema;
	
	public RelacionesController(MaterialCapacitacionDao dao) {
		this.materiales = dao.listaMateriales();
	}
	
	public void setListaTema(String tema){
		List<MaterialCapacitacion> filtrados = this.materiales;
		filtrados.removeIf(mat -> !mat.getTema().equalsIgnoreCase(tema));
		matMismoTema=filtrados;
	}

	public List<MaterialCapacitacion> getMatMismoTema() {
		return matMismoTema;
	}


}

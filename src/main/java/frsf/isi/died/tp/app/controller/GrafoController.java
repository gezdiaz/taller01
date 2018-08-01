package frsf.isi.died.tp.app.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import frsf.isi.died.tp.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.tp.app.dao.MaterialCapacitacionDaoDefault;
import frsf.isi.died.tp.app.interfaz.grafo.AristaView;
import frsf.isi.died.tp.app.interfaz.grafo.ControlPanel;
import frsf.isi.died.tp.app.interfaz.grafo.GrafoPanel;
import frsf.isi.died.tp.app.interfaz.grafo.VerticeView;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

public class GrafoController {

	private GrafoPanel vistaGrafo;
	private ControlPanel vistaControl;
	private MaterialCapacitacionDao dao;
	private List<MaterialCapacitacion> matMismoTema;
	private List<VerticeView> verticesDibujados;
	
	public void setListaTema(String tema){
		List<MaterialCapacitacion> filtrados = this.dao.listaMateriales();
		filtrados.removeIf(mat -> !mat.getTema().equalsIgnoreCase(tema));
		matMismoTema=filtrados;
	}
	
	public List<MaterialCapacitacion> getMatMismoTema() {
		return matMismoTema;
	}

	public GrafoController(GrafoPanel panelGrf, ControlPanel panelCtrl) {
		this.vistaGrafo = panelGrf;
		this.dao = new MaterialCapacitacionDaoDefault();
		this.vistaGrafo.setController(this);
		this.vistaControl = panelCtrl;
		this.vistaControl.setController(this);
		this.vistaControl.armarPanelBuscarCaminos(dao.listaMateriales());
		this.verticesDibujados = new ArrayList<VerticeView>();
	}

	public void crearVertice(Integer coordenadaX, Integer coordenadaY, Color color, MaterialCapacitacion mc) {
		VerticeView v = new VerticeView(coordenadaX, coordenadaY, color);
		v.setId(mc.getId());
		v.setNombre(mc.getTitulo());
		this.verticesDibujados.add(v);
		this.vistaGrafo.agregar(v);
		this.vistaGrafo.repaint();
		this.matMismoTema.remove(mc);
	}

	public void crearArista(AristaView arista) {
		this.dao.crearCamino(arista.getOrigen().getId(), arista.getDestino().getId());
		this.vistaGrafo.agregar(arista);
		this.vistaGrafo.repaint();
	}
	
	public void dibujarAristaExistente(AristaView arista) {
		this.vistaGrafo.agregar(arista);
		this.vistaGrafo.repaint();
	}

	public void buscarCamino(Integer nodo1, Integer nodo2, Integer saltos) {
		List<MaterialCapacitacion> camino = this.dao.buscarCamino(nodo1, nodo2, saltos);
		this.vistaGrafo.caminoPintar(camino);
		this.vistaGrafo.repaint();
	}


	public List<MaterialCapacitacion> listaVertices() {
		return dao.listaMateriales();
	}

	public MaterialCapacitacion getMaterial(Integer id) {
		return dao.findById(id);
	}
	
	public boolean existeArista(Integer idOrigen, Integer idDestino) {
		return dao.existeArista(idOrigen, idDestino);
	}

	public VerticeView buscarVertice(MaterialCapacitacion mc) {
		if(!this.verticesDibujados.isEmpty()) {
			for(VerticeView v : this.verticesDibujados) {
				if(v.getId().equals(mc.getId())) return v;
			}
		}
		return null;
	}

	public List<MaterialCapacitacion> ordenadosPR() {
		return dao.ordenarPR(this.matMismoTema);
		
	}
}

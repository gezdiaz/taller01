package frsf.isi.died.tp.app.controller;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

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


	public GrafoController(GrafoPanel panelGrf, ControlPanel panelCtrl) {
		this.vistaGrafo = panelGrf;
		this.dao = new MaterialCapacitacionDaoDefault();
		this.vistaGrafo.setController(this);
		this.vistaControl = panelCtrl;
		this.vistaControl.setController(this);
		this.vistaControl.armarPanelBuscarCaminos(dao.listaMateriales());
		this.verticesDibujados = new ArrayList<VerticeView>();
	}
	
	public void setListaTema(String tema){
		List<MaterialCapacitacion> filtrados = this.dao.listaMateriales();
		filtrados.removeIf(mat -> !mat.getTema().equalsIgnoreCase(tema));
		matMismoTema=filtrados;
	}
	
	public List<MaterialCapacitacion> getMatMismoTema() {
		return matMismoTema;
	}

	public void crearVertice(Integer coordenadaX, Integer coordenadaY, Color color, MaterialCapacitacion mc) {
		VerticeView v = new VerticeView(coordenadaX, coordenadaY, color);
		v.setId(mc.getId());
		v.setNombre(mc.getTitulo());
		this.verticesDibujados.add(v);
		this.vistaGrafo.agregar(v);
		this.vistaGrafo.repaint();
//		this.matMismoTema.remove(mc);
	}

	public void crearArista(AristaView arista) {
		if(!dao.existeArista(arista.getOrigen().getId(), arista.getDestino().getId())) {
			if(arista.getOrigen().equals(arista.getDestino())) {
				System.out.println("Bucle.");
				return;
			}
			this.dao.crearCamino(arista.getOrigen().getId(), arista.getDestino().getId());
		}
		this.vistaGrafo.agregar(arista);
		this.vistaGrafo.repaint();
	}
	
	public void buscarCamino(Integer nodo1, Integer nodo2, Integer saltos) {
		List<MaterialCapacitacion> camino = this.dao.buscarCamino(nodo1, nodo2, saltos);
		if(camino!=null) {
			this.vistaGrafo.caminoPintar(camino);
			this.vistaGrafo.repaint();
		}else {
			JOptionPane.showConfirmDialog(this.vistaGrafo.getParent(), "No se encontro un camino de "+saltos+" saltos.", "Información", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
		}
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
	
	public boolean existeVertice(MaterialCapacitacion mc) {
		if(this.buscarVertice(mc) != null) return true;
		return false;
	}

	public VerticeView buscarVertice(MaterialCapacitacion mc) {
		if(!this.verticesDibujados.isEmpty()) {
			for(VerticeView v : this.verticesDibujados) {
				if(v.getId().equals(mc.getId())) return v;
			}
		}
		return null;
	}

	public List<MaterialCapacitacion> actualizarPR() {
		if(!this.matMismoTema.isEmpty() || this.matMismoTema.size()!=1)	return dao.actualizarPR(this.matMismoTema);
		return null;
	}
}

package frsf.isi.died.tp.app.interfaz.grafo;

import java.util.List;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import frsf.isi.died.tp.app.controller.GrafoController;
import frsf.isi.died.tp.modelo.BibliotecaABB;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

/**
 *
 * @author mdominguez
 */
public class ControlPanel extends JPanel {
    
    private JComboBox<MaterialCapacitacion> cmbVertice1; 
    private JComboBox<MaterialCapacitacion> cmbVertice2; 
    private JTextField txtLongitudCamino; 
    private JButton btnBuscarCamino; 
    private GrafoController controller;
    private List<MaterialCapacitacion> listaVertices;
        
    public void armarPanelRelaciones(MaterialCapacitacion material) {
    	
//    	BUSCO LOS QUE TIENEN EL MISMO TEMA
    	this.listaVertices = controller.listaVertices();
    	BibliotecaABB aFiltrar = new BibliotecaABB();
    	aFiltrar.agregar((ArrayList<MaterialCapacitacion>)listaVertices);
    	ArrayList<MaterialCapacitacion> filtrados = aFiltrar.buscar(null, null, null, material.getTema(), null, null);
//    	
    	
    }
    
    public void armarPanelBuscarCaminos( List<MaterialCapacitacion> listaVertices){
    	this.listaVertices = listaVertices;
    	this.cmbVertice1 = new JComboBox(listaVertices.toArray()); 
        this.cmbVertice2 = new JComboBox(listaVertices.toArray()); 
        this.txtLongitudCamino = new JTextField(5); 
        this.btnBuscarCamino = new JButton("Buscar Camino");
        this.btnBuscarCamino.addActionListener(
                e -> { 
                    Integer n =Integer.parseInt(txtLongitudCamino.getText());
                    Integer idOrigen = this.listaVertices.get(cmbVertice1.getSelectedIndex()).getId();
                    Integer idDestino= this.listaVertices.get(cmbVertice2.getSelectedIndex()).getId();
                    controller.buscarCamino(idOrigen,idDestino,n); 
                }
        );
        this.add(new JLabel("Vertice Origen"));        
    	this.add(cmbVertice1);
    	this.add(new JLabel("Vertice Destino"));
    	this.add(cmbVertice2);
    	this.add(new JLabel("Cantidad de saltos"));        
    	this.add(txtLongitudCamino);        
    	this.add(btnBuscarCamino);        
    }

    public GrafoController getController() {
        return controller;
    }

    public void setController(GrafoController controller) {
        this.controller = controller;
    }

    
}


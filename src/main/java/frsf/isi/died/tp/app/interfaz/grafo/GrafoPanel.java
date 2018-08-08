package frsf.isi.died.tp.app.interfaz.grafo;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import frsf.isi.died.tp.app.controller.GrafoController;
import frsf.isi.died.tp.app.controller.MenuGrafoController;
import frsf.isi.died.tp.app.interfaz.Principal;
import frsf.isi.died.tp.app.interfaz.tabla.MaterialesTablaModelo;
import frsf.isi.died.tp.estructuras.Arista;
import frsf.isi.died.tp.modelo.BibliotecaABB;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;

/**
 *
 * @author martdominguez
 */
public class GrafoPanel extends JPanel {

    private JFrame framePadre;
    private Queue<Color> colaColores;
    private GrafoController controller;
    private MenuGrafoController menuController;
//    private MaterialesPRController matPRController;
    private List<VerticeView> vertices;
    private List<AristaView> aristas;

    private AristaView auxiliar;

    public GrafoPanel(JFrame ventana) {
        this.framePadre = ventana;
        
        this.vertices = new ArrayList<>();
        this.aristas = new ArrayList<>();
        this.menuController = new MenuGrafoController(controller, this);
        
        this.colaColores = new LinkedList<Color>();
        this.colaColores.add(Color.RED);
        this.colaColores.add(Color.BLUE);
        this.colaColores.add(Color.ORANGE);
        this.colaColores.add(Color.CYAN);

        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent event) {
                if (event.getButton() == MouseEvent.BUTTON1 && event.getClickCount() == 2 && !event.isConsumed()) {
                    event.consume();
                    Object[] mats = controller.getMatMismoTema().toArray();
                    Object verticeMatSeleccionado;
					try {
						verticeMatSeleccionado = (MaterialCapacitacion) JOptionPane.showInputDialog(framePadre, 
						        "¿Qué material corresponde con el vertice?",
						        "Agregar Vertice",
						        JOptionPane.QUESTION_MESSAGE, 
						        null, 
						        mats, 
						        mats[0]);
						if (verticeMatSeleccionado != null & !controller.existeVertice((MaterialCapacitacion)verticeMatSeleccionado)) {
	                        Color aux = ((MaterialCapacitacion)verticeMatSeleccionado).esLibro()?Color.RED:Color.BLUE;
	                        controller.crearVertice(event.getX(), event.getY(), aux,(MaterialCapacitacion) verticeMatSeleccionado);
		                    //Confirmo que no haya una arista existente
//	                        if (auxiliar!=null) {
//	                        	if (controller.existeArista(auxiliar.getOrigen().getId(),((MaterialCapacitacion)verticeMatSeleccionado).getId())) {
//		                        	AristaView existente = new AristaView();
//		                        	existente.setOrigen(auxiliar.getOrigen());
//		                        	existente.setDestino(controller.buscarVertice((MaterialCapacitacion)verticeMatSeleccionado));
//		                        	controller.dibujarAristaExistente(existente);
//		                        	if(controller.existeArista(existente.getDestino().getId(), existente.getOrigen().getId())) {
//		                        		VerticeView auxV = existente.getDestino();
//		                        		existente.setDestino(existente.getOrigen());
//		                        		existente.setOrigen(auxV);
//		                        		controller.dibujarAristaExistente(existente);
//		                        	}
//		                        }
//		                    }
	                    }else {
	                    	JOptionPane.showConfirmDialog(ventana, "Ese material ya fue añadido", "Material existente", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
	                    }
					} catch (ArrayIndexOutOfBoundsException e) {
						JOptionPane.showConfirmDialog(ventana, "No quedan más materiales para agregar", "Sin materiales", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					}  
					System.out.println("vertices: "+vertices);
                }else {
                	if(event.getButton() == MouseEvent.BUTTON3 && event.getClickCount() == 1 && !event.isConsumed()) {
                		event.consume();
                		System.out.println("Mostrar Menu");
                		JPopupMenu menu = new JPopupMenu();
                		JMenuItem menuItem;
                		menuItem = new JMenuItem("Buscar caminos");
                		menuItem.addActionListener(a -> menuController.opcionPopUp(OpcionesPopUp.BUSCAR_CAMINO));
                		menu.add(menuItem);
                		menuItem = new JMenuItem("Materiales por PR");
                		menuItem.addActionListener(a -> menuController.opcionPopUp(OpcionesPopUp.LISTA_TEMA));
                		menu.add(menuItem);
                		menuItem = new JMenuItem("Volver al inicio");
                		menuItem.addActionListener(a -> Principal.mostrarInterfaz());
                		menu.add(menuItem);
                		menu.show(event.getComponent(), event.getX(), event.getY());
                	}                	
                }
                
            }

            public void mouseReleased(MouseEvent event) {
                VerticeView vDestino = clicEnUnNodo(event.getPoint());
                if (auxiliar!=null && vDestino != null) {
//                	if (!controller.existeArista(auxiliar.getOrigen().getId(),vDestino.getId())) {
                		auxiliar.setDestino(vDestino);
                		controller.crearArista(auxiliar);
                		auxiliar = null;
//                	}else {
//                		controller.dibujarAristaExistente(auxiliar);
//                	}
                }
            }

        });

        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent event) {
                VerticeView vOrigen = clicEnUnNodo(event.getPoint());
                if (auxiliar==null && vOrigen != null) {
                    auxiliar = new AristaView();                    
                    auxiliar.setOrigen(vOrigen);
                }
            }
        });
    }

    public void agregar(AristaView arista){
        this.aristas.add(arista);
    }    
    
    public void agregar(VerticeView vert){
        this.vertices.add(vert);
    }

    public void caminoPintar(List<MaterialCapacitacion> camino){
        //this.vertices.add(vert);
    	Integer idOrigen =-1;
    	Integer idDestino =-1;
    	for(MaterialCapacitacion mat : camino) {
    		if(idOrigen<0) {
    			idOrigen=mat.getId();
    		}else {
    			idDestino = mat.getId();
    			for(AristaView av : this.aristas) {
    				if(av.getOrigen().getId().equals(idOrigen) && av.getDestino().getId().equals(idDestino) ) {
    	    			av.setColor(Color.RED);
    	    			av.getOrigen().setColor(Color.GREEN);
    	    			av.getDestino().setColor(Color.GREEN);
    				}
    			}
    			idOrigen = idDestino;
    		}
    	}
    }
    
    private void dibujarVertices(Graphics2D g2d) {
        for (VerticeView v : this.vertices) {
//            g2d.setPaint(Color.BLUE);
            g2d.drawString(v.etiqueta(),v.getCoordenadaX()-5,v.getCoordenadaY()-5);
            g2d.setPaint(v.getColor());
            g2d.fill(v.getNodo());
        }
    }

    private void dibujarAristas(Graphics2D g2d) {
        System.out.println(this.aristas);
        for (AristaView a : this.aristas) {
            g2d.setPaint(a.getColor());
            g2d.setStroke ( a.getFormatoLinea());
            g2d.draw(a.getLinea());
            //dibujo una flecha al final
            // con el color del origen para que se note
            g2d.setPaint(Color.BLACK);
            Polygon flecha = new Polygon();  
            flecha.addPoint(a.getDestino().getCoordenadaX(), a.getDestino().getCoordenadaY()+7);
            flecha.addPoint(a.getDestino().getCoordenadaX()+20, a.getDestino().getCoordenadaY()+10);
            flecha.addPoint(a.getDestino().getCoordenadaX(), a.getDestino().getCoordenadaY()+18);
            g2d.fillPolygon(flecha);
        }
    }

    private VerticeView clicEnUnNodo(Point p) {
        for (VerticeView v : this.vertices) {
            if (v.getNodo().contains(p)) {
                return v;
            }
        }
        return null;
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        dibujarVertices(g2d);
        dibujarAristas(g2d);
    }

    public Dimension getPreferredSize() {
        return new Dimension(450, 400);
    }

    public GrafoController getController() {
        return controller;
    }
    
    public void setAuxiliarOrigen(VerticeView v) {
    	this.auxiliar = new AristaView();
    	this.auxiliar.setOrigen(v);
    }

    public void setController(GrafoController controller) {
        this.controller = controller;
    }

	public void buscarCamino() {
		JFrame popup = new JFrame("Buscar Camino");
		JPanel panel = new JPanel(new GridBagLayout());
		ArrayList<MaterialCapacitacion> materiales = new ArrayList<MaterialCapacitacion>();
		for(VerticeView ver: vertices) {
			materiales.add(controller.getMaterial(ver.getId()));
		}
		JComboBox<MaterialCapacitacion> listaInicio = new JComboBox<MaterialCapacitacion>(),
										listaFin = new JComboBox<MaterialCapacitacion>();
		for(MaterialCapacitacion mat: materiales) {
			listaInicio.addItem(mat);
			listaFin.addItem(mat);
		}
		JTextField tNumSaltos = new JTextField(5);
		JLabel label = new JLabel();
		GridBagConstraints cons = new GridBagConstraints();
		JButton aceptar = new JButton("Aceptar"), cancelar = new JButton("Cancelar");
		
		cons.insets = new Insets(5, 5, 5, 5);
		cons.gridx = 0;
		cons.gridx = 0;
		label.setText("Seleccione vertice de inicio: ");
		panel.add(label, cons);
		
		cons.gridx = 1;
		panel.add(listaInicio, cons);
		
		cons.gridx=0;
		cons.gridy=1;
		label = new JLabel("Seleccione el vertice final: ");
		panel.add(label, cons);
		
		cons.gridx=1;
		panel.add(listaFin, cons);
		
		cons.gridx=0;
		cons.gridy=2;
		label = new JLabel("Número de saltos: ");
		panel.add(label, cons);
		
		cons.gridx=1;
		panel.add(tNumSaltos, cons);
		
		cons.gridx=0;
		cons.gridy=3;
		cancelar.addActionListener(a -> popup.dispose());
		panel.add(cancelar, cons);
		
		cons.gridx=1;
		aceptar.addActionListener(a -> {
			MaterialCapacitacion inicio, fin;
			Integer saltos = 0;
			
			try {
				inicio = (MaterialCapacitacion) listaInicio.getSelectedItem();
				fin = (MaterialCapacitacion) listaFin.getSelectedItem();
				if(!tNumSaltos.getText().isEmpty()) {
					saltos = Integer.parseInt(tNumSaltos.getText());
				}
				if(saltos>0) {
					controller.buscarCamino(inicio.getId(), fin.getId(), saltos);
					popup.dispose();
				}
				else JOptionPane.showConfirmDialog(popup, "Ingrese la cantidad de saltos", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			} catch (NumberFormatException e) {
				JOptionPane.showConfirmDialog(popup, "El campo Número de saltos debe ser un número entero.", "Error", JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
			}
		});
		panel.add(aceptar, cons);
		

		popup.setContentPane(panel);
		popup.pack();
		popup.setLocationRelativeTo(framePadre);
		popup.setVisible(true);
	}
	
	public void mostrarListaTemaPR() {
		JFrame popup = new JFrame("Lista de materiales ordenados por PageRank");
		JPanel panel = new JPanel(new GridBagLayout());
		BibliotecaABB matOrdenados = new BibliotecaABB();
		ArrayList<MaterialCapacitacion> aux = (ArrayList<MaterialCapacitacion>) controller.actualizarPR();
		if(aux.isEmpty()) {
			JOptionPane.showConfirmDialog(null,"No hay materiales suficientes con ese tema!","Faltan datos",JOptionPane.DEFAULT_OPTION,JOptionPane.ERROR_MESSAGE);
		}else {
//			Ordeno los materiales
			matOrdenados.agregar(aux);
			matOrdenados.ordenarPorPR();
			
//			Armo la ventana
			MaterialesTablaModelo tableModel = new MaterialesTablaModelo();
			JTable tabla = new JTable(tableModel);
			GridBagConstraints constraints = new GridBagConstraints();
			tableModel.setMateriales((ArrayList<MaterialCapacitacion>)matOrdenados.materiales());
			tabla = new JTable(tableModel);
			tabla.setFillsViewportHeight(true);
			JScrollPane scroll = new JScrollPane(tabla);
			constraints.gridx=0;
			constraints.gridy=0;
			constraints.anchor=GridBagConstraints.CENTER;
			panel.add(scroll);
			popup.setContentPane(panel);
			popup.pack();
			popup.setLocationRelativeTo(framePadre);
			popup.setVisible(true);
		}
	}
    
    
}


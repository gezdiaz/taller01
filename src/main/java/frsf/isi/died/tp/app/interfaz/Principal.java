package frsf.isi.died.tp.app.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowEvent;

import javax.swing.*;

import frsf.isi.died.tp.app.controller.MenuController;
import frsf.isi.died.tp.app.controller.OpcionesMenu;
import frsf.isi.died.tp.app.dao.MaterialCapacitacionDao;
import frsf.isi.died.tp.app.dao.MaterialCapacitacionDaoDefault;



public class Principal {

	private static JFrame ventana;
	private static MenuController controller;
	
	public static void main(String[] args) {
		ventana = new JFrame();
		MaterialCapacitacionDao dao = new MaterialCapacitacionDaoDefault();
        controller = new MenuController(dao, ventana);
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	        	mostrarInterfaz();
	        }
	    });
	}
	
	public static void mostrarInterfaz() {
		
		JPanel panel = new JPanel();
		JLabel imagen = new JLabel(), vacio = new JLabel(), encabezado = new JLabel("    Sistema de suscripci�n a biblioteca.");
		JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        JMenu submenu;
        
//        configuracion del panel (imagen central)
        ventana.setContentPane(panel);
//        Image imagen = new Image((new Principal()).getClass().getResourceAsStream("logo.jpg"));
        imagen.setIcon(new ImageIcon("logo.png"));
        vacio.setPreferredSize(new Dimension(293,16));
        encabezado.setFont(new Font(encabezado.getFont().getFontName(), encabezado.getFont().getStyle(), 40));
        panel.setLayout(new BorderLayout());
        panel.add(encabezado, BorderLayout.NORTH);
        panel.add(vacio,BorderLayout.WEST);
        panel.add(imagen,BorderLayout.CENTER);
        
        
//        Conficuracion de la barra de men�s.
        menuBar = new JMenuBar();

        menu = new JMenu("Sistema");
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Acerca de...");
        menuItem.addActionListener(e -> acercaDe(ventana));
        menu.add(menuItem);
        menu.addSeparator();
        
        menuItem = new JMenuItem("Salir");
        menuItem.addActionListener(e->ventana.dispatchEvent(new WindowEvent(ventana, WindowEvent.WINDOW_CLOSING)));
        menu.add(menuItem);
        
        
		menu = new JMenu("Materiales Capacitaci�n");
		menuBar.add(menu);
		
        submenu = new JMenu("Libro");
        menu.add(submenu);
        menuItem = new JMenuItem("Agregar");
        menuItem.addActionListener(e -> controller.opcion(OpcionesMenu.AGREGAR_LIBRO, ventana));
        //menuItem.addActionListener(a -> );
        submenu.add(menuItem);
        menuItem = new JMenuItem("Editar");
        menuItem.addActionListener(e -> controller.opcion(OpcionesMenu.EDITAR_LIBRO, ventana));
        submenu.add(menuItem);
        menuItem = new JMenuItem("Eliminar");
        menuItem.addActionListener(e -> controller.opcion(OpcionesMenu.ELIMINAR_LIBRO, ventana));
        submenu.add(menuItem);
        
        submenu = new JMenu("Video");
        menu.add(submenu);
        menuItem = new JMenuItem("Agregar");
        menuItem.addActionListener(e -> controller.opcion(OpcionesMenu.AGREGAR_VIDEO, ventana));
        submenu.add(menuItem);
        menuItem = new JMenuItem("Editar");
        menuItem.addActionListener(e -> controller.opcion(OpcionesMenu.EDITAR_VIDEO, ventana));
        submenu.add(menuItem);
        menuItem = new JMenuItem("Eliminar");
        menuItem.addActionListener(e -> controller.opcion(OpcionesMenu.ELIMINAR_VIDEO, ventana));
        submenu.add(menuItem);
        
        
        menu = new JMenu("Opciones");
        menuBar.add(menu);
        
        menuItem = new JMenuItem("B�squeda");
        menuItem.addActionListener(e -> controller.opcion(OpcionesMenu.BUSQUEDA, ventana));
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Lista de deseos");
        menuItem.addActionListener(e -> controller.opcion(OpcionesMenu.WHISLIST, ventana));
        menu.add(menuItem);
        
        ventana.setJMenuBar(menuBar);
        
        //configuracion final de la ventana
        ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ventana.setTitle("Sistema de suscripci�n a biblioteca");
//		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
//      ventana.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        ventana.pack();
        ventana.setSize(800, 600);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
	}
	
	private static void acercaDe(JFrame ventana) {
		JOptionPane.showConfirmDialog(ventana, "Trabajo pr�ctico - DIED 2018\n                  Grupo N� 7\nGast�n D�az y Joaqu�n Vacca", "Acerca de", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}


}

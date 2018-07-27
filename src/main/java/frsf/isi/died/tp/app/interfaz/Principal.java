package frsf.isi.died.tp.app.interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;

import javax.swing.*;
import javax.swing.text.html.ImageView;

import frsf.isi.died.tp.app.controller.MenuController;
import frsf.isi.died.tp.app.controller.OpcionesMenu;



public class Principal {

	public static void main(String[] args) {		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	          mostrarInterfaz(new JFrame());
	        }
	    });
	}
	
	public static void mostrarInterfaz(JFrame ventana) {
		
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
        
        
		menu = new JMenu("Materiales Capacaiatcion");
		menuBar.add(menu);
		
        submenu = new JMenu("Libro..");
        menu.add(submenu);
        menuItem = new JMenuItem("Agregar");
        menuItem.addActionListener(e -> MenuController.opcion(OpcionesMenu.AGREGAR_LIBRO, ventana));
        //menuItem.addActionListener(a -> );
        submenu.add(menuItem);
        menuItem = new JMenuItem("Editar");
        menuItem.addActionListener(e -> MenuController.opcion(OpcionesMenu.EDITAR_LIBRO, ventana));
        submenu.add(menuItem);
        menuItem = new JMenuItem("Eliminar");
        menuItem.addActionListener(e -> MenuController.opcion(OpcionesMenu.ELIMINAR_LIBRO, ventana));
        submenu.add(menuItem);
        
        submenu = new JMenu("Video..");
        menu.add(submenu);
        menuItem = new JMenuItem("Agregar");
        menuItem.addActionListener(e -> MenuController.opcion(OpcionesMenu.AGREGAR_VIDEO, ventana));
        submenu.add(menuItem);
        menuItem = new JMenuItem("Editar");
        menuItem.addActionListener(e -> MenuController.opcion(OpcionesMenu.EDITAR_VIDEO, ventana));
        submenu.add(menuItem);
        menuItem = new JMenuItem("Eliminar");
        menuItem.addActionListener(e -> MenuController.opcion(OpcionesMenu.ELIMINAR_VIDEO, ventana));
        submenu.add(menuItem);
        
        
        menu = new JMenu("Opciones");
        menuBar.add(menu);
        
        menuItem = new JMenuItem("B�squeda");
        menuItem.addActionListener(e -> MenuController.opcion(OpcionesMenu.BUSQUEDA, ventana));
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Lista de deseos");
        menuItem.addActionListener(e -> MenuController.opcion(OpcionesMenu.WHISLIST, ventana));
        menu.add(menuItem);
        
        ventana.setJMenuBar(menuBar);
        
        //configuracion final de la ventana
        ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ventana.setTitle("Sistema de suscripcion a biblioteca");
//		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
//      ventana.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        ventana.pack();
        ventana.setSize(800, 600);
        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
	}
	
	private static void acercaDe(JFrame ventana) {
		JOptionPane.showConfirmDialog(ventana, "TP de DIED, Gast�n D�az y Joqu�n Vacca", "Acerca de", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
	}


}

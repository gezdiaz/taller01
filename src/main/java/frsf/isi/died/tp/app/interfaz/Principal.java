package frsf.isi.died.tp.app.interfaz;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;



public class Principal {

	public static void main(String[] args) {
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        public void run() {
	          mostrarInterfaz();
	        }
	    });
		

	}
	
	public static void mostrarInterfaz() {
		JFrame ventana = new JFrame();
		
		
		
		JPanel panel = new JPanel();
		ventana.setContentPane(panel);
		
		JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;
        JMenu submenu;
        
        menuBar = new JMenuBar();

        menu = new JMenu("Sistema");
        menuBar.add(menu);
		
        submenu = new JMenu("Libro..");
        menu.add(submenu);
        menuItem = new JMenuItem("Agregar");
        //menuItem.addActionListener(a -> );
        submenu.add(menuItem);
        menuItem = new JMenuItem("Editar");
        submenu.add(menuItem);
        menuItem = new JMenuItem("Quitar");
        submenu.add(menuItem);
        
        
        submenu = new JMenu("Video..");
        menu.add(submenu);
        menuItem = new JMenuItem("Agregar");
        submenu.add(menuItem);
        menuItem = new JMenuItem("Editar");
        submenu.add(menuItem);
        menuItem = new JMenuItem("Quitar");
        submenu.add(menuItem);
        menu.addSeparator();
        
        menuItem = new JMenuItem("Acerca de...");
        menu.add(menuItem);
        menu.addSeparator();
        
        menuItem = new JMenuItem("Salir");
        menuItem.addActionListener(e->System.exit(99));
        menu.add(menuItem);
        
        menu = new JMenu("Opciones");
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Búsqueda");
        menu.add(menuItem);
        
        menuItem = new JMenuItem("Lista de deseos");
        menu.add(menuItem);
        
        
        
        ventana.setJMenuBar(menuBar);
        ventana.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		ventana.setTitle("Sistema de suscripcion a biblioteca");
		
//        ventana.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
        ventana.pack();
        ventana.setSize(800, 600);
        ventana.setVisible(true);
	}

}

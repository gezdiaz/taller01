package frsf.isi.died.tp.taller03;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import frsf.isi.died.tp.estructuras.ArbolBinarioBusqueda;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.Video;

public class Taller03Paso05 {
	
	private ArbolBinarioBusqueda arbol;
	private Libro l1;
	private Libro l2;
	private Libro l3;
	private Libro l4;
	private Libro l5;
	private Libro l6;
	private Video v1;
	private Video v2;
	private Video v3;
	private Video v4;
	private Video v5;
	
	@Before
	public void init() {	
		arbol = new ArbolBinarioBusqueda((m1,m2) -> m1.precio().compareTo(m2.precio()));
		l1= new Libro(1, "Libro1", 10.0, 20.0, 154);
		l2= new Libro(2, "Libro2", 20.0, 24.0, 361);
		l3= new Libro(3, "Libro3", 15.0, 18.0, 108);
		l4= new Libro(4, "Libro4", 30.0, 16.0, 250);
		l5= new Libro(5, "Libro5", 24.0, 32.0, 545);
		l6= new Libro(6, "Libro6", 28.0, 54.0, 302);
		v1= new Video(7, "Video7", 28.0, 360);
		v2= new Video(8, "Video8", 15.0, 625);
		v3= new Video(9, "Video9", 30.0, 145);
		v4= new Video(10, "Video10", 45.0, 38);
		v5= new Video(11, "Video11", 12.0, 60);
	}
	
	
	@Test
	public void testRango() {
		arbol.add(l1); // Precio = 30.616
		arbol.add(l2); // Precio = 45.7328
		arbol.add(l3); // Precio = 33.3888
		arbol.add(l4); // Precio = 46.80
		arbol.add(l5); // Precio = 59.488
		arbol.add(l6); // Precio = 85.2616
		arbol.add(v1); // Precio = 82.00
		arbol.add(v2); // Precio = 108.75
		arbol.add(v3); // Precio = 51.75
		arbol.add(v4); // Precio = 50.7
		arbol.add(v5); // Precio = 21
		List<MaterialCapacitacion> lista = arbol.rango(45.7328, 59.488);
//		System.out.println(lista);
		for(MaterialCapacitacion m : lista) {
//			System.out.println("PRECIO: "+ m.precio());
			assertTrue(m.precio()>=45.7328 && m.precio()<=59.488);
		}
//		System.out.println("TAMAÑO LISTA: "+lista.size());
		assertTrue(lista.size()==5);
	}
}

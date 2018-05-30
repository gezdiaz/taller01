package frsf.isi.died.tp.taller03;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import frsf.isi.died.tp.estructuras.ArbolBinarioBusqueda;
import frsf.isi.died.tp.modelo.productos.MaterialCapacitacion;
import frsf.isi.died.tp.modelo.BibliotecaABB;
import frsf.isi.died.tp.modelo.productos.Libro;
import frsf.isi.died.tp.modelo.productos.Video;

public class Taller03Paso05 {
	
	private BibliotecaABB biblioteca;
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
	public void init() 	{
		biblioteca = new BibliotecaABB();
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
	}
	
	
	@Test
	public void testRango() {
		biblioteca.agregar(l1); // Precio = 30.616
		biblioteca.agregar(l2); // Precio = 45.7328
		biblioteca.agregar(l3); // Precio = 33.3888
		biblioteca.agregar(l4); // Precio = 46.80
		biblioteca.agregar(l5); // Precio = 59.488
		biblioteca.agregar(l6); // Precio = 85.2616
		biblioteca.agregar(v1); // Precio = 82.00
		biblioteca.agregar(v2); // Precio = 108.75
		biblioteca.agregar(v3); // Precio = 51.75
		biblioteca.agregar(v4); // Precio = 50.7
		List<MaterialCapacitacion> lista = (List<MaterialCapacitacion>) biblioteca.rango(45.7328, 59.488);
//		System.out.println(lista);
		for(MaterialCapacitacion m : lista) {
//			System.out.println("PRECIO: "+ m.precio());
			assertTrue(m.precio()>=45.7328 && m.precio()<=59.488);
		}
//		System.out.println("TAMAÑO LISTA: "+lista.size());
		assertEquals(5, lista.size());
		
		lista = (List<MaterialCapacitacion>) biblioteca.rango(0.0, 20.0);
		assertTrue(lista.isEmpty());
		
		lista = (List<MaterialCapacitacion>) biblioteca.rango(120.0, 130.0);
		assertTrue(lista.isEmpty());
		
		lista = (List<MaterialCapacitacion>) biblioteca.rango(0.0, 120.0);
		
		assertEquals(10, lista.size());
		
		
		
	}
}

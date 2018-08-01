/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package frsf.isi.died.tp.estructuras;

/**
 *
 * @author mdominguez
 */
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



public class Grafo<T> {

	protected List<Arista<T>> aristas;
	protected List<Vertice<T>> vertices;

	/**
	 * 
	 */
	public Grafo(){
		this.aristas = new ArrayList<Arista<T>>();
		this.vertices = new ArrayList<Vertice<T>>();
	}

	/**
	 * @param nodo
	 */
	public void addNodo(T nodo){
		this.addNodo(new Vertice<T>(nodo));
	}

	/**
	 * @param nodo
	 */
	public void addNodo(Vertice<T> nodo){
		this.vertices.add(nodo);
	}
	
	/**
	 * @param n1
	 * @param n2
	 */
	public void conectar(T n1,T n2){
		this.conectar(getNodo(n1), getNodo(n2), 0.0);
	}

        /**
	 * @param nodo1
	 * @param nodo2
	 */
	public Arista<T> conectar(Vertice<T> nodo1,Vertice<T> nodo2){
            Arista<T> arista = new Arista<T>(nodo1,nodo2,0.0);
            this.aristas.add(arista);
            return arista;
	}
        
	/**
	 * @param n1
	 * @param n2
	 * @param valor
	 */
	public void conectar(T n1,T n2,Number valor){
		this.conectar(getNodo(n1), getNodo(n2), valor);
	}

	/**
	 * @param nodo1
	 * @param nodo2
	 * @param valor
	 */
	public void conectar(Vertice<T> nodo1,Vertice<T> nodo2,Number valor){
		this.aristas.add(new Arista<T>(nodo1,nodo2,valor));
	}
	
	/**
	 * @param valor
	 * @return
	 */
	public Vertice<T> getNodo(T valor){
		return this.vertices.get(this.vertices.indexOf(new Vertice<T>(valor)));
	}

	/**
	 * @param valor
	 * @return
	 */
	public List<T> getAdyacentes(T valor){ 
		Vertice<T> unNodo = this.getNodo(valor);
		List<T> salida = new ArrayList<T>();
		for(Arista<T> enlace : this.aristas){
			if( enlace.getInicio().equals(unNodo)){
				salida.add(enlace.getFin().getValor());
			}
		}
		return salida;
	}	

	/**
	 * @param unNodo
	 * @return
	 */
	protected List<Vertice<T>> getAdyacentes(Vertice<T> unNodo){ 
		List<Vertice<T>> salida = new ArrayList<Vertice<T>>();
		for(Arista<T> enlace : this.aristas){
			if( enlace.getInicio().equals(unNodo)){
				salida.add(enlace.getFin());
			}
		}
		return salida;
	}
                
	/**
	 * 
	 */
	public void imprimirAristas(){
		System.out.println(this.aristas.toString());
	}
        
	/**
	 * @param v1
	 * @param v2
	 * @return
	 */
	public boolean esAdyacente(T n1, T n2) {
		Vertice<T> origen = this.getNodo(n1);
		Vertice<T> destino = this.getNodo(n2);
		return this.esAdyacente(origen, destino);
	}
	public boolean esAdyacente(Vertice<T> v1,Vertice<T> v2){
            List<Vertice<T>> ady = this.getAdyacentes(v1);
            for(Vertice<T> unAdy : ady){
                if(unAdy.equals(v2)) return true;
            }
            return false;
    }
        
    public Boolean esVacio(){
    	if(this.vertices!= null && !this.vertices.isEmpty()) return false;
    	return true;
    }
    
    public List<T> listaVertices(){
    	List<T> lista = new ArrayList<>();
    	this.vertices.forEach(v -> lista.add(v.getValor()));
    	return lista;
    }

		/**
	 * @param vertice
	 * @return
	 */
	public Integer gradoEntrada(T v){
		Vertice<T> vertice = this.getNodo(v);
		Integer res =0;
		
		for(Arista<T> a: aristas) {
			if(a.getFin().equals(vertice)){
				res++;
			}
		}
		
		return res;
	}

	/**
	 * @param vertice
	 * @return
	 */
	public Integer gradoSalida(T v){
		Vertice<T> vertice = this.getNodo(v);
		Integer res =0;
		List<Vertice<T>> adyacentes = getAdyacentes(vertice);
		res = adyacentes.size();	
		
		return res;
	}

    public T primerVerticeGradoK(T v,Integer gradoK) {
    	HashSet<Vertice<T>> visitados= new HashSet<>();
    	visitados.add(this.getNodo(v));
    	return primerVerticeGradoK(v, gradoK, visitados);
    	
    }

    private T primerVerticeGradoK(T v, Integer gradoK, HashSet<Vertice<T>> visitados) {
		
    	Vertice<T> vertice = this.getNodo(v);
    	List<Vertice<T>> adyacentes = this.getAdyacentes(vertice);
    	
    	for(Vertice<T> ver: adyacentes) {
    		if(!visitados.contains(ver)) {
    			visitados.add(ver);
    			if(this.gradoSalida(ver.getValor()).equals(gradoK)) {
    				return ver.getValor();
    			}
    		}
    	}
    	for(Vertice<T> ver: adyacentes) {
    		T resultado = primerVerticeGradoK(ver.getValor(), gradoK, visitados);
    		if(resultado != null) {
    			return resultado;
    		}
    	}
    	
    	return null;
		
	}

	public boolean existeCamino(T v) {
		Vertice<T> vertice = this.getNodo(v);
    	return true;
    }
    
    /**
     * @param n1
     * @param n2
     * @param valor
     */
    public List<T> buscarCaminoNSaltos(T n1,T n2,Integer saltos){
		Vertice<T> origen = this.getNodo(n1);
		Vertice<T> destino= this.getNodo(n2);
		HashSet<Vertice<T>> visitados = new HashSet<Vertice<T>>();
//		visitados.add(origen);
		return this.buscarCaminoNSaltos(origen, destino, saltos, visitados);
         
    }
    
    private List<T> buscarCaminoNSaltos(Vertice<T> n1,Vertice<T> n2,Integer saltos,HashSet<Vertice<T>> visitados){
        ArrayList<T> resultado = new ArrayList<>();
        if(!visitados.contains(n1)) {
        	
        	if(n1.equals(n2)) {
        		if(saltos.equals(0)) {
        			visitados.add(n2);
        			resultado.add(n2.getValor());
        		}
        	}else {
        		if(!saltos.equals(0)) {
        			for(Vertice<T> ady: this.getAdyacentes(n1)) {
        				ArrayList<T> aux = (ArrayList<T>) buscarCaminoNSaltos(ady, n2, saltos-1, visitados);
        				if(!aux.isEmpty()) {
        					visitados.add(n1);
        					resultado.add(n1.getValor());
        					resultado.addAll(aux);
        				}
        			}
        		}
        	}
        }
        
        return resultado;
    }

	public void eliminarNodo(T val) {
		if(vertices.contains(new Vertice<T>(val))) {
			aristas.removeIf(v -> v.getInicio().equals(new Vertice<T>(val)) || v.getFin().equals(new Vertice<T>(val)));
			vertices.remove(new Vertice<T>(val));
		}
	}

	public void reemplazarNodo(T anterior, T nuevo) {
		if(vertices.contains(new Vertice<T>(anterior))) {
			for(Vertice<T> v: vertices) {
				if(v.equals(new Vertice<T>(anterior))) {
					v.setValor(nuevo);
				}
			}
		}
		
	}
	
	public List<T> getReferentes(T valor){
		List<T> referentes = new ArrayList<T>();
		for(Arista arista: this.aristas) {
			if(arista.getFin().equals(this.getNodo(valor))) {
				referentes.add((T) arista.getInicio().getValor());
			}
		}
		return referentes;
	}

}


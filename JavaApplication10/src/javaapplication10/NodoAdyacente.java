/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication10;

/**
 *
 * @author Antonio Yibirin, Nicolas Mendez
 */


/**
 * Representa un nodo adyacente en una lista de adyacencia para el grafo.
 * Cada nodo adyacente contiene una referencia al nodo de destino y un enlace
 * al siguiente nodo adyacente en la lista.
 */
public class NodoAdyacente {
    
    /**
    *   Declaracion de Variable
    */
    Nodo destino;
    NodoAdyacente siguiente;

    /**
     * Crea un nuevo nodo adyacente con el nodo de destino especificado.
     * 
     * @param destino El nodo al que apunta esta arista.
     */
    public NodoAdyacente(Nodo destino) {
        this.destino = destino;
        this.siguiente = null;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication10;

/**
 *
 * @author Antonio Yibirin, Nicolas Mendez
 */
public class NodoAdyacente {
    Nodo destino;
    NodoAdyacente siguiente;

    public NodoAdyacente(Nodo destino) {
        this.destino = destino;
        this.siguiente = null;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication10;

/**
 *
 * @author Antonio Yibirin, Nicolas Mendez, Antonio Guzzo
 */

/**
 * Representa un nodo en un grafo.
 * Cada nodo almacena su posición (fila y columna), una letra asociada y
 * una lista de nodos adyacentes.
 */
public class Nodo {
    
    /**
    *   Declaracion de Variable
    */
    int fila;
    int columna;
    char letra;
    NodoAdyacente primero; // Puntero a lista de adyacentes
    
    
    /**
     * Crea un nuevo nodo con su posición (fila y columna) y la letra asociada.
     * 
     * @param fila    La fila en la que se encuentra el nodo.
     * @param columna La columna en la que se encuentra el nodo.
     * @param letra   La letra almacenada en esta celda del grafo.
     */
    public Nodo(int fila, int columna, char letra) {
        this.fila = fila;
        this.columna = columna;
        this.letra = letra;
        this.primero = null;
    }
}


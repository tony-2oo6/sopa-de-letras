/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication10;

/**
 *
 * @author Antonio Yibirin, Nicolas Mendez
 */
public class Nodo {
    int fila;
    int columna;
    char letra;
    NodoAdyacente primero; // Puntero a lista de adyacentes

    public Nodo(int fila, int columna, char letra) {
        this.fila = fila;
        this.columna = columna;
        this.letra = letra;
        this.primero = null;
    }
}


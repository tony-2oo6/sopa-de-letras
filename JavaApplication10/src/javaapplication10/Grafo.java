/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication10;

/**
 *
 * @author Antonio Yibirin, Nicolas Mendez
 */
public class Grafo {
    Nodo[][] nodos = new Nodo[4][4]; // Matriz de nodos (grafo implícito en forma de matriz)
    
    public Grafo(char[][] tablero) {
        construirGrafo(tablero);
    }

    public void construirGrafo(char[][] tablero) {
        // Crear nodos
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                nodos[i][j] = new Nodo(i, j, tablero[i][j]);
            }
        }
        
        // Conectar adyacencias
        int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
        int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                Nodo actual = nodos[i][j];
                for (int k = 0; k < 8; k++) {
                    int ni = i + dx[k];
                    int nj = j + dy[k];
                    if (ni >= 0 && ni < 4 && nj >= 0 && nj < 4) {
                        agregarAdyacente(actual, nodos[ni][nj]);
                    }
                }
            }
        }
    }

    private void agregarAdyacente(Nodo origen, Nodo destino) {
        NodoAdyacente nuevo = new NodoAdyacente(destino);
        nuevo.siguiente = origen.primero;
        origen.primero = nuevo;
    }
}

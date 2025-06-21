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
    
    public boolean bfs(String palabra) {
        int maxCola = 1000;
        Nodo[] nodosCola = new Nodo[maxCola];
        int[] indices = new int[maxCola];
        boolean[][][] visitadosCola = new boolean[maxCola][4][4];

        int frente = 0;
        int fin = 0;

        // Buscar cada posible inicio
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (nodos[i][j].letra == palabra.charAt(0)) {
                    nodosCola[fin] = nodos[i][j];
                    indices[fin] = 1; // ya acertamos la primera letra

                    boolean[][] visitados = new boolean[4][4];
                    visitados[i][j] = true;
                    copiarMatriz(visitados, visitadosCola[fin]);

                    fin++;
                }
            }
        }

        while (frente < fin) {
            Nodo actual = nodosCola[frente];
            int indice = indices[frente];
            boolean[][] visitados = visitadosCola[frente];
            frente++;

            if (indice == palabra.length()) {
                return true;
            }

            NodoAdyacente ady = actual.primero;
            while (ady != null) {
                Nodo sig = ady.destino;
                int f = sig.fila;
                int c = sig.columna;

                if (!visitados[f][c] && sig.letra == palabra.charAt(indice)) {
                    nodosCola[fin] = sig;
                    indices[fin] = indice + 1;

                    boolean[][] nuevoVisitados = new boolean[4][4];
                    copiarMatriz(visitados, nuevoVisitados);
                    nuevoVisitados[f][c] = true;
                    copiarMatriz(nuevoVisitados, visitadosCola[fin]);

                    fin++;
                }

                ady = ady.siguiente;
            }
        }

        return false;
    }
    
    private void copiarMatriz(boolean[][] origen, boolean[][] destino) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                destino[i][j] = origen[i][j];
            }
        }
    }


}

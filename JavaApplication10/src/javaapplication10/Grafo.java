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
 * Representa un grafo en forma de matriz 4x4 donde cada celda contiene un nodo con una letra.
 * 
 * Los nodos se conectan con sus 8 vecinos adyacentes (horizontal, vertical y diagonal).
 * 
 */
public class Grafo {
    
    /**
    *   Declaracion de Variable
    */
    Nodo[][] nodos = new Nodo[4][4]; // Matriz de nodos (grafo implícito en forma de matriz)
    
    
     /**
     * Construye el grafo a partir de una matriz de caracteres, creando los nodos
     * y conectando sus adyacencias.
     * 
     * @param tablero Una matriz de 4x4 caracteres que representa el tablero de letras.
     */
    public Grafo(char[][] tablero) {
        construirGrafo(tablero);
    }
    
    
    /**
     * Crea los nodos del grafo y establece sus adyacencias con vecinos válidos.
     * 
     * @param tablero La matriz de letras que sirve de base para crear los nodos.
     */
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
    
    /**
     * Agrega un nodo adyacente a la lista de adyacentes del nodo de origen.
     * 
     * @param origen  Nodo al que se le va a agregar una adyacencia.
     * @param destino Nodo adyacente que se va a conectar.
     */
    private void agregarAdyacente(Nodo origen, Nodo destino) {
        NodoAdyacente nuevo = new NodoAdyacente(destino);
        nuevo.siguiente = origen.primero;
        origen.primero = nuevo;
    }
    
    /**
     * Realiza una búsqueda en anchura (BFS) para determinar si una palabra puede ser formada
     * siguiendo caminos de letras adyacentes en el grafo, sin repetir celdas.
     * 
     * @param palabra La palabra que se desea buscar.
     * @return true si la palabra puede formarse, false en caso contrario.
     */
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
    
    /**
     * Copia el contenido de una matriz booleana a otra.
     * 
     * @param origen  Matriz original que se va a copiar.
     * @param destino Matriz destino donde se colocará la copia.
     */
    private void copiarMatriz(boolean[][] origen, boolean[][] destino) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                destino[i][j] = origen[i][j];
            }
        }
    }
    
    
    /**
    * Realiza una búsqueda en profundidad (DFS) para verificar si una palabra puede ser
    * formada en el grafo siguiendo caminos de letras adyacentes, sin repetir nodos.
    * 
    * @param palabra La palabra que se desea buscar.
    * @return true si la palabra puede ser construida a partir de letras adyacentes, false en caso contrario.
    */
    public boolean dfs(String palabra) {
        for (int i = 0; i < nodos.length; i++) {
            for (int j = 0; j < nodos[0].length; j++) {
                if (nodos[i][j].letra == palabra.charAt(0)) {
                    boolean[][] visitados = new boolean[4][4];
                    visitados[i][j] = true;
                    if (dfsDesde(nodos[i][j], palabra, 1, visitados)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    /**
    * Método recursivo auxiliar para realizar la búsqueda en profundidad desde un nodo dado.
    * 
    * @param actual   Nodo actual en la búsqueda.
    * @param palabra  Palabra objetivo que se está buscando.
    * @param indice   Índice actual de la letra que se debe buscar en la palabra.
    * @param visitados Matriz booleana que indica qué nodos ya han sido visitados.
    * @return true si se puede completar la palabra desde este punto, false en caso contrario.
    */
    private boolean dfsDesde(Nodo actual, String palabra, int indice, boolean[][] visitados) {
        if (indice == palabra.length()) {
            return true;
        }

        NodoAdyacente ady = actual.primero;
        while (ady != null) {
            Nodo sig = ady.destino;
            int f = sig.fila;
            int c = sig.columna;

            if (!visitados[f][c] && sig.letra == palabra.charAt(indice)) {
                visitados[f][c] = true;
                if (dfsDesde(sig, palabra, indice + 1, visitados)) {
                    return true;
                }
                visitados[f][c] = false; // backtrack
            }

            ady = ady.siguiente;
        }

        return false;
    }




}

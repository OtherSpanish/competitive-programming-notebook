import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Estructuras de datos mas usadas en Java para CP.
 * Cada operacion lleva su complejidad algoritmica.
 */
public class EstructurasDeDatos {

    static void arreglosYMatrices() {
        // Arreglo: tamano FIJO. Acceso O(1); crear O(n).
        int[] arreglo = new int[5];        // crear: O(n)
        arreglo[0] = 10;                   // acceso/asignacion: O(1)
        int[] literal = {1, 2, 3, 4, 5};
        int tam = literal.length;          // O(1)  (.length sin parentesis)

        // Matriz (2D): acceso [i][j] O(1); crear n*m O(n*m).
        int[][] matriz = new int[3][4];    // crear: O(n*m)
        matriz[1][2] = 7;                  // acceso: O(1)
    }

    static void listasDinamicas() {
        // ArrayList: acceso O(1); add al final O(1) amortizado;
        // insertar/borrar en medio O(n); buscar O(n).
        ArrayList<Integer> lista = new ArrayList<>();
        lista.add(3);                      // final: O(1) amortizado
        int x = lista.get(0);              // acceso: O(1)
        lista.set(0, 9);                   // O(1)
        int n = lista.size();              // O(1)

        // LinkedList: add/remove en extremos O(1); acceso por indice O(n).
        LinkedList<Integer> enlazada = new LinkedList<>();
        enlazada.addFirst(1);              // O(1)
        enlazada.addLast(9);               // O(1)
        enlazada.removeFirst();            // O(1)   ;  get(i): O(n)
    }

    static void conjuntos() {
        // HashSet: insertar/buscar/borrar O(1) promedio (O(n) peor caso).
        HashSet<Integer> hset = new HashSet<>();
        hset.add(3);                       // O(1) prom.
        boolean hay = hset.contains(3);    // O(1) prom.

        // TreeSet: ORDENADO. insertar/buscar/borrar/piso/techo O(log n).
        TreeSet<Integer> tset = new TreeSet<>();
        tset.add(5);                       // O(log n)
        tset.add(1);                       // O(log n)
        int menor = tset.first();          // O(log n)
        Integer techo = tset.ceiling(3);   // O(log n)  (menor >= 3)
    }

    static void mapasYDiccionarios() {
        // HashMap: put/get/remove O(1) promedio (O(n) peor caso).
        HashMap<String, Integer> hmap = new HashMap<>();
        hmap.put("a", 1);                  // O(1) prom.
        int v = hmap.getOrDefault("a", 0); // O(1) prom.

        // TreeMap: ORDENADO por clave. put/get/remove/floor/ceiling O(log n).
        TreeMap<String, Integer> tmap = new TreeMap<>();
        tmap.put("b", 2);                  // O(log n)
        tmap.put("a", 1);                  // O(log n)
        String primera = tmap.firstKey();  // O(log n)  ("a")
    }

    public static void main(String[] args) {
        arreglosYMatrices();
        listasDinamicas();
        conjuntos();
        mapasYDiccionarios();
    }
}

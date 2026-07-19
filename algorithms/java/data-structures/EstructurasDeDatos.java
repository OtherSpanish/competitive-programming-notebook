import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Estructuras de datos mas usadas en Java para competitive programming.
 */
public class EstructurasDeDatos {

    static void arreglosYMatrices() {
        // Arreglo: tamano FIJO, mismo tipo, acceso O(1) por indice.
        int[] arreglo = new int[5];
        arreglo[0] = 10;
        int[] literal = {1, 2, 3, 4, 5};
        int tam = literal.length;          // ! .length (sin parentesis)

        // Matriz: arreglo 2D (filas x columnas), acceso O(1) por [i][j].
        int[][] matriz = new int[3][4];
        matriz[1][2] = 7;
    }

    static void listasDinamicas() {
        // ArrayList: arreglo que CRECE. Acceso O(1), add al final O(1) amortizado.
        ArrayList<Integer> lista = new ArrayList<>();
        lista.add(3);
        int x = lista.get(0);
        lista.set(0, 9);
        int n = lista.size();

        // LinkedList: lista doblemente enlazada. Add/remove en extremos O(1),
        // pero acceso por indice O(n). Sirve como cola/pila/deque.
        LinkedList<Integer> enlazada = new LinkedList<>();
        enlazada.addFirst(1);
        enlazada.addLast(9);
        enlazada.removeFirst();
    }

    static void conjuntos() {
        // HashSet: sin duplicados, SIN orden. insertar/buscar/borrar O(1) promedio.
        HashSet<Integer> hset = new HashSet<>();
        hset.add(3);
        boolean hay = hset.contains(3);

        // TreeSet: sin duplicados, ORDENADO. O(log n). Permite piso/techo/rango.
        TreeSet<Integer> tset = new TreeSet<>();
        tset.add(5);
        tset.add(1);
        int menor = tset.first();          // 1
        Integer techo = tset.ceiling(3);   // menor valor >= 3
    }

    static void mapasYDiccionarios() {
        // HashMap: pares clave->valor SIN orden. Acceso por clave O(1) promedio.
        HashMap<String, Integer> hmap = new HashMap<>();
        hmap.put("a", 1);
        int v = hmap.getOrDefault("a", 0);

        // TreeMap: clave->valor ORDENADO por clave. O(log n). Rango, floor/ceiling.
        TreeMap<String, Integer> tmap = new TreeMap<>();
        tmap.put("b", 2);
        tmap.put("a", 1);
        String primera = tmap.firstKey();  // "a"
    }

    public static void main(String[] args) {
        arreglosYMatrices();
        listasDinamicas();
        conjuntos();
        mapasYDiccionarios();
    }
}

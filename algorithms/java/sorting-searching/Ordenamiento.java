import java.util.Arrays;
import java.util.Random;

/**
 * Ordenamiento y seleccion en Java.
 */
public class Ordenamiento {

    // ============================================================
    // HEAP SORT: O(n log n) SIEMPRE, in-place.
    // ============================================================
    static void bajar(int[] a, int n, int i) {     // sift-down: O(log n)
        while (true) {
            int may = i, l = 2*i + 1, r = 2*i + 2; // hijos del nodo i
            if (l < n && a[l] > a[may]) may = l;
            if (r < n && a[r] > a[may]) may = r;
            if (may == i) break;                   // ya cumple la propiedad de heap
            int t = a[i]; a[i] = a[may]; a[may] = t; i = may;
        }
    }
    static void heapSort(int[] a) {
        int n = a.length;
        for (int i = n/2 - 1; i >= 0; i--) bajar(a, n, i);   // construye heap: O(n)
        for (int i = n - 1; i > 0; i--) {                     // extrae el max
            int t = a[0]; a[0] = a[i]; a[i] = t;              // mayor -> al final
            bajar(a, i, 0);
        }
    }

    // ============================================================
    // QUICKSELECT iterativo con pivote ALEATORIO. Prom O(n), peor O(n^2).
    // ============================================================
    static Random rng = new Random(12345);
    static int quickSelect(int[] a, int k) {       // k in [0, n-1]; modifica a
        int lo = 0, hi = a.length - 1;
        while (lo < hi) {
            int pivote = a[lo + rng.nextInt(hi - lo + 1)];   // pivote aleatorio
            int i = lo, j = hi;
            while (i <= j) {
                while (a[i] < pivote) i++;
                while (a[j] > pivote) j--;
                if (i <= j) { int t = a[i]; a[i] = a[j]; a[j] = t; i++; j--; }
            }
            if (k <= j) hi = j;                     // k en la izquierda
            else if (k >= i) lo = i;                // k en la derecha
            else return a[k];
        }
        return a[lo];
    }

    public static void main(String[] args) {
        int[] b = {5, 2, 8, 1, 9, 3};
        heapSort(b);
        System.out.println(Arrays.toString(b));         // [1, 2, 3, 5, 8, 9]

        System.out.println(quickSelect(new int[]{5,2,8,1,9,3}, 2)); // 3
        System.out.println(quickSelect(new int[]{5,2,8,1,9,3}, 0)); // 1
    }
}

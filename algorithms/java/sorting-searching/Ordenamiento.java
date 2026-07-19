import java.util.Arrays;

/**
 * Ordenamiento y seleccion en Java.
 */
public class Ordenamiento {

    // ===== HEAP SORT =====  O(n log n), IN-PLACE.
    static void bajar(int[] a, int n, int i) {     // sift-down: O(log n)
        while (true) {
            int may = i, l = 2*i + 1, r = 2*i + 2;
            if (l < n && a[l] > a[may]) may = l;
            if (r < n && a[r] > a[may]) may = r;
            if (may == i) break;
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

    // ===== QUICKSELECT =====  k-esimo menor (0-indexed). Prom O(n), peor O(n^2).
    static int quickSelect(int[] a, int lo, int hi, int k) {
        if (lo == hi) return a[lo];
        int pivote = a[(lo + hi) / 2], i = lo, j = hi;
        while (i <= j) {
            while (a[i] < pivote) i++;
            while (a[j] > pivote) j--;
            if (i <= j) { int t = a[i]; a[i] = a[j]; a[j] = t; i++; j--; }
        }
        if (k <= j) return quickSelect(a, lo, j, k);
        if (k >= i) return quickSelect(a, i, hi, k);
        return a[k];
    }

    public static void main(String[] args) {
        int[] b = {5, 2, 8, 1, 9, 3};
        heapSort(b);
        System.out.println(Arrays.toString(b));         // [1, 2, 3, 5, 8, 9]

        int[] a = {5, 2, 8, 1, 9, 3};
        System.out.println(quickSelect(a, 0, a.length - 1, 2)); // 3
    }
}

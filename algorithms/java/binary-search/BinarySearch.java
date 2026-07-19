import java.util.Arrays;
import java.util.function.DoublePredicate;
import java.util.function.LongPredicate;

/**
 * Busqueda binaria en Java: 5 usos.
 * Todo O(log n) salvo reales O(iter) y strings O(L log n).
 */
public class BinarySearch {

    // ---- USO 1: arreglo YA ORDENADO ----  O(log n)
    static void enArregloOrdenado() {
        int[] a = {1, 3, 5, 7, 9};
        int idx = Arrays.binarySearch(a, 5);   // 2 (>=0 si existe)
        int lb = lowerBound(a, 5);             // primer >= 5 -> 2
    }
    static int lowerBound(int[] a, int x) {
        int lo = 0, hi = a.length;             // rango [lo, hi)
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;         // >>> evita overflow
            if (a[mid] < x) lo = mid + 1; else hi = mid;
        }
        return lo;
    }

    // ---- USO 2: MENOR x que cumple ----  (F..F T..T)  O(log n)
    static long buscarMenor(long lo, long hi, LongPredicate cumple) {
        long res = -1;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (cumple.test(mid)) { res = mid; hi = mid - 1; }
            else lo = mid + 1;
        }
        return res;
    }

    // ---- USO 3: MAYOR x que cumple ----  (T..T F..F)  O(log n)
    static long buscarMayor(long lo, long hi, LongPredicate cumple) {
        long res = -1;
        while (lo <= hi) {
            long mid = lo + (hi - lo) / 2;
            if (cumple.test(mid)) { res = mid; lo = mid + 1; }
            else hi = mid - 1;
        }
        return res;
    }

    // ---- USO 4: sobre REALES ----  O(iter)
    static double buscarReal(double lo, double hi, DoublePredicate cumple) {
        for (int it = 0; it < 100; it++) {
            double mid = (lo + hi) / 2;
            if (cumple.test(mid)) hi = mid; else lo = mid;
        }
        return lo;
    }

    // ---- USO 5: arreglo ordenado de STRINGS ----  O(L log n)
    static void enArregloDeStrings() {
        String[] s = {"ana", "beto", "caro", "dan"};
        int idx = Arrays.binarySearch(s, "caro");   // usa compareTo
        int lb = lowerBoundStr(s, "caro");
    }
    static int lowerBoundStr(String[] s, String x) {
        int lo = 0, hi = s.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (s[mid].compareTo(x) < 0) lo = mid + 1;  // compareTo, NO ==
            else hi = mid;
        }
        return lo;
    }

    public static void main(String[] args) {
        enArregloOrdenado();
        long menor = buscarMenor(0, 1000000, x -> x * x >= 50);  // 8
        long mayor = buscarMayor(0, 1000000, x -> x * x <= 50);  // 7
        double raiz = buscarReal(0, 50, x -> x * x >= 50);       // ~7.071
        enArregloDeStrings();
        System.out.println(menor + " " + mayor + " " + raiz);
    }
}

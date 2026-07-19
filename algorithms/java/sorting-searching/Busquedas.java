import java.util.Arrays;

/**
 * Busquedas variadas en Java. Complejidad en cada una.
 */
public class Busquedas {

    // 1) MISSING NUMBER: en [0..n] falta uno. O(n) tiempo, O(1) espacio.
    static int missingNumber(int[] a) {
        long n = a.length;
        long esperada = n * (n + 1) / 2;      // suma 0..n
        long actual = 0;
        for (int x : a) actual += x;
        return (int)(esperada - actual);
    }

    // 2) MAX/MIN: recorrido lineal. O(n).  (devuelve {min, max})
    static int[] maxMin(int[] a) {
        int mn = a[0], mx = a[0];
        for (int x : a) { mn = Math.min(mn, x); mx = Math.max(mx, x); }
        return new int[]{mn, mx};
    }

    // 3) MINIMUM ABSOLUTE SUM PAIR: |a[i]+a[j]| minimo. Ordena + 2 punteros. O(n log n).
    static long minAbsSumPair(int[] a) {
        Arrays.sort(a);
        int lo = 0, hi = a.length - 1;
        long mejor = Long.MAX_VALUE;
        while (lo < hi) {
            long suma = (long)a[lo] + a[hi];
            mejor = Math.min(mejor, Math.abs(suma));
            if (suma < 0) lo++; else hi--;
        }
        return mejor;
    }

    // 4) DIFFERENCE PAIR: existe par con a[j]-a[i] == d ? O(n log n).
    static boolean hayParConDiferencia(int[] a, int d) {
        Arrays.sort(a);
        int n = a.length, i = 0, j = 1;
        while (i < n && j < n) {
            if (i != j && a[j] - a[i] == d) return true;
            if (a[j] - a[i] < d) j++; else i++;
        }
        return false;
    }

    // 5) TERNARY SEARCH: maximo de funcion UNIMODAL en [lo,hi]. O(log n).
    static long f(long x) { return -(x - 3) * (x - 3) + 10; } // pico en x=3
    static long ternaryMax(long lo, long hi) {
        while (hi - lo > 2) {
            long m1 = lo + (hi - lo) / 3;
            long m2 = hi - (hi - lo) / 3;
            if (f(m1) < f(m2)) lo = m1 + 1; else hi = m2 - 1;
        }
        long best = lo;
        for (long x = lo; x <= hi; x++) if (f(x) > f(best)) best = x;
        return best;
    }

    // 6) FIBONACCI SEARCH: en arreglo ORDENADO. O(log n).
    static int fibonacciSearch(int[] a, int target) {
        int n = a.length;
        int fib2 = 0, fib1 = 1, fib = fib1 + fib2;
        while (fib < n) { fib2 = fib1; fib1 = fib; fib = fib1 + fib2; }
        int offset = -1;
        while (fib > 1) {
            int i = Math.min(offset + fib2, n - 1);
            if (a[i] < target) { fib = fib1; fib1 = fib2; fib2 = fib - fib1; offset = i; }
            else if (a[i] > target) { fib = fib2; fib1 = fib1 - fib2; fib2 = fib - fib1; }
            else return i;
        }
        if (fib1 == 1 && offset + 1 < n && a[offset + 1] == target) return offset + 1;
        return -1;
    }

    // 7) EXPONENTIAL SEARCH: en arreglo ORDENADO. O(log n).
    static int exponentialSearch(int[] a, int target) {
        int n = a.length;
        if (n == 0) return -1;
        if (a[0] == target) return 0;
        int i = 1;
        while (i < n && a[i] <= target) i *= 2;
        int lo = i / 2, hi = Math.min(i, n - 1);
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (a[mid] == target) return mid;
            if (a[mid] < target) lo = mid + 1; else hi = mid - 1;
        }
        return -1;
    }

    // 8) JUMP SEARCH: en arreglo ORDENADO. O(sqrt(n)).
    static int jumpSearch(int[] a, int target) {
        int n = a.length;
        int paso = Math.max(1, (int)Math.sqrt(n));
        int prev = 0;
        while (prev < n && a[Math.min(prev + paso, n) - 1] < target) prev += paso;
        for (int i = prev; i < Math.min(prev + paso, n); i++)
            if (a[i] == target) return i;
        return -1;
    }

    public static void main(String[] args) {
        int[] ord = {1, 3, 5, 7, 9, 11};
        System.out.println(missingNumber(new int[]{0, 1, 3, 4}));       // 2
        System.out.println(Arrays.toString(maxMin(new int[]{2,5,1,9,3}))); // [1, 9]
        System.out.println(minAbsSumPair(new int[]{-8,-3,2,4,9}));      // 1
        System.out.println(hayParConDiferencia(new int[]{1,5,3,8}, 2)); // true
        System.out.println(ternaryMax(-10, 10));                        // 3
        System.out.println(fibonacciSearch(ord, 7));                    // 3
        System.out.println(exponentialSearch(ord, 9));                  // 4
        System.out.println(jumpSearch(ord, 11));                        // 5
    }
}

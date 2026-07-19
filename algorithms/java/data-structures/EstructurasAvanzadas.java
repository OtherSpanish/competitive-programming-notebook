/**
 * Estructuras de datos avanzadas en Java.
 */
public class EstructurasAvanzadas {

    // ===== VECTOR CIRCULAR (Circular Buffer) =====
    // Arreglo FIJO cuyos indices dan la vuelta con modulo. push/get O(1).
    static class VectorCircular {
        int[] buf; int cap, ini = 0, tam = 0;
        VectorCircular(int c) { buf = new int[c]; cap = c; }
        void push(int x) {                        // O(1)
            buf[(ini + tam) % cap] = x;
            if (tam < cap) tam++;
            else ini = (ini + 1) % cap;           // lleno: pisa el mas viejo
        }
        int get(int i) { return buf[(ini + i) % cap]; }   // O(1)
    }

    // ===== FENWICK TREE / BIT =====
    // Suma de prefijos + update puntual, ambas O(log n). lowbit = i & (-i).
    static class Fenwick {
        long[] t; int n;
        Fenwick(int n) { t = new long[n + 1]; this.n = n; }
        void update(int i, long v) {              // suma v en pos i (1-indexed)
            for (; i <= n; i += i & (-i)) t[i] += v;
        }
        long query(int i) {                       // suma [1..i]
            long s = 0;
            for (; i > 0; i -= i & (-i)) s += t[i];
            return s;
        }
        long rango(int l, int r) { return query(r) - query(l - 1); }
    }

    // ===== SPARSE TABLE (RMQ estatico) =====
    // Minimo de rangos 2^k. Consulta O(1) para min/max/gcd. Arreglo INMUTABLE.
    static class SparseTable {
        int[][] sp; int[] lg;
        SparseTable(int[] a) {
            int n = a.length, K = 1; while ((1 << K) <= n) K++;
            lg = new int[n + 1];
            for (int i = 2; i <= n; i++) lg[i] = lg[i / 2] + 1;
            sp = new int[K][n];
            sp[0] = a.clone();                    // build: O(n log n)
            for (int k = 1; k < K; k++)
                for (int i = 0; i + (1 << k) <= n; i++)
                    sp[k][i] = Math.min(sp[k-1][i], sp[k-1][i + (1 << (k-1))]);
        }
        int minRango(int l, int r) {              // O(1)
            int k = lg[r - l + 1];
            return Math.min(sp[k][l], sp[k][r - (1 << k) + 1]);
        }
    }

    public static void main(String[] args) {
        VectorCircular vc = new VectorCircular(3);
        vc.push(1); vc.push(2); vc.push(3); vc.push(4);
        System.out.println(vc.get(0));            // 2

        Fenwick fw = new Fenwick(5);
        fw.update(1, 3); fw.update(3, 2);
        System.out.println(fw.rango(1, 3));       // 5

        int[] a = {5, 2, 8, 1, 9, 3};
        SparseTable st = new SparseTable(a);
        System.out.println(st.minRango(1, 4));    // 1
    }
}

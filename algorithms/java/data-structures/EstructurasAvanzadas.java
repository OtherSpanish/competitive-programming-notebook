/**
 * Estructuras de datos avanzadas en Java.
 */
public class EstructurasAvanzadas {

    // ============================================================
    // VECTOR CIRCULAR (Ring Buffer): cola DOBLE de capacidad fija. Todo O(1).
    // ============================================================
    static class VectorCircular {
        int[] buf; int cap, ini = 0, tam = 0;
        VectorCircular(int c) { buf = new int[c]; cap = c; }

        boolean vacio() { return tam == 0; }
        boolean lleno() { return tam == cap; }
        int size() { return tam; }

        void pushBack(int x) {                 // agrega al final
            buf[(ini + tam) % cap] = x;
            if (tam < cap) tam++;
            else ini = (ini + 1) % cap;        // lleno: descarta el frente
        }
        void pushFront(int x) {                // agrega al frente
            ini = (ini - 1 + cap) % cap;
            buf[ini] = x;
            if (tam < cap) tam++;
        }
        int popFront() { int v = buf[ini]; ini = (ini + 1) % cap; tam--; return v; }
        int popBack()  { tam--; return buf[(ini + tam) % cap]; }
        int front() { return buf[ini]; }
        int back()  { return buf[(ini + tam - 1) % cap]; }
        int get(int i) { return buf[(ini + i) % cap]; }
    }

    // ============================================================
    // FENWICK TREE / BIT + metodos auxiliares. lowbit = i & (-i).
    // ============================================================
    static class Fenwick {
        long[] t; int n;
        Fenwick(int n) { t = new long[n + 1]; this.n = n; }

        // Construccion en O(n) desde un arreglo.
        Fenwick(long[] a) {
            n = a.length; t = new long[n + 1];
            for (int i = 1; i <= n; i++) {
                t[i] += a[i - 1];
                int j = i + (i & (-i));
                if (j <= n) t[j] += t[i];
            }
        }
        void update(int i, long v) {           // suma v en pos i. O(log n)
            for (; i <= n; i += i & (-i)) t[i] += v;
        }
        long query(int i) {                    // suma [1..i]. O(log n)
            long s = 0;
            for (; i > 0; i -= i & (-i)) s += t[i];
            return s;
        }
        long rango(int l, int r) { return query(r) - query(l - 1); }

        // Menor indice con prefijo >= objetivo (binary lifting). O(log n).
        int lowerBound(long objetivo) {
            int pos = 0; long acum = 0; int LOG = 1;
            while ((1 << LOG) <= n) LOG++;
            for (int k = LOG - 1; k >= 0; k--) {
                int nxt = pos + (1 << k);
                if (nxt <= n && acum + t[nxt] < objetivo) { pos = nxt; acum += t[nxt]; }
            }
            return pos + 1;
        }
    }

    // ============================================================
    // FENWICK DE RANGO: update de rango + query de rango, O(log n).
    // ============================================================
    static class FenwickRango {
        Fenwick b1, b2;
        FenwickRango(int n) { b1 = new Fenwick(n); b2 = new Fenwick(n); }
        void rangoUpdate(int l, int r, long v) {
            b1.update(l, v);        b1.update(r + 1, -v);
            b2.update(l, v*(l-1));  b2.update(r + 1, -v*r);
        }
        long prefijo(int i) { return b1.query(i) * i - b2.query(i); }
        long rango(int l, int r) { return prefijo(r) - prefijo(l - 1); }
    }

    // ============================================================
    // SPARSE TABLE (RMQ estatico): min de rango en O(1). Arreglo INMUTABLE.
    // ============================================================
    static class SparseTable {
        int[][] sp; int[] lg;
        SparseTable(int[] a) {
            int n = a.length, K = 1; while ((1 << K) <= n) K++;
            lg = new int[n + 1];
            for (int i = 2; i <= n; i++) lg[i] = lg[i / 2] + 1;
            sp = new int[K][n];
            sp[0] = a.clone();
            for (int k = 1; k < K; k++)
                for (int i = 0; i + (1 << k) <= n; i++)
                    sp[k][i] = Math.min(sp[k-1][i], sp[k-1][i + (1 << (k-1))]);
        }
        int minRango(int l, int r) {
            int k = lg[r - l + 1];
            return Math.min(sp[k][l], sp[k][r - (1 << k) + 1]);
        }
    }

    public static void main(String[] args) {
        VectorCircular vc = new VectorCircular(3);
        vc.pushBack(1); vc.pushBack(2); vc.pushFront(0);
        System.out.println(vc.front() + " " + vc.back());   // 0 2
        vc.pushBack(9);
        System.out.println(vc.front());                     // 1

        Fenwick fw = new Fenwick(new long[]{1, 1, 1, 1, 1});
        fw.update(2, 3);
        System.out.println(fw.rango(1, 3));                 // 6
        System.out.println(fw.lowerBound(5));               // 2

        FenwickRango fr = new FenwickRango(6);
        fr.rangoUpdate(2, 4, 5);
        System.out.println(fr.rango(1, 6));                 // 15

        int[] a = {5, 2, 8, 1, 9, 3};
        SparseTable st = new SparseTable(a);
        System.out.println(st.minRango(1, 4));              // 1
    }
}

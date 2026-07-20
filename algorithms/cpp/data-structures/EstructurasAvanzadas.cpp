// Estructuras de datos avanzadas en C++ (g++ 8.1, -std=c++17).
#include <iostream>
#include <vector>
using namespace std;

// ============================================================
// VECTOR CIRCULAR (Ring Buffer): cola DOBLE de capacidad fija.
// Todos los indices "dan la vuelta" con modulo. Todo O(1).
// ============================================================
struct VectorCircular {
    vector<int> buf; int cap, ini = 0, tam = 0;
    VectorCircular(int c) : buf(c), cap(c) {}

    bool vacio() { return tam == 0; }
    bool lleno() { return tam == cap; }
    int  size()  { return tam; }

    void pushBack(int x) {                     // agrega al final
        buf[(ini + tam) % cap] = x;
        if (tam < cap) tam++;
        else ini = (ini + 1) % cap;            // lleno: descarta el frente
    }
    void pushFront(int x) {                     // agrega al frente
        ini = (ini - 1 + cap) % cap;
        buf[ini] = x;
        if (tam < cap) tam++;
    }
    int popFront() { int v = buf[ini]; ini = (ini + 1) % cap; tam--; return v; }
    int popBack()  { tam--; return buf[(ini + tam) % cap]; }
    int front() { return buf[ini]; }
    int back()  { return buf[(ini + tam - 1) % cap]; }
    int get(int i) { return buf[(ini + i) % cap]; }   // i-esimo del frente
};

// ============================================================
// FENWICK TREE / BIT: sumas de prefijo con update puntual.
// Truco lowbit: i & (-i) aisla el bit menos significativo.
// Incluye varios metodos auxiliares.
// ============================================================
struct Fenwick {
    vector<long long> t; int n;
    Fenwick(int n) : t(n + 1, 0), n(n) {}

    // Construccion desde arreglo en O(n) (mejor que n updates O(n log n)).
    Fenwick(const vector<long long>& a) : t(a.size() + 1, 0), n(a.size()) {
        for (int i = 1; i <= n; i++) {
            t[i] += a[i - 1];
            int j = i + (i & (-i));
            if (j <= n) t[j] += t[i];
        }
    }

    void update(int i, long long v) {          // suma v en pos i.   O(log n)
        for (; i <= n; i += i & (-i)) t[i] += v;
    }
    long long query(int i) {                   // suma de [1..i].    O(log n)
        long long s = 0;
        for (; i > 0; i -= i & (-i)) s += t[i];
        return s;
    }
    long long rango(int l, int r) {            // suma de [l..r].    O(log n)
        return query(r) - query(l - 1);
    }

    // Menor indice cuyo prefijo acumulado es >= objetivo (binary lifting).
    // Requiere valores no-negativos. O(log n). Sirve como "k-esimo elemento".
    int lowerBound(long long objetivo) {
        int pos = 0; long long acum = 0, LOG = 1;
        while ((1 << LOG) <= n) LOG++;
        for (int k = LOG - 1; k >= 0; k--) {
            int nxt = pos + (1 << k);
            if (nxt <= n && acum + t[nxt] < objetivo) { pos = nxt; acum += t[nxt]; }
        }
        return pos + 1;
    }
};

// ============================================================
// FENWICK DE RANGO: update de RANGO + query de RANGO, O(log n).
// Usa DOS BIT con el truco de diferencias. "Suma v a todo [l,r]".
// ============================================================
struct FenwickRango {
    Fenwick b1, b2;
    FenwickRango(int n) : b1(n), b2(n) {}
    void rangoUpdate(int l, int r, long long v) {     // suma v a [l,r]
        b1.update(l, v);        b1.update(r + 1, -v);
        b2.update(l, v*(l-1));  b2.update(r + 1, -v*r);
    }
    long long prefijo(int i) { return b1.query(i) * i - b2.query(i); }
    long long rango(int l, int r) { return prefijo(r) - prefijo(l - 1); }
};

// ============================================================
// SPARSE TABLE (resuelve RMQ estatico): min de rango en O(1)
// sobre arreglo INMUTABLE. Sirve igual para max o gcd (cambiar min).
// ============================================================
struct SparseTable {
    vector<vector<int>> sp; vector<int> lg;
    SparseTable(vector<int>& a) {
        int n = a.size(), K = 1; while ((1 << K) <= n) K++;
        lg.assign(n + 1, 0);
        for (int i = 2; i <= n; i++) lg[i] = lg[i / 2] + 1;
        sp.assign(K, vector<int>(n));
        sp[0] = a;                             // build: O(n log n)
        for (int k = 1; k < K; k++)
            for (int i = 0; i + (1 << k) <= n; i++)
                sp[k][i] = min(sp[k-1][i], sp[k-1][i + (1 << (k-1))]);
    }
    int minRango(int l, int r) {               // min de [l,r]: O(1)
        int k = lg[r - l + 1];
        return min(sp[k][l], sp[k][r - (1 << k) + 1]);
    }
};

int main() {
    VectorCircular vc(3);
    vc.pushBack(1); vc.pushBack(2); vc.pushFront(0);   // [0,1,2]
    cout << vc.front() << " " << vc.back() << "\n";     // 0 2
    vc.pushBack(9);                                     // lleno: descarta 0 -> [1,2,9]
    cout << vc.front() << "\n";                          // 1

    Fenwick fw(vector<long long>{1, 1, 1, 1, 1});        // build O(n)
    fw.update(2, 3);                                     // pos2 += 3
    cout << fw.rango(1, 3) << "\n";                       // 1+4+1 = 6
    cout << fw.lowerBound(5) << "\n";                     // 1er prefijo >= 5 -> 2

    FenwickRango fr(6);
    fr.rangoUpdate(2, 4, 5);                             // suma 5 a [2,4]
    cout << fr.rango(1, 6) << "\n";                       // 15

    vector<int> a = {5, 2, 8, 1, 9, 3};
    SparseTable st(a);
    cout << st.minRango(1, 4) << "\n";                   // min de [2,8,1,9] = 1
    return 0;
}

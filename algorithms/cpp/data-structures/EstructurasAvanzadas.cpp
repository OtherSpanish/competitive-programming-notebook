// Estructuras de datos avanzadas en C++ (g++ 8.1, -std=c++17).
#include <iostream>
#include <vector>
using namespace std;

// ===== VECTOR CIRCULAR (Circular Buffer) =====
// Arreglo de tamano FIJO donde los indices "dan la vuelta" con modulo.
// push/get en O(1). Util como cola de tamano fijo o ventana deslizante.
struct VectorCircular {
    vector<int> buf; int cap, ini = 0, tam = 0;
    VectorCircular(int c) : buf(c), cap(c) {}
    void push(int x) {                         // agrega al final: O(1)
        buf[(ini + tam) % cap] = x;
        if (tam < cap) tam++;
        else ini = (ini + 1) % cap;            // lleno: pisa el mas viejo
    }
    int get(int i) { return buf[(ini + i) % cap]; }   // O(1)
};

// ===== FENWICK TREE / BIT (Binary Indexed Tree) =====
// Suma de prefijos + actualizacion PUNTUAL, ambas O(log n).
// Truco lowbit: i & (-i) aisla el bit menos significativo.
struct Fenwick {
    vector<long long> t; int n;
    Fenwick(int n) : t(n + 1, 0), n(n) {}
    void update(int i, long long v) {          // suma v en la pos i (1-indexed)
        for (; i <= n; i += i & (-i)) t[i] += v;
    }
    long long query(int i) {                   // suma de [1..i]
        long long s = 0;
        for (; i > 0; i -= i & (-i)) s += t[i];
        return s;
    }
    long long rango(int l, int r) { return query(r) - query(l - 1); }
};

// ===== SPARSE TABLE (resuelve RMQ estatico) =====
// Precalcula el minimo de cada rango de largo 2^k. Consulta O(1) para
// operaciones idempotentes (min, max, gcd). Solo arreglos INMUTABLES.
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
    int minRango(int l, int r) {               // minimo de [l, r]: O(1)
        int k = lg[r - l + 1];
        return min(sp[k][l], sp[k][r - (1 << k) + 1]);
    }
};

int main() {
    VectorCircular vc(3);
    vc.push(1); vc.push(2); vc.push(3); vc.push(4);  // pisa el 1
    cout << vc.get(0) << "\n";                        // 2

    Fenwick fw(5);
    fw.update(1, 3); fw.update(3, 2);
    cout << fw.rango(1, 3) << "\n";                    // 5

    vector<int> a = {5, 2, 8, 1, 9, 3};
    SparseTable st(a);
    cout << st.minRango(1, 4) << "\n";                // min de [2,8,1,9] = 1
    return 0;
}

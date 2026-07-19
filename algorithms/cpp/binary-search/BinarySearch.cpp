// Busqueda binaria en C++ (g++ 8.1, -std=c++17): 5 usos.
// Todo O(log n) salvo reales O(iter) y strings O(L log n).
#include <algorithm>
#include <functional>
#include <iostream>
#include <string>
#include <vector>
using namespace std;

// ---- USO 1: buscar en arreglo YA ORDENADO (STL) ----  O(log n)
void enArregloOrdenado() {
    vector<int> a = {1, 3, 5, 7, 9};
    int i = lower_bound(a.begin(), a.end(), 5) - a.begin(); // primer >= 5 -> 2
    int j = upper_bound(a.begin(), a.end(), 5) - a.begin(); // primer >  5 -> 3
    bool existe = binary_search(a.begin(), a.end(), 5);     // true
}

// ---- USO 2: MENOR x en [lo,hi] que cumple ----  (F..F T..T)  O(log n)
long long buscarMenor(long long lo, long long hi, function<bool(long long)> cumple) {
    long long res = -1;                        // -1 = ninguno cumple
    while (lo <= hi) {
        long long mid = lo + (hi - lo) / 2;    // evita overflow
        if (cumple(mid)) { res = mid; hi = mid - 1; }  // sirve -> busca a la izq
        else lo = mid + 1;
    }
    return res;
}

// ---- USO 3: MAYOR x en [lo,hi] que cumple ----  (T..T F..F)  O(log n)
long long buscarMayor(long long lo, long long hi, function<bool(long long)> cumple) {
    long long res = -1;
    while (lo <= hi) {
        long long mid = lo + (hi - lo) / 2;
        if (cumple(mid)) { res = mid; lo = mid + 1; }  // sirve -> busca a la der
        else hi = mid - 1;
    }
    return res;
}

// ---- USO 4: sobre REALES (respuesta continua) ----  O(iter)
double buscarReal(double lo, double hi, function<bool(double)> cumple) {
    for (int it = 0; it < 100; it++) {         // 100 iter ~ precision de sobra
        double mid = (lo + hi) / 2;
        if (cumple(mid)) hi = mid; else lo = mid;
    }
    return lo;
}

// ---- USO 5: en arreglo ordenado de STRINGS ----  O(L log n)
void enArregloDeStrings() {
    vector<string> s = {"ana", "beto", "caro", "dan"};  // lexicografico
    int i = lower_bound(s.begin(), s.end(), string("caro")) - s.begin(); // 2
    bool existe = binary_search(s.begin(), s.end(), string("caro"));     // true
}

int main() {
    enArregloOrdenado();
    // menor x con x*x >= 50  -> 8   (7*7=49, 8*8=64)
    long long menor = buscarMenor(0, 1000000, [](long long x){ return x * x >= 50; });
    // mayor x con x*x <= 50  -> 7
    long long mayor = buscarMayor(0, 1000000, [](long long x){ return x * x <= 50; });
    // raiz de 50 (real) ~ 7.0710678
    double raiz = buscarReal(0, 50, [](double x){ return x * x >= 50; });
    enArregloDeStrings();
    cout << menor << " " << mayor << " " << raiz << "\n";
    return 0;
}

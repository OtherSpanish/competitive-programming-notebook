// Ordenamiento y seleccion en C++ (g++ 8.1, -std=c++17).
#include <algorithm>
#include <cstdlib>     // rand, srand
#include <iostream>
#include <vector>
using namespace std;

// ============================================================
// HEAP SORT: O(n log n) SIEMPRE, in-place (sin memoria extra).
// Construye un max-heap y saca el mayor al final, repetidamente.
// ============================================================
void bajar(vector<int>& a, int n, int i) {     // sift-down: O(log n)
    while (true) {
        int may = i, l = 2*i + 1, r = 2*i + 2; // hijos del nodo i
        if (l < n && a[l] > a[may]) may = l;
        if (r < n && a[r] > a[may]) may = r;
        if (may == i) break;                   // ya cumple la propiedad de heap
        swap(a[i], a[may]); i = may;           // baja e insiste
    }
}
void heapSort(vector<int>& a) {
    int n = a.size();
    // Construir el heap desde el ultimo padre hacia arriba: O(n) (no n log n).
    for (int i = n/2 - 1; i >= 0; i--) bajar(a, n, i);
    // Extraer el maximo n-1 veces:
    for (int i = n - 1; i > 0; i--) {
        swap(a[0], a[i]);                      // el mayor -> a su posicion final
        bajar(a, i, 0);                        // reordena el heap restante
    }
}

// ============================================================
// QUICKSELECT: k-esimo menor (0-indexed). Promedio O(n), peor O(n^2).
// Pivote ALEATORIO -> el peor caso es practicamente imposible.
// Version ITERATIVA (recurre a un solo lado, sin pila de recursion).
// ============================================================
int quickSelect(vector<int> a, int k) {        // recibe copia; k in [0, n-1]
    int lo = 0, hi = a.size() - 1;
    while (lo < hi) {
        int pivote = a[lo + rand() % (hi - lo + 1)];   // pivote aleatorio
        int i = lo, j = hi;
        while (i <= j) {                        // particion tipo Hoare
            while (a[i] < pivote) i++;
            while (a[j] > pivote) j--;
            if (i <= j) { swap(a[i], a[j]); i++; j--; }
        }
        if (k <= j) hi = j;                     // k en la izquierda
        else if (k >= i) lo = i;                // k en la derecha
        else return a[k];                       // k cayo en la zona del pivote
    }
    return a[lo];
}
// Nota: en C++ existe nth_element(a.begin(), a.begin()+k, a.end()); // O(n)

int main() {
    srand(12345);
    vector<int> b = {5, 2, 8, 1, 9, 3};
    heapSort(b);
    for (int x : b) cout << x << " ";
    cout << "\n";                              // 1 2 3 5 8 9

    vector<int> a = {5, 2, 8, 1, 9, 3};
    cout << quickSelect(a, 2) << "\n";         // 3er menor (k=2) = 3
    cout << quickSelect(a, 0) << "\n";         // minimo = 1
    return 0;
}

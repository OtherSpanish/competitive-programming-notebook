// Ordenamiento y seleccion en C++ (g++ 8.1, -std=c++17).
#include <algorithm>
#include <iostream>
#include <vector>
using namespace std;

// ===== HEAP SORT =====  O(n log n), IN-PLACE (sin memoria extra).
// Construye un max-heap y saca el mayor al final, repetidamente.
void bajar(vector<int>& a, int n, int i) {     // sift-down: O(log n)
    while (true) {
        int may = i, l = 2*i + 1, r = 2*i + 2;
        if (l < n && a[l] > a[may]) may = l;
        if (r < n && a[r] > a[may]) may = r;
        if (may == i) break;
        swap(a[i], a[may]); i = may;
    }
}
void heapSort(vector<int>& a) {
    int n = a.size();
    for (int i = n/2 - 1; i >= 0; i--) bajar(a, n, i);   // construye heap: O(n)
    for (int i = n - 1; i > 0; i--) {                     // extrae el max
        swap(a[0], a[i]);                                 // mayor -> al final
        bajar(a, i, 0);                                   // reordena el resto
    }
}

// ===== QUICKSELECT =====  k-esimo menor (0-indexed). Prom O(n), peor O(n^2).
// Como quicksort, pero recurre SOLO al lado que contiene la posicion k.
int quickSelect(vector<int>& a, int lo, int hi, int k) {
    if (lo == hi) return a[lo];
    int pivote = a[(lo + hi) / 2], i = lo, j = hi;
    while (i <= j) {
        while (a[i] < pivote) i++;
        while (a[j] > pivote) j--;
        if (i <= j) { swap(a[i], a[j]); i++; j--; }
    }
    if (k <= j) return quickSelect(a, lo, j, k);   // k en la izquierda
    if (k >= i) return quickSelect(a, i, hi, k);   // k en la derecha
    return a[k];
}

int main() {
    vector<int> b = {5, 2, 8, 1, 9, 3};
    heapSort(b);
    for (int x : b) cout << x << " ";
    cout << "\n";                                   // 1 2 3 5 8 9

    vector<int> a = {5, 2, 8, 1, 9, 3};
    cout << quickSelect(a, 0, a.size() - 1, 2) << "\n";  // 3er menor (k=2) = 3
    return 0;
}

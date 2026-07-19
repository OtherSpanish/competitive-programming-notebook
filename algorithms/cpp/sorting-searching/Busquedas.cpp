// Busquedas variadas en C++ (g++ 8.1, -std=c++17). Complejidad en cada una.
#include <algorithm>
#include <climits>
#include <cmath>
#include <cstdlib>
#include <iostream>
#include <vector>
using namespace std;

// 1) MISSING NUMBER: en [0..n] falta uno. O(n) tiempo, O(1) espacio.
int missingNumber(vector<int>& a) {
    long long n = a.size();                 // faltan 1 de 0..n  -> n = size
    long long esperada = n * (n + 1) / 2;   // suma de 0..n
    long long actual = 0;
    for (int x : a) actual += x;
    return (int)(esperada - actual);
}

// 2) MAX/MIN: recorrido lineal. O(n).  (devuelve {min, max})
pair<int,int> maxMin(vector<int>& a) {
    int mn = a[0], mx = a[0];
    for (int x : a) { mn = min(mn, x); mx = max(mx, x); }
    return {mn, mx};
}

// 3) MINIMUM ABSOLUTE SUM PAIR: par con |a[i]+a[j]| minimo.
//    Ordena + dos punteros. O(n log n).
long long minAbsSumPair(vector<int> a) {
    sort(a.begin(), a.end());
    int lo = 0, hi = (int)a.size() - 1;
    long long mejor = LLONG_MAX;
    while (lo < hi) {
        long long suma = (long long)a[lo] + a[hi];
        mejor = min(mejor, llabs(suma));
        if (suma < 0) lo++; else hi--;      // acerca la suma a 0
    }
    return mejor;
}

// 4) DIFFERENCE PAIR: existe par con a[j]-a[i] == d ? O(n log n) por el sort.
bool hayParConDiferencia(vector<int> a, int d) {
    sort(a.begin(), a.end());
    int n = a.size(), i = 0, j = 1;
    while (i < n && j < n) {
        if (i != j && a[j] - a[i] == d) return true;
        if (a[j] - a[i] < d) j++; else i++;
    }
    return false;
}

// 5) TERNARY SEARCH: maximo de funcion UNIMODAL en [lo,hi]. O(log n).
//    (para minimo, invierte la comparacion)
long long f(long long x) { return -(x - 3) * (x - 3) + 10; } // pico en x=3
long long ternaryMax(long long lo, long long hi) {
    while (hi - lo > 2) {
        long long m1 = lo + (hi - lo) / 3;
        long long m2 = hi - (hi - lo) / 3;
        if (f(m1) < f(m2)) lo = m1 + 1; else hi = m2 - 1;
    }
    long long best = lo;
    for (long long x = lo; x <= hi; x++) if (f(x) > f(best)) best = x;
    return best;                            // x que maximiza f
}

// 6) FIBONACCI SEARCH: en arreglo ORDENADO. O(log n).
int fibonacciSearch(vector<int>& a, int target) {
    int n = a.size();
    int fib2 = 0, fib1 = 1, fib = fib1 + fib2;
    while (fib < n) { fib2 = fib1; fib1 = fib; fib = fib1 + fib2; }
    int offset = -1;
    while (fib > 1) {
        int i = min(offset + fib2, n - 1);
        if (a[i] < target) { fib = fib1; fib1 = fib2; fib2 = fib - fib1; offset = i; }
        else if (a[i] > target) { fib = fib2; fib1 = fib1 - fib2; fib2 = fib - fib1; }
        else return i;
    }
    if (fib1 == 1 && offset + 1 < n && a[offset + 1] == target) return offset + 1;
    return -1;
}

// 7) EXPONENTIAL SEARCH: en arreglo ORDENADO. O(log n).
int exponentialSearch(vector<int>& a, int target) {
    int n = a.size();
    if (n == 0) return -1;
    if (a[0] == target) return 0;
    int i = 1;
    while (i < n && a[i] <= target) i *= 2;  // dobla el rango
    int lo = i / 2, hi = min(i, n - 1);      // binaria en el bloque
    while (lo <= hi) {
        int mid = lo + (hi - lo) / 2;
        if (a[mid] == target) return mid;
        if (a[mid] < target) lo = mid + 1; else hi = mid - 1;
    }
    return -1;
}

// 8) JUMP SEARCH: en arreglo ORDENADO. O(sqrt(n)).
int jumpSearch(vector<int>& a, int target) {
    int n = a.size();
    int paso = max(1, (int)sqrt((double)n));
    int prev = 0;
    while (prev < n && a[min(prev + paso, n) - 1] < target) prev += paso;
    for (int i = prev; i < min(prev + paso, n); i++)
        if (a[i] == target) return i;
    return -1;
}

int main() {
    vector<int> arr = {2, 5, 1, 9, 3};
    vector<int> ord = {1, 3, 5, 7, 9, 11};
    vector<int> falta = {0, 1, 3, 4};
    vector<int> sumas = {-8, -3, 2, 4, 9};
    vector<int> difs = {1, 5, 3, 8};

    cout << missingNumber(falta) << "\n";                 // 2
    auto mm = maxMin(arr); cout << mm.first << " " << mm.second << "\n"; // 1 9
    cout << minAbsSumPair(sumas) << "\n";                 // 1
    cout << hayParConDiferencia(difs, 2) << "\n";         // 1 (3-1)
    cout << ternaryMax(-10, 10) << "\n";                  // 3
    cout << fibonacciSearch(ord, 7) << "\n";              // 3
    cout << exponentialSearch(ord, 9) << "\n";            // 4
    cout << jumpSearch(ord, 11) << "\n";                  // 5
    return 0;
}

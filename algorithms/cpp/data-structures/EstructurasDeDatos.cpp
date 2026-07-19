// Estructuras de datos mas usadas en C++ (g++ 8.1, -std=c++17) para CP.
// Cada operacion lleva su complejidad algoritmica.
#include <iostream>
#include <vector>
#include <list>
#include <deque>
#include <stack>
#include <queue>
#include <set>
#include <map>
#include <unordered_set>
#include <unordered_map>
#include <functional>   // greater<> para min-heap
#include <string>
using namespace std;

void arreglosYMatrices() {
    // Arreglo: tamano FIJO. Acceso O(1); crear O(n).
    int arreglo[5] = {1, 2, 3, 4, 5};      // crear: O(n)
    arreglo[0] = 10;                       // acceso: O(1)

    // Matriz fija (2D): acceso O(1); crear O(n*m).
    int matriz[3][4] = {};
    matriz[1][2] = 7;                      // acceso: O(1)

    // Matriz dinamica (vector de vectores): crear O(n*m).
    vector<vector<int>> mat(3, vector<int>(4, 0));
    mat[1][2] = 7;                         // acceso: O(1)
}

void vectoresYListas() {
    // vector: acceso O(1); push_back O(1) amortizado;
    // insertar/borrar en medio O(n); buscar O(n).
    vector<int> v;
    v.push_back(3);                        // final: O(1) amortizado
    int x = v[0];                          // acceso: O(1)
    int n = (int) v.size();                // O(1)

    // list: insertar/borrar en extremos O(1); acceso por indice O(n).
    list<int> enlazada;
    enlazada.push_back(9);                 // O(1)
    enlazada.push_front(1);                // O(1)

    // deque: push/pop en AMBOS extremos O(1); acceso [i] O(1).
    deque<int> cola;
    cola.push_back(2);                     // O(1)
    cola.push_front(0);                    // O(1)
}

void conjuntos() {
    // unordered_set = HashSet: insert/find/erase O(1) prom. (O(n) peor).
    unordered_set<int> hset;
    hset.insert(3);                        // O(1) prom.
    bool hay = hset.count(3);              // O(1) prom.

    // set = TreeSet: ORDENADO. insert/find/erase/lower_bound O(log n).
    set<int> tset;
    tset.insert(5);                        // O(log n)
    tset.insert(1);                        // O(log n)
    int menor = *tset.begin();             // min: O(1)
    auto it = tset.lower_bound(3);         // techo: O(log n)
}

void mapasYDiccionarios() {
    // unordered_map = HashMap: insert/acceso/erase O(1) prom.
    unordered_map<string, int> hmap;
    hmap["a"] = 1;                         // O(1) prom.
    int v = hmap["a"];                     // O(1) prom.

    // map = TreeMap: ORDENADO por clave. insert/acceso/erase O(log n).
    map<string, int> tmap;
    tmap["b"] = 2;                         // O(log n)
    tmap["a"] = 1;                         // O(log n)
    auto primera = tmap.begin();           // clave menor: O(1)
}

void pilasColasYPrioridad() {
    // Pila (stack): LIFO. push/pop/top O(1). DFS iterativo, parentesis.
    stack<int> pila;
    pila.push(3);              // O(1)
    int tope = pila.top();     // O(1)
    pila.pop();                // O(1)

    // Cola (queue): FIFO. push/pop/front O(1). Base del BFS.
    queue<int> cola;
    cola.push(3);              // encolar: O(1)
    int frente = cola.front(); // O(1)
    cola.pop();                // desencolar: O(1)

    // Cola de prioridad (heap): MAX-heap por defecto.
    // push/pop O(log n); top O(1). Para Dijkstra, Prim, k-esimo mayor.
    priority_queue<int> pq;
    pq.push(5);                // O(log n)
    pq.push(1);                // O(log n)
    int mayor = pq.top();      // O(1)   (el mayor: 5)
    pq.pop();                  // O(log n)

    // MIN-heap:
    priority_queue<int, vector<int>, greater<int>> pqMin;
    pqMin.push(5);             // O(log n)   top() da el menor
}

int main() {
    arreglosYMatrices();
    vectoresYListas();
    conjuntos();
    mapasYDiccionarios();
    pilasColasYPrioridad();
    return 0;
}

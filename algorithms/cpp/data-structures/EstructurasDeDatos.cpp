// Estructuras de datos mas usadas en C++ (g++ 8.1, -std=c++17) para CP.
#include <iostream>
#include <vector>
#include <list>
#include <deque>
#include <set>
#include <map>
#include <unordered_set>
#include <unordered_map>
#include <string>
using namespace std;

void arreglosYMatrices() {
    // Arreglo: tamano FIJO, acceso O(1) por indice.
    int arreglo[5] = {1, 2, 3, 4, 5};
    arreglo[0] = 10;

    // Matriz fija (2D):
    int matriz[3][4] = {};
    matriz[1][2] = 7;

    // Matriz dinamica (vector de vectores):
    vector<vector<int>> mat(3, vector<int>(4, 0));
    mat[1][2] = 7;
}

void vectoresYListas() {
    // vector: arreglo DINAMICO (equivale a ArrayList). Acceso O(1), push_back O(1).
    vector<int> v;
    v.push_back(3);
    int x = v[0];
    int n = (int) v.size();

    // list: lista doblemente enlazada. Insertar en extremos O(1).
    list<int> enlazada;
    enlazada.push_back(9);
    enlazada.push_front(1);

    // deque: cola doble. push/pop en AMBOS extremos O(1).
    deque<int> cola;
    cola.push_back(2);
    cola.push_front(0);
}

void conjuntos() {
    // unordered_set = HashSet: sin orden, O(1) promedio.
    unordered_set<int> hset;
    hset.insert(3);
    bool hay = hset.count(3);

    // set = TreeSet: ORDENADO (arbol), O(log n). lower_bound = piso/techo.
    set<int> tset;
    tset.insert(5);
    tset.insert(1);
    int menor = *tset.begin();             // 1
    auto it = tset.lower_bound(3);         // primer valor >= 3
}

void mapasYDiccionarios() {
    // unordered_map = HashMap: sin orden, O(1) promedio.
    unordered_map<string, int> hmap;
    hmap["a"] = 1;
    int v = hmap["a"];

    // map = TreeMap: ORDENADO por clave, O(log n).
    map<string, int> tmap;
    tmap["b"] = 2;
    tmap["a"] = 1;
    auto primera = tmap.begin();           // clave menor ("a")
}

int main() {
    arreglosYMatrices();
    vectoresYListas();
    conjuntos();
    mapasYDiccionarios();
    return 0;
}

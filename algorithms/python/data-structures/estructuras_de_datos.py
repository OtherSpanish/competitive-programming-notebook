# Estructuras de datos mas usadas en Python (3.7.4) para CP.
# Cada operacion lleva su complejidad algoritmica.
from collections import deque   # cola doble (lista enlazada practica)


def arreglos_y_matrices():
    # Python no tiene arreglo fijo: se usa list. Acceso O(1); crear O(n).
    arreglo = [0] * 5            # crear: O(n)
    arreglo[0] = 10             # acceso: O(1)

    # Matriz 3x4: crear O(n*m). ! usa comprehension, NO [[0]*4]*3.
    matriz = [[0] * 4 for _ in range(3)]   # O(n*m)
    matriz[1][2] = 7            # acceso: O(1)


def listas():
    # list = arreglo dinamico. Acceso O(1); append O(1) amortizado;
    # insertar/borrar en medio O(n); buscar O(n).
    lista = []
    lista.append(3)            # final: O(1) amortizado
    x = lista[0]               # acceso: O(1)
    n = len(lista)             # O(1)

    # deque = lista enlazada / cola doble. append/pop en extremos O(1).
    cola = deque()
    cola.append(9)             # O(1)   (al final)
    cola.appendleft(1)         # O(1)   (al inicio)
    cola.popleft()             # O(1)   ;  cola[i]: O(n)


def conjuntos():
    # set = HashSet: add/in/discard O(1) promedio (O(n) peor caso).
    conjunto = set()
    conjunto.add(3)            # O(1) prom.
    hay = 3 in conjunto        # O(1) prom.

    # ! Python NO trae TreeSet built-in. Conjunto ORDENADO -> O(log n):
    from sortedcontainers import SortedSet
    ordenado = SortedSet()
    ordenado.add(5)            # O(log n)
    ordenado.add(1)            # O(log n)
    menor = ordenado[0]        # min: O(log n)


def diccionarios():
    # dict = HashMap: asignar/get/borrar O(1) promedio (O(n) peor caso).
    diccionario = {}
    diccionario["a"] = 1       # O(1) prom.
    v = diccionario.get("a", 0)  # O(1) prom. (con default)
    # ! dict conserva ORDEN DE INSERCION (3.7+), no orden por clave.

    # Mapa ORDENADO por clave -> O(log n):
    from sortedcontainers import SortedDict
    ordenado = SortedDict()
    ordenado["b"] = 2          # O(log n)
    ordenado["a"] = 1          # O(log n)
    prim = ordenado.peekitem(0)  # menor clave: O(log n)


def main():
    arreglos_y_matrices()
    listas()
    conjuntos()
    diccionarios()


main()

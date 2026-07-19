# Estructuras de datos mas usadas en Python (3.7.4) para CP.
from collections import deque   # cola doble (lista enlazada practica)


def arreglos_y_matrices():
    # Python NO tiene arreglos de tamano fijo: se usa list.
    arreglo = [0] * 5            # "arreglo" de 5 ceros
    arreglo[0] = 10

    # Matriz 3x4: ! usa comprehension, NO [[0]*4]*3 (las filas se comparten).
    matriz = [[0] * 4 for _ in range(3)]
    matriz[1][2] = 7


def listas():
    # list = arreglo dinamico (ArrayList / vector). Acceso O(1), append O(1).
    lista = []
    lista.append(3)
    x = lista[0]
    n = len(lista)

    # deque = lista enlazada / cola doble. append/pop en extremos O(1).
    cola = deque()
    cola.append(9)       # al final
    cola.appendleft(1)   # al inicio
    cola.popleft()


def conjuntos():
    # set = HashSet: sin duplicados, sin orden, O(1) promedio.
    conjunto = set()
    conjunto.add(3)
    hay = 3 in conjunto

    # ! Python NO trae TreeSet/TreeMap. Para conjunto ORDENADO:
    #   'sortedcontainers' (SortedSet) o el modulo 'bisect' sobre lista ordenada.


def diccionarios():
    # dict = HashMap / diccionario: clave->valor, O(1) promedio.
    diccionario = {}
    diccionario["a"] = 1
    v = diccionario.get("a", 0)   # con valor por defecto
    # ! desde 3.7 el dict conserva ORDEN DE INSERCION (no orden por clave).


def main():
    arreglos_y_matrices()
    listas()
    conjuntos()
    diccionarios()


main()

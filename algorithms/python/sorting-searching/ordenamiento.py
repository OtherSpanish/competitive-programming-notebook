# Ordenamiento y seleccion en Python (3.7.4).
import random


# ============================================================
# HEAP SORT: O(n log n) SIEMPRE, in-place.
# ============================================================
def bajar(a, n, i):                       # sift-down: O(log n)
    while True:
        may, l, r = i, 2*i + 1, 2*i + 2   # hijos del nodo i
        if l < n and a[l] > a[may]:
            may = l
        if r < n and a[r] > a[may]:
            may = r
        if may == i:                      # ya cumple la propiedad de heap
            break
        a[i], a[may] = a[may], a[i]
        i = may


def heap_sort(a):
    n = len(a)
    for i in range(n // 2 - 1, -1, -1):   # construye heap: O(n)
        bajar(a, n, i)
    for i in range(n - 1, 0, -1):         # extrae el max
        a[0], a[i] = a[i], a[0]           # mayor -> al final
        bajar(a, i, 0)


# ============================================================
# QUICKSELECT iterativo con pivote ALEATORIO. Prom O(n), peor O(n^2).
# ============================================================
def quick_select(a, k):                   # k in [0, n-1]; modifica a
    lo, hi = 0, len(a) - 1
    while lo < hi:
        pivote = a[random.randint(lo, hi)]    # pivote aleatorio
        i, j = lo, hi
        while i <= j:
            while a[i] < pivote:
                i += 1
            while a[j] > pivote:
                j -= 1
            if i <= j:
                a[i], a[j] = a[j], a[i]
                i += 1
                j -= 1
        if k <= j:
            hi = j                        # k en la izquierda
        elif k >= i:
            lo = i                        # k en la derecha
        else:
            return a[k]
    return a[lo]


def main():
    random.seed(12345)
    b = [5, 2, 8, 1, 9, 3]
    heap_sort(b)
    print(b)                              # [1, 2, 3, 5, 8, 9]

    print(quick_select([5, 2, 8, 1, 9, 3], 2))   # 3
    print(quick_select([5, 2, 8, 1, 9, 3], 0))   # 1


main()

# Ordenamiento y seleccion en Python (3.7.4).


# ===== HEAP SORT =====  O(n log n), IN-PLACE.
def bajar(a, n, i):                       # sift-down: O(log n)
    while True:
        may, l, r = i, 2*i + 1, 2*i + 2
        if l < n and a[l] > a[may]:
            may = l
        if r < n and a[r] > a[may]:
            may = r
        if may == i:
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


# ===== QUICKSELECT =====  k-esimo menor (0-indexed). Prom O(n), peor O(n^2).
def quick_select(a, lo, hi, k):
    if lo == hi:
        return a[lo]
    pivote = a[(lo + hi) // 2]
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
        return quick_select(a, lo, j, k)
    if k >= i:
        return quick_select(a, i, hi, k)
    return a[k]


def main():
    b = [5, 2, 8, 1, 9, 3]
    heap_sort(b)
    print(b)                              # [1, 2, 3, 5, 8, 9]

    a = [5, 2, 8, 1, 9, 3]
    print(quick_select(a, 0, len(a) - 1, 2))   # 3


main()

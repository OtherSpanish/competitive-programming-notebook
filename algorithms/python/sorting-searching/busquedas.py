# Busquedas variadas en Python (3.7.4). Complejidad en cada una.
import math


# 1) MISSING NUMBER: en [0..n] falta uno. O(n) tiempo, O(1) espacio.
def missing_number(a):
    n = len(a)
    esperada = n * (n + 1) // 2       # suma 0..n
    return esperada - sum(a)


# 2) MAX/MIN: recorrido lineal. O(n).  (devuelve (min, max))
def max_min(a):
    mn = mx = a[0]
    for x in a:
        if x < mn:
            mn = x
        if x > mx:
            mx = x
    return mn, mx


# 3) MINIMUM ABSOLUTE SUM PAIR: |a[i]+a[j]| minimo. Ordena + 2 punteros. O(n log n).
def min_abs_sum_pair(a):
    a = sorted(a)
    lo, hi = 0, len(a) - 1
    mejor = float("inf")
    while lo < hi:
        suma = a[lo] + a[hi]
        mejor = min(mejor, abs(suma))
        if suma < 0:
            lo += 1
        else:
            hi -= 1
    return mejor


# 4) DIFFERENCE PAIR: existe par con a[j]-a[i] == d ? O(n log n).
def hay_par_con_diferencia(a, d):
    a = sorted(a)
    n = len(a)
    i, j = 0, 1
    while i < n and j < n:
        if i != j and a[j] - a[i] == d:
            return True
        if a[j] - a[i] < d:
            j += 1
        else:
            i += 1
    return False


# 5) TERNARY SEARCH: maximo de funcion UNIMODAL en [lo,hi]. O(log n).
def f(x):
    return -(x - 3) ** 2 + 10         # pico en x=3


def ternary_max(lo, hi):
    while hi - lo > 2:
        m1 = lo + (hi - lo) // 3
        m2 = hi - (hi - lo) // 3
        if f(m1) < f(m2):
            lo = m1 + 1
        else:
            hi = m2 - 1
    best = lo
    for x in range(lo, hi + 1):
        if f(x) > f(best):
            best = x
    return best


# 6) FIBONACCI SEARCH: en lista ORDENADA. O(log n).
def fibonacci_search(a, target):
    n = len(a)
    fib2, fib1 = 0, 1
    fib = fib1 + fib2
    while fib < n:
        fib2 = fib1
        fib1 = fib
        fib = fib1 + fib2
    offset = -1
    while fib > 1:
        i = min(offset + fib2, n - 1)
        if a[i] < target:
            fib = fib1
            fib1 = fib2
            fib2 = fib - fib1
            offset = i
        elif a[i] > target:
            fib = fib2
            fib1 = fib1 - fib2
            fib2 = fib - fib1
        else:
            return i
    if fib1 == 1 and offset + 1 < n and a[offset + 1] == target:
        return offset + 1
    return -1


# 7) EXPONENTIAL SEARCH: en lista ORDENADA. O(log n).
def exponential_search(a, target):
    n = len(a)
    if n == 0:
        return -1
    if a[0] == target:
        return 0
    i = 1
    while i < n and a[i] <= target:
        i *= 2
    lo, hi = i // 2, min(i, n - 1)
    while lo <= hi:
        mid = (lo + hi) // 2
        if a[mid] == target:
            return mid
        if a[mid] < target:
            lo = mid + 1
        else:
            hi = mid - 1
    return -1


# 8) JUMP SEARCH: en lista ORDENADA. O(sqrt(n)).
def jump_search(a, target):
    n = len(a)
    paso = max(1, int(math.sqrt(n)))
    prev = 0
    while prev < n and a[min(prev + paso, n) - 1] < target:
        prev += paso
    for i in range(prev, min(prev + paso, n)):
        if a[i] == target:
            return i
    return -1


def main():
    ordenada = [1, 3, 5, 7, 9, 11]
    print(missing_number([0, 1, 3, 4]))             # 2
    print(max_min([2, 5, 1, 9, 3]))                 # (1, 9)
    print(min_abs_sum_pair([-8, -3, 2, 4, 9]))      # 1
    print(hay_par_con_diferencia([1, 5, 3, 8], 2))  # True
    print(ternary_max(-10, 10))                     # 3
    print(fibonacci_search(ordenada, 7))            # 3
    print(exponential_search(ordenada, 9))          # 4
    print(jump_search(ordenada, 11))                # 5


main()

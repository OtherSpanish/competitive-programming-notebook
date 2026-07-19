# Busqueda binaria en Python (3.7.4): 5 usos.
# Todo O(log n) salvo reales O(iter) y strings O(L log n).
from bisect import bisect_left, bisect_right


# ---- USO 1: lista YA ORDENADA ----  O(log n)
def en_lista_ordenada():
    a = [1, 3, 5, 7, 9]
    i = bisect_left(a, 5)    # primer indice con a[i] >= 5 -> 2
    j = bisect_right(a, 5)   # primer indice con a[i] >  5 -> 3
    existe = i < len(a) and a[i] == 5


# ---- USO 2: MENOR x que cumple ----  (F..F T..T)  O(log n)
def buscar_menor(lo, hi, cumple):
    res = -1                       # -1 = ninguno cumple
    while lo <= hi:
        mid = (lo + hi) // 2
        if cumple(mid):
            res = mid
            hi = mid - 1           # sirve -> busca a la izq
        else:
            lo = mid + 1
    return res


# ---- USO 3: MAYOR x que cumple ----  (T..T F..F)  O(log n)
def buscar_mayor(lo, hi, cumple):
    res = -1
    while lo <= hi:
        mid = (lo + hi) // 2
        if cumple(mid):
            res = mid
            lo = mid + 1           # sirve -> busca a la der
        else:
            hi = mid - 1
    return res


# ---- USO 4: sobre REALES ----  O(iter)
def buscar_real(lo, hi, cumple, iters=100):
    for _ in range(iters):
        mid = (lo + hi) / 2
        if cumple(mid):
            hi = mid
        else:
            lo = mid
    return lo


# ---- USO 5: lista ordenada de STRINGS ----  O(L log n)
def en_lista_de_strings():
    s = ["ana", "beto", "caro", "dan"]   # lexicografico
    i = bisect_left(s, "caro")           # primer >= "caro" -> 2
    existe = i < len(s) and s[i] == "caro"


def main():
    en_lista_ordenada()
    menor = buscar_menor(0, 1000000, lambda x: x * x >= 50)  # 8
    mayor = buscar_mayor(0, 1000000, lambda x: x * x <= 50)  # 7
    raiz = buscar_real(0, 50, lambda x: x * x >= 50)         # ~7.071
    en_lista_de_strings()
    print(menor, mayor, raiz)


main()

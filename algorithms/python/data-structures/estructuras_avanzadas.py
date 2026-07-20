# Estructuras de datos avanzadas en Python (3.7.4).


# ============================================================
# VECTOR CIRCULAR (Ring Buffer): cola DOBLE de capacidad fija. Todo O(1).
# ============================================================
class VectorCircular:
    def __init__(self, cap):
        self.buf = [0] * cap
        self.cap = cap
        self.ini = 0
        self.tam = 0

    def vacio(self):  return self.tam == 0
    def lleno(self):  return self.tam == self.cap
    def size(self):   return self.tam

    def push_back(self, x):               # agrega al final
        self.buf[(self.ini + self.tam) % self.cap] = x
        if self.tam < self.cap:
            self.tam += 1
        else:
            self.ini = (self.ini + 1) % self.cap   # lleno: descarta el frente

    def push_front(self, x):              # agrega al frente
        self.ini = (self.ini - 1) % self.cap
        self.buf[self.ini] = x
        if self.tam < self.cap:
            self.tam += 1

    def pop_front(self):
        v = self.buf[self.ini]
        self.ini = (self.ini + 1) % self.cap
        self.tam -= 1
        return v

    def pop_back(self):
        self.tam -= 1
        return self.buf[(self.ini + self.tam) % self.cap]

    def front(self): return self.buf[self.ini]
    def back(self):  return self.buf[(self.ini + self.tam - 1) % self.cap]
    def get(self, i): return self.buf[(self.ini + i) % self.cap]


# ============================================================
# FENWICK TREE / BIT + metodos auxiliares. lowbit = i & (-i).
# ============================================================
class Fenwick:
    def __init__(self, n_or_arr):
        if isinstance(n_or_arr, int):
            self.n = n_or_arr
            self.t = [0] * (self.n + 1)
        else:                             # construccion O(n) desde arreglo
            a = n_or_arr
            self.n = len(a)
            self.t = [0] * (self.n + 1)
            for i in range(1, self.n + 1):
                self.t[i] += a[i - 1]
                j = i + (i & (-i))
                if j <= self.n:
                    self.t[j] += self.t[i]

    def update(self, i, v):               # suma v en pos i. O(log n)
        while i <= self.n:
            self.t[i] += v
            i += i & (-i)

    def query(self, i):                   # suma [1..i]. O(log n)
        s = 0
        while i > 0:
            s += self.t[i]
            i -= i & (-i)
        return s

    def rango(self, l, r):
        return self.query(r) - self.query(l - 1)

    def lower_bound(self, objetivo):      # menor idx con prefijo >= objetivo
        pos, acum = 0, 0
        LOG = self.n.bit_length()
        for k in range(LOG, -1, -1):
            nxt = pos + (1 << k)
            if nxt <= self.n and acum + self.t[nxt] < objetivo:
                pos = nxt
                acum += self.t[nxt]
        return pos + 1


# ============================================================
# FENWICK DE RANGO: update de rango + query de rango, O(log n).
# ============================================================
class FenwickRango:
    def __init__(self, n):
        self.b1 = Fenwick(n)
        self.b2 = Fenwick(n)

    def rango_update(self, l, r, v):      # suma v a todo [l,r]
        self.b1.update(l, v);        self.b1.update(r + 1, -v)
        self.b2.update(l, v*(l-1));  self.b2.update(r + 1, -v*r)

    def prefijo(self, i):
        return self.b1.query(i) * i - self.b2.query(i)

    def rango(self, l, r):
        return self.prefijo(r) - self.prefijo(l - 1)


# ============================================================
# SPARSE TABLE (RMQ estatico): min de rango en O(1). Arreglo INMUTABLE.
# ============================================================
class SparseTable:
    def __init__(self, a):
        n = len(a)
        K = 1
        while (1 << K) <= n:
            K += 1
        self.lg = [0] * (n + 1)
        for i in range(2, n + 1):
            self.lg[i] = self.lg[i // 2] + 1
        self.sp = [[0] * n for _ in range(K)]
        self.sp[0] = a[:]
        for k in range(1, K):
            for i in range(n - (1 << k) + 1):
                self.sp[k][i] = min(self.sp[k-1][i],
                                    self.sp[k-1][i + (1 << (k-1))])

    def min_rango(self, l, r):
        k = self.lg[r - l + 1]
        return min(self.sp[k][l], self.sp[k][r - (1 << k) + 1])


def main():
    vc = VectorCircular(3)
    vc.push_back(1); vc.push_back(2); vc.push_front(0)
    print(vc.front(), vc.back())          # 0 2
    vc.push_back(9)
    print(vc.front())                     # 1

    fw = Fenwick([1, 1, 1, 1, 1])         # build O(n)
    fw.update(2, 3)
    print(fw.rango(1, 3))                 # 6
    print(fw.lower_bound(5))              # 2

    fr = FenwickRango(6)
    fr.rango_update(2, 4, 5)
    print(fr.rango(1, 6))                 # 15

    a = [5, 2, 8, 1, 9, 3]
    st = SparseTable(a)
    print(st.min_rango(1, 4))             # 1


main()

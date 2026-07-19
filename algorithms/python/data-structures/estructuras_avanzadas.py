# Estructuras de datos avanzadas en Python (3.7.4).


# ===== VECTOR CIRCULAR (Circular Buffer) =====
# Arreglo FIJO cuyos indices dan la vuelta con modulo. push/get O(1).
class VectorCircular:
    def __init__(self, cap):
        self.buf = [0] * cap
        self.cap = cap
        self.ini = 0
        self.tam = 0

    def push(self, x):                    # O(1)
        self.buf[(self.ini + self.tam) % self.cap] = x
        if self.tam < self.cap:
            self.tam += 1
        else:
            self.ini = (self.ini + 1) % self.cap   # lleno: pisa el mas viejo

    def get(self, i):                     # O(1)
        return self.buf[(self.ini + i) % self.cap]


# ===== FENWICK TREE / BIT =====
# Suma de prefijos + update puntual, ambas O(log n). lowbit = i & (-i).
class Fenwick:
    def __init__(self, n):
        self.t = [0] * (n + 1)
        self.n = n

    def update(self, i, v):               # suma v en pos i (1-indexed)
        while i <= self.n:
            self.t[i] += v
            i += i & (-i)

    def query(self, i):                   # suma [1..i]
        s = 0
        while i > 0:
            s += self.t[i]
            i -= i & (-i)
        return s

    def rango(self, l, r):
        return self.query(r) - self.query(l - 1)


# ===== SPARSE TABLE (RMQ estatico) =====
# Minimo de rangos 2^k. Consulta O(1) para min/max/gcd. Arreglo INMUTABLE.
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
        self.sp[0] = a[:]                 # build: O(n log n)
        for k in range(1, K):
            for i in range(n - (1 << k) + 1):
                self.sp[k][i] = min(self.sp[k-1][i],
                                    self.sp[k-1][i + (1 << (k-1))])

    def min_rango(self, l, r):            # O(1)
        k = self.lg[r - l + 1]
        return min(self.sp[k][l], self.sp[k][r - (1 << k) + 1])


def main():
    vc = VectorCircular(3)
    for x in [1, 2, 3, 4]:
        vc.push(x)
    print(vc.get(0))                      # 2

    fw = Fenwick(5)
    fw.update(1, 3)
    fw.update(3, 2)
    print(fw.rango(1, 3))                 # 5

    a = [5, 2, 8, 1, 9, 3]
    st = SparseTable(a)
    print(st.min_rango(1, 4))             # 1


main()

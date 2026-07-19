import sys  # E/S rápida


# TIER 1 — BÁSICO: input() + print() (fácil, LENTO en muchas lecturas)
def tier1_basico():
    entero = int(input())  # input() lee UNA línea (sin el '\n')
    decimal = float(input())
    varios = input().split()  # "10 20 30" -> ['10','20','30'] (STRINGS)
    primero, segundo, tercero = map(int, varios)  # castea todos a int


# TIER 2 — INTERMEDIO: sys.stdin/sys.stdout + split + map
def tier2_intermedio():
    entrada = sys.stdin.readline  # más rápido que input()
    salida = sys.stdout.write  # más rápido que print()

    entero, largo, decimal = entrada().split()
    entero = int(entero)
    largo = int(largo)  # ! int de precisión ARBITRARIA: no hay overflow
    decimal = float(decimal)

    piezas = []  # acumula salida y escribe UNA vez
    piezas.append(str(entero + 1))
    salida("\n".join(piezas) + "\n")


# TIER 3 — readline directo (rápido para líneas)
def tier3_readline():
    linea = sys.stdin.readline()  # incluye el '\n' al final
    entero = int(linea)  # int() ignora espacios y '\n'
    palabra = sys.stdin.readline().strip()  # ! .strip() si vas a comparar cadenas
    print(entero, palabra)


# TIER 4 — MÁS RÁPIDO: leer TODO el stdin de golpe y tokenizar
def tier4_todo_de_golpe():
    datos = sys.stdin.buffer.read().split()  # todos los bytes, partidos por espacios/saltos
    indice = 0

    def siguiente_int():
        nonlocal indice
        valor = int(datos[indice])
        indice += 1
        return valor

    entero = siguiente_int()
    largo = siguiente_int()
    sys.stdout.write(f"{entero + largo}\n")


if __name__ == "__main__":
    # Ojo Python 32-bit: límite ~2 GB de memoria por proceso.
    # Para DFS recursivo en CP sube el límite de recursión:
    sys.setrecursionlimit(300000)
    tier1_basico()
    tier2_intermedio()
    tier3_readline()
    tier4_todo_de_golpe()
    pass

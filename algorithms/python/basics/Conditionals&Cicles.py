def condicionales():
    entero = 5

    # if / elif / else   (¡se escribe 'elif', no 'else if'!)
    if entero > 0:
        print("positivo")
    elif entero < 0:
        print("negativo")
    else:
        print("cero")

    # ternario: valor_si_verdadero if condicion else valor_si_falso
    signo = "no negativo" if entero >= 0 else "negativo"

    # ! Python 3.7 NO tiene match/case (llegó en 3.10).
    # El reemplazo del switch es un dict de despacho:
    opciones = {1: "uno", 2: "dos", 3: "tres"}
    print(opciones.get(entero, "otro"))   # .get(clave, default) = como el 'default'


def ciclos():
    # for con range(inicio, fin, paso)  -> el 'fin' NO se incluye
    for i in range(5):
        print(i, end="")

    # for-each directo sobre la colección (lo idiomático en Python)
    datos = [10, 20, 30]
    for valor in datos:
        print(valor, end="")

    # enumerate: índice + valor a la vez
    for indice, valor in enumerate(datos):
        print(indice, valor)

    # while
    contador = 0
    while contador < 5:
        contador += 1

    # ! Python NO tiene do-while; se emula con while True + break
    while True:
        contador -= 1
        if contador <= 0:
            break

    # break / continue
    for i in range(10):
        if i == 3:
            continue
        if i == 6:
            break

    # EXCLUSIVO de Python: for-else / while-else
    # el 'else' corre SOLO si el ciclo terminó SIN pasar por un break
    for i in range(5):
        if i == 99:
            break
    else:
        print("terminó sin break")


def main():
    condicionales()
    ciclos()


main()

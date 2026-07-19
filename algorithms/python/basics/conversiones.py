# Conversiones, parseos y casteos de variables en Python (3.7.4).


def string_a_numero():
    entero = int("42")
    decimal = float("3.14")

    # string en otra base -> int (2do arg = base)
    binario = int("1010", 2)      # 10
    hexa = int("ff", 16)          # 255

    # ! int("3.14") LANZA error: primero float, luego int
    entero2 = int(float("3.99"))  # 3


def numero_a_string():
    entero = 42
    s = str(entero)               # "42"

    # con prefijo:
    b = bin(10)                   # "0b1010"
    h = hex(255)                  # "0xff"
    o = oct(8)                    # "0o10"

    # sin prefijo (para imprimir limpio):
    b2 = format(10, "b")          # "1010"
    h2 = format(255, "x")         # "ff"


def casteo_numerico():
    decimal = 3.99
    truncado = int(decimal)       # 3   (TRUNCA hacia 0)
    redondeado = round(3.99)      # 4   (redondeo real)
    negativo = int(-3.99)         # -3  (hacia 0, NO hacia -inf)
    piso = 7 // 2                 # 3   (division ENTERA / floor)
    real = 7 / 2                  # 3.5 (division normal SIEMPRE da float)
    flotante = float(3)           # 3.0
    booleano = bool(0)            # False (0, "", [], None -> False)


def caracteres():
    codigo = ord("A")             # 65
    letra = chr(66)               # "B"

    d = int("7")                  # 7
    d2 = ord("7") - ord("0")      # 7  (digito char -> int, rapido)

    # string <-> lista de digitos
    digitos = list(map(int, str(12345)))   # [1, 2, 3, 4, 5]
    # lista de chars -> string
    s = "".join(["h", "o", "l", "a"])      # "hola"


def main():
    string_a_numero()
    numero_a_string()
    casteo_numerico()
    caracteres()


main()

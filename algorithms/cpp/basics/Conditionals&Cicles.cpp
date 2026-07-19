#include <iostream>  // cout
#include <string>    // string
#include <vector>    // vector (para range-based for)

using namespace std;

void condicionales()
{
    int entero = 5;

    // if / else if / else
    if (entero > 0) cout << "positivo\n";
    else if (entero < 0) cout << "negativo\n";
    else cout << "cero\n";

    // ternario
    string signo = (entero >= 0) ? "no negativo" : "negativo";

    // switch: SOLO enteros o char, NO strings (a diferencia de Java)
    switch (entero)
    {
    case 1:
        cout << "uno\n";
        break;              // ! sin break hay fall-through
    case 2:
    case 3:
        cout << "dos o tres\n";
        break;
    default:
        cout << "otro\n";
    }

    // EXCLUSIVO C++17: if con inicializador; 'resto' vive solo dentro del if/else
    if (int resto = entero % 2; resto == 0) cout << "par\n";
    else cout << "impar\n";
}

void ciclos()
{
    // for clásico
    for (int i = 0; i < 5; i++) cout << i;

    // range-based for (C++11+): recorre sin índice
    vector<int> datos = {10, 20, 30};
    for (int valor : datos) cout << valor;      // copia
    for (int &valor : datos) valor *= 2;        // con & modifica el original

    // while
    int contador = 0;
    while (contador < 5) contador++;

    // do-while: al menos una vez
    do
    {
        contador--;
    }
    while (contador > 0);

    // break / continue
    for (int i = 0; i < 10; i++)
    {
        if (i == 3) continue;
        if (i == 6) break;
    }
    // ! C++ NO tiene break con etiqueta. Para salir de anidados: una bandera bool
    //   o mete los bucles en una función y usa return.
}

int main()
{
    condicionales();
    ciclos();
    return 0;
}

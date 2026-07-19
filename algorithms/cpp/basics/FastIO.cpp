#include <cstdio>    // scanf, printf, fread
#include <iostream>  // cin, cout
#include <string>    // string, getline

using namespace std;

// TIER 1 — BÁSICO: cin + cout (fácil, LENTO)
void tier1Basico()
{
    int entero;

    double decimal;
    string palabra, linea;
    cin >> entero >> decimal >> palabra;   // >> lee UN token
    // ! cin >> deja el '\n' pendiente; el primer getline sale VACÍO.
    cin.ignore();          // quema el '\n'
    getline(cin, linea);   // ahora sí lee la línea real
}

// TIER 2 — cin RÁPIDO: desactiva sync con C (ponlo 1 vez al inicio de main)
void tier2CinRapido()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    // ! si haces esto, NO mezcles cin con scanf/printf en el mismo programa
    int entero;
    cin >> entero;
    cout << entero << "\n";   // usa "\n", NUNCA endl (endl hace flush y frena)
}

// TIER 3 — MÁS RÁPIDO: lector propio por BYTES con fread (10^6-10^7 lecturas)
struct FastReader
{
    static const int TAM = 1 << 16;   // 64 KB
    char bufer[TAM];
    int puntero = 0, leidos = 0;

    int leer()
    {
        if (puntero == leidos)
        {
            leidos = (int) fread(bufer, 1, TAM, stdin);
            puntero = 0;
        }
        return leidos == 0 ? -1 : bufer[puntero++];   // -1 = EOF
    }

    int nextInt()
    {
        int resultado = 0, byteActual = leer();
        while (byteActual <= ' ') byteActual = leer();
        bool negativo = (byteActual == '-');
        if (negativo) byteActual = leer();
        while (byteActual >= '0' && byteActual <= '9')
        {
            resultado = resultado * 10 + (byteActual - '0');
            byteActual = leer();
        }
        return negativo ? -resultado : resultado;
    }

    long long nextLong()
    {
        long long resultado = 0;
        int byteActual = leer();
        while (byteActual <= ' ') byteActual = leer();
        bool negativo = (byteActual == '-');
        if (negativo) byteActual = leer();
        while (byteActual >= '0' && byteActual <= '9')
        {
            resultado = resultado * 10 + (byteActual - '0');
            byteActual = leer();
        }
        return negativo ? -resultado : resultado;
    }

    double nextDouble()
    {
        double resultado = 0, divisor = 1;
        int byteActual = leer();
        while (byteActual <= ' ') byteActual = leer();
        bool negativo = (byteActual == '-');
        if (negativo) byteActual = leer();
        while (byteActual >= '0' && byteActual <= '9')
        {
            resultado = resultado * 10 + (byteActual - '0');
            byteActual = leer();
        }
        if (byteActual == '.')
        {
            byteActual = leer();
            while (byteActual >= '0' && byteActual <= '9')
            {
                resultado += (byteActual - '0') / (divisor *= 10);
                byteActual = leer();
            }
        }
        return negativo ? -resultado : resultado;
    }

    string next()     // un token (palabra)
    {
        string palabra;
        int byteActual = leer();
        while (byteActual <= ' ') byteActual = leer();
        while (byteActual > ' ')
        {
            palabra += (char) byteActual;
            byteActual = leer();
        }
        return palabra;
    }

    string nextLine()     // línea completa
    {
        string linea;
        int byteActual = leer();
        while (byteActual != '\n' && byteActual != -1)
        {
            if (byteActual != '\r') linea += (char) byteActual;
            byteActual = leer();
        }
        return linea;
    }
};

void tier3FastReader()
{
    FastReader lector;
    long long largo = lector.nextLong();
    printf("%lld\n", largo);   // long long SIEMPRE con %lld
}

int main()
{
    // tier1Basico();
    // tier2CinRapido();
    // tier3FastReader();
    return 0;
}

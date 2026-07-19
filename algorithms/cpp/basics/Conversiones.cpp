// Conversiones, parseos y casteos de variables en C++ (g++ 8.1, -std=c++17).
#include <iostream>   // cout
#include <string>     // string, stoi, stoll, stod, to_string
#include <sstream>    // stringstream (conversiones con formato/base)
#include <cstdio>     // sprintf
using namespace std;

void stringANumero() {
    // string -> numero (PARSEO). Lanza std::invalid_argument si no es valido.
    int entero = stoi("42");
    long long largo = stoll("10000000000");
    double decimal = stod("3.14");

    // string en otra base -> int (2do arg = posicion final, 3ro = base)
    int binario = stoi("1010", nullptr, 2);   // 10
    int hexa = stoi("ff", nullptr, 16);       // 255
}

void numeroAString() {
    int entero = 42;
    string s = to_string(entero);      // "42"
    string d = to_string(3.14);        // "3.140000"

    // a otra base con stringstream:
    stringstream ss;
    ss << hex << 255;                  // hex, oct, dec como manipuladores
    string h = ss.str();               // "ff"

    // o con sprintf (estilo C):
    char bufer[32];
    sprintf(bufer, "%x", 255);         // "ff"
}

void casteoNumerico() {
    double decimal = 3.99;
    int truncado = (int) decimal;              // 3 (TRUNCA hacia 0)
    int explicito = static_cast<int>(decimal); // 3 (forma preferida en C++)
    long long largo = 10;
    int comoInt = (int) largo;                 // ! overflow si no cabe

    // ! int / int es division ENTERA:
    double mal = 7 / 2;                        // 3.0
    double bien = 7 / 2.0;                     // 3.5 (un operando double)
    double cast = (double) 7 / 2;              // 3.5 (castear antes de dividir)
}

void caracteres() {
    char c = 'A';
    int codigo = (int) c;              // 65
    char letra = (char) 66;            // 'B'

    int d = '7' - '0';                 // 7  (digito char -> int)
    char digito = (char) (5 + '0');    // '5'

    // string <-> C-string (char*)
    string s = "hola";
    const char* cs = s.c_str();        // string -> const char*
    string s2(cs);                     // char* -> string
    string uno(1, 'x');                // char -> string ("x")
}

int main() {
    stringANumero();
    numeroAString();
    casteoNumerico();
    caracteres();
    return 0;
}

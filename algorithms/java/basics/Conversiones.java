/**
 * Conversiones, parseos y casteos de variables en Java.
 * Sin imports: todo esto vive en java.lang (parseo/casteo/caracteres).
 */
public class Conversiones {

    static void stringANumero() {
        // String -> numero (PARSEO). Lanza NumberFormatException si no es valido.
        int entero = Integer.parseInt("42");
        long largo = Long.parseLong("10000000000");
        double decimal = Double.parseDouble("3.14");

        // String en otra base -> int (base 2, 8, 16, ...)
        int binario = Integer.parseInt("1010", 2);   // 10
        int hexa = Integer.parseInt("ff", 16);       // 255
    }

    static void numeroAString() {
        int entero = 42;
        String a = String.valueOf(entero);       // "42"
        String b = Integer.toString(entero);     // "42"
        String c = "" + entero;                  // "42" (concatenacion)

        // int -> String en otra base
        String bin  = Integer.toBinaryString(10);  // "1010"
        String hexa = Integer.toHexString(255);    // "ff"
        String oct  = Integer.toOctalString(8);    // "10"
        String base = Integer.toString(255, 16);   // "ff" (base arbitraria)
    }

    static void casteoNumerico() {
        double decimal = 3.99;
        int truncado = (int) decimal;             // 3  (TRUNCA hacia 0, NO redondea)
        int redondeado = (int) Math.round(3.99);  // 4  (redondeo real)

        long largo = 10L;
        int comoInt = (int) largo;                // ! overflow si largo > 2^31-1

        double aDouble = 7;                       // 7.0 (int -> double, implicito)
        // ! int / int es division ENTERA:
        double mal = 7 / 2;                       // 3.0  (se divide antes de castear)
        double bien = 7 / 2.0;                    // 3.5  (fuerza un operando a double)
    }

    static void caracteres() {
        char c = 'A';
        int codigo = (int) c;                 // 65
        char letra = (char) 66;               // 'B'

        int d = '7' - '0';                    // 7  (digito char -> int)
        char digito = (char) (5 + '0');       // '5' (int -> digito char)

        char[] arreglo = "hola".toCharArray();   // String -> char[]
        String s = new String(arreglo);          // char[] -> String
        String uno = Character.toString('x');    // char -> String
    }

    static void booleanos() {
        boolean b = Boolean.parseBoolean("true");  // true
        // ! Java NO convierte int a boolean: usa comparacion
        int x = 5;
        boolean esPar = (x % 2 == 0);               // false
    }

    public static void main(String[] args) {
        stringANumero();
        numeroAString();
        casteoNumerico();
        caracteres();
        booleanos();
    }
}

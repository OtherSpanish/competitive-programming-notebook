import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StreamTokenizer;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * FastIO — Referencia de entrada/salida en Java para competitive programming.
 * De más lento (didáctico) a más rápido (para TLE ajustado).
 */
public class FastIO {

	// TIER 1 — BÁSICO: Scanner + System.out (fácil, LENTO)
	static void tier1Basico() {
		Scanner sc = new Scanner(System.in);

		int entero = sc.nextInt();
		double decimal = sc.nextDouble();
		String palabra = sc.next(); // un token (hasta espacio/salto)

		// ! Al pasar de nextInt/nextDouble/next a nextLine hay que "quemar la línea":
		// ! nextInt deja el '\n' pendiente, así que el primer nextLine sale VACÍO.
		sc.nextLine(); // quema el resto de la línea anterior
		String linea = sc.nextLine(); // ahora sí lee la línea real
	}

	// TIER 2 — INTERMEDIO: BufferedReader + StringTokenizer + StringBuilder +
	// PrintWriter
	static void tier2Intermedio() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); // In
		// autoFlush=false: más rápido, pero OBLIGA a flush() al final.
		PrintWriter escritor = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out))); // Out

		// Varios enteros de una misma línea:
		StringTokenizer st = new StringTokenizer(br.readLine()); // Separa TODA la linea que lee en Tokens,
		// cada espacio delimita un token distinto (SIEMPRE SON STRINGS, TOCA
		// PARSEAR/CASTEAR AL TIPO DE DATO QUE QUIERAS USAR)
		int primero = Integer.parseInt(st.nextToken());
		int segundo = Integer.parseInt(st.nextToken());

		StringBuilder salida = new StringBuilder(); // acumula, evita miles de print
		salida.append(primero + segundo).append('\n');
		escritor.print(salida);

		escritor.printf("%.6f%n", 3.14159265); // formato con precisión fija
		escritor.flush(); // ! IMPRESCINDIBLE con PrintWriter buffered, si no, no imprime

		// ── ENTEROS ──────────────────────────────────────────────
		System.out.printf("%d%n", 42); // 42 entero decimal
		System.out.printf("%d%n", -7); // -7
		System.out.printf("%5d%n", 42); // " 42" ancho 5, alineado derecha
		System.out.printf("%-5d|%n", 42); // "42 |" ancho 5, alineado izquierda (flag -)
		System.out.printf("%05d%n", 42); // 00042 rellena con ceros (flag 0)
		System.out.printf("%+d%n", 42); // +42 fuerza el signo (flag +)
		System.out.printf("%,d%n", 1000000); // 1,000,000 separador de miles (flag ,)
		System.out.printf("%(d%n", -42); // (42) negativos entre paréntesis

		// ── OTRAS BASES ──────────────────────────────────────────
		System.out.printf("%x%n", 255); // ff hexadecimal (minúscula)
		System.out.printf("%X%n", 255); // FF hexadecimal (mayúscula)
		System.out.printf("%#x%n", 255); // 0xff con prefijo (flag #)
		System.out.printf("%o%n", 8); // 10 octal

		// ── DECIMALES (float / double) ───────────────────────────
		System.out.printf("%f%n", 3.14159); // 3.141590 por defecto 6 decimales
		System.out.printf("%.2f%n", 3.14159); // 3.14 2 decimales (lo más usado en CP)
		System.out.printf("%.0f%n", 3.7); // 4 0 decimales, REDONDEA
		System.out.printf("%10.2f%n", 3.14); // " 3.14" ancho 10 total
		System.out.printf("%-10.2f|%n", 3.14); // "3.14 |" alineado izquierda
		System.out.printf("%f%n", 5.0); // 5.000000 un "entero" como double igual sale con decimales
		System.out.printf("%e%n", 31415.9); // 3.141590e+04 notación científica
		System.out.printf("%g%n", 0.0001234); // 0.000123400 elige fijo o científico según magnitud

		// ── TEXTO Y CARACTERES ───────────────────────────────────
		System.out.printf("%s%n", "hola"); // hola cadena
		System.out.printf("%S%n", "hola"); // HOLA cadena en MAYÚSCULAS
		System.out.printf("%10s%n", "hi"); // " hi" ancho 10
		System.out.printf("%-10s|%n", "hi"); // "hi |" izquierda
		System.out.printf("%c%n", 'A'); // A carácter
		System.out.printf("%c%n", 66); // B código -> carácter
		System.out.printf("%b%n", true); // true booleano

		// ── LITERALES ESPECIALES ─────────────────────────────────
		System.out.printf("%n"); // salto de línea portable (mejor que \n)
		System.out.printf("100%%%n"); // 100% %% imprime un % literal

		// ── VARIOS ARGUMENTOS EN UNA LÍNEA ───────────────────────
		System.out.printf("%d + %d = %d%n", 2, 3, 5); // 2 + 3 = 5
		System.out.printf("%s tiene %.1f%n", "pi", 3.14); // pi tiene 3.1
	}

	// TIER 3 — StreamTokenizer (rápido para NÚMEROS; no ideal para líneas crudas)
	static void tier3StreamTokenizer() throws IOException {
		StreamTokenizer entrada = new StreamTokenizer(new BufferedReader(new InputStreamReader(System.in)));

		entrada.nextToken();
		int entero = (int) entrada.nval; // ! los números llegan en nval (double);
											// ! ojo con enteros > 2^53, pierden precisión
		System.out.println(entero);
	}

	// TIER 4 — MÁS RÁPIDO: lector propio por BYTES (ideal para 10^6-10^7 lecturas)
	static class FastReader {
		private final DataInputStream flujo;
		private final byte[] bufer = new byte[1 << 16]; // 64 KB
		private int puntero = 0, leidos = 0;

		FastReader() {
			flujo = new DataInputStream(System.in);
		}

		FastReader(String archivo) throws IOException {
			flujo = new DataInputStream(new FileInputStream(archivo));
		}

		int nextInt() throws IOException {
			int resultado = 0, byteActual = leer();
			while (byteActual <= ' ')
				byteActual = leer(); // saltar blancos
			boolean negativo = (byteActual == '-');
			if (negativo)
				byteActual = leer();
			while (byteActual >= '0' && byteActual <= '9') {
				resultado = resultado * 10 + (byteActual - '0');
				byteActual = leer();
			}
			return negativo ? -resultado : resultado;
		}

		long nextLong() throws IOException {
			long resultado = 0;
			int byteActual = leer();
			while (byteActual <= ' ')
				byteActual = leer();
			boolean negativo = (byteActual == '-');
			if (negativo)
				byteActual = leer();
			while (byteActual >= '0' && byteActual <= '9') {
				resultado = resultado * 10 + (byteActual - '0');
				byteActual = leer();
			}
			return negativo ? -resultado : resultado;
		}

		double nextDouble() throws IOException {
			double resultado = 0, divisor = 1;
			int byteActual = leer();
			while (byteActual <= ' ')
				byteActual = leer();
			boolean negativo = (byteActual == '-');
			if (negativo)
				byteActual = leer();
			while (byteActual >= '0' && byteActual <= '9') {
				resultado = resultado * 10 + (byteActual - '0');
				byteActual = leer();
			}
			if (byteActual == '.') {
				byteActual = leer();
				while (byteActual >= '0' && byteActual <= '9') {
					resultado += (byteActual - '0') / (divisor *= 10);
					byteActual = leer();
				}
			}
			return negativo ? -resultado : resultado;
		}

		String next() throws IOException { // un token (palabra)
			StringBuilder palabra = new StringBuilder();
			int byteActual = leer();
			while (byteActual <= ' ')
				byteActual = leer();
			while (byteActual > ' ') {
				palabra.append((char) byteActual);
				byteActual = leer();
			}
			return palabra.toString();
		}

		String nextLine() throws IOException { // línea completa
			StringBuilder linea = new StringBuilder();
			int byteActual = leer();
			while (byteActual != '\n' && byteActual != -1) {
				if (byteActual != '\r')
					linea.append((char) byteActual);
				byteActual = leer();
			}
			return linea.toString();
		}

		private int leer() throws IOException {
			if (puntero == leidos) {
				leidos = flujo.read(bufer, 0, bufer.length);
				puntero = 0;
			}
			return leidos == -1 ? -1 : bufer[puntero++]; // -1 = EOF
		}

		void close() throws IOException {
			if (flujo != null)
				flujo.close();
		}
	}

	static void tier4FastReader() throws IOException {
		FastReader lector = new FastReader();
		long largo = lector.nextLong();
		System.out.print(largo + "\n"); // salida mínima
	}

	public static void main(String[] args) throws IOException {
		// Descomenta el tier que quieras probar:
		 tier1Basico();
		 tier2Intermedio();
		 tier3StreamTokenizer();
		 tier4FastReader();
	}
}

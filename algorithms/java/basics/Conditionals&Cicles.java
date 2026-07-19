public class CondicionalesYCiclos {
	// Sin imports: if/else, switch y ciclos son del lenguaje base (java.lang).

	static void condicionales() {
		int entero = 5;

		// if / else if / else
		if (entero > 0) { // Si condicion
			System.out.println("positivo");
		} else if (entero < 0) { // Si no, entonces si esto
			System.out.println("negativo");
		} else { // Si nada se cumple
			System.out.println("cero");
		}

		// ternario: condicion ? siVerdadero : siFalso
		String signo = (entero >= 0) ? "no negativo" : "negativo";

		// switch clásico (compara contra valores exactos)
		switch (entero) {
		case 1:
			System.out.println("uno");
			break; // ! sin break "cae" al siguiente case (fall-through)
		case 2:
		case 3:
			System.out.println("dos o tres"); // varios case juntos = OR
			break;
		default:
			System.out.println("otro");
		}
	}

	static void ciclos() {
		// for clásico
		for (int i = 0; i < 5; i++)
			System.out.print(i);

		// for-each (recorre arreglos/colecciones sin índice)
		int[] arreglo = { 10, 20, 30 };
		for (int valor : arreglo)
			System.out.print(valor);

		// while
		int contador = 0;
		while (contador < 5)
			contador++;

		// do-while: ejecuta AL MENOS una vez (evalúa la condición al final)
		do {
			contador--;
		} while (contador > 0);

		// break / continue
		for (int i = 0; i < 10; i++) {
			if (i == 3)
				continue; // salta esta iteración
			if (i == 6)
				break; // corta el ciclo entero
		}

		// EXCLUSIVO de Java: break con ETIQUETA, rompe el ciclo EXTERNO desde uno
		// anidado
		externo: for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				if (i + j == 3)
					break externo;
	}

	public static void main(String[] args) {
		condicionales();
		ciclos();
	}
}

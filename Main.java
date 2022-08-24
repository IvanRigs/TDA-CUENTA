package opcionales;

import java.util.Scanner;

public class Main {

	// Variables globales (Sirve para acceder a la informacion de 10 usuarios
	// distintos).
	static String[] numCuenta = new String[10];
	static String[] contraseñaUsuario = new String[10];
	static String[] nombreUsuario = new String[10];
	static String[] sucursal = new String[10];
	static float[] saldoCuenta = new float[10];
	static Scanner cin = new Scanner(System.in);

	public static int cuenta() {// CREAR CUENTA O INGRESAR A CUENTA.
		// Variables locales
		boolean digitos = false, cuentaExistente = false;
		String numCuentaTemp, contraseñaTemp;
		int caracteres = 0, posicionCuenta = -1;
		char caracter = ' ';

		System.out.println("     ALTA CUENTA");
		System.out.println("Bienvenido!!\n");

		do {

			System.out.print("\nNumero de cuenta (16 numeros): ");
			numCuentaTemp = cin.nextLine();

			// Filtro (saber la cantidad de caracteres y si son solo numeros)
			caracteres = numCuentaTemp.length();

			if (caracteres == 16) {
				for (int i = 0; i < 16; i++) {// Evaluar caracter por caracter si es un numero utilizando codigo ASCII.
					caracter = numCuentaTemp.charAt(i);
					if (caracter >= 48 && caracter <= 57) {
						if (i == 15) {
							digitos = true;
						}
					} else {
						System.out.println("\nSolo digitos.");
						System.out.print("Numero de cuenta ingresado: " + numCuentaTemp);
						System.out.println("\nIngresa el numero de cuenta de nuevo...");
						System.out.println("\n----------------------------------------------------------\n");
						break;
					}
				}
			} else if (caracteres < 16) {
				System.out.println("\nHaz colocado menos caracteres de lo esperado.");
				System.out.print("Numero de cuenta ingresado: " + numCuentaTemp);
				System.out.println("\nIngresa el numero de cuenta de nuevo...");
				System.out.println("\n----------------------------------------------------------\n");
			} else {
				System.out.println("\nHaz colocado mas caracteres de lo esperado.");
				System.out.print("Numero de cuenta ingresado: " + numCuentaTemp);
				System.out.println("\nIngresa el numero de cuenta de nuevo...");
				System.out.println("\n----------------------------------------------------------\n");
			}

		} while (!digitos);

		for (int i = 0; i < 10; i++) {// Verificar si la cuenta ya existe
			if (numCuenta[i].equals(numCuentaTemp)) {
				System.out.println("\nEl numero de cuenta ya esta registrada.\n");
				cuentaExistente = true;
				posicionCuenta = i;
				break;
			}
		}

		if (cuentaExistente == true) {// Ingresar a cuenta existente.
			int intentoContraseña = 0;

			do {
				System.out.println("\n--------------------------------------------------------\n");
				System.out.println("\nNumero de cuenta: " + numCuenta[posicionCuenta]);
				System.out.println("\nIngrese contraseña: ");
				contraseñaTemp = cin.nextLine();
				if (contraseñaUsuario[posicionCuenta].equals(contraseñaTemp)) {
					break;
				} else if (intentoContraseña <= 5) {
					System.out.println("\nContraseña incorrecta, intente de nuevo.\n");
					intentoContraseña++;
				} else {
					System.out.println("\nCuenta bloqueada, ha exedido el numero de intentos.");
				}
			} while (intentoContraseña <= 5);
		} else {// Cuenta no existente.
			digitos = false;
		}

		if (digitos == false) {// crear cuenta nueva.

			System.out.println("\nHola " + numCuentaTemp);

			for (int i = 0; i < 10; i++) {// verificar disponibilidad de espacios(solo se puede crear 10 cuentas) y
											// asignar posicion.
				if (numCuenta[i] == "disponible") {
					numCuenta[i] = numCuentaTemp;
					posicionCuenta = i;
					saldoCuenta[posicionCuenta] = 0;
					System.out.println("Saldo: $" + saldoCuenta[posicionCuenta]);
					break;
				} else if (i == 9) {
					System.out.println("\nLo siento no tenemos disponabilidad para otra cuenta.");
				}
			}

			System.out.println("\nIngresa los siguientes datos. \n"); // Asignacion de datos del usuario en su posicion.
			System.out.print("Nombre: ");
			nombreUsuario[posicionCuenta] = cin.nextLine();
			System.out.print("Sucursal: ");
			sucursal[posicionCuenta] = cin.nextLine();
			System.out.print("Contraseña (sin espacios): ");
			contraseñaUsuario[posicionCuenta] = cin.nextLine();

		}

		System.out.println("\n\n\n\n\n\n\n\n\n\n----------------------------------------------------------\n");

		return posicionCuenta;// regresa el valor de posicion de la cuenta.
	}

	public static void deposito(int posicionCuenta) {
		float saldo = 0;
		boolean dineroMaximo = false;

		System.out.println("DEPOSITO\n");
		do {
			System.out.println("¿De cuanto sera el deposito a su cuenta?\n");
			System.out.print("$ ");
			saldo = cin.nextFloat();

			if (saldo <= 999999) {
				dineroMaximo = true;
			}else {
				System.out.println("\nHa excedido la cantidad maxima de dinero. Deposito  maximo de $999,999.99");
				System.out.println("\n------------------------------------------------------------------------------------\n");
			}

		} while (dineroMaximo == false);
		saldoCuenta[posicionCuenta] += saldo;

		System.out.println("Saldo actual: $ " + saldoCuenta[posicionCuenta]);

	}

	public static void retiro() {// TODO crear metodo retiro

	}

	public static void imprimir() {// TODO crear metodo imprimir

	}

	public static void main(String[] args) {

		for (int i = 0; i < 10; i++) { // inicializar arreglo de cadenas globales.
			numCuenta[i] = "disponible";
		}

		// variables locales
		int cerrarCuenta = 0;
		do {
			int posicionCuenta = -1, opcion = 0;
			char regresar = ' ';
			cerrarCuenta = 0;
			System.out.println("CUENTA BANCARIA\n");

			posicionCuenta = cuenta();// Ingresar o crear cuenta.

			do {// Menu de opciones.

				System.out.println("CUENTA BANCARIA\n");
				System.out.println("Bienvenido " + nombreUsuario[posicionCuenta] + " ¿Que desea realizar?\n");
				System.out.println("1. Consultar cuenta");
				System.out.println("2. Depositar");
				System.out.println("3. Retirar");
				System.out.println("4. Cerrar cuenta");
				System.out.println("5. Salir");
				opcion = cin.nextInt();

				switch (opcion) {

				case 1:// TODO Consultar cuenta
					imprimir();
					break;
				case 2:// Depositar
					deposito(posicionCuenta);
					break;
				case 3:// TODO Retirar

					break;
				case 4:// Cerrar cuenta
					cerrarCuenta = 1;
					break;

				}

				if (opcion < 4) {
					System.out.print("¿Desea regresar al menu? (y/n) ");
					regresar = cin.next().charAt(0);
				} else {
					regresar = 'n';
				}

			} while (regresar == 'y' || regresar == 'Y');
		} while (cerrarCuenta == 1);

		System.out.println("\n\n\n\n\n\nHasta luego <33");

	}

}

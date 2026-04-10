package controller;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int contador = 0;
		int answer;
		int prueba1 = 0;
		int prueba2 = 0;
		
		//Crear un bluce dowhile porque mínimo tiene que entrar una vez al bucle, aunque podría hacerse también con while,
		//porque no sabemos cuantas veces van a presionar el boton.
	do {
		
		System.out.println("Bienvenido, si quiere realizar alguna prueba de diagnosis siga las instrucciones:  "
				+ "\n 1.Si quiere realizar una Análitica de Sangre, pulse 1. \n 2.Si quiere realizar una PCR, pulse 2.");
		
		System.out.println();
		answer = sc.nextInt();
			
		while (!(answer == 0 || answer == 1 || answer == 2)) { //Creamos un bucle para que no se puedan meter otros números que no sean 0,1 o 2).
			System.err.println("Número erróneo, por favor pulse una de las dos oopciones."); //Para que quede más estético el error en rojo.
			System.out.println();
			answer = sc.nextInt();
		}
			
			if(answer == 1) {
				System.out.println("Su número de turno para la Análitica de Sangres es " + contador);
				System.out.println();
				System.out.println("==============================================================================================");
				System.out.println();
				prueba1++; //Va sumando el número de veces que pulsan el boton 1
				contador++; //Va sumando cada pulsación para acumular el turno 
				
				
			
			} else if (answer == 2) {
				System.out.println("Su número de turno para la PCR es " + contador);
				System.out.println();
				System.out.println("==============================================================================================");
				System.out.println();
				prueba2++; //Va sumando el número de veces que pulsan el boton 2.
				contador++;//Va sumando cada pulsación para acumular el turno.
				
			}
		
		
	} while (!(answer == 0)); // La condición para que se siga repitiendo el bucle entero es que no pulse 0. Si pulsa 0 printea el resumen del día.
		
		if (answer == 0) {
			System.out.println();
			System.out.println("===============================================================================");
			System.out.println("Se ha obtenido " + (prueba1 * 60) + "€ de Anaíticas de Sangre.");
			System.out.println("Se ha obtenido " + (prueba2 * 120) + "€ de PCRs.");
			System.out.println("El número de pacientes que ha tenido hoy es de: " + contador + " pacientes.");
			System.out.println("El dinero facturado total es de: " + ((prueba1* 60) + (prueba2* 120)) +"€.");
			System.out.println("===============================================================================");
		}
		
		
	sc.close();	


	}

}

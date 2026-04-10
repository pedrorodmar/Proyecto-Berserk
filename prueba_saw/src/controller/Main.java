package controller;

import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Random random = new Random();
		int num= random.nextInt();
		System.out.println("Dime un numero del 1 al 10 chaval");
		int numero1 = sc.nextInt();
		if (num==numero1) {
			System.out.println("Muy bien, HAS GANADO, toma 10 puntitos");
		}else if(numero1>=0 && numero1<4) {
			System.out.println("Te has equivocado, el numero es mayor. Tienes solo dos oportunidades mas");
		}else if(numero1>4 && numero1<11 ) {
			System.out.println("Te has equivocado, el numero es menor. Tienes solo dos oportunidades mas");
		}
		System.out.println("Toma otra oportunidad, ¿cuál es el número?");
		int numero2 = sc.nextInt();
		if (num==numero2) {
			System.out.println("Muy bien, HAS GANADO, toma 5 puntitos");
		}else if(numero2>=0 && numero2<4) {
			System.out.println("Te has equivocado, el numero es mayor. Tienes solo una oportunidades mas");
		}else if(numero2>4 && numero2<11 ) {
			System.out.println("Te has equivocado, el numero es menor. Tienes solo una oportunidades mas");
		}
		System.out.println("Toma la ultima oportunidad, ¿cuál es el número?");
		int numero3 = sc.nextInt();
		if (num==numero3) {
			System.out.println("Muy mal,como suelen decir a la tercera es la vencida, HAS GANADO, toma 2 puntitos");
		}else if(numero3>=0 && numero3<4) {
			System.out.println("Te has vuelto a equivocar,el numero que has puesto sigue siendo muy pequeño, el numero es 4 SE ACABO EL JUEGO, ahora muere!!");
		}else if(numero3>4 && numero3<11 ) {
			System.out.println("Te has vuelto a equivocar, el numero que has puesto sigue siendo muy alto, el numero es 4 SE ACABO EL JUEGO, ahora muere!!!");
		}else {
			
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}

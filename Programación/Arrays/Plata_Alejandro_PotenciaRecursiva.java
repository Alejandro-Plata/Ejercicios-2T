package helloWorld;

import java.util.Scanner;

public class Recursividad {

	public static int hacerPotencia(int base, int exponente) {
		
		int potencia; //Declaro una variable para devolver para mejorar la legibilidad del código
		
		if(exponente<0 || base<0){
		
			System.out.println("No se permiten exponentes menores a 0 ni bases negativas");
			return potencia = 0; //Para que no de error por no estar inicializada.
			
		}
		
		else if (exponente==0) {
		System.out.println("Caso base");
		potencia = 1;
		}
		
		else{
			System.out.println("Recursión con valor = " + (exponente-1));
			potencia = base*hacerPotencia(base, exponente-1);
		}
		
		return potencia;
		
	}
	
	
	public static void main(String[] args) {
		
		int base;
		int exponente;
		int resultadoPotencia;
		
		Scanner teclado = new Scanner (System.in);
		
		System.out.println("Ingrese la base de la exponenciación");
		
		base = teclado.nextInt();
		
		System.out.println("Ingrese el exponente de la exponenciación");
		
		exponente = teclado.nextInt();
		
		resultadoPotencia = hacerPotencia(base, exponente);
		
		//Validación de las condiciones
		
		if(base>=0 && exponente>=0) {
		
		System.out.println("El resultado de la exponenciación es: " + resultadoPotencia);
		
		}
		else {
			
			System.out.println("Programa finalizado.");
			
		}

	}

}

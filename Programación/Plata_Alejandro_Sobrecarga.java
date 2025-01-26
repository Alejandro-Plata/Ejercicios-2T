package helloWorld;

import java.util.Scanner;

public class Sobrecarga {
	
	public static double areaCirculo(double radio) {
		
		double area = 3.14*radio*radio;
		
		return area;
		
	}

	//Diferente número de parámetros, lo que permite la sobrecarga
	
	public static double areaCirculo(double radio, double grados) {
		
		double area = (3.14*radio*grados)/360;
		
		return area;
		
		
	}
	
	public static void main(String[] args) {
		
		byte calculo;
		double radio;
		double grados;
		double area;
		
		//do while para validar que ingresen el número solicitado
		
		do {
		
		System.out.println("Si quiere calcular el área de una circunferencia, ingrese 1.\nEn caso contrario, ingrese 2.");
		
		Scanner teclado = new Scanner(System.in);
		
		calculo = teclado.nextByte();
		
		if (calculo == 1) {
			
			System.out.println("Ingrese el radio de la circunferencia (en centímetros)");
			
			radio = teclado.nextDouble();
			
			area = areaCirculo(radio);
			
			System.out.println("El área de la circunferencia de radio " + radio + " es: " + area + " cm²");
			
			teclado.close();
			
			break;
		}
		
		else if(calculo == 2) {
			
			
			System.out.println("Ingrese el radio de la circunferencia");
			
			radio = teclado.nextDouble();
			
			System.out.println("Ingrese los grados de la circunferencia");
			
			grados = teclado.nextDouble();
			
			area = areaCirculo(radio, grados);
			
			teclado.close();
			
			System.out.println("El área del sector de circunferencia de radio " + radio + " cm" + " y de " + grados + "º" +  " es: " + area + " cm²");
			
			break;
			
		}
		
		else {
			System.out.println("Número equivocado. Inténtelo de nuevo");
		}
		
		}while(calculo !=1 | calculo !=2);
		
		
		
		
		
	}

}

package logica;

import java.util.Scanner;

public class Dni {
	
	
	public static char separarLetra(String dni) {
		
		return dni.charAt(8);
		
	}
public static String separarNumero(String dni) {
		
		return dni.substring(0,8);
		
	}
	
	public static char calcularLetraEsperada(String numeroDNI) {
		
		String letraEsperada = "TRWAGMYFPDXBNJZSQVHLCKE"; //Letras ordenadas con las posiciones de sus correspondientes valores 
		int resto = (Integer.parseInt(numeroDNI))%23;
		
		return letraEsperada.charAt(resto);
		
	}
	
	public static boolean comprobarLetra(char letra, char letraEsperada) {
		
		return letra == letraEsperada;
		
	}

	public static void main(String[] args) {
		
		String dni;
		char letraDNI;
		char letraDNIesperada;
		String numeroDNI;
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Ingrese su DNI");
		dni = teclado.nextLine();
		
		do{
			while(dni.length()!=9) { //Verifica que tenga 9 caracteres
			System.out.println("DNI incorrecto.\nIngrese su DNI");
			dni = teclado.nextLine();
			}
		teclado.close();
		
		letraDNI = separarLetra(dni.toUpperCase());//Evita que afecten las minúsculas
		numeroDNI = separarNumero(dni);
		letraDNIesperada = calcularLetraEsperada(numeroDNI);

		if (comprobarLetra(letraDNIesperada, letraDNI) == true) {
			System.out.println("El DNI es válido");
			
		}
		else {
			System.out.println("El DNI es inválido");
		}
	}while(comprobarLetra(letraDNIesperada, letraDNI) == false);//No se me ocurre como validar que solo se ingresen números en la substring (0,8) sin usar expresiones regulares
		
		
	}

}

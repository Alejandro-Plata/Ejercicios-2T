
import java.util.Scanner;

public class Matriz {

	public static void invertirValor(int [][] matriz) {
		
	  for (int i=0; i<matriz.length; i++) {
	    for (int j=0; j<matriz[0].length; j++) {
				
	      if(matriz[i][j] == 1) {//Si el valor es 1, lo transforma en 0
	          matriz[i][j] = 0; 
	      } else {
		  matriz[i][j] = 1;//Si el valor es 0, lo transforma en 1
		}		
		    
	      }
	    }	
	 }
	
	public static int contarUno(int [][] matriz) {
		int contadorUno = 0;
		
		for (int i = 0; i < matriz.length; i++) {
		  for (int j = 0; j < matriz[0].length; j++) {

		    if(matriz[i][j] == 1) { //Cada vez que encuentre un 1, aumenta el contador de unos
		      contadorUno++;
		    }	
			  
		  }	
		 }
		
		return contadorUno;	
	}
	
	public static boolean comprobarCeros(int [][] matriz) {
	
		boolean flag = true; //Asumimos que son todo ceros
		
		for (int i=0; i<matriz.length && flag == true; i++) {
			
			for (int j=0; j<matriz[0].length && flag == true; j++) {
				
				if(matriz[i][j] == 1) {
					flag=false; //Si hay un uno, devuelve false
				}
				
			}
			
		}
		return flag;
		
	}

	public static void imprimirMatriz(int [][] matriz) {
	
		for (int i=0; i<matriz.length; i++) {
		
			for (int j=0; j<matriz[0].length; j++) {
				System.out.print("["+ matriz[i][j] +"]");
			}
			System.out.println();
		}
	
	}

	public static void modificarDiagonalesACinco(int [][] matriz) { //Pinta la diagonal con 5 (solo si la matriz es cuadrada, sino lo es pinta desde la diagonal izquierda hasta que topa con el elemento de la posición n-1 (siendo n el número de columnas, si filas>columnas, o el número de filas, si columnas>filas) 
	
		for(int i=0, j=0; i<matriz.length&&j<matriz[0].length; i++, j++) {
			
			matriz[i][j] = 5;
			
		}
		
	}
	
	public static void main(String[] args) {
	
		int [][] matriz;
		int tamanhoFilas, tamanhoColumnas;
		
		Scanner teclado = new Scanner(System.in);
		
		System.out.println("Ingrese el número de filas de su matriz");
		tamanhoFilas = teclado.nextInt();
		System.out.println("Ingrese el número de columnas de su matriz");
		tamanhoColumnas = teclado.nextInt();
		
		matriz = new int [tamanhoFilas][tamanhoColumnas]; //Crea una matriz con el número de filas y columnas ingresado por el usuario
		
		//Inicializado de matriz
		for (int i=0; i<matriz.length; i++) {
			
			for (int j=0; j<matriz[0].length; j++) {
				matriz[i][j] = (int)(Math.random()*2);
			}
			
		}
		System.out.println("La matriz resultante es: ");
		System.out.println("-------------------");
		imprimirMatriz(matriz);
		System.out.println("-------------------");
		System.out.println("La matriz solo tiene ceros: "+ comprobarCeros(matriz)); //Si la matriz solo tiene ceros, muestra true; sino, muestra false.
		System.out.println("-------------------");
		System.out.println("El numero de 1 en la matriz es: "+ contarUno(matriz));
		System.out.println("-------------------");
		System.out.println("La matriz invertida es: ");
		invertirValor(matriz);
		imprimirMatriz(matriz);
		System.out.println("-------------------");
		System.out.println("La matriz modificada es: ");
		modificarDiagonalesACinco(matriz);
		imprimirMatriz(matriz);
	}

}

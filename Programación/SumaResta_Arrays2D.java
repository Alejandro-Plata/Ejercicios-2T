import java.util.Scanner;

/*
 Dada dos matrices de mismas dimensiones, retorne una matriz con la suma de ambas.
 Crear un método que retorne una matriz con la SUMA de las matrices
 Crear un método que retorne una matriz con la RESTA de las matrices
 Problar los dos métodos con matrices de dimesiones 3x4, 5x4 y 4x4
 */

public class Array2d {

	public static int[][] sumarMatrices(int [][] matriz1, int [][] matriz2, int filas, int columnas){
		
		int [][] matrizSuma = new int [filas][columnas];
		
		for(int i = 0; i<matrizSuma.length; i++) {
			for(int j=0; j<matrizSuma[0].length; j++) {
				
				matrizSuma[i][j] = matriz1[i][j] +  matriz2[i][j];
				
			}
		}
		
		return matrizSuma;
		
	}
	
public static int[][] restarMatrices(int [][] matriz1, int [][] matriz2, int filas, int columnas){
		
		int [][] matrizResta = new int [filas][columnas];
		
		for(int i = 0; i<matrizResta.length; i++) {
			for(int j=0; j<matrizResta[0].length; j++) {
				
				matrizResta[i][j] = matriz1[i][j] -  matriz2[i][j];
				
			}
		}
		
		return matrizResta;
		
	}
	
	public static int[][] inicializarMatriz(int [][] matriz){//Método que inicializa las matrices aleatoriamente entre el 1 y el 100
		
		for(int i = 0; i<matriz.length; i++) {
			for(int j=0; j<matriz[0].length; j++) {
				
				matriz[i][j] = (int) (Math.random()*100 + 1) ;
				
			}
			
		}
		return matriz;
	}
	
	public static void mostrarMatriz(int [][]matriz) {
		
		for(int i = 0; i<matriz.length; i++) {
			for(int j=0; j<matriz[0].length; j++) {
				
				System.out.print(matriz[i][j] + " ");
				
			}
			System.out.println();
		}
		
	}
	
	public static void main(String[] args) {
		
		int filas;
		int columnas;
		int [][] matriz1;
		int [][] matriz2;
		
		Scanner teclado = new Scanner (System.in);
		
		System.out.println("Ingrese el número de filas");
		filas = teclado.nextInt();
		System.out.println("Ingrese el número de columnas");
		columnas = teclado.nextInt();
		
		matriz1 = new int [filas][columnas];
		matriz2 = new int [filas][columnas];
		
		//Inicializar las matrices aleatoriamente
		inicializarMatriz(matriz1);
		inicializarMatriz(matriz2);
		System.out.println("--------------------");
		
		//Mostrar matrices
		System.out.println("Matriz 1:\n");
		mostrarMatriz(matriz1);
		System.out.println("--------------------");
		System.out.println("Matriz 2:\n");
		mostrarMatriz(matriz2);
		System.out.println("--------------------");
		//Suma matriz 1 + matriz 2 
		System.out.println("Matriz resultante de la suma: \n");
		mostrarMatriz(sumarMatrices(matriz1, matriz2, filas, columnas));
		System.out.println("--------------------");
		//Resta matriz 1 - matriz 2 
		System.out.println("Matriz resultante de la resta: \n");
		mostrarMatriz(restarMatrices(matriz1, matriz2, filas, columnas));
		
		
		
		
	}

}

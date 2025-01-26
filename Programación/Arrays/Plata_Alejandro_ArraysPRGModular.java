import java.util.Scanner;

public class ArrayEjercicio {
	
	public static double hacerMedia(int notaTotal, int numeroAsignaturas) {
			
		return notaTotal/numeroAsignaturas;
		
		
	}
	public static double notaMinima(double [] calificaciones) {
		
		
		double calificacionMinima = calificaciones[0];//Asumo que la calificación mínima es la de la primera posición
		
		for(int i = 0; i<calificaciones.length;i++) {
			
			if(calificacionMinima>calificaciones[i]) {//Si la calificación i es menor que la máxima, cambio la calificación mínima
				calificacionMinima = calificaciones[i];
			}
			
		}
		return calificacionMinima;
		
	}
	public static double notaMaxima(double [] calificaciones) {
		
		double calificacionMaxima = calificaciones[0];//Asumo que la calificación máxima es la de la primera posición
		
		for(int i = 0; i<calificaciones.length;i++) {
			
			if(calificacionMaxima<calificaciones[i]) { //Si la calificación i es mayor que la máxima, cambio la calificación máxima
				calificacionMaxima = calificaciones[i];
			}
			
		}
		return calificacionMaxima;
		
	}
	
	public static void main(String[] args) {
	
		Scanner teclado = new Scanner(System.in);
		int notaTotal = 0;
		int numeroAsignaturas;
		double [] calificaciones;
		 //Asumo que nadie tendrá más asignaturas que las que hay en el grado, así se inicializa todo a 0
	
		do {
			System.out.println("Ingresa el número de asignaturas que tengas");
			numeroAsignaturas = teclado.nextInt();
		
			if(numeroAsignaturas<0 || numeroAsignaturas>=20) {
			System.out.println("El número de asignaturas no puede ser negativo, inténtalo de nuevo");
			}
		}while(numeroAsignaturas<0 || numeroAsignaturas>=20);//Validación de que el número de asignaturas no sea negativo o mayor que el tamaño del array
		
		calificaciones = new double[numeroAsignaturas];
		
		for(int i = 0; i<numeroAsignaturas;i++) { 
			
			System.out.printf("Ingrese la nota de la asignatura %d\n",i+1);
			calificaciones[i] = teclado.nextInt();
			notaTotal += calificaciones[i];//Calcula la suma de todas las calificaciones
			
		}
		
		System.out.printf("Tu nota media es : %.2f\nTu calificación mínima es: %.2f\nTu calificación máxima es: %.2f", hacerMedia(notaTotal,numeroAsignaturas), notaMinima(calificaciones), notaMaxima(calificaciones));
		
		
		
		
	}

}

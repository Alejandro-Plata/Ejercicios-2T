import java.util.Scanner;

public class EjercicioArray {
	
	public static void main(String[] args) {
	
		int [] asignaturas = new int [7];
		Scanner teclado = new Scanner(System.in);
		double notaTotal = 0;
		double notaMedia;
		
		
		for(int i = 0; i<asignaturas.length;i++) {
			System.out.printf("Ingrese la nota %d\n",i+1);
			asignaturas[i] = teclado.nextInt();
			notaTotal += asignaturas[i];
			
		}
		notaMedia = notaTotal/7;
		
		System.out.printf("Tu nota media es : %.2f", notaMedia);
		

	}

}

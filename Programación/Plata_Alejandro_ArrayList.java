import java.util.ArrayList;
import java.util.Scanner;

public class ArrayLists {

    public static int numeroAlumnos() {
        Scanner teclado = new Scanner(System.in);
        
        System.out.println("Ingrese el número de alumnos");
        int numeroAlumnos = teclado.nextInt();
        
        return numeroAlumnos; //Devuelve el número de alumnos
    }
    
    public static void leerAlturas(ArrayList<Double> alturas, int numeroAlumnos) {
        Scanner teclado = new Scanner(System.in);
        
        for(int i= 0 ; i < numeroAlumnos; i++){
            System.out.println("Ingrese la altura del alumno " + (i+1) + " en metros:");
            alturas.add(teclado.nextDouble()); //Almacena la altura de cada alumno en cada iteración
        }
        
        teclado.close();
    }
    
    public static double calcularMedia(ArrayList<Double> alturas) {
        double mediaAltura = 0;
        
        for(int i = 0; i < alturas.size(); i++) {
            mediaAltura+=alturas.get(i);
        }

        return mediaAltura/alturas.size();
    }

    public static int calcularAlumnosAlturaSuperior(ArrayList<Double> alturas) {
        int alumnosAltos = 0;
        
        for(int i=0; i<alturas.size(); i++){//Recorre la Arraylist
            
            if(alturas.get(i) > calcularMedia(alturas)) { //Llamo al método calcularMedia para comparar las alturas con la media
               alumnosAltos++; //Si la altura de la posición i es mayor que la media, aumenta el número de alumnos altos
            }
            
        }
        
        return alumnosAltos;
    }
    
    public static int calcularAlumnosAlturaInferior(ArrayList<Double> alturas){
        int alumnosBajos = 0;
        
        for(int i=0; i<alturas.size(); i++){//Recorre la Arraylist
            
            if(alturas.get(i) < calcularMedia(alturas)) {//Llamo al método calcularMedia para comparar las alturas con la media
                alumnosBajos++; //Si la altura de la posición i es menor que la media, aumenta el número de alumnos bajos
            }
            
        }
        
        
        return alumnosBajos;
    }

    public static void mostrarResultados(ArrayList<Double> alturas) {
        System.out.println("Las alturas (en metros) son:");
        System.out.println(alturas);
        System.out.println("El número de alumnos cuya altura es superior a la media es: " + calcularAlumnosAlturaSuperior(alturas));
        System.out.println("El número de alumnos cuya altura es inferior a la media es: " + calcularAlumnosAlturaInferior(alturas));
    }
    public static void main(String[] args) {
        int numeroAlumnos = 0;
        double media = 0;
        ArrayList<Double> alturas = new ArrayList<>();

        numeroAlumnos = numeroAlumnos();
        leerAlturas(alturas, numeroAlumnos);
        media = calcularMedia(alturas);
        mostrarResultados(alturas);
        
    }

}

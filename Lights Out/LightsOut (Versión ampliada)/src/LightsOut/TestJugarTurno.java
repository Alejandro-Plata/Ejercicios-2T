package LightsOut;

import java.util.Scanner;

/**
 * Clase para probar la funcionalidad del método "jugarTurno" de la clase Partida.
 * 
 * @author Alejandro Plata Cortés
 * @since 31/03/2025
 */
public class TestJugarTurno {

	public static void main (String[] args) {
		
		// Creamos una instancia de Partida y de Jugador
		Jugador jugador = new Jugador("Jugador 1");
		jugador.setPuntuacion(20);
		Partida partida = new Partida("Test"); // Tablero de 5x5, todas las casillas apagadas
		partida.setMultijugador(false); // Modo individual para simplificar las pruebas

		Scanner teclado = new Scanner(System.in); 

		//Jugada válida en el centro del tablero
		System.out.println("Prueba: Jugada válida");
		partida.jugarTurno(partida.getTablero().getTablero(), teclado, jugador); //Fila 2, Columna 2

		//Jugada con coordenadas incorrectas
        System.out.println("\nPrueba: Coordenadas incorrectas");
        partida.jugarTurno(partida.getTablero().getTablero(), teclado, jugador); //fila 10, columna 3

        //Usar comodín con comodines disponibles
        System.out.println("\nPrueba: Usar comodín");
        jugador.setComodines(1); //Me aseguro de que el jugador tiene un comodín disponible
        partida.jugarTurno(partida.getTablero().getTablero(), teclado, jugador);
        System.out.println("Comodines restantes: " + jugador.getComodines()); 

        //Usar comodín sin comodines disponibles
        System.out.println("\nPrueba: Usar comodín sin comodines");
        jugador.setComodines(0); //Me aseguro de que el jugador no tiene comodines disponible
        partida.jugarTurno(partida.getTablero().getTablero(), teclado, jugador); 

        //Mostrar comodines disponibles
        System.out.println("\nPrueba: Mostrar comodines");
        jugador.setComodines(2);
        partida.jugarTurno(partida.getTablero().getTablero(), teclado, jugador);

        //Mostrar historial
        System.out.println("\nPrueba: Mostrar historial");
        partida.jugarTurno(partida.getTablero().getTablero(), teclado, jugador);

        //Salir del juego
        System.out.println("\nPrueba: Salir del juego");
        partida.jugarTurno(partida.getTablero().getTablero(), teclado, jugador);//Opción 00 (salir del juego)

        //Valor de opción inválido
        System.out.println("\nPrueba: Valor inválido");
        partida.jugarTurno(partida.getTablero().getTablero(), teclado, jugador); //Opción 5

        teclado.close();
		
	}
	
}

package LightsOut;

/**
 * Clase para probar la funcionalidad del método "compararPuntuaciones" de la clase Partida.
 * 
 * @author Alejandro Plata Cortés
 * @since 31/03/2025
 */
public class TestCompararPuntuaciones {

	public static void main (String[] args) {
		
		//Creamos instancias de la clase Partida y Jugador para comparar las puntuaciones
        Partida partida = new Partida("Test");
        Jugador jugador1 = new Jugador("Jugador 1");
        Jugador jugador2 = new Jugador("Jugador 2");
        
        //Empate (ambos pierden)
        jugador1.setPuntuacion(15);
        jugador2.setPuntuacion(15);
        partida.compararPuntuaciones(jugador1, jugador2);
        
        System.out.println("\nPrueba: Empate");
        System.out.println("El jugador 1 pierde: " + !jugador1.getEsGanador());
        System.out.println("El jugador 2 pierde: " + !jugador2.getEsGanador());
        
        //Jugador 1 pierde
        jugador1.setPuntuacion(10);
        jugador2.setPuntuacion(20);
        partida.compararPuntuaciones(jugador1, jugador2);
        
        System.out.println("\nPrueba: Jugador 1 pierde");
        System.out.println("El jugador 1 pierde: " + !jugador1.getEsGanador());
        System.out.println("El jugador 2 gana: " + jugador2.getEsGanador());
        System.out.println("\n");
        
        //Jugador 2 pierde
        jugador1.setPuntuacion(20);
        jugador2.setPuntuacion(10);
        partida.compararPuntuaciones(jugador1, jugador2);
        
        System.out.println("\nPrueba: Jugador 2 pierde");
        System.out.println("El jugador 1 gana: " + jugador1.getEsGanador());
        System.out.println("El jugador 2 pierde: " + !jugador2.getEsGanador());
        System.out.println("\n");
        
		
	}
	
}

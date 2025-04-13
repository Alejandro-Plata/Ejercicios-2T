package LightsOut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Clase para probar el método configurarJuego de la clase Partida.
 * @author: Alejandro Plata Cortés
 * @since: 31/03/2025
 */
public class TestConfigurarJuego {

	public static void main(String[] args) {
		
		/* Haciendo estas pruebas, me he dado cuenta de que el atributo tablero en la clase
		 * Partida debería llamarse diferente, para mejorar la legibilidad de la prueba. */
		
		//Creamos una instancia de Partida para probar el método configurarJuego
        Partida partida = new Partida("Test de configuración");

        //Configuración correcta con modo Aleatorio
        //Método estático idéntico, que recibe 2 parámetros para facilitar las pruebas
        try {
			configurarJuego("src/LightsOut/tests/resources/config1.txt", partida);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // Verificar que la configuración se ha aplicado correctamente
        System.out.println("Caso 1: Configuración Aleatoria");
        System.out.println("Tamaño: " + (partida.getTablero().getLongitud() == 5));
        System.out.println("Modo: " + (partida.getModo().equalsIgnoreCase("Aleatorio")));
        System.out.println("Casillas activas: " + (partida.getTablero().getCasillasActivas() == 10));
        System.out.println("Duración: " + (partida.getTiempoMaximo() == 120));
        
        //Verificamos que el tablero NO sea la identidad (tablero por defecto)
        partida.getTablero().pintarTablero();
        
        //Configuración correcta con modo Personalizado
        try {
			configurarJuego("src/LightsOut/tests/resources/config2.txt", partida);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        //Verificamos que la configuración se ha aplicado correctamente
        System.out.println("\nCaso 2: Configuración Personalizada");
        System.out.println("Tamaño: " + (partida.getTablero().getLongitud() == 4));
        System.out.println("Modo: " + (partida.getModo().equalsIgnoreCase("Personalizado")));
        System.out.println("Tablero (0, 0): " + (partida.getTablero().getTablero()[0][0].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Tablero (1, 1): " + (partida.getTablero().getTablero()[1][1].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Tablero (2, 2): " + (partida.getTablero().getTablero()[3][0].getEstado() == Tablero.Casilla.Estado.APAGADO));

        //Verificamos que el tablero SEA la identidad (tablero por defecto)
        partida.getTablero().pintarTablero();
        
        //Configuración incorrecta con letras en lugar de 0 o 1   
        try {
        	configurarJuego("src/LightsOut/tests/resources/config3.txt", partida);
        	System.out.println("Éxito. No debería.");
        } catch (Exception e) {
            System.out.println("Error, formato inválido");
        }

        //Configuración incorrecta con archivo no encontrado
        try {
        	configurarJuego("archivoNoEncontrado.txt", partida);
        	System.out.println("Éxito. No debería.");
        } catch (IOException e) {
            System.out.println("Error, archivo no encontrado.");
        }

	}
	/* Traemos el método configurarJuego como método estático para las pruebas. He creado un constructor específico para 
	   realizar los tests en la clase Partida */
	public static void configurarJuego(String archivo, Partida partida) throws IOException {
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
			String linea;
			int contadorTablero = 0; //Indica la posición del tablero en la que irá cada casilla dibujada con 0 o 1

			//Actualiza el texto dentro del bucle, lee línea a línea el documento
			while ((linea = lector.readLine()) != null) {

			    if (linea.startsWith("Tamaño:")) {
			        //Inicializo el tablero aquí porque es necesario que se indique la longitud del tablero para su correcta inicialización
			        partida.setTablero(new Tablero(Integer.parseInt(linea.split(":")[1].trim()))); //Separamos el texto por los 2 puntos y le quitamos los espacios en blanco

			    } else if (linea.startsWith("Modo:")) {

			        partida.setModo(linea.split(":")[1].trim());

			    } else if (linea.startsWith("Casillas activas:")) {

			        if (partida.getModo().equalsIgnoreCase("Aleatorio")) { //Si no es aleatorio, se ignorará
			        	partida.getTablero().setCasillasActivas(Integer.parseInt(linea.split(":")[1].trim()));
			        }

			    } else if (linea.startsWith("Duración:")) {

			    	partida.setTiempoMaximo(Integer.parseInt(linea.split(":")[1].trim()));

			    } else if (linea.startsWith("Tablero:") && partida.getModo().equalsIgnoreCase("Personalizado")) {
			        int[] contenidoTablero = new int[partida.getTablero().getLongitud() * partida.getTablero().getLongitud()];

			        //Lee línea a línea la sección del fichero debajo de "Tablero:" hasta que no haya más líneas escritas
			        while ((linea = lector.readLine()) != null && contadorTablero < contenidoTablero.length) {

			            for (int i = 0; i < linea.length(); i++) {

			                if (linea.charAt(i) != '0' && linea.charAt(i) != '1') {
			                    throw new ArithmeticException("El tablero debe completarse con 1 y 0 (sin espacios entre medias)");
			                }

			                contenidoTablero[contadorTablero] = Character.getNumericValue(linea.charAt(i)); //Transforma el char en un valor entero
			                contadorTablero++;
			            }
			        }

			        //Completamos el tablero con las casillas que están en su contenido:
			        for (int i = 0; i < partida.getTablero().getTablero().length; i++) {
			            for (int j = 0; j < partida.getTablero().getTablero()[i].length; j++) {
			            	partida.getTablero().getTablero()[i][j] = partida.getTablero().new Casilla(contenidoTablero[i * partida.getTablero().getTablero()[i].length + j]);
			            }
			        }

			    }

			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        if (partida.getModo().equalsIgnoreCase("Aleatorio")) {
            partida.getTablero().inicializarTablero(partida.getTablero().getTablero()); //Modificamos el tablero después de los cambios
        }

    }

}

package LightsOut;

import java.io.*;
import java.util.Scanner;

/**
 * La clase Partida gestiona el flujo principal del programa.
 * Permite configurar partidas individuales y multijugador, gestionar turnos,
 * puntuaciones, comodines, historial de partidas y verificar el estado del juego.
 * Además, incluye la opción de configurar el estado inicial de la partida a través
 * de un fichero de carga.
 * Utiliza la clase Tablero para interactuar con el tablero de juego.
 *
 * @author: Alejandro Plata Cortés
 * @since: 31/03/2025
 */
public class Partida {

    private boolean partidaTerminada;
    private int tiempoMaximo;
    private long tiempoInicial;
    private String modo;
    private Tablero tablero;

    /* * * * * * * * * * * *
     *    Constructores    *
     * * * * * * * * * * * */

    //Constructor que recibe la longitud, el número de casillas activas y el tiempo de partida

    /**
     * Crea una instancia de la clase Partida.
     * @param longitud Tamaño del tablero (número de filas).
     * @param casillasActivas Número de casillas activas al inicio de la partida.
     * @param tiempoMaximo Duración de la partida, expresada en segundos.
     */
    public Partida(int longitud, int casillasActivas, int tiempoMaximo) {
        setTiempoMaximo(tiempoMaximo);
        this.tiempoInicial = System.currentTimeMillis() / 1000;
        this.tablero = new Tablero(longitud, casillasActivas);
        this.tablero.inicializarTablero(this.tablero.getTablero());
       
        iniciarPartida(this.tablero.getTablero());
    }

    //Constructor desde el fichero de configuración

    /**
     * Crea una instancia de la clase Partida a partir del fichero de configuración.
     */
    public Partida() {
        this.tiempoInicial = System.currentTimeMillis() / 1000;

        configurarJuego();

        iniciarPartida(this.tablero.getTablero());
    }
    
    /**
     * Constructor para tests
     */
    public Partida(String test) {}

    /* * * * * * * * * * * *
     *  Getters y Setters  *
     * * * * * * * * * * * */

    //Getter personalizado que permite saber cuántos segundos quedan (a implementar en la partida)
    public long getSegundosRestantes() { return this.tiempoMaximo - temporizador(); }
    
    public int getTiempoMaximo() { return tiempoMaximo; }
    
	public void setTablero(Tablero tablero) { this.tablero = tablero; }

    public Tablero getTablero() { return tablero; }
    
    public String getModo() { return this.modo; }

    //El encapsulamiento me permite validar qué valor recibe el atributo this.modo. Si no es aleatorio o personalizado
    public void setModo(String modo) {

        if (!(modo.equalsIgnoreCase("Aleatorio") || modo.equalsIgnoreCase("Personalizado"))) {
            throw new IllegalArgumentException("El modo debe ser 'Aleatorio' o 'Personalizado', no se conoce el modo " + modo);
        }

        this.modo = modo;
    }

    //El encapsulamiento me permite validar qué valor recibe el atributo this.tiempoMaximo, de forma que no sea negativo o 0
    public void setTiempoMaximo(int tiempoMaximo) {
        if (tiempoMaximo <= 0) { throw new IllegalArgumentException("La duración de la partida no puede ser 0 o negativa"); }

        this.tiempoMaximo = tiempoMaximo;
    }

    public boolean isPartidaTerminada() { return partidaTerminada; }

    public void setPartidaTerminada(boolean partidaTerminada) { this.partidaTerminada = partidaTerminada; }

    /* * * * * * * * * * * *
     *       Métodos       *
     * * * * * * * * * * * */

    /**
     * Devuelve el tiempo que ha pasado desde que inició la partida.
     */
    public long temporizador() {
        return (System.currentTimeMillis() / 1000) - this.tiempoInicial;
    }

    /**
     * Lee el fichero de configuración y asigna valores a los atributos correspondientes.
     * @throws IOException
     */
    public void configurarJuego() {
        try (BufferedReader lector = new BufferedReader(new FileReader("resources/config.txt"))) {
			String linea;
			int contadorTablero = 0; //Indica la posición del tablero en la que irá cada casilla dibujada con 0 o 1

			//Actualiza el texto dentro del bucle, lee línea a línea el documento
			while ((linea = lector.readLine()) != null) {

			    if (linea.startsWith("Tamaño:")) {
			        //Inicializo el tablero aquí porque es necesario que se indique la longitud del tablero para su correcta inicialización
			        this.tablero = new Tablero(Integer.parseInt(linea.split(":")[1].trim())); //Separamos el texto por los 2 puntos y le quitamos los espacios en blanco

			    } else if (linea.startsWith("Modo:")) {

			        setModo(linea.split(":")[1].trim());

			    } else if (linea.startsWith("Casillas activas:")) {

			        if (getModo().equalsIgnoreCase("Aleatorio")) { //Si no es aleatorio, se ignorará
			            this.tablero.setCasillasActivas(Integer.parseInt(linea.split(":")[1].trim()));
			        }

			    } else if (linea.startsWith("Duración:")) {

			        setTiempoMaximo(Integer.parseInt(linea.split(":")[1].trim()));

			    } else if (linea.startsWith("Tablero:") && getModo().equalsIgnoreCase("Personalizado")) {
			        int[] contenidoTablero = new int[this.tablero.getLongitud() * this.tablero.getLongitud()];

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
			        for (int i = 0; i < this.tablero.getTablero().length; i++) {
			            for (int j = 0; j < this.tablero.getTablero()[i].length; j++) {
			                tablero.getTablero()[i][j] = tablero.new Casilla(contenidoTablero[i * tablero.getTablero()[i].length + j]);
			            }
			        }

			    }

			}

			if (getModo().equalsIgnoreCase("Aleatorio")) {
			    this.tablero.inicializarTablero(this.tablero.getTablero()); //Modificamos el tablero después de los cambios
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    /**
     * Módulo principal de la clase. Permite realizar jugadas sobre el tablero y verifica el estado del mismo, así como el tiempo restante.
     * @param tablero Tablero de juego
     */
    public void iniciarPartida(Tablero.Casilla[][] tablero) {
        Scanner teclado = new Scanner(System.in);
        int opcion;

        System.out.println("\nBienvenido a Lights Out.\n");
        this.tablero.pintarTablero(); //Lo pinta por primera vez

        //Permite inicializar el tablero sin tener en cuenta el tiempo
        System.out.println("\nTiempo restante: " + getSegundosRestantes());

        do {
            System.out.println("1. Realizar jugada.\n00. Salir de la partida");
            opcion = teclado.nextInt();

            switch (opcion) {
                case 1 -> {
                    System.out.println("\nIntroduce la fila y columna de la luz que desea apagar o encender (separados por un espacio).\n" +
                            "Si quieres terminar la partida, ingresa '00':");
                    int fila = teclado.nextInt();
                    int col = teclado.nextInt();


                    if (fila < 0 || fila >= tablero.length || col < 0 || col >= tablero.length) {
                        System.out.println("Coordenadas incorrectas, inténtalo de nuevo.");
                        continue; //Pasa a la siguiente iteración del bucle directamente
                    }

                    this.tablero.cambiarLuces(fila, col, tablero);
                    verificarResultado(tablero);

                    this.tablero.pintarTablero();
                    System.out.println("\nTiempo restante: " + Math.max(0, getSegundosRestantes())); //Math.max evita que el tiempo restante sea negativo);
                }
                case 00 -> {
                    System.out.println("Una lástima que tengas que irte");
                    setPartidaTerminada(true);
                }
            }

        } while (!isPartidaTerminada());

        teclado.close();
        
    }

    /**
     * Verifica que se hayan apagado todas las luces o que se haya acabado el tiempo.
     * @param tablero Tablero de juego.
     */
    public void verificarResultado(Tablero.Casilla[][] tablero) {

        if (this.tablero.verificarTablero(tablero)) {
            System.out.println("\u001B[33m¡Has ganado, enhorabuena!\u001B[0m");

            setPartidaTerminada(true);
        }
        //Por si ganas justo cuando el tiempo se ha terminado
        if (getSegundosRestantes() <= 0 && !partidaTerminada) {
            System.out.println("\u001B[31m¡¡¡Tiempo!!! Lo siento, has perdido.\u001B[0m");

            setPartidaTerminada(true);
        }

    }
    
}






package LightsOut;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * La clase Partida gestiona el flujo principal del programa.
 * Permite configurar partidas individuales y multijugador, gestionar turnos,
 * puntuaciones, comodines, historial de partidas y verificar el estado del juego.
 * Además, incluye la opción de configurar el estado inicial de la partida a través
 * de un fichero de carga.
 * Utiliza la clase Tablero para interactuar con el tablero de juego.
 *
 * @author Alejandro Plata Cortés
 * @since 31/03/2025
 */
public class Partida {

    private boolean partidaTerminada;
    private boolean multijugador;
    private int dificultad;
    private long tiempoInicial;
    private int tiempoMaximo;
    private static int numeroPartidas = 0; //Atributo estático ya que no está ligado a una instancia de la clase
    private static int numeroPartidasMulti = 0;
    private String modo;
    private Tablero tablero;

    /* * * * * * * * * * * *
     *    Constructores    *
     * * * * * * * * * * * */

    /**
     * Crea una instancia de la clase Partida.
     * @param longitud Tamaño del tablero (número de filas).
     * @param casillasActivas Número de casillas activas al inicio de la partida.
     * @param tiempoMaximo Tiempo máximo, expresado en segundos.
     * @param dificultad Valor entero entre 1 y 3.
     */
    public Partida(int longitud, int casillasActivas, int tiempoMaximo, int dificultad) {
        this.tiempoInicial = System.currentTimeMillis() / 1000;
        this.tablero = new Tablero(longitud, casillasActivas);
        setTiempoMaximo(tiempoMaximo);
        setDificultad(dificultad);
        
        try {
            iniciarPartida(this.tablero.getTablero());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        
    }

    /**
     * Crea una instancia de la clase Partida a partir del fichero de configuración.
     * @param multijugador Valor booleano que indica el modo de juego.
     */
    public Partida(boolean multijugador) {
        setMultijugador(multijugador);
        this.tiempoInicial = System.currentTimeMillis() / 1000;
       
            configurarJuego();
        
        if (isMultijugador()) {
            try {
                iniciarPartidaMulti(this.tablero.getTablero());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                iniciarPartida(this.tablero.getTablero());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
    
    /**
     * Constructor para tests, tablero con todas las luces apagadas, 5x5
     */
    public Partida(String test) {
    	
    	this.tiempoInicial = System.currentTimeMillis() / 1000;
    	this.tablero = new Tablero();
    	
    	setDificultad(1);
    	setTiempoMaximo(180);
    }

    /* * * * * * * * * * * *
     *  Getters y Setters  *
     * * * * * * * * * * * */

    //Getter personalizado que permite saber cuántos segundos quedan (a implementar en la partida)
    public long getSegundosRestantes() { return this.tiempoMaximo - temporizador(); }

    public String getModo() { return this.modo; }

	public Tablero getTablero() { return this.tablero; }
    
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

    public boolean isMultijugador() {
        return multijugador;
    }


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
     * Lee el fichero de configuración y asigna valores a los atributos correspondientes. Si alguno no se especifica, se inicializa por defecto
     */
    public void configurarJuego() {
        String archivo = "resources/config.txt";
        try (BufferedReader lector = new BufferedReader(new FileReader(archivo))) {
			String linea;
			String fragmentoLectura = "";
			int contadorTablero = 0; //Indica la posición del tablero en la que irá cada casilla dibujada con 0 o 1

			//Actualiza el texto dentro del bucle, lee línea a línea el documento
			while ((linea = lector.readLine()) != null) {
				
				if (linea.split(":").length > 1) { fragmentoLectura = linea.split(":")[1].trim(); }
			    
				if (linea.startsWith("Tamaño:")) {
			        //Inicializo el tablero aquí porque es necesario que se indique la longitud del tablero para su correcta inicialización
			        this.tablero = new Tablero(fragmentoLectura.equals("") ? 5 : Integer.parseInt(linea.split(":")[1].trim())); //Separamos el texto por los 2 puntos y le quitamos los espacios en blanco

			    } else if (linea.startsWith("Modo:")) {

			        setModo(fragmentoLectura.equals("") ? "Personalizado" : linea.split(":")[1].trim()); //El modo por defecto es personalizado

			    } else if (linea.startsWith("Casillas activas:")) {

			        if (getModo().equalsIgnoreCase("Aleatorio")) { //Si no es aleatorio, se ignorará
			            this.tablero.setCasillasActivas(fragmentoLectura.equals("") ? (int) (Math.random() * getTablero().getLongitud() * getTablero().getLongitud())
			            		                                                    : Integer.parseInt(fragmentoLectura));
			           
			        }

			    } else if (linea.startsWith("Duración:")) {

			        setTiempoMaximo(fragmentoLectura.equals("") ? 180 : Integer.parseInt(linea.split(":")[1].trim()));

			    } else if (linea.startsWith("Dificultad:")) {
			    	
			        setDificultad(fragmentoLectura.equals("") ? 2 : Integer.parseInt(linea.split(":")[1].trim()));
			        
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
			        for (int i = 0; i < tablero.getTablero().length; i++) {
			            for (int j = 0; j < tablero.getTablero()[i].length; j++) {
			                tablero.getTablero()[i][j] = tablero.new Casilla(contenidoTablero[i * tablero.getTablero()[i].length + j]);
			            }
			        }

			    }

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
        if (getModo().equalsIgnoreCase("Aleatorio")) {
            this.tablero.inicializarTablero(this.tablero.getTablero()); //Modificamos el tablero después de los cambios
        }

    }

    /**
     * Gestiona la partida en el modo individual
     * @param tablero
     * @throws IOException
     */
    public void iniciarPartida(Tablero.Casilla[][] tablero) throws IOException {
        Scanner teclado = new Scanner(System.in);
        Jugador indiv = new Jugador("jugador");
        leerNumeroPartidas("resources/numeroPartidas.txt");
        setComodines(indiv);
        setPuntuacion(indiv);

        System.out.println("\nBienvenido a Lights Out.\n");
        this.tablero.pintarTablero(); //Lo pinta por primera vez

        do {
            jugarTurno(tablero, teclado, indiv);
            verificarTiempo();
        } while(!isPartidaTerminada());

        guardarHistorial("resources/historial.txt", "resources/numeroPartidas.txt", indiv);
    }

    /* * * * * * * * * * * * * * *
     *        Ampliaciones       *
     * * * * * * * * * * * * * * */


    /**
     * Menú de opciones del modo individual
     */
    public void menu (){

        System.out.println("\nMenú de opciones\n"
                + "1. Jugar.\n"
                + "2. Ver comodines disponibles\n"
                + "3. Utilizar comodín\n"
                + "4. Consultar historial de partidas.\n"
                + "00. Finalizar partida.");
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *          Ampliación: Dificultad, puntuaciones y comodines           *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    public int getDificultad() { return dificultad; }

    //Validación del valor ingresado de dificultad
    public void setDificultad(int dificultad) {

        if (dificultad < 1 || dificultad > 3) { throw new IllegalArgumentException("No se reconoce ese nivel de dificultad."); }

        this.dificultad = dificultad;
    }

    /**
     * Asigna una puntuación a cada jugador según el nivel de dificultad.
     * @param jugadores
     */
    public void setPuntuacion(Jugador ... jugadores) {

        switch(getDificultad()) {
            case 1 -> {
                jugadores[0].setPuntuacion(5 * this.tablero.getLongitud());
                if (jugadores.length > 1) { jugadores[1].setPuntuacion(5 * this.tablero.getLongitud());  }
            }
            case 2 -> {
                jugadores[0].setPuntuacion(4 * this.tablero.getLongitud());
                if (jugadores.length > 1) { jugadores[1].setPuntuacion((4 * this.tablero.getLongitud()));  }
            }
            case 3 -> {
                jugadores[0].setPuntuacion(3* this.tablero.getLongitud());
                if (jugadores.length > 1) { jugadores[1].setPuntuacion(3 * this.tablero.getLongitud()); }
            }
        }

    }

    public void setComodines(Jugador ... jugadores) {
        //No es necesario incluir el caso 3 (dificultad difícil) ya que no hay comodines en esa dificultad
        switch(getDificultad()) {
            case 1 -> {
                jugadores[0].setComodines(2);
                if (jugadores.length > 1) { jugadores[1].setComodines(2); }
            }
            case 2 -> {
                jugadores[0].setComodines(1);
                if (jugadores.length > 1) { jugadores[1].setComodines(1); }
            }
        }
    }


    /**
     * Utilizar un comodín, lo consume y apaga el 10% de las casillas del tablero.
     * Además, devuelve el número de luces que se apagan. Utilizar un comodín
     * hace que pierdas el turno, pero no disminuye tu puntuación.
     *
     * @param jugadorTurno Jugador que tiene el turno.
     * @return int
     */
    public int utilizarComodin(Jugador jugadorTurno) {

        int interruptores = (int) Math.floor(tablero.getLongitud() * tablero.getLongitud() * 0.1); //el 10% de la longitud al cuadrado

        jugadorTurno.setComodines(jugadorTurno.getComodines() - 1);

        for (Tablero.Casilla[] casillas : this.tablero.getTablero()) {
            for (int j = 0; j < this.tablero.getTablero()[0].length; j++) {
                if (casillas[j].getEstado() == Tablero.Casilla.Estado.ENCENDIDO && interruptores >= 1) {
                    casillas[j].setCasilla(0); //Apaga la casilla
                    interruptores--;
                }
            }
        }

        return (int) Math.floor(tablero.getLongitud() * tablero.getLongitud() * 0.1);
    }

    /* * * * * * * * * * * * * * * * * * * * * * *
     *          Ampliación: Multijugador         *
     * * * * * * * * * * * * * * * * * * * * * * */

    public void setMultijugador(boolean multijugador) {
        this.multijugador = multijugador;
    }

    /**
     * Asigna el turno en función de si es una partida individual o multijugador. Recibe una variable
     * de tipo scanner que permite procesar lecturas de teclado. Devuelve true si el turno se termina
     * 
     * @param tablero Tablero de juego.
     * @param teclado Valor ingresado por consola que define el contenido de la variable opcion.
     * @param jugadorTurno Jugador que tiene el turno.
     * @return boolean
     */
    public boolean jugarTurno(Tablero.Casilla[][] tablero, Scanner teclado, Jugador jugadorTurno) {
    	long tiempoTurno = getSegundosRestantes();

        // Si es individual, mostramos el menú sin pasar el jugador como argumento
        if (isMultijugador()) {
            menu(jugadorTurno);
        } else {
            menu();
        }

        int opcion = teclado.nextInt();

        switch (opcion) {
            case 1 -> {
                System.out.println("\nIntroduce la fila y columna de la luz que desea apagar o encender (separados por un espacio):");
                int fila = teclado.nextInt();
                int col = teclado.nextInt();

                if (fila < 0 || fila >= tablero.length || col < 0 || col >= tablero.length) {
                    System.out.println("Coordenadas incorrectas, inténtalo de nuevo.");
                    return false;
                }

                this.tablero.cambiarLuces(fila, col, tablero);

                verificarResultado(tablero, jugadorTurno);
                this.tablero.pintarTablero();
                System.out.println("\nTiempo restante: " + getSegundosRestantes() + "\nPuntuación" + (jugadorTurno.getNombre().equals("jugador") ? ": " : " de " + jugadorTurno.getNombre() + ": ") + jugadorTurno.getPuntuacion());
            }
            case 2 -> {
                System.out.println("\n\u001B[33mTe quedan " + jugadorTurno.getComodines() + " comodines.\u001B[0m");
                return false;
            }
            case 3 -> {

                if (jugadorTurno.getComodines() < 1) {
                    System.out.println("\n\u001B[31mNo te quedan comodines\u001B[0m");
                    return false;
                }

                System.out.println("\n\uD83E\uDDD9\u200D♂️\uD83E\uDDE8\uD83D\uDD33 x" + utilizarComodin(jugadorTurno) + "\n");
                this.tablero.pintarTablero();
                System.out.println("\nTiempo restante: " + getSegundosRestantes() + "\nPuntuación: " + jugadorTurno.getPuntuacion());
            }

            case 4 -> {
                String rutaHistorial = isMultijugador() ? "resources/historialMulti.txt" : "resources/historial.txt";
                leerHistorial(rutaHistorial);
                return false;
            }

            case 00 -> {
                System.out.println("Una pena que no quieras continuar");
                incrementarNumeroPartidas();
                setPartidaTerminada(true);
            }

            default -> System.out.println("\n\u001B[31mValor inválido\u001B[0m");

        }
        //En modo multijugador, se penaliza la pérdida de tiempo. Cada 20 segundos se resta 1 punto.
        if (isMultijugador()) {
            long tiempoTranscurrido = tiempoTurno - getSegundosRestantes();
            if (tiempoTranscurrido > 20) {
                int penalizacion = Math.round((float) tiempoTranscurrido / 20);
                int nuevaPuntuacion = Math.max(0, jugadorTurno.getPuntuacion() - penalizacion);
                jugadorTurno.setPuntuacion(nuevaPuntuacion);
                verificarResultado(tablero, jugadorTurno); //Como modifica la puntuación, volvemos a verificar el resultado
            }
        }
        return true;
    }

    /**
     * Menú de opciones del modo multijugador.
     * @param jugador
     */
    public void menu (Jugador jugador){
        if (isMultijugador()){
            System.out.println("\u001B[35m\nTurno de " + jugador.getNombre());
        }
        System.out.println("\u001B[0m\nMenú de opciones\n"
                + "1. Jugar.\n"
                + "2. Ver comodines disponibles\n"
                + "3. Utilizar comodín\n"
                + "4. Consultar historial de partidas.\n");
    }

    /**
     * Gestiona la partida en el modo multijugador
     *
     * @param tablero Tablero de juego.
     * @throws IOException
     */
    public void iniciarPartidaMulti(Tablero.Casilla[][] tablero) throws IOException {
        System.out.println("\nBienvenido a Lights Out. Las normas son sencillas: si te quedas sin tiempo o sin puntos, pierdes." +
                "\nPerdéis 1 punto por cada movimiento, así que pensáoslo bien.\n" +
                "Utilizar un comodín no gasta vuestro turno, pero el tiempo sigue corriendo.\n" +
                "Por cada 20 segundos, el jugador de dicho turno perderá un punto. ¡Sed rápidos!");

        leerNumeroPartidas("resources/numeroPartidasMulti.txt");
        Scanner teclado = new Scanner(System.in);

        System.out.println("Nombre del jugador 1:");
        Jugador jugador1 = new Jugador(teclado.nextLine());
        System.out.println("Nombre del jugador 2:");
        Jugador jugador2 = new Jugador(teclado.nextLine());

        //Le damos la puntuación y los comodines a los jugadores
        setPuntuacion(jugador1, jugador2);
        setComodines(jugador1, jugador2);
        System.out.println(); //Salto de línea

        boolean turnoJugador1 = true;
        this.tablero.pintarTablero(); //Lo pinta por primera vez
        System.out.println("\nTiempo restante: " + Math.max(0,getSegundosRestantes()));

        while (!isPartidaTerminada()) {
            Jugador jugadorTurno = turnoJugador1 ? jugador1 : jugador2;
            boolean cambioTablero = jugarTurno(tablero, teclado, jugadorTurno);
            verificarTiempo(jugador1, jugador2);
            if(cambioTablero) { turnoJugador1 = !turnoJugador1; } // Cambia el turno si se modifica el tablero
        }
        guardarHistorial("resources/historialMulti.txt", "resources/numeroPartidasMulti.txt", jugador1, jugador2);
    }

    /**
     * Comprueba si el juego debe terminar y, en caso del modo multijugador, si hay un ganador.
     * @param tablero Tablero de juego.
     * @param jugadorTurno Jugador del turno actual.
     */
    public void verificarResultado(Tablero.Casilla[][] tablero, Jugador jugadorTurno) {

        if (this.tablero.verificarTablero(tablero)) {

            if(isMultijugador()) {
                System.out.println("\n\u001B[33m¡Has ganado, " + jugadorTurno.getNombre() + ", enhorabuena!\u001B[0m\n");
            } else {
                System.out.println("\u001B[33m¡Enhorabuena, has ganado!\u001B[0m");
            }

            jugadorTurno.setEsGanador(true);
            incrementarNumeroPartidas();
            setPartidaTerminada(true);
        }

        //Si no ganas o pierdes después del movimiento, la puntuación disminuye
        jugadorTurno.setPuntuacion(Math.max(0, jugadorTurno.getPuntuacion() - 1));
        if (jugadorTurno.getPuntuacion() <= 0) {
            System.out.println("\u001B[31m\nPuntuación: 0");
            System.out.println("¡Te quedaste sin movimientos! Suerte para la próxima, la vas a necesitar.\u001B[0m");

            incrementarNumeroPartidas();
            setPartidaTerminada(true);
        }

    }



    /**
     * Compara las puntuaciones de ambos jugadores para determinar un ganador.
     * @param jugador1
     * @param jugador2
     */
    public void compararPuntuaciones (Jugador jugador1, Jugador jugador2) {
    	
        if (jugador1.getPuntuacion() < jugador2.getPuntuacion()) {
            System.out.println("\n\u001B[31m¡¡¡Tiempo!!! Lo siento, " + jugador1.getNombre() + ", has perdido.\u001B[0m\n"
                    + "\u001B[33m¡Has ganado, " + jugador2.getNombre() + ", enhorabuena!\u001B[0m\n");
            jugador2.setEsGanador(true);
            jugador1.setEsGanador(false);

            this.tablero.pintarTablero();
            System.out.println("Puntuación de " + jugador1.getNombre() + ": " + jugador1.getPuntuacion());
            System.out.println("Puntuación " + jugador2.getNombre() + ": " + jugador2.getPuntuacion());

            numeroPartidasMulti++;
            setPartidaTerminada(true);
        } else if (jugador1.getPuntuacion() == jugador2.getPuntuacion()) {
            System.out.println("Ah que se puede empatar\n");

            jugador1.setEsGanador(false);
            jugador2.setEsGanador(false);
            
            this.tablero.pintarTablero();
            System.out.println("Puntuación de " + jugador1.getNombre() + ": " + jugador1.getPuntuacion());
            System.out.println("Puntuación " + jugador2.getNombre() + ": " + jugador2.getPuntuacion());

            numeroPartidasMulti++;
            setPartidaTerminada(true);
        } else {
            System.out.println("\n\u001B[31m¡¡¡Tiempo!!! Lo siento, " + jugador2.getNombre() + ", has perdido.\u001B[0m\n"
                    + "\u001B[33m¡Has ganado, " + jugador1.getNombre() + ", enhorabuena!\u001B[0m");
            jugador1.setEsGanador(true);
            jugador2.setEsGanador(false);

            this.tablero.pintarTablero();
            System.out.println("Puntuación de " + jugador1.getNombre() + ": " + jugador1.getPuntuacion());
            System.out.println("Puntuación " + jugador2.getNombre() + ": " + jugador2.getPuntuacion());
        }

    }

    /**
     * Comprueba si aún queda tiempo y esgrime un ganador (si lo hubiere).
     * @param jugadores
     */
    public void verificarTiempo(Jugador ... jugadores) {

        if (getSegundosRestantes() <= 0) {

                if (jugadores.length > 1) {
                    compararPuntuaciones(jugadores[0], jugadores[1]);
                }

                incrementarNumeroPartidas();
                setPartidaTerminada(true);
        }
    }

    /* * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *          Ampliación: Historial de partidas          *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * */

    /**
     * Método que permite guardar el historial de partidas en un archivo externo. Además, utiliza otro archivo
     * para llevar la cuenta del número de partidas, que se refleja en el título de cada partida del historial.
     * @param rutaArchivoHistorial Ruta en la que se encuentra el archivo que contiene el historial de partidas.
     * @param rutaNumeroPartidas Ruta en la que se encuentra el archivo que almacena el número de partidas.
     * @param jugadores
     */
    public void guardarHistorial (String rutaArchivoHistorial, String rutaNumeroPartidas, Jugador ... jugadores) {

        try (BufferedWriter escribirHistorial = new BufferedWriter(new FileWriter(rutaArchivoHistorial, true));
             BufferedWriter escribirNumeroPartidas = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(rutaNumeroPartidas), StandardCharsets.UTF_8))){

            int numPartida = isMultijugador() ? numeroPartidasMulti : numeroPartidas;

            escribirNumeroPartidas.write("" + numPartida); //Guarda el número de partidas jugadas
            escribirHistorial.write("\nPartida " + numPartida + ":\n\n");

            escribirHistorial.write(isMultijugador() ? "Puntuación jugador 1: " + jugadores[0].getPuntuacion() + "\n"
                    + "Puntuación jugador 2: " + jugadores[1].getPuntuacion() + "\n"
                    : "Puntuación: " + jugadores[0].getPuntuacion() + "\n");

            escribirHistorial.write("Tiempo: " + (getSegundosRestantes() > 0 ? getSegundosRestantes() : 0) + "\n");
            escribirHistorial.write("Estado final:\n");

            for (Tablero.Casilla[] casillas : this.tablero.getTablero()) {
                for (int j = 0; j < this.tablero.getTablero()[0].length; j++) {
                    escribirHistorial.write(casillas[j].getEstado() == Tablero.Casilla.Estado.ENCENDIDO ? "\uD83D\uDD32 " : "\uD83D\uDD33 ");
                }
                escribirHistorial.newLine();
            }

            escribirHistorial.newLine();
            if (isMultijugador()){
                escribirHistorial.write("\nResultado: \n" + (jugadores[0].getEsGanador() ? "\uD83D\uDCAA Victoria de " + jugadores[0].getNombre() + " \uD83D\uDCC8\n" :
                        "😞 Derrota de " + jugadores[1].getNombre() + " \uD83D\uDCC9\n")
                                + (jugadores[1].getEsGanador() ? "\uD83D\uDCAA Victoria de " + jugadores[0].getNombre() + " \uD83D\uDCC8\n" :
                                "😞 Derrota de " + jugadores[1].getNombre() + " \uD83D\uDCC8\n"));
            } else {
                escribirHistorial.write("Resultado: " + (jugadores[0].getEsGanador() ? "\uD83D\uDCAA Victoria \uD83D\uDCC8" : "\uD83D\uDC4E Derrota \uD83D\uDCC9\n"));
            }
            escribirHistorial.write("\n----------------------------\n");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * Método que permite recuperar el número de partidas jugadas presente en el documento numeroPartidas.txt
     * @param rutaArchivo Ruta en la que se encuentra el archivo que almacena el número de partidas.
     */
    public void leerNumeroPartidas(String rutaArchivo) {

        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;

            while ((linea = lector.readLine()) != null) {
                if (isMultijugador()){
                    numeroPartidasMulti = Integer.parseInt(linea.trim());
                } else {
                    numeroPartidas = Integer.parseInt(linea.trim());
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que permite leer el historial.
     * @param rutaArchivo Ruta en la que se encuentra el archivo que almacena el número de partidas.
     */
    public void leerHistorial(String rutaArchivo) {
        try (BufferedReader lector = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                System.out.println(linea);
            }
            System.out.println("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Encapsula el incremento del número de partidas según el modo de juego.
     */
    public void incrementarNumeroPartidas() {
        if(isMultijugador()) {
            numeroPartidasMulti++;
        } else {
            numeroPartidas++;
        }
    }

}








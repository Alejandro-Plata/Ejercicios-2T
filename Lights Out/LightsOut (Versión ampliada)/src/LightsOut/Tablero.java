package LightsOut;

/**
 * Matriz de Casillas que actúa como tablero del juego Lights Out.
 * Este tablero cuenta con una clase interna, denominada "Casilla", 
 * lo que permite organizar mejor el código.
 *
 * @author Alejandro Plata Cortés
 * @since 31/03/2025
 */
public class Tablero {

    private int casillasActivas;
    private int longitud; //Longitud del tablero (como es cuadrado, será lxl)
    private Casilla[][] tablero;

    /* * * * * * * * * * * *
     *    Constructores    *
     * * * * * * * * * * * */

    /**
     * Constructor en caso de que no se indique el número de casillas activas.
     * @param longitud Tamaño del tablero (número de filas).
     */
    public Tablero(int longitud) {
        setLongitud(longitud);
        setCasillasActivas ((int) (Math.random() * (longitud * longitud))); // Número aleatorio entre 0 y longitud^2 - 1

        setTablero(new Casilla[longitud][longitud]);
    }



    /**
     * Constructor indicando el número de casillas activas.
     * @param longitud Tamaño del tablero (número de filas).
     * @param casillasActivas Número de casillas activas al inicio de la partida.
     */
    public Tablero (int longitud, int casillasActivas){
        setLongitud(longitud);
        setCasillasActivas(casillasActivas); //Inicializa de forma personalizada el número de casillas activa
        setTablero(new Casilla[longitud][longitud]);

        inicializarTablero(getTablero());
    }

    /**
     * Constructor para tests con todas las luces apagadas
     */
    public Tablero() {
    	setLongitud(5);
    	setCasillasActivas(0);
    	setTablero(new Casilla[longitud][longitud]);
    	
    	inicializarTablero(getTablero());
    }
    
    /* * * * * * * * * * * *
     *  Getters y Setters  *
     * * * * * * * * * * * */

    public int getLongitud() {
        return longitud;
    }

    //Verifica que la longitud del tablero esté entre 4x4 y 9x9. Aprovecha el encapsulamiento para validar la entrada del usuario
    public void setLongitud(int longitud) {

        if (longitud < 4 || longitud > 9) {
            throw new IllegalArgumentException("La longitud del tablero debe estar entre 4 y 9");
        }

        this.longitud = longitud;
    }

    public int getCasillasActivas() {
        return casillasActivas;
    }

    public void setTablero(Casilla[][] tablero) {
        this.tablero = tablero;
    }

    //Verifica que el número de casillas activas esté dentro del rango de casillas. Aprovecha el encapsulamiento para una correcta validación
    public void setCasillasActivas(int casillasActivas) {

        if (casillasActivas < 0 || casillasActivas > (this.longitud * this.longitud)) {
            throw new IllegalArgumentException("El número de casillas activas debe ser positivo y menor de " + this.longitud * this.longitud);
        }

        this.casillasActivas = casillasActivas;
    }

    /* * * * * * * * * * * *
     *       Métodos       *
     * * * * * * * * * * * */

    /**
     * Genera un número aleatorio entre 0 y 1
     * @return Devuelve un número aleatorio entre 0 y 1
     */
    public int estadoAleatorio() { return Math.random() < 0.5 ? 0 : 1; } //Podría utilizar Math.Round y castearlo a int, pero así lo veo más limpio

    public Casilla[][] getTablero() {
        return tablero;
    }


    /**
     * Inicializa el tablero de forma aleatoria.
     * @param tablero Tablero de juego.
     */
    public void inicializarTablero (Casilla[][] tablero) {

        int contadorCasillasActivas;

        do {
            contadorCasillasActivas = 0; //Reiniciamos el contador para cada iteración
            for (int i = 0; i < tablero.length; i++) {
                for (int j = 0; j < tablero[0].length; j++) {

                    if (tablero[i][j] == null || contadorCasillasActivas < getCasillasActivas()) { // Si la casilla no está creada
                        if (contadorCasillasActivas < getCasillasActivas()) {
                            tablero[i][j] = new Casilla(estadoAleatorio());
                        } else {
                            tablero[i][j] = new Casilla(); // Inicializa la casilla a apagada
                        }
                    }

                    // Contar si la casilla está encendida
                    if (tablero[i][j].estado == Casilla.Estado.ENCENDIDO) {
                        contadorCasillasActivas++;
                    }
                }
            }


        } while (contadorCasillasActivas < getCasillasActivas());

    }
    /**
     * Imprime el tablero por consola utilizando emoticonoes para representar el estado de las casillas.
     */
    public void pintarTablero() {

        for (Casilla[] casillas : getTablero()) {
            for (int j = 0; j < getTablero()[0].length; j++) {

                System.out.print(casillas[j].estado == Casilla.Estado.ENCENDIDO ? "\uD83D\uDD32 " : "\uD83D\uDD33 ");

            }
            System.out.println();
        }

    }
    /**
     * Comprueba si el tablero tiene alguna casilla encendida.
     * @param tablero Tablero de juego
     * @return Devuelve falso si alguna de las luces está encendida.
     */
    public boolean verificarTablero (Casilla[][] tablero) {

        for (Casilla[] casillas : tablero) {
            for (int j = 0; j < tablero[0].length; j++) {

                if (casillas[j].estado == Casilla.Estado.ENCENDIDO) {
                    return false;
                }
            }
        }
        return true;
    }
    /**
     * Método que cambia el estado de las luces según la casilla indicada por el jugador.
     * @param fila Fila en la que se encuentra la casilla que desea seleccionarse.
     * @param col Columna en la que se encuentra la casilla que desea seleccionarse.
     * @param tablero Tablero de juego.
     */
    public void cambiarLuces (int fila, int col, Tablero.Casilla[][] tablero) {

        // Cambia la casilla presionada
        tablero[fila][col].cambiarEstado(tablero[fila][col]);

        // Cambia la de arriba si no está en la primera fila
        if (fila > 0) {
            tablero[fila - 1][col].cambiarEstado(tablero[fila - 1][col]);
        }

        // Cambia la de abajo si no está en la última fila
        if (fila < tablero.length - 1) {
            tablero[fila + 1][col].cambiarEstado(tablero[fila + 1][col]);
        }

        // Cambia la de la izquierda si no está en la primera columna
        if (col > 0) {
            tablero[fila][col - 1].cambiarEstado(tablero[fila][col - 1]);
        }

        // Cambia la de la derecha si no está en la última columna
        if (col < tablero[0].length - 1) {
            tablero[fila][col + 1].cambiarEstado(tablero[fila][col + 1]);
        }

    }

    /* * * * * * * * * * * *
     *    Clase Casilla    *
     * * * * * * * * * * * */

    /***
     * La clase Casilla representa una luz individual en el tablero de juego "Lights Out".
     * Almacena el estado de la luz (encendida o apagada) y proporciona métodos
     * para cambiar su estado. Es una clase interna de la clase Tablero para
     * encapsular su funcionalidad y mejorar la organización del código.
     *
     * @Autor: Alejandro Plata Cortés
     * @Fecha: 30/03/2025
     */
    public class Casilla {

        private Estado estado;

        /* * * * * * * * * * * *
         *    Constructores    *
         * * * * * * * * * * * */

        /**
         * Inicializa la casilla con un estado dado.
         * @param estado 0 (apagado) o 1 (encendido)
         */
        public Casilla (int estado) {
            setCasilla(estado);
        }

        /**
         * Por defecto, la casilla se inicializa a apagado.
         */
        public Casilla () { this.estado = Casilla.Estado.APAGADO; }

        /* * * * * * * * * * * *
         *  Getters y Setters  *
         * * * * * * * * * * * */

        public void setCasilla(int estado) { this.estado = estado == 0 ? Estado.APAGADO : Estado.ENCENDIDO; }

        public Estado getEstado() { return estado; }

        /**
         * Cambia el estado de la casilla de APAGADO a ENCENDIDO y viceversa.
         * @param casilla
         */
        public void cambiarEstado(Casilla casilla) { casilla.estado = (estado == Estado.APAGADO) ? Estado.ENCENDIDO : Estado.APAGADO; }

        /* * * * * * * * * * * *
         *     ENUMERADOR      *
         * * * * * * * * * * * */
        /* Utilicé un enum porque tenía en mente posibles ampliaciones, pero no terminé concretándolas.
           Además, utilizar un enum mejora la legibilidad del código, aunque en este caso un booleano
           cumpliría una función muy similar. */

        /**
         * Indica si la casilla está apagada o encendida.
         */
        public enum Estado {
            ENCENDIDO,
            APAGADO
        }


    }

}

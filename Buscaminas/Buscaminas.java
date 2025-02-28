package Buscaminas;

import java.util.Scanner;

/* La clase Buscaminas implementa la lógica del juego, utilizando principalmente Arrays y, para la apertura de casillas adyacentes desprovistas de bomba,
    * el método abrirCasilla() implementa una variante de un algoritmo llamado flood fill, que consiste en una llamada recursiva que propaga la apertura de casillas
    * hasta que se topa con casillas que tienen una o varias bombas adyacentes a ella.
    *
    * @Author: Alejandro Plata Cortés
    * @Fecha: 28/02/2025
    * */

public class Buscaminas {

    private Casilla[][] tablero;
    private int filas;
    private static int numeroMinas;
    private boolean juegoTerminado;
    public static int numeroBanderas = 0; //El número de banderas debe ser estático para poder utilizarlo en la clase casilla

    public Buscaminas(int filas, int numeroMinas) {
        this.filas = filas;
        this.numeroMinas = numeroMinas;
        this.juegoTerminado = false;
        this.tablero = new Casilla[filas][filas];

        // Inicializar el tablero creando cada casilla.
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.filas; j++) {
                tablero[i][j] = new Casilla();
            }
        }

        colocarMinas();
        calcularMinasAdyacentes();
    }

    public static int getNumeroMinas() {
        return numeroMinas;
    }

    // Coloca minas de forma aleatoria en el tablero. Es privado porque no me interesa que el jugador pueda tocarlo en mitad de la partida
    private void colocarMinas() {
        int minasColocadas = 0;
        while (minasColocadas < numeroMinas) {
            int i = (int)(Math.random() * this.filas);
            int j = (int)(Math.random() * this.filas);
            if (!tablero[i][j].tieneMina()) {
                tablero[i][j].setTieneMina(true);
                minasColocadas++;
            }
        }
    }

    // Para cada casilla sin mina, calcula el número de minas en las casillas adyacentes.
    private void calcularMinasAdyacentes() {
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.filas; j++) {
                if (!tablero[i][j].tieneMina()) {
                    int minas = contarMinasAdyacentes(i, j);
                    tablero[i][j].setMinasAdyacentes(minas);
                }
            }
        }
    }

    // Cuenta las minas en las 8 casillas que rodean (fila, col)
    private int contarMinasAdyacentes(int fila, int col) {
        int minas = 0;
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < this.filas && j >= 0 && j < this.filas) { //Verifica que la casilla evaluada esté dentro de los límites del tablero
                    if (tablero[i][j].tieneMina()) {
                        minas++;
                    }
                }
            }
        }
        return minas;
    }

    // Método para revelar una casilla.
    // Si no tiene minas adyacentes, se revelan todos sus vecinos
    public void abrirCasilla(int fila, int col) {
        // Verificar límites.
        if (fila < 0 || fila >= this.filas || col < 0 || col >= this.filas) {
            return;
        }
        // Si ya está abierta, no se hace nada
        if (tablero[fila][col].isAbierta()) {
            return;
        }
        // Abrir la casilla.
        tablero[fila][col].setAbierta();
        // Si la casilla no tiene minas adyacentes y no es una mina, se revela sus vecinos.
        if (tablero[fila][col].getMinasAdyacentes() == 0 && !tablero[fila][col].tieneMina()) {
            for (int i = fila - 1; i <= fila + 1; i++) {
                for (int j = col - 1; j <= col + 1; j++) {
                    if (i == fila && j == col) {
                        continue; //Evita que se trate de abrir la casilla de nuevo que acabamos de abrir
                    }
                    abrirCasilla(i, j); //Se llama a la función abrirCasilla() para cada una de las minas
                    //alrededor de la mina abierta que no tienen una mina
                }
            }
        }
    }

    // Verifica si el jugador ha ganado (todas las casillas sin mina han sido abiertas).
    public boolean esGanador() {
        int contadorMinasMarcadas = 0;
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.filas; j++) {
                if (tablero[i][j].tieneMina() && tablero[i][j].isMarcada()){
                    contadorMinasMarcadas++; //Si la mina está marcada y tiene una bomba
                }
            }
        }
        if(contadorMinasMarcadas == numeroMinas){ //Si has marcado todas las minas, has ganado :)
            return true;
        }
        return false;
    }

    public void marcarCasilla (int fila, int columna){
            tablero[fila][columna].setMarcada();
    }

    public void dudaCasilla (int fila, int columna){
        tablero[fila][columna].setDuda();
    }

    // Revela todas las minas (usado cuando se pisa una mina para finalizar el juego).
    public void abrirMinas() {
        for (int i = 0; i < this.filas; i++) {
            for (int j = 0; j < this.filas; j++) {
                if (tablero[i][j].tieneMina()) {
                    tablero[i][j].setAbierta();
                }
            }
        }
    }

    // Imprime el tablero en consola.
    public void mostrarTablero() {
        System.out.println("\nTablero:");
        System.out.print("   ");
        for (int j = 0; j < this.filas; j++) {
            System.out.print(j + "  ");
        }
        System.out.println();
        for (int i = 0; i < this.filas; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < this.filas; j++) {
                System.out.print(tablero[i][j].pintarCasilla() + " ");
            }
            System.out.println();
        }
    }

    public void menu (){

        System.out.println("\nMenú de opciones\n"
                + "1. Abrir una casilla.\n"
                + "2. Marcar o desmarcar una casilla (BANDERA). Solo puedes marcar " + numeroMinas + " banderas.\n"
                + "3. Marcar o desmarcar una casilla (DUDA).");

    }

    // Bucle principal del juego.
    public void jugar() {
        Scanner teclado = new Scanner(System.in);
        while (!juegoTerminado) {
            mostrarTablero();
            menu();
            int opcion = teclado.nextInt();
            switch (opcion) {

                case 1 -> {
                    System.out.println("\nIntroduce la fila y columna a abrir (separados por un espacio):");
                    int fila = teclado.nextInt();
                    int col = teclado.nextInt();

                    if (fila < 0 || fila >= this.filas || col < 0 || col >= this.filas) {
                        System.out.println("Coordenadas inválidas. Inténtalo de nuevo.");
                        continue; //Pasa a la siguiente iteración del bucle directamente
                    }

                    if (tablero[fila][col].isAbierta()) {
                        System.out.println("Esta casilla ya ha sido descubierta. Prueba con otra.");
                        continue; //Pasa a la siguiente iteración del bucle directamente
                    }

                    // Si la casilla tiene mina, el juego se termina.
                    if (tablero[fila][col].tieneMina()) {
                        System.out.println("¡Boooooooooooooom! \u001B[31mHas perdido.\u001B[0m");
                        abrirMinas();
                        mostrarTablero();
                        juegoTerminado = true;
                    } else {
                        // Revela la casilla y sus vecinas.
                        abrirCasilla(fila, col);
                    }

                }

                case 2 -> {
                    System.out.println("\nIntroduce la fila y columna a marcar (separados por un espacio):");
                    int fila = teclado.nextInt();
                    int col = teclado.nextInt();

                    if (fila < 0 || fila >= this.filas || col < 0 || col >= this.filas) {
                        System.out.println("Coordenadas inválidas. Inténtalo de nuevo.");
                        continue; //Pasa a la siguiente iteración del bucle directamente
                    }

                    if (tablero[fila][col].isAbierta()) {
                        System.out.println("Esta casilla ya ha sido descubierta. Prueba con otra.");
                        continue; //Pasa a la siguiente iteración del bucle directamente
                    }

                    marcarCasilla(fila, col); //Marcamos o desmarcamos la casilla

                    if (esGanador()) { //Verificamos si ha ganado o no
                        System.out.println("\u001B[32m¡Felicidades! Has ganado el juego.\u001B[0m");
                        abrirMinas();
                        mostrarTablero();
                        juegoTerminado = true;
                    }

                }
                case 3 -> {

                    System.out.println("\nIntroduce la fila y columna en la que quieras poner o quitar tu duda (separados por un espacio):");
                    int fila = teclado.nextInt();
                    int col = teclado.nextInt();

                    if (fila < 0 || fila >= this.filas || col < 0 || col >= this.filas) {
                        System.out.println("Coordenadas inválidas. Inténtalo de nuevo.");
                        continue; //Pasa a la siguiente iteración del bucle directamente
                    }

                    if (tablero[fila][col].isAbierta()) {
                        System.out.println("Esta casilla ya ha sido descubierta. Prueba con otra.");
                        continue; //Pasa a la siguiente iteración del bucle directamente
                    }

                    dudaCasilla(fila, col);

                }

                default -> System.out.println("\n\u001B[31mValor inválido\u001B[0m");

            }

        }
    }

}



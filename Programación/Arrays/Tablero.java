package org.lightsOut;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Timer;
import java.util.TimerTask;

/***
 * @author Alejandro Plata Cortés
 *
 * Este tablero cuenta con una clase interna, denominada "Casilla", lo que permite
 * organizar mejor el código, así como dotarle de un acceso privado para que
 * no se pueda acceder a ella desde fuera de esta clase.
 */

public class Tablero {

    private int longitud; //Longitud del tablero (como es cuadrado, será lxl)
    private int casillasActivas;
    private Casilla tablero[][];
    private Timer temporizador;

    //Constructor en caso de que no se indique el número de casillas activas
    public Tablero (int longitud) {
        longitud = this.longitud;
        tablero = new Casilla [longitud][longitud]; //Inicializamos el tamaño del tablero

        //Inicializar el tablero creando cada casilla
        for (int i = 0; i < longitud; i++){

            for(int j = 0; j < longitud; j++) {
                tablero[i][j] = new Casilla(estadoAleatorio());
            }

        }
    }

    public Tablero (int longitud, int casillasActivas){
        longitud = this.longitud;
        setCasillasActivas(casillasActivas); //Inicializa de forma personalizada el número de casillas activa

    }

    public Tablero (int longitud, int casillasActivas, int segundos){
        longitud = this.longitud;
        setCasillasActivas(casillasActivas); //Inicializa de forma personalizada el número de casillas activa

    }

    /* Métodos */

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getCasillasActivas() {
        return casillasActivas;
    }

    public void setCasillasActivas(int casillasActivas) {

        //Verifica que el número de casillas activas sea factible
        if (casillasActivas < 0 || casillasActivas >= longitud*longitud) {
            throw new IllegalArgumentException("El número de casillas activas debe ser positivo y menor de " + longitud*longitud);
        }

        this.casillasActivas = casillasActivas;
    }

    /* Métodos */

    /**
     * Este método inicia un temporizador utilizando la clase Timer, que recibe una
     * instancia de TimerTask. Para simplificarlo, he implementado esta tarea como
     * una clase anónima.
     *
     * Las clases anónimas pueden acceder a variables locales del método exterior,
     * siempre que estas sean constantes. Como quería contar la cantidad de segundos que pasan
     * y modificar esta variable desde la clase anónima, necesitaba usar una estructura
     * mutable.
     *
     * Para ello, he utilizado un array, ya que la referencia del array se mantiene constante
     * y su contenido puede modificarse dentro de la clase anónima.
     *
     * @param segundos
     */
    private void iniciarTemporizador(int segundos) {
        final int[] contadorSegundos = {0};

        while(contadorSegundos[0] <= segundos) {
            temporizador.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    contadorSegundos[0]++;
                }
            }, 0, 1000);
        }

        temporizador.cancel(); //Cerramos el hilo que maneja el temporizador

        terminarPartida();

    }

    private void terminarPartida() {

    }

    private int estadoAleatorio() {
        return Math.random() < 0.5 ? 0 : 1;
        //Podría utilizar Math.Round y castearlo a int, pero así lo veo más limpio
    }

    private class Casilla {
        private Estado estado;

        public Casilla (int estadoAleatorio) {
            estado = setCasilla(estadoAleatorio);
        }

        public Estado setCasilla(int estado) {

            if (estado == 0) {
                return Estado.ENCENDIDO;
            }
            return Estado.APAGADO;
        }

        public enum Estado {
            ENCENDIDO,
            APAGADO,
            MARCADO
        }

    }


}

package LightsOut;

import java.util.Objects;

/**
 * La clase Jugador representa a un jugador en una partida del juego.
 * Almacena información sobre el nombre, puntuación, comodines y estado del jugador.
 * Se utiliza para gestionar los turnos, comodines y puntuaciones en partidas individuales y multijugador.
 *
 * @author Alejandro Plata Cortés
 * @since 31/03/2025
 */
public class Jugador {

    private boolean esGanador;
    private int comodines;
    private int puntuacion;
    private String nombre;

    /* * * * * * * * * * * *
     *     Constructor     *
     * * * * * * * * * * * */

    public Jugador(String nombre) {
        setNombre(nombre);
        this.esGanador = false;
        this.comodines = 0;
        this.puntuacion = 0;
    }

    /* * * * * * * * * * * *
     *  Getters y Setters  *
     * * * * * * * * * * * */

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

        if (nombre.isEmpty() || nombre.length() > 20) throw new IllegalArgumentException("Nombre inválido");

        this.nombre = nombre;
    }

    public int getComodines() { return comodines; }

    public void setComodines(int comodines) { this.comodines = comodines; }

    public boolean getEsGanador() { return esGanador; }

    public void setEsGanador(boolean esGanador) { this.esGanador = esGanador; }

    public int getPuntuacion() { return puntuacion; }

    public void setPuntuacion(int puntuacion) { this.puntuacion = puntuacion; }

    //Método equals sobreescrito en el que dos jugadores son el mismo si tienen el mismo nombre
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(nombre, jugador.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nombre);
    }
}

package EjerciciosPOO.buscaminas;

import static EjerciciosPOO.buscaminas.Buscaminas.getNumeroMinas; //Importamos el método estático parapoder conocer el número de minas
import static EjerciciosPOO.buscaminas.Buscaminas.numeroBanderas; //Lo mismo para manipular el número de banderas puestas


public class Casilla {

    private boolean tieneMina;
    private Estado estado;
    private int minasAdyacentes;


    public Casilla() {
        this.tieneMina = false; //En un principio, ninguna casilla tiene minas
        this.estado = Estado.CERRADO; //Cerrada por defecto
        this.minasAdyacentes = 0;
    }

    public boolean tieneMina() {
        return tieneMina;
    }

    public void setTieneMina(boolean tieneMina) {
        this.tieneMina = tieneMina;
    }

    public Estado getEstado() {
        return estado;
    }

    public boolean isAbierta() {

        return this.estado == Estado.ABIERTO; //Devuelve true si está abierta

    }

    public void setAbierta() {
        this.estado = Estado.ABIERTO;
    }

    public boolean isMarcada() {

        return this.estado == Estado.MARCADO; //Devuelve true si está abierta

    }

    public void setMarcada() {

        if (numeroBanderas < getNumeroMinas()){

            if (this.estado != Estado.MARCADO){
                numeroBanderas++;
                this.estado = Estado.MARCADO;
                System.out.println(numeroBanderas);
            } else {
                numeroBanderas--;
                this.estado = Estado.CERRADO;
            }

        } else{
            System.out.println("\u001B[31mNúmero máximo de banderas alcanzado.\u001B[0m Elimina una bandera para poder colocar una nueva.");
        }

    }

    public boolean isDuda() {
        return this.estado == Estado.DUDA; //Devuelve true si está abierta
    }

    public void setDuda() {

        if (this.estado != Estado.DUDA){
            this.estado = Estado.DUDA;
        } else {
            this.estado = Estado.CERRADO;
        }

        ;
    }

    public int getMinasAdyacentes() {
        return minasAdyacentes;
    }

    public void setMinasAdyacentes(int minasAdyacentes) {
        this.minasAdyacentes = minasAdyacentes;
    }

    public String pintarCasilla() { //Nos dice como se debe pintar la casilla
        if (this.estado == Estado.CERRADO) { //Si está cerrada
            return "· ";
        } else if (this.estado == Estado.MARCADO){
            return "\uD83D\uDEA9";
        } else if (this.estado == Estado.DUDA){
            return "❓";
        } //Si no está cerrado, marcado o en duda es que está abierto
        else if (tieneMina) {
            return "\uD83D\uDCA3"; //Si tiene bomba, la pinta
        } else {
            // Si no hay minas adyacentes, se muestra un guión (en un inicio era un espacio en blanco, pero en mi opinión no queda muy claro).
            //Si hay minas adyacentes, se muestra el número de minas adyacentes a una casilla
            return (minasAdyacentes > 0 ? String.valueOf(minasAdyacentes) + " " : "- ");
        }
    }

    public enum Estado {

        ABIERTO,
        CERRADO,
        MARCADO,
        DUDA

    }

}

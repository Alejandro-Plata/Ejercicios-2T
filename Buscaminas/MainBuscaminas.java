package EjerciciosPOO.buscaminas;


    /* En la clase Main se lanza la aplicación del buscaminas. Contiene, por tanto, las dimensiones del tablero,
    así como los elementos necesarios para su interfaz gráfica. Para ello, he utilizado clases de la biblioteca javax.swing
    *
    * @Author: Alejandro Plata Cortés
    * @Fecha: 28/02/2025
    * */

public class MainBuscaminas {


    public static void main(String[] args) {

        Buscaminas partida = new Buscaminas(9,0);


        partida.jugar();

    }


}

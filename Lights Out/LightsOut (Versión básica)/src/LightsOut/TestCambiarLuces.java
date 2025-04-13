package LightsOut;

/**
 * Clase para probar el método cambiarLuces de la clase Tablero.
 * @author: Alejandro Plata Cortés
 * @since: 31/03/2025
 */
public class TestCambiarLuces {

	public static void main(String[] args) {
		
		//Creamos un tablero de 5x5 con el constructor específico para tests
		
		Tablero tablero = new Tablero();
		
		//Casilla en el centro del tablero (fila 2, columna 2)
        int fila1 = 2;
        int col1 = 2;
        Tablero.Casilla[][] resultado1 = cambiarLuces(fila1, col1, tablero.getTablero());
        //Verificamos que la casilla y sus adyacentes han cambiado de estado
        System.out.println("Caso 1: Centro del tablero");
        System.out.println("Casilla central: " + (resultado1[fila1][col1].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla arriba: " + (resultado1[fila1 - 1][col1].getEstado()== Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla abajo: " + (resultado1[fila1 + 1][col1].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla izquierda: " + (resultado1[fila1][col1 - 1].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla derecha: " + (resultado1[fila1][col1 + 1].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        
        //Comprobamos el tablero
        tablero.setTablero(resultado1);
        tablero.pintarTablero();
        
        //Reiniciamos el tablero
        tablero = new Tablero();
        
        //Casilla en la esquina superior izquierda (fila 0, columna 0)
        int fila2 = 0;
        int col2 = 0;
        Tablero.Casilla[][] resultado2 = cambiarLuces(fila2, col2, tablero.getTablero());
        //Verificamos que la casilla y sus adyacentes han cambiado de estado
        System.out.println("\nCaso 2: Esquina superior izquierda");
        System.out.println("Casilla central: " + (resultado2[fila2][col2].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla abajo: " + (resultado2[fila2 + 1][col2].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla derecha: " + (resultado2[fila2][col2 + 1].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));

        //Comprobamos el tablero
        tablero.setTablero(resultado2);
        tablero.pintarTablero();
        
        //Reiniciamos el tablero
        tablero = new Tablero();
        
        //Casilla en el borde superior (fila 0, columna 2)
        int fila3 = 0;
        int col3 = 2;
        Tablero.Casilla[][] resultado3 = cambiarLuces(fila3, col3, tablero.getTablero());
        //Verificamos que la casilla y sus adyacentes han cambiado de estado
        System.out.println("\nCaso 3: Borde superior");
        System.out.println("Casilla central: " + (resultado3[fila3][col3].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla abajo: " + (resultado3[fila3 + 1][col3].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla izquierda: " + (resultado3[fila3][col3 - 1].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla derecha: " + (resultado3[fila3][col3 + 1].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));

        //Comprobamos el tablero
        tablero.setTablero(resultado3);
        tablero.pintarTablero();
        
        //Reiniciamos el tablero
        tablero = new Tablero();
        
        //Casilla en el borde izquierdo (fila 2, columna 0)
        int fila4 = 2;
        int col4 = 0;
        Tablero.Casilla[][] resultado4 = cambiarLuces(fila4, col4, tablero.getTablero());
        //Verificamos que la casilla y sus adyacentes han cambiado de estado
        System.out.println("\nCaso 4: Borde izquierdo");
        System.out.println("Casilla central: " + (resultado4[fila4][col4].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla arriba: " + (resultado4[fila4 - 1][col4].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla abajo: " + (resultado4[fila4 + 1][col4].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla derecha: " + (resultado4[fila4][col4 + 1].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));

        //Comprobamos el tablero
        tablero.setTablero(resultado4);
        tablero.pintarTablero();
        
        //Reiniciamos el tablero
        tablero = new Tablero();
        
        //Casilla en la esquina inferior derecha (fila 4, columna 4)
        int fila5 = 4;
        int col5 = 4;
        Tablero.Casilla[][] resultado5 = cambiarLuces(fila5, col5, tablero.getTablero());
        // Verificamos que la casilla y sus adyacentes han cambiado de estado
        System.out.println("\nCaso 5: Esquina inferior derecha");
        System.out.println("Casilla central: " + (resultado5[fila5][col5].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla arriba: " + (resultado5[fila5 - 1][col5].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla izquierda: " + (resultado5[fila5][col5 - 1].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));

        //Comprobamos el tablero
        tablero.setTablero(resultado5);
        tablero.pintarTablero();
        
        //Reiniciamos el tablero
        tablero = new Tablero();
        
        //Casilla en el borde inferior (fila 4, columna 2)
        int fila6 = 4;
        int col6 = 2;
        Tablero.Casilla[][] resultado6 = cambiarLuces(fila6, col6, tablero.getTablero());
        //Verificamos que la casilla y sus adyacentes han cambiado de estado
        System.out.println("\nCaso 6: Borde inferior");
        System.out.println("Casilla central: " + (resultado6[fila6][col6].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla arriba: " + (resultado6[fila6 - 1][col6].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla izquierda: " + (resultado6[fila6][col6 - 1].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla derecha: " + (resultado6[fila6][col6 + 1].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));

        //Comprobamos el tablero
        tablero.setTablero(resultado6);
        tablero.pintarTablero();
        
        //Reiniciamos el tablero
        tablero = new Tablero();
        
        //Casilla en el borde derecho (fila 2, columna 4)
        int fila7 = 2;
        int col7 = 4;
        Tablero.Casilla[][] resultado7 = cambiarLuces(fila7, col7, tablero.getTablero());
        //Verificamos que la casilla y sus adyacentes han cambiado de estado
        System.out.println("\nCaso 7: Borde derecho");
        System.out.println("Casilla central: " + (resultado7[fila7][col7].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla arriba: " + (resultado7[fila7 - 1][col7].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla abajo: " + (resultado7[fila7 + 1][col7].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));
        System.out.println("Casilla izquierda: " + (resultado7[fila7][col7 - 1].getEstado() == Tablero.Casilla.Estado.ENCENDIDO));

        //Comprobamos el tablero
        tablero.setTablero(resultado7);
        tablero.pintarTablero();
        
        //Reiniciamos el tablero
        tablero = new Tablero();
        
	}
	//Método idéntico al método de la clase Tablero, para realizar las comprobaciones
	public static Tablero.Casilla[][] cambiarLuces (int fila, int col, Tablero.Casilla[][] tablero) {

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

        return tablero;
        
    }

}

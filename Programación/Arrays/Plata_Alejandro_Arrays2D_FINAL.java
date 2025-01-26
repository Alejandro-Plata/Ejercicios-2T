import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Plata_Alejandro_Arrays2D_FINAL {

    public static void mostrarMatriz(int [][] matriz) {
        for (int i = 0; i< matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                System.out.print(matriz[i][j] + " "); //Pinta la fila i entera, deja un espacio entre valores
            }
            System.out.println(); //Hace un salto de línea para que la matriz se vea como tal
        }
    }

    public static void inicializarMatriz(int [][] matriz) {
        for(int i = 0; i<matriz.length; i++) {//Recorremos la matriz completa
            for(int j = 0; j<matriz[0].length; j++) {
                matriz[i][j] = (int) (Math.random()*10); //Inicializamos cada elemento de
            }                                            //la matriz con un número aleatorio entre el 0 y el 9
        }
    }

    public static int sumarElementos(int [][] matriz) {
        int sumaElementos = 0; //Inicializamos la suma de elementos a 0 para poder asignarle la suma de elementos

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[0].length; j++) {
                sumaElementos += matriz[i][j]; //Guardamos la suma de los elementos de la matriz en la variable
            }
        }

        return sumaElementos;
    }

    public static int [] valorMax(int [][] matriz) {
        int valorMax = matriz[0][0]; //Asumimos que la posición [0][0] contiene el valor máximo
        int []vectorValorMax = new int[3];

        for (int i = 0; i < matriz.length; i++) { //Recorre la matriz completa
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] > valorMax) { // Si el elemento de la posición [i][j] es mayor que el elemento
                                               // contenido en valorMax, se intercambia por él
                                               // Si el valor máximo se encuentra en más de una posición,
                                               // devuelve solo la última posición, el resto se sobreescriben
                    vectorValorMax[0] = matriz[i][j]; //Almacena el valor máximo de la matriz
                    vectorValorMax[1] = i; //Almacena la fila en la que se encuentra el valor máximo
                    vectorValorMax[2] = j; //Almacena la fila en la que se encuentra el valor mínimo
                }
            }
        }

        return vectorValorMax;
    }

    public static int [] valorMin(int [][] matriz) {
        int valorMin = matriz[0][0]; //Asumimos que la posición [0][0] contiene el valor mínimo
        int []vectorValorMin = new int[3];

        for (int i = 0; i < matriz.length; i++) { //Recorre la matriz completa
            for (int j = 0; j < matriz[0].length; j++) {
                if (matriz[i][j] < valorMin) { // Si el elemento de la posición [i][j] es mayor que el elemento contenido
                    // en valorMin, se intercambia por él.
                    // Si el valor mínimo se encuentra en más de una posición, devuelve solo la primera
                    // devuelve solo la última posición, el resto se sobreescriben
                    valorMin = matriz[i][j]; // Permite que se actualice la condición
                    vectorValorMin[0] = valorMin; //Almacena el valor mínimo de la matriz
                    vectorValorMin[1] = i; //Almacena la fila en la que se encuentra el valor mínimo
                    vectorValorMin[2] = j; //Almacena la fila en la que se encuentra el valor mínimo
                }
            }
        }

        return vectorValorMin;
    }

    public static int [][] trasponerMatriz(int [][] matriz) {
        int filasMatriz = matriz.length;
        int columnasMatriz = matriz[0].length;
        int [][] matrizTraspuesta = new int [columnasMatriz][filasMatriz]; //Cambia las columnas y las filas de la matriz

        for(int i = 0; i < matrizTraspuesta.length; i++) { //Recorre la matriz completa
            for(int j = 0; j< matrizTraspuesta[i].length; j++) {
                matrizTraspuesta [i][j] = matriz[j][i]; //Traspone la matriz
            }
        }

        return matrizTraspuesta;
    }

    public static int [][] ordenarMatriz(int matriz[][]) {

        int [][] matrizOrdenada = new int [matriz.length][matriz[0].length];
        int [] vector = new int [matriz.length * matriz[0].length]; //Inicializamos un vector de tamaño FILAS X COLUMNAS (area)
        int contadorTamanho = 0; //Inicia en 0

        for(int i = 0; i < matriz.length; i++) { //Recorre cada fila
            for(int j = 0; j< matriz[0].length; j++) {//Recorre cada columna
                vector[contadorTamanho] = matriz[i][j]; //Le asigna todos los valores de la fila [i] al vector "vector"

                contadorTamanho++; //Aumenta el índice del vector en cada iteración
            }
        }
        Arrays.sort(vector); //Ordena el vector
        contadorTamanho = 0; //Reiniciamos el contador para reutilizarlo al rellenar la matriz ordenada

        for(int i = 0; i<matrizOrdenada.length; i++) { //Recorre cada fila
            for(int j = 0; j<matrizOrdenada[0].length; j++) {//Recorre cada columna
                matrizOrdenada[i][j] = vector[contadorTamanho]; //Asignamos los valores ordenados a la matriz ordenada

                contadorTamanho++;//Aumenta el índice del vector en cada iteración
            }
        }

        return matrizOrdenada; //Devolvemos la matriz ordenada
    }

    public static Integer[] buscarValor(int [][] matriz, int valor) {
        Integer [] valorBuscadoP;
        Integer [] valorBuscadoTemp = new Integer[matriz.length*matriz[0].length*2]; // Almacenará de forma temporal
        // las posiciones del valor buscado,por lo que su tamaño será la totalidad
        // de posiciones de todos los elementos del array (2 por elemento)
        boolean valorEncontrado = false;
        int contador = 0; //Delimita el tamaño del array a devolver

        for (int i = 0; i < matriz.length; i++) { //Recorre la matriz completa
            for(int j = 0; j < matriz[0].length; j++) {

                if(matriz[i][j] == valor) { //Si el elemento de la posición [i][j] es igual que el valor buscado
                    valorEncontrado = true;
                    valorBuscadoTemp[contador] = i; // Almacena la posición de la fila del elemento
                    valorBuscadoTemp[contador+1] = j; // Almacena la posición de la columna del elemento
                    System.out.printf("| [%d][%d] | ", valorBuscadoTemp[contador], valorBuscadoTemp[contador+1]);

                    contador += 2; //Incrementamos el contador en 2, ya que necesitamos suplir
                    //ambas posiciones del próximo elemento (si lo hubiera)
                }

            }
        }

        valorBuscadoP = new Integer [contador]; // Inicializa en función del número de apariciones del vector
        valorBuscadoP = valorBuscadoTemp;  // Antes de eliminar el vector temporal, se asignan sus valores al vector
                                         // que se retornará
        valorBuscadoTemp = null; //Permite que el recolector de basura se encargue de eliminar el vector temporal

        if (!valorEncontrado) {
            valorBuscadoP = new Integer [1]; // Si el valor no se encuentra, se inicializa el vector a 1
            valorBuscadoP[0] = -1; // y se devuelve -1
            System.out.println("El valor no se encuentra");
        }

        return valorBuscadoP;
    }

    public static int[][] copiarMatriz(int [][] matriz) {
        int [][] matrizCopia = new int [matriz.length][matriz[0].length];
        // Indicamos el mismo tamaño de la matriz a copiar, para que sean idénticas

        for(int i = 0; i < matriz.length; i++) { //Recorre la matriz original
            for(int j = 0; j < matriz[0].length; j++) {
                matrizCopia [i][j] = matriz[i][j]; //Copia cada elemento de la matriz original a la matriz copia; Podría usar el método .copyOf
            }
        }

        return matrizCopia;
    }

    public static void insertarValor(int[][] matriz, int valorIns, int posicionFila, int posicionColumna) {//Continuar

        for(int j=matriz[0].length-1; j>posicionColumna; j--) { //Como la posición 1 corresponde al índice 0, le resto 1

            matriz[posicionFila][j] = matriz[posicionFila][j-1]; //Desplaza los valores a la derecha,
        //solo tiene en cuenta el cambio de columna ya que la fila es la misma; el último valor se sobreescribe
        }

        matriz[posicionFila][posicionColumna] = valorIns; //Inserto el valor en la posición indicada
    }

    public static void eliminarValor(int[][] matriz, int posicionFila, int posicionColumna) {

        matriz[posicionFila][posicionColumna] = -1; //Para eliminar un valor, simplemente
        //reemplazo el valor de la posición indicada por -1
    }

    public static boolean compararMatriz(int[][]matriz) {

        int [][] matrizAleatoria = new int [matriz.length][matriz[0].length];
        inicializarMatriz(matrizAleatoria); //Inicializa la matriz aleatoria con datos aleatorios
        System.out.println("La matriz a comparar es: ");
        mostrarMatriz(matriz);
        System.out.println("\nLa matriz aleatoria es: ");
        mostrarMatriz(matrizAleatoria);
        return matrizAleatoria == matriz; //Devuelve true si ambas matrices son iguales
    }

    public static void separarConsola(){

        System.out.println("------------------------------------------------------------");
        //Separa bloques por consola
    }

    public static void main(String[] args) {

        int [][] matriz;
        int [][] matrizCopia;
        int [][] matrizTraspuesta;
        int [] valorMaxV; // Almacena el valor máximo y su posición
        int [] valorMinV; //Almacena el valor mínimo y su posición
        List<Integer> valorBusquedaV; // Almacena las posiciones en las que se encuentra el valor buscado
        //utilizo un array dinámico para no delimitar un array con un tamaño demasiado grande
        //es entrega de Arrays, no de ArrayList, pero así ves que he entendido como usar las ArrayList :)
        int filas, columnas, valorBusqueda, valorInsertar, fila, columna; //Declaro 5 variables, una para cada
        //método, aunque podría utilizar solo una y reescribirla
        int valorMax, valorMin; // Almacenan el valor máximo y el valor mínimo respectivamente
        // por si se necesitan en una variable aparte
        boolean matrizIgual = false;//En la comparación de la matriz con una matriz aleatoria, si son iguales, matrizIgual = true
        Scanner teclado = new Scanner(System.in);

        System.out.println("Ingrese el número de filas de su matriz");
        filas = teclado.nextInt();
        System.out.println("Ingrese el número de columnas de su matriz");
        columnas = teclado.nextInt();
        matriz = new int[filas][columnas];

        separarConsola();
        inicializarMatriz(matriz); //Inicializa aleatoriamente la matriz
        System.out.println("Matriz original:");
        mostrarMatriz(matriz); //Muestra la matriz por pantalla
        separarConsola();
        System.out.println("La suma de elementos es: " + sumarElementos(matriz));
        separarConsola();

        valorMaxV = valorMax(matriz);
        valorMax = valorMaxV[0];
        System.out.printf("El valor máximo es %d y su posición es [%d][%d]\n", valorMaxV[0], valorMaxV[1],valorMaxV[2]);
        separarConsola();
        valorMinV = valorMin(matriz);
        valorMin = valorMinV[0];
        System.out.printf("El valor mínimo es %d y su posición es [%d][%d]\n", valorMinV[0], valorMinV[1],valorMinV[2]);
        separarConsola();

        System.out.println("Matriz traspuesta:");
        matrizTraspuesta = new int [columnas][filas]; //Inicializamos la matriz con columnas y filas intercambiadas
        matrizTraspuesta = trasponerMatriz(matriz);
        mostrarMatriz(matrizTraspuesta);
        separarConsola();

        System.out.println("La matriz ordenada es: ");
        mostrarMatriz(ordenarMatriz(matriz));
        separarConsola();

        System.out.println("Introduzca el número que busca dentro de la matriz (un entero entre 0 y 9)");
        valorBusqueda = teclado.nextInt();
        System.out.println("El número " + valorBusqueda + " se encuentra en las posiciones:");
        valorBusquedaV = Arrays.asList(buscarValor(matriz, valorBusqueda));
        //Inicializa la arraylist a partir del vector que retorna el método buscarValor
        System.out.println();
        separarConsola();

        System.out.println("Matriz original:");
        mostrarMatriz(matriz);
        System.out.println("Matriz copia:");
        matrizCopia = copiarMatriz(matriz); //Guardo la matriz copia en una variable para poder utilizarla más adelante, y así no solo mostrarla
        mostrarMatriz(matrizCopia);
        separarConsola();

        do {
            System.out.println("Para insertar un valor en la matriz:\nIntroduzca la fila en la que desea insertarlo");
            fila = teclado.nextInt()-1; //Se resta -1 ya que la fila 1 corresponde al índice i=0
            if(fila < 0 || fila >= filas) System.out.println("Error. Introduzca un número de fila válido");

        } while (fila < 0 || fila >= filas); //Confirma que el número de filas esté en el rango disponible
        do {
            System.out.println("Introduzca la columna en la que desea insertarlo");
            columna = teclado.nextInt()-1; //Se resta -1 ya que la columna 1 corresponde al índice i=0
            if(columna < 0 || columna >= columnas) System.out.println("Error. Introduzca un número de columna válido");

        } while(columna < 0 || columna >= columnas);//Confirma que el número de columnas esté en el rango disponible
        do{
            System.out.println("Introduzca el valor que desea insertar (del 0 al 9)");
            valorInsertar = teclado.nextInt();

            if(valorInsertar < 0 || valorInsertar > 9) {
                System.out.println("Error. Introduzca un valor entre 0 y 9");
            }

        } while(valorInsertar < 0 || valorInsertar > 9);//Confirma que el valor a insertar esté entre los valores solicitados
        separarConsola();
        insertarValor(matriz, valorInsertar, fila, columna);
        System.out.println("La matriz con el valor " + valorInsertar + " insertado en la posición [" + fila + "]" + "[" + columna + "] es:" );
        mostrarMatriz(matriz);
        separarConsola();

        do {
            System.out.println("Para eliminar un valor de la matriz:\nIntroduzca la fila donde se encuentra el valor que desea eliminar");
            fila = teclado.nextInt() - 1; //Se resta -1 ya que la fila 1 corresponde al índice i=0

            if(fila < 0 || fila >= filas) {
                System.out.println("Error. Introduzca un número de fila válido");
            }

        } while(fila < 0 || fila >= filas); //Confirma que el número de filas esté en el rango disponible
        do {
            System.out.println("Introduzca la columna donde se encuentra el valor que desea eliminar");
            columna = teclado.nextInt() - 1; //Se resta -1 ya que la columna 1 corresponde al índice i=0

            if(columna < 0 || columna >= columnas) {
                System.out.println("Error. Introduzca un número de columna válido");
            }

        } while(columna < 0 || columna >= columnas); //Confirma que el número de columnas esté en el rango disponible
        eliminarValor(matriz, fila, columna);
        System.out.println("La matriz con el valor eliminado de la posición [" + fila + "]" + "[" + columna + "] (se representa como -1) es:" );
        mostrarMatriz(matriz);
        separarConsola();

        matrizIgual = compararMatriz(matrizCopia); //Utilizo la matrizCopia ya que al eliminar un valor, este es -1 y nunca podría ser igual a la matriz aleatoria que se genere
        if(matrizIgual) {
            System.out.println("\n¡Qué suerte! Las matrices son exactamente iguales. Deberías jugar la bonoloto.");
        } else {
            System.out.println("\nNo son iguales");
        }

    }
}

package org.lightsOut;

import java.util.Arrays;

public class Main {

    public static void ordenarArray(int [][] array) {

        int key;
        int j;
        for (int i = 1; i < array.length; i++) {

             key = array[i];
             j = i - 1;

             while(i >= 0 && array[i][j] > key)

                while (j >= 0 && array[i][j] > key ) {

                    array[i][j+1] = array[i][j];
                    j--;

                }

             array[j+1] = key;

        }

    }

    public static void main(String[] args) {

        int [] array = {3,10,20,3,4,5,2,3,5,7,8,15,5,8,22};

        System.out.println(Arrays.toString(array));

        ordenarArray(array);

        System.out.println("---------------------");

        System.out.println(Arrays.toString(array));



    }
}
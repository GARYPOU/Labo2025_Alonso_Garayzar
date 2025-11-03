package Complejidad;

import java.util.ArrayList;

public class Ejercicio10 {



    private ArrayList<Integer> ordenar(ArrayList<Integer>numeros){
        ArrayList<Integer> numerosNuevos = new ArrayList<>();
        int cant1=0;
        int cant2=0;
        int cant3=0;
        int i=0;

        for (int num: numeros){ //0(n)
            if(num==1){
                cant1+=1;
            }if(num==2){
                cant2+=1;
            }
            if(num==3){
                cant3+=1;
            }

        }
        while(i<cant1){
            numerosNuevos.add(1);
            i++;
        }
        i=0;
        while(i<cant2){
            numerosNuevos.add(2);
            i++;
        }
        i=0;
        while(i<cant3){
            numerosNuevos.add(3);
            i++;
        }
        i=0;
        return numerosNuevos;
    }

    public static void main(String[] args) {
        Ejercicio10 e = new Ejercicio10();
        ArrayList<Integer> numeros = new ArrayList<>();
        numeros.add(1);
        numeros.add(3);
        numeros.add(2);
        numeros.add(3);
        numeros.add(3);
        numeros.add(1);
        numeros.add(3);
        numeros.add(2);

        System.out.println(e.ordenar(numeros));

    }


}

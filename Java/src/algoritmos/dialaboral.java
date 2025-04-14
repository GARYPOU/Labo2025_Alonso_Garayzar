package algoritmos;

import java.util.Scanner;

public class dialaboral {
    public static void main(String[] args) {
        Scanner e= new Scanner(System.in);
        int t;
        System.out.println("ingrese el numero del 1 al 7 de la semana");
        t= e.nextInt();
        if(t<=5){
            System.out.println("El dia es laboral");
        }
        else {
            System.out.println("el dia no es laboral");
        }
    }
}

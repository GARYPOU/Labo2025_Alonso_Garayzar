package algoritmos;

import java.util.Scanner;

public class mayor {
    public static void main(String[] args) {
        int x;
        int y;
        Scanner e=new Scanner(System.in);
        System.out.println("Ingrese numero 1");
        x= e.nextInt();
        System.out.println("ingrese numero 2");
        y= e.nextInt();
        if(x>y){
            System.out.println("el mumero 1 es algoritmos.mayor="+x);
        } else if (y>x) {
            System.out.println("el mumero 2 es algoritmos.mayor="+y);
        }
        else {
            System.out.println("son iguales");
        }
    }
}
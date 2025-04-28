package algoritmos;

import java.util.Scanner;
public class reemplazoletra {
    public static void main(String[] args) {
        Scanner e = new Scanner(System.in);
        String texto = "Ayer me compre muñecos de la marca 'ToyCo' por internet.";
        String nuevo_string = "";
        System.out.println("por cual char quiere reemplazar");
        char nuevo_char = e.nextLine().charAt(0);
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);
            if (c == 'e' || c == 'E' || c == 'é' || c == 'É') {
                nuevo_string = texto.replace('e', nuevo_char);
            }
        }
        System.out.println(nuevo_string);
    }
}
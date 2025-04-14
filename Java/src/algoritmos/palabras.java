package algoritmos;

import java.util.Scanner;

public class palabras {
    public static void main(String[] args) {
        String pal1;
        String pal2;
        int contador=0;
        int verda=1;
        Scanner entrada=new Scanner(System.in);
       
            
                System.out.print("Ingrese la primera palabra");
                pal1=entrada.nextLine();
                System.out.print("Ingrese la segunda palabra");
                pal2=entrada.nextLine();
                if(pal1.length()==pal2.length()){
                    for (int i = 0; i < pal1.length(); i++) {
                    
                        if(pal1.charAt(i)==pal2.charAt(i)){
                        contador=contador+1;
                        }
                            if(contador==pal1.length()){
                            System.out.print("Las algoritmos.palabras son iguales");
                            verda=0;
                            }
                    
               
                
                      
                }
             
                if (verda==1){
                System.out.print("Las algoritmos.palabras no son iguales");
                }
            
            

    }
    else{
        System.out.print("Las algoritmos.palabras no son iguales");
    }
}
}
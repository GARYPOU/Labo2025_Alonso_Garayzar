import java.util.Scanner;

public class ContadorDeNumeros {
    public static void main(String[] args) {
        int contador=0;
        int num=0;
        int bigg=0;
        int smalll=0;
        int aux=0;
        int total=0;
        int totalpos=0;
        int totalneg=0;
        Scanner entrada=new Scanner(System.in);
       
            while(num!=-1){
                System.out.print("Ingrese un numero, Cuando desea salir ingrese -1");
                num=entrada.nextInt();
                
                contador=contador+1;
               
               
                if(num>bigg){
                    bigg=num;
                    
                }
                else if(num<smalll){
                    smalll=num;
                }
                total=total+num;
                if(totalpos+num>=totalpos){
                    totalpos=totalpos+num;
                }
                else if(totalneg+num<totalneg){
                    totalneg=totalneg+num;
                }
                

            }
            
        System.out.print("La cantidad de numeros fue");
        System.out.println(contador);
        System.out.print("El mas grande fue");
        System.out.println(bigg);
        System.out.print("El mas chico fue");
        System.out.println(smalll);
        System.out.print("La sumatoria de todos fue");
        System.out.println(total);
        System.out.print("La sumatoria de los positivos fue");
        System.out.println(totalpos);
        System.out.print("La sumatoria de los negativos fue");
        System.out.println(totalneg);
    }
}
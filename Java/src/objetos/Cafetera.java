


import java.util.Scanner;

public class Cafetera {
    private int cantMaxima;
    private int cantActual;
  


    public Cafetera() {
      this.cantMaxima=1000;
      this.cantActual=0;

    }

    
    public Cafetera(int cantActual, int cantMaxima) {
        this.cantActual=cantMaxima;
        this.cantMaxima=cantMaxima;


    }
    public ajustar() {
        if(cantActual>cantMaxima){
        cantActual=1000;
        }


    }

    public int getcantMaxima() {
        return cantMaxima;
    }

    public void setcantMaxima(int cantMaxima) {
        this.cantMaxima = cantMaxima;
    }

    public int getcantActual() {
        return cantActual;
    }

    public void setcantActual(int cantActual) {
        this.cantactual = cantActual;
    }

   


    

    public void llenar() {
        if(cantActual<cantMaxima){
            cantActual=cantMaxima;
        }
    }
    public int llenarTaza(int capacidad){
        int taza=0;
        taza=capacidad;
        return taza;
    }
    public void vaciarCafe(){
        cantActual=0;

    }

    public int añadirCafe(int cantidad){
       
        cantActual=cantActual+cantidad;
        if(cantActual>cantMaxima) {
            cantActual=cantMaxima;
        }
        return cantActual;
       
    }
    public static void main(String[] args) {
        Cafetera cafe1 = new Cafetera();
        Scanner e = new Scanner(System.in);
        cafe1.llenar();
        System.out.println("Ingrese la capacidad de la taza");
        int capacidad = e.nextInt();
        System.out.println("usted lleno la taza con:"+cafe1.llenarTaza(capacidad)+ "CC de cafe");
        cafe1.vaciarCafe();
        System.out.println("Ingrese la cantidad de cafe que quiere agregar a la cafetera");
        int cantidad = e.nextInt();
        System.out.print("usted agrego:"+cafe1.añadirCafe(cantidad));



       
    }
}
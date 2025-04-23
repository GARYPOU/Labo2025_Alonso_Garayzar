package Fechas;

import java.util.Scanner;

public class Fecha

{
    private int dia;
    private int mes;
    private int anio;


    public Fecha(){
        this.dia=12;
        this.mes=12;
        this.anio=2009;


    }


    public Fecha

(int day, int month, int year){
        this.dia=day;
        this.mes=month;
        this.anio=year;


    }



    public int getDia(){
        return dia;
    }

    public void setDia(int day){
        this.dia=day;
    }

    public int getMes(){
        return mes;
    }

    public void setMes(int month){ this.mes=month;}

    public int getAnio(){
        return anio;
    }

    public void setAnio(int year){
        this.anio=year;
    }



    public void correccion() {


        if(dia<=31 && mes<=12) {
            if (dia > 28 && mes == 2) {
                dia = 28;
            }
            if (dia > 31 && (mes==4 || mes==6 || mes==9 || mes==11)){
                dia = 30;
            }
        }
        else{
            dia=31;
            mes=12;
        }
    }
    public int cantdias(){
        if(mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12){
            return 31;
        }
        if(mes==4 || mes==6 || mes==9  || mes==11){
            return 30;
        }
        else{
            return 28;
        }
    }

    public void corto(){
        System.out.println(dia+"/"+mes+"/"+anio);
    }
    public void largo(){
        String ñia;
        if(dia==1 || dia==8 || dia==15 || dia==22 || dia==29){
            ñia="lunes";
        }
        else if(dia==2 || dia==9 || dia==16 || dia==23 || dia==30){
            ñia="martes";
        }
        else if(dia==3 || dia==10 || dia==17 || dia==24 || dia==31){
            ñia="miercoles";
        }
        else if(dia==4 || dia==11 || dia==18 || dia==25){
            ñia="jueves";
        }
        else if(dia==5 || dia==12 || dia==19 || dia==26){
            ñia="viernes";
        }
        else if(dia==6 || dia==13 || dia==20 || dia==27){
            ñia="sabado";
        }
        else{
            ñia="domingo";
        }
        System.out.println(ñia+" "+dia+" "+"de"+" "+mes+" "+"de"+" "+anio);
    }
    public void avanza(){
        dia=dia+1;
    }
    public void retrocede(){
        dia=dia-1;
    }
    public void menorque(int dia2, int mes2, int anio2){
        if(anio<anio2){

            System.out.println("La primera Fecha es anterior");
        }
        else if(mes<mes2){
            System.out.println("La primera Fecha es anterior");
        }
        else if(dia<dia2){
            System.out.println("La primera Fecha es anterior");
        }
        else{
            mayorque(dia2,mes2,anio2);
        }
    }
    public void mayorque(int dia2, int mes2, int anio2){
        System.out.println("La primera Fecha es posterior");
    }
    public void igualque(int dia2, int mes2, int anio2) {
        if (dia == dia2 && mes == mes2 && anio == anio2) {
            if (dia == dia2 && mes == mes2 && anio == anio2) {
                System.out.println("Los dias son iguales");
            } else {
                System.out.println("Los dias no son iguales");
                menorque(dia2, mes2, anio2);
            }
        }

    }


        public static void main(String[] args) {
            Scanner e = new Scanner(System.in);
            Fecha f1 = new Fecha();
            int dia2;
            int mes2;
            int anio2;

            f1.correccion();
            f1.cantdias();
            f1.corto();
            f1.largo();
            f1.avanza();
            f1.retrocede();
            System.out.println(f1.dia);
            System.out.println("ingrese un dia a comparar");
            dia2 = e.nextInt();
            System.out.println("ingrese un mes a comparar");
            mes2 = e.nextInt();
            System.out.println("ingrese un año a comparar");
            anio2 = e.nextInt();
            f1.igualque(dia2,mes2,anio2);

        }
    }


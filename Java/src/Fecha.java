public class Fecha{
    private int dia;
    private int mes;
    private int anio;


    public Fecha(){
        this.dia=12;
        this.mes=12;
        this.anio=2009;


    }


    public Fecha(int day, int month, int year){
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
            if (dia > 31 && (mes==4 || mes==6 || mes==7 || mes==11)){
                dia = 30;
            }
        }
    }




    public static void main(String[] args) {
        Fecha f1 = new Fecha();
        f1.correccion();
        System.out.println(f1.dia);

    }
}


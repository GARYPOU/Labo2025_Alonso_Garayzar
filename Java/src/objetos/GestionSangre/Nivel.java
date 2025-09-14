package objetos.GestionSangre;

public enum Nivel {
    UNO(1),DOS(2),TRES(3),CUATRO(4),CINCO(5),SEIS(6),SIETE(7),OCHO(8),NUEVE(9),DIEZ(10);

    private int valores;

    Nivel(int i) {

    }


    public void getValor(int valores){
        this.valores=valores;
    }

    public int getValores() {
        return valores;
    }
}

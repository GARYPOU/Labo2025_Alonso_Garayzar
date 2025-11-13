package P6;


public class Cliente1 extends Cliente {
    public static void main(String[] args) {
        String[] newArgs = new String[1];
        newArgs[0] = "cliente2.properties";
        Cliente.main(newArgs);
    }
}



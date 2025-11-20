package P8;


import P9.Cliente;

public class Cliente2 extends P9.Cliente {
    public static void main(String[] args) {
        String[] newArgs = new String[1];
        newArgs[0] = "cliente2.properties";
        Cliente.main(newArgs);
    }
}


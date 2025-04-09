import java.util.Scanner;

public class ingredatos {

    public static void main(String[] args) {
        int n;
        double a;
        char c;

        Scanner entrada= new Scanner(System.in);
        System.out.print("Valor para n");
        n = entrada.nextInt();
        System.out.print("valor para a");
        a = entrada.nextDouble();
        System.out.print("valor para c");
        c  = entrada.next().charAt(0);
        System.out.println(n+a);
        System.out.println(a-n);
        System.out.println((int) c);
    }
}


package algoritmos;

public class Vocales {
    public static void main(String[] args) {
        String texto = "Ayer me compre muñecos de la marca 'ToyCo' por internet.";
        int contadorvocales = 0;
        for (int i = 0; i < texto.length(); i++) {
            char c = texto.charAt(i);

            if (c == 'a' || c == 'A' || c == 'á' || c == 'Á' || c == 'e' || c == 'E' || c == 'é' || c == 'É' || c == 'i' || c == 'I' || c == 'í' ||
                    c == 'Í' ||c == 'o' || c == 'O' || c == 'ó' || c == 'Ó' ||c == 'u' || c == 'U' || c == 'ú' || c == 'Ú' )
            {
                contadorvocales++;
            }
        }
        System.out.println(contadorvocales);
    }
}

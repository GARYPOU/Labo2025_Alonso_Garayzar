package objetos.Programa_Comida;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.Normalizer;

public class CorrectorMasterChef {

    private static String norm(String s) {
        if (s == null) return "";
        String n = Normalizer.normalize(s, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return n.toLowerCase();
    }

    private static boolean containsAny(String haystack, String... needles) {
        for (String n : needles) {
            if (haystack.contains(n.toLowerCase())) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        // 1) Captura salida del main
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);

        try {
            MasterChefApp.main(new String[]{});
        } catch (Throwable t) {
            System.out.flush();
            System.setOut(old);
            System.out.println("❌ Error ejecutando MasterChefApp.main(): " + t.getClass().getSimpleName() + " - " + t.getMessage());
            return;
        }

        System.out.flush();
        System.setOut(old);

        String salida = baos.toString();
        String S = norm(salida);

        System.out.println("=== Salida del alumno (normalizada) ===");
        System.out.println(S + "\n");

        int puntos = 0;
        int total = 8;

        boolean pPrin = S.contains("guarde") && S.contains("prohibid"); // “Ya guardé todos los elementos prohibidos…” + “no voy a usar…”
        if (pPrin) { puntos++; System.out.println("✅ Preparacion PRINCIPIANTE detectada"); }
        else System.out.println("❌ Preparacion PRINCIPIANTE no detectada");

        boolean pInter = S.contains("stock a utilizar") || (S.contains("stock") && S.contains(">") || S.contains("cantidad"));
        if (pInter) { puntos++; System.out.println("✅ Preparacion INTERMEDIO detectada"); }
        else System.out.println("❌ Preparacion INTERMEDIO no detectada");

        boolean pExp = S.contains("perdi 5 minutos") && (S.contains("tiempo restante") || S.contains("restante"));
        if (pExp) { puntos++; System.out.println("✅ Preparacion EXPERTO detectada"); }
        else System.out.println("❌ Preparacion EXPERTO no detectada");

        boolean prinEntrada = containsAny(S, "principiante", "entrada") || (S.contains("entrada") && S.contains("serv"));
        if (prinEntrada) { puntos++; System.out.println("✅ PRINCIPIANTE cocina/serve ENTRADA"); }
        else System.out.println("❌ PRINCIPIANTE no evidencia ENTRADA");

        boolean interPrincipal = containsAny(S, "intermedio", "principal") || (S.contains("principal") && S.contains("serv"));
        if (interPrincipal) { puntos++; System.out.println("✅ INTERMEDIO cocina/serve PRINCIPAL"); }
        else System.out.println("❌ INTERMEDIO no evidencia PRINCIPAL");

        boolean expAmbos = (S.contains("exper") && S.contains("entrada")) && (S.contains("exper") && S.contains("principal"))
                || (S.contains("experto") && S.contains("entrada") && S.contains("principal"));
        if (expAmbos) { puntos++; System.out.println("✅ EXPERTO puede ENTRADA y PRINCIPAL"); }
        else System.out.println("❌ EXPERTO no evidencia ambos tipos");

        boolean exProhibido = S.contains("prohibid"); // ingrediente prohibido
        if (exProhibido) { puntos++; System.out.println("✅ Excepcion por ingrediente prohibido detectada"); }
        else System.out.println("❌ No se detecto excepcion por ingrediente prohibido");

        boolean exFaltantes = S.contains("stock") || S.contains("ingrediente") && (S.contains("falta") || S.contains("insuficiente"));
        boolean exTiempo = S.contains("tiempo") && (S.contains("insuficiente") || S.contains("no alcanza") || S.contains("no alcanz"));
        if (exFaltantes || exTiempo) { puntos++; System.out.println("✅ Excepcion por faltantes/tiempo detectada"); }
        else System.out.println("❌ No se detecto excepcion por faltantes/tiempo");

        System.out.println("\nPuntaje final MasterChef: " + puntos + "/" + total);
    }
}

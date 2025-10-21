package P3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.*;
import java.util.Base64;

/**
 * KeyLoader: carga claves RSA desde .der (binario) o .pem (texto base64 con headers).
 */
public class KeyLoader {

    public static PublicKey loadPublicKey(String path) throws Exception {
        byte[] keyBytes = readKeyBytes(path);
        // Asumimos formato X.509 para clave pública
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public static PrivateKey loadPrivateKey(String path) throws Exception {
        byte[] keyBytes = readKeyBytes(path);
        // Asumimos PKCS#8 para clave privada
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    private static byte[] readKeyBytes(String path) throws IOException {
        byte[] file = Files.readAllBytes(Paths.get(path));
        String s = new String(file);
        if (s.contains("BEGIN")) {
            // PEM: extraer base64 entre cabeceras
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new StringReader(s));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("-----BEGIN ")) continue;
                if (line.startsWith("-----END ")) continue;
                sb.append(line.trim());
            }
            return Base64.getDecoder().decode(sb.toString());
        } else {
            // DER binario
            return file;
        }
    }
}


package P7.Mensajeria;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encriptador {
    public static SecretKey generarClaveAES(int bits) throws Exception {
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(bits);
        return kg.generateKey();
    }
    public static byte[] generarIV() {
        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        return iv;
    }
    public static byte[] cifrarAES(byte[] datos, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(iv));
        return cipher.doFinal(datos);
    }

    public static byte[] descifrarAES(byte[] datos, SecretKey key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(iv));
        return cipher.doFinal(datos);
    }

    public static KeyPair generarParRSA(int bits) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(bits);
        return kpg.generateKeyPair();
    }

    public static byte[] cifrarRSA(byte[] datos, PublicKey pub) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, pub);
        return cipher.doFinal(datos);
    }

    public static byte[] descifrarRSA(byte[] datos, PrivateKey priv) throws Exception {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
        cipher.init(Cipher.DECRYPT_MODE, priv);
        return cipher.doFinal(datos);
    }

    public static byte[] sign(byte[] datos, PrivateKey priv) throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initSign(priv);
        sig.update(datos);
        return sig.sign();
    }

    public static boolean verify(byte[] datos, byte[] firma, PublicKey pub) throws Exception {
        Signature sig = Signature.getInstance("SHA256withRSA");
        sig.initVerify(pub);
        sig.update(datos);
        return sig.verify(firma);
    }

    public static byte[] claveAESaBytes(SecretKey key) {
        return key.getEncoded();
    }
    public static SecretKey bytesASecretKey(byte[] keyBytes) {
        return new SecretKeySpec(keyBytes, "AES");
    }
    // Base64 helpers (para JSON)
    public static String toB64(byte[] b) {
        return Base64.getEncoder().encodeToString(b);
    }
    public static byte[] fromB64(String s) {
        return Base64.getDecoder().decode(s);
    }
}
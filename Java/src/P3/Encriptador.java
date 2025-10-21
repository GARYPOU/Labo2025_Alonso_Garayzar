package P3;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.Base64;

public class Encriptador {

    // --- AES ---
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

    // Convertir bytes <-> SecretKey para AES
    public static byte[] claveAESaBytes(SecretKey key) {
        return key.getEncoded();
    }
    public static SecretKey bytesASecretKey(byte[] keyBytes) {
        return new SecretKeySpec(keyBytes, "AES");
    }

    // --- RSA (OAEP con SHA-256) ---
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

    // --- Firma (SHA256withRSA) ---
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

    // --- Hash SHA-256 ---
    public static byte[] hashSHA256(byte[] data) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        return digest.digest(data);
    }

    // --- Base64 helpers ---
    public static String toB64(byte[] b) {
        return Base64.getEncoder().encodeToString(b);
    }
    public static byte[] fromB64(String s) {
        return Base64.getDecoder().decode(s);
    }
}

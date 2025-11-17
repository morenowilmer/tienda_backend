package co.com.poli.tienda.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;

public class CifradoUtil {

    private static final String ALGORITHM = "AES";
    private static final String CLAVE_CONSTANTE_TEXTO = "ClavePrueba123";
    private static final SecretKeySpec CLAVE_SECRETA_SPEC;

    static {
        SecretKeySpec keySpec = null;
        try {
            MessageDigest sha = MessageDigest.getInstance("SHA-1");
            byte[] key = sha.digest(CLAVE_CONSTANTE_TEXTO.getBytes(StandardCharsets.UTF_8));
            key = Arrays.copyOf(key, 16);
            keySpec = new SecretKeySpec(key, ALGORITHM);
        } catch (Exception e) {
            System.err.println("Error al inicializar la clave de cifrado: " + e.getMessage());
        }
        CLAVE_SECRETA_SPEC = keySpec;
    }

    public static String cifrar(String valorCifrar) {
        if (CLAVE_SECRETA_SPEC == null)
            return null;

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // Usa la clave constante
            cipher.init(Cipher.ENCRYPT_MODE, CLAVE_SECRETA_SPEC);

            byte[] datosCifrados = cipher.doFinal(valorCifrar.getBytes(StandardCharsets.UTF_8));

            return Base64.getEncoder().encodeToString(datosCifrados);

        } catch (Exception e) {
            System.err.println("Error durante el cifrado: " + e.getMessage());
            return null;
        }
    }

    public static String descifrar(String datosCifradosTexto) {
        if (CLAVE_SECRETA_SPEC == null)
            return null;

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, CLAVE_SECRETA_SPEC);

            byte[] datosDecodificados = Base64.getDecoder().decode(datosCifradosTexto);
            byte[] datosDescifrados = cipher.doFinal(datosDecodificados);

            return new String(datosDescifrados, StandardCharsets.UTF_8);

        } catch (Exception e) {
            System.err.println("Error durante el descifrado: " + e.getMessage());
            return null;
        }
    }
}

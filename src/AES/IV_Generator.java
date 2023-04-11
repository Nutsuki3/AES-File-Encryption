import java.util.Base64;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class IV_Generator {
    public static void generateIV(String Mode, String Keys) {
        final byte[] salt = Keys.getBytes();
        final String key = Keys + Base64.getEncoder().encodeToString(salt);
        final String iv;

        try {
            if (Mode.equalsIgnoreCase("cbc")) {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(key.getBytes(StandardCharsets.UTF_8));
                iv = Base64.getEncoder().encodeToString(hash).substring(0, 16);
                Main.ivg = iv.getBytes(StandardCharsets.UTF_8);
            }
            else if (Mode.equalsIgnoreCase("gcm")) {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hash = md.digest(key.getBytes(StandardCharsets.UTF_8));
                iv = Base64.getEncoder().encodeToString(hash).substring(0, 12);
                Main.ivg = iv.getBytes(StandardCharsets.UTF_8);
            }
        } catch (NoSuchAlgorithmException e) {
            System.err.println(e);
        }
    }
}
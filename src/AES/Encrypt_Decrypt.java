import java.io.File;
import java.nio.file.Files;
import java.security.spec.AlgorithmParameterSpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;

public class Encrypt_Decrypt {

        private static Cipher cipher;
        private static AlgorithmParameterSpec paramSpec;

    public static void encrypt(File infile, SecretKey secretKey, String Mode, byte[] iv) {

        try {
            if (Mode.equalsIgnoreCase("ecb") || Mode.equalsIgnoreCase("cbc")) {
                cipher = Cipher.getInstance("AES/" + Mode + "/PKCS5Padding");
            } else if (Mode.equalsIgnoreCase("gcm")) {
                cipher = Cipher.getInstance("AES/" + Mode + "/NoPadding");
            } else {
                System.err.println("Unknown " + Mode + "Mode");
            }
            if (Mode.equalsIgnoreCase("cbc")) {
                paramSpec = new IvParameterSpec(iv);
            } else if (Mode.equalsIgnoreCase("gcm")) {
                paramSpec = new GCMParameterSpec(128, iv);
            }
            byte[] file = Files.readAllBytes(infile.toPath());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, paramSpec);
            byte[] encrypt = cipher.doFinal(file);
            Files.write(infile.toPath(), encrypt);
            System.out.println("\n" + infile + " \u001B[32malready encrypted\u001B[0m\n");
           } catch (Exception e) {
            System.out.println("\n" + infile + " \u001B[31mis not encrypted\u001B[0m\n");
        }
    }

    public static void decrypt(File infile, SecretKey secretKey, String Mode, byte[] iv) {

        try {
            if (Mode.equalsIgnoreCase("ecb") || Mode.equalsIgnoreCase("cbc")) {
                cipher = Cipher.getInstance("AES/" + Mode + "/PKCS5Padding");
            } else if (Mode.equalsIgnoreCase("gcm")) {
                cipher = Cipher.getInstance("AES/" + Mode + "/NoPadding");
            } else {
                System.err.println("Unknown " + Mode + "Mode");
            }
            if (Mode.equalsIgnoreCase("cbc")) {
                paramSpec = new IvParameterSpec(iv);
            } else if (Mode.equalsIgnoreCase("gcm")) {
                paramSpec = new GCMParameterSpec(128, iv);
            }
            byte[] file = Files.readAllBytes(infile.toPath());
            cipher.init(Cipher.DECRYPT_MODE, secretKey, paramSpec);
            byte[] decrypt = cipher.doFinal(file);
            Files.write(infile.toPath(), decrypt);
            System.out.println("\n" + Validate.path + " \u001B[32malready decrypted\u001B[0m\n");
        } catch (Exception e) {
            System.out.println("\n" + Validate.path + " \u001B[31mis not decrypted\u001B[0m\n");
        }
    }
}
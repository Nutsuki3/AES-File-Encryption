import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Objects;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Validate {

    public static File path;

    public static void validate(String file, String AES, String key_size, byte[] iv, String Mode, String Ed, String buf, int loop) {
        final String Modes = AES + " " + Mode;
        final String[] aes = Main.AES;
        final byte[] pass = key_size.getBytes(StandardCharsets.UTF_8);
        File files = new File(file);
        String BackupFol = "Backup";
        SecretKey secretKey;
        int loops = 0;

        try {
            if (files.exists()) {
                if (files.isDirectory()) {
                    File[] folder = files.listFiles();
                    if (folder == null || folder.length == 0) {
                        System.out.println("\n\u001B[31mFile not found in folder at>>> \u001B[0m" + files + "\n");
                        return;
                    }
                    for (File folders: Objects.requireNonNull(folder)) {
                        files = new File(folders.getAbsolutePath());
                        
                        if (buf.equalsIgnoreCase("y")) {
                            BackupFile.backup(files);
                        }
                    }
                    while (loops < loop) {
                        loops++;
                        for (File folders: Objects.requireNonNull(folder)) {
                            files = new File(folders.getAbsolutePath());
                            if (folders.isDirectory() && folders.getName().equals(BackupFol)) {
                                continue;
                            }
                            path = files;
                            if (Modes.equalsIgnoreCase(aes[0])) {
                                if (pass.length == 16 && iv == null) {
                                    secretKey = new SecretKeySpec(Arrays.copyOf(pass, 16), "AES");
                                    if (Ed.equalsIgnoreCase("enc")) {
                                        Encrypt_Decrypt.encrypt(folders, secretKey, Mode, null);
                                    } else if (Ed.equalsIgnoreCase("dec")) {
                                        Encrypt_Decrypt.decrypt(folders, secretKey, Mode, null);
                                    }
                                } else {
                                    System.out.println("\n\u001B[31mPassword must be 16 characters\u001B[0m\n");
                                    return;
                                }
                            } else if (Modes.equalsIgnoreCase(aes[1])) {
                                if (pass.length == 24 && iv == null) {
                                    secretKey = new SecretKeySpec(Arrays.copyOf(pass, 24), "AES");
                                    if (Ed.equalsIgnoreCase("enc")) {
                                        Encrypt_Decrypt.encrypt(folders, secretKey, Mode, null);
                                    } else if (Ed.equalsIgnoreCase("dec")) {
                                        Encrypt_Decrypt.decrypt(folders, secretKey, Mode, null);
                                    }
                                } else {
                                    System.out.println("\n\u001B[31mPassword must be 24 characters\u001B[0m\n");
                                    return;
                                }
                            } else if (Modes.equalsIgnoreCase(aes[2])) {
                                if (pass.length == 32 && iv == null) {
                                    secretKey = new SecretKeySpec(Arrays.copyOf(pass, 32), "AES");
                                    if (Ed.equalsIgnoreCase("enc")) {
                                        Encrypt_Decrypt.encrypt(folders, secretKey, Mode, null);
                                    } else if (Ed.equalsIgnoreCase("dec")) {
                                        Encrypt_Decrypt.decrypt(folders, secretKey, Mode, null);
                                    }
                                } else {
                                    System.out.println("\n\u001B[31mPassword must be 32 characters\u001B[0m\n");
                                    return;
                                }
                            } else if (Modes.equalsIgnoreCase(aes[3])) {
                                if (pass.length == 16 && iv.length == 16) {
                                    secretKey = new SecretKeySpec(Arrays.copyOf(pass, 16), "AES");
                                    if (Ed.equalsIgnoreCase("enc")) {
                                        Encrypt_Decrypt.encrypt(folders, secretKey, Mode, iv);
                                    } else if (Ed.equalsIgnoreCase("dec")) {
                                        Encrypt_Decrypt.decrypt(folders, secretKey, Mode, iv);
                                    }
                                } else if (pass.length != 16) {
                                    System.out.println("\n\u001B[31mPassword must be 16 characters\u001B[0m\n");
                                    return;
                                } else if (iv.length != 16) {
                                    System.out.println("\n\u001B[31miv must be 16 characters\u001B[0m\n");
                                    return;
                                }
                            } else if (Modes.equalsIgnoreCase(aes[4])) {
                                if (pass.length == 24 && iv.length == 16) {
                                    secretKey = new SecretKeySpec(Arrays.copyOf(pass, 24), "AES");
                                    if (Ed.equalsIgnoreCase("enc")) {
                                        Encrypt_Decrypt.encrypt(folders, secretKey, Mode, iv);
                                    } else if (Ed.equalsIgnoreCase("dec")) {
                                        Encrypt_Decrypt.decrypt(folders, secretKey, Mode, iv);
                                    }
                                } else if (pass.length != 24) {
                                    System.out.println("\n\u001B[31mPassword must be 24 characters\u001B[0m\n");
                                    return;
                                } else if (iv.length != 16) {
                                    System.out.println("\n\u001B[31miv must be 16 characters\u001B[0m\n");
                                    return;
                                }
                            } else if (Modes.equalsIgnoreCase(aes[5])) {
                                if (pass.length == 32 && iv.length == 16) {
                                    secretKey = new SecretKeySpec(Arrays.copyOf(pass, 32), "AES");
                                    if (Ed.equalsIgnoreCase("enc")) {
                                        Encrypt_Decrypt.encrypt(folders, secretKey, Mode, iv);
                                    } else if (Ed.equalsIgnoreCase("dec")) {
                                        Encrypt_Decrypt.decrypt(folders, secretKey, Mode, iv);
                                    }
                                } else if (pass.length != 32) {
                                    System.out.println("\n\u001B[31mPassword must be 32 characters\u001B[0m\n");
                                    return;
                                } else if (iv.length != 16) {
                                    System.out.println("\n\u001B[31miv must be 16 characters\u001B[0m\n");
                                    return;
                                }
                            } else if (Modes.equalsIgnoreCase(aes[6])) {
                                if (pass.length == 16 && iv.length == 12) {
                                    secretKey = new SecretKeySpec(Arrays.copyOf(pass, 16), "AES");
                                    if (Ed.equalsIgnoreCase("enc")) {
                                        Encrypt_Decrypt.encrypt(folders, secretKey, Mode, iv);
                                    } else if (Ed.equalsIgnoreCase("dec"))
                                        Encrypt_Decrypt.decrypt(folders, secretKey, Mode, iv);
                                } else if (pass.length != 16) {
                                    System.out.println("\n\u001B[31mPassword must be 16 characters\u001B[0m\n");
                                    return;
                                } else if (iv.length != 12) {
                                    System.out.println("\n\u001B[31miv must be 12 characters\u001B[0m\n");
                                    return;
                                }
                            } else if (Modes.equalsIgnoreCase(aes[7])) {
                                if (pass.length == 24 && iv.length == 12) {
                                    secretKey = new SecretKeySpec(Arrays.copyOf(pass, 24), "AES");
                                    if (Ed.equalsIgnoreCase("enc")) {
                                        Encrypt_Decrypt.encrypt(folders, secretKey, Mode, iv);
                                    } else if (Ed.equalsIgnoreCase("dec"))
                                        Encrypt_Decrypt.decrypt(folders, secretKey, Mode, iv);
                                } else if (pass.length != 24) {
                                    System.out.println("\n\u001B[31mPassword must be 24 characters\u001B[0m\n");
                                    return;
                                } else if (iv.length != 12) {
                                    System.out.println("\n\u001B[31miv must be 12 characters\u001B[0m\n");
                                    return;
                                }
                            } else if (Modes.equalsIgnoreCase(aes[8])) {
                                if (pass.length == 32 && iv.length == 12) {
                                    secretKey = new SecretKeySpec(Arrays.copyOf(pass, 32), "AES");
                                    if (Ed.equalsIgnoreCase("enc")) {
                                        Encrypt_Decrypt.encrypt(folders, secretKey, Mode, iv);
                                    } else if (Ed.equalsIgnoreCase("dec"))
                                        Encrypt_Decrypt.decrypt(folders, secretKey, Mode, iv);
                                } else if (pass.length != 32) {
                                    System.out.println("\n\u001B[31mPassword must be 32 characters\u001B[0m\n");
                                    return;
                                } else if (iv.length != 12) {
                                    System.out.println("\n\u001B[31miv must be 12 characters\u001B[0m\n");
                                    return;
                                }
                            }
                        }
                    }
                } else {
                    if (buf.equalsIgnoreCase("y")) {
                        BackupFile.backup(files);
                    }
                    path = files;
                    while (loops < loop) {
                        loops++;
                        if (Modes.equalsIgnoreCase(aes[0])) {
                            if (pass.length == 16 && iv == null) {
                                secretKey = new SecretKeySpec(Arrays.copyOf(pass, 16), "AES");
                                if (Ed.equalsIgnoreCase("enc")) {
                                    Encrypt_Decrypt.encrypt(files, secretKey, Mode, null);
                                } else if (Ed.equalsIgnoreCase("dec")) {
                                    Encrypt_Decrypt.decrypt(files, secretKey, Mode, null);
                                }
                            } else {
                                System.out.println("\n\u001B[31mPassword must be 16 characters\u001B[0m\n");
                                return;
                            }
                        } else if (Modes.equalsIgnoreCase(aes[1])) {
                            if (pass.length == 24 && iv == null) {
                                secretKey = new SecretKeySpec(Arrays.copyOf(pass, 24), "AES");
                                if (Ed.equalsIgnoreCase("enc")) {
                                    Encrypt_Decrypt.encrypt(files, secretKey, Mode, null);
                                } else if (Ed.equalsIgnoreCase("dec")) {
                                    Encrypt_Decrypt.decrypt(files, secretKey, Mode, null);
                                }
                            } else {
                                System.out.println("\n\u001B[31mPassword must be 24 characters\u001B[0m\n");
                                return;
                            }
                        } else if (Modes.equalsIgnoreCase(aes[2])) {
                            if (pass.length == 32 && iv == null) {
                                secretKey = new SecretKeySpec(Arrays.copyOf(pass, 32), "AES");
                                if (Ed.equalsIgnoreCase("enc")) {
                                    Encrypt_Decrypt.encrypt(files, secretKey, Mode, null);
                                } else if (Ed.equalsIgnoreCase("dec")) {
                                    Encrypt_Decrypt.decrypt(files, secretKey, Mode, null);
                                }
                            } else {
                                System.out.println("\n\u001B[31mPassword must be 32 characters\u001B[0m\n");
                                return;
                            }
                        } else if (Modes.equalsIgnoreCase(aes[3])) {
                            if (pass.length == 16 && iv.length == 16) {
                                secretKey = new SecretKeySpec(Arrays.copyOf(pass, 16), "AES");
                                if (Ed.equalsIgnoreCase("enc")) {
                                    Encrypt_Decrypt.encrypt(files, secretKey, Mode, iv);
                                } else if (Ed.equalsIgnoreCase("dec")) {
                                    Encrypt_Decrypt.decrypt(files, secretKey, Mode, iv);
                                }
                            } else if (pass.length != 16) {
                                System.out.println("\n\u001B[31mPassword must be 16 characters\u001B[0m\n");
                                return;
                            } else if (iv.length != 16) {
                                System.out.println("\n\u001B[31miv must be 16 characters\u001B[0m\n");
                                return;
                            }
                        } else if (Modes.equalsIgnoreCase(aes[4])) {
                            if (pass.length == 24 && iv.length == 16) {
                                secretKey = new SecretKeySpec(Arrays.copyOf(pass, 24), "AES");
                                if (Ed.equalsIgnoreCase("enc")) {
                                    Encrypt_Decrypt.encrypt(files, secretKey, Mode, iv);
                                } else if (Ed.equalsIgnoreCase("dec")) {
                                    Encrypt_Decrypt.decrypt(files, secretKey, Mode, iv);
                                }
                            } else if (pass.length != 24) {
                                System.out.println("\n\u001B[31mPassword must be 24 characters\u001B[0m\n");
                                return;
                            } else if (iv.length != 16) {
                                System.out.println("\n\u001B[31miv must be 16 characters\u001B[0m\n");
                                return;
                            }
                        } else if (Modes.equalsIgnoreCase(aes[5])) {
                            if (pass.length == 32 && iv.length == 16) {
                                secretKey = new SecretKeySpec(Arrays.copyOf(pass, 32), "AES");
                                if (Ed.equalsIgnoreCase("enc")) {
                                    Encrypt_Decrypt.encrypt(files, secretKey, Mode, iv);
                                } else if (Ed.equalsIgnoreCase("dec")) {
                                    Encrypt_Decrypt.decrypt(files, secretKey, Mode, iv);
                                }
                            } else if (pass.length != 32) {
                                System.out.println("\n\u001B[31mPassword must be 32 characters\u001B[0m\n");
                                return;
                            } else if (iv.length != 16) {
                                System.out.println("\n\u001B[31miv must be 16 characters\u001B[0m\n");
                                return;
                            }
                        } else if (Modes.equalsIgnoreCase(aes[6])) {
                            if (pass.length == 16 && iv.length == 12) {
                                secretKey = new SecretKeySpec(Arrays.copyOf(pass, 16), "AES");
                                if (Ed.equalsIgnoreCase("enc")) {
                                    Encrypt_Decrypt.encrypt(files, secretKey, Mode, iv);
                                } else if (Ed.equalsIgnoreCase("dec"))
                                    Encrypt_Decrypt.decrypt(files, secretKey, Mode, iv);
                            } else if (pass.length != 16) {
                                System.out.println("\n\u001B[31mPassword must be 16 characters\u001B[0m\n");
                                return;
                            } else if (iv.length != 16) {
                                System.out.println("\n\u001B[31miv must be 12 characters\u001B[0m\n");
                                return;
                            }
                        } else if (Modes.equalsIgnoreCase(aes[7])) {
                            if (pass.length == 24 && iv.length == 12) {
                                secretKey = new SecretKeySpec(Arrays.copyOf(pass, 24), "AES");
                                if (Ed.equalsIgnoreCase("enc")) {
                                    Encrypt_Decrypt.encrypt(files, secretKey, Mode, iv);
                                } else if (Ed.equalsIgnoreCase("dec"))
                                    Encrypt_Decrypt.decrypt(files, secretKey, Mode, iv);
                            } else if (pass.length != 24) {
                                System.out.println("\n\u001B[31mPassword must be 24 characters\u001B[0m\n");
                                return;
                            } else if (iv.length != 12) {
                                System.out.println("\n\u001B[31miv must be 12 characters\u001B[0m\n");
                                return;
                            }
                        } else if (Modes.equalsIgnoreCase(aes[8])) {
                            if (pass.length == 32 && iv.length == 12) {
                                secretKey = new SecretKeySpec(Arrays.copyOf(pass, 32), "AES");
                                if (Ed.equalsIgnoreCase("enc")) {
                                    Encrypt_Decrypt.encrypt(files, secretKey, Mode, iv);
                                } else if (Ed.equalsIgnoreCase("dec"))
                                    Encrypt_Decrypt.decrypt(files, secretKey, Mode, iv);
                            } else if (pass.length != 32) {
                                System.out.println("\n\u001B[31mPassword must be 32 characters\u001B[0m\n");
                                return;
                            } else if (iv.length != 12) {
                                System.out.println("\n\u001B[31miv must be 12 characters\u001B[0m\n");
                                return;
                            }
                        }
                    }
                }
            } else {
                System.out.println("\n\u001B[31mFile not found at >>>\u001B[0m" + files + "\n");
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
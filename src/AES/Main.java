import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    private static final String[] key_size = {
            "128",
            "192",
            "256"
    };
    private static final String[] qt = {
            "File or Folder path",
            "Password",
            "iv (Optional)",
            "iv (It's not required if you didn't enter it when encrypting.)",
            "Would you like to back up your files? (Y/N)",
            "How many times do you want to encrypt? If you don't want to loop, press the any key.",
            "How many times do you want to decrypt? If you don't want to loop, press any key."
    };
    protected static final String[] AES = {
            "128 ecb",
            "192 ecb",
            "256 ecb",
            "128 cbc",
            "192 cbc",
            "256 cbc",
            "128 gcm",
            "192 gcm",
            "256 gcm"
    };
    protected static byte[] ivg;
    protected static byte[] passgen;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice, KSize, Mode, path, password, ivstr, ed, backup, loops;
        int loop;
        byte[] iv = new byte[0];
        while (true) {
            try {
                System.out.println("\nType enc for Encrypt.");
                System.out.println("Type dec for Decrypt.");
                System.out.println("Type ext for Exit");
                System.out.print(">>> ");
                choice = sc.nextLine();
                ed = choice.substring(0, 3);
                System.out.print("\n");

                if (choice.equalsIgnoreCase("enc") || choice.equalsIgnoreCase("dec")) {
                    while (true) {
                        for (String keys: key_size) {
                            System.out.println("Type " + keys +
                                    " and select the desired cipher to encrypt with AES 128 and the mode of your choice.");
                        }
                        System.out.println("Mode ECB, CBC, GCM");
                        System.out.println("Example  " + "\"" + AES[0] + "\"");
                        System.out.println("\nType [1] for Back.");
                        System.out.println("Type [2] for Exit");
                        System.out.print(">>> ");
                        choice = sc.nextLine();
                        if (Arrays.asList(AES).contains(choice)) {
                            KSize = choice.substring(0, 3);
                            Mode = choice.substring(4, 7).toUpperCase();
                            while (true) {
                                if (choice.equalsIgnoreCase(AES[0]) ||
                                        choice.equalsIgnoreCase(AES[1]) ||
                                        choice.equalsIgnoreCase(AES[2])
                                ) {
                                    System.out.println("\nInput your " + qt[0]);
                                    System.out.print(">>> ");
                                    path = sc.nextLine();
                                    if (path.isEmpty()) {
                                        System.out.println("\n\u001B[31mPlease enter a file or folder path.\u001B[0m");
                                        continue;
                                    }
                                    if (choice.substring(0, 3).equals(key_size[0])) {
                                        System.out.println("\nInput your " + qt[1] + " (16 characters)");
                                    } else if (choice.substring(0, 3).equals(key_size[1])) {
                                        System.out.println("\nInput your " + qt[1] + " (24 characters)");
                                    } else if (choice.substring(0, 3).equals(key_size[2])) {
                                        System.out.println("\nInput your " + qt[1] + " (32 characters)");
                                    }
                                    System.out.print(">>> ");
                                    password = sc.nextLine();
                                    System.out.println();
                                    if (ed.equalsIgnoreCase("enc")) {
                                        System.out.println(qt[5]);
                                    } else {
                                        System.out.println(qt[6]);
                                    }
                                    System.out.print(">>> ");
                                    loops = sc.nextLine();
                                    if (loops.isEmpty()) {
                                        loop = 1;
                                    } else if (Integer.parseInt(loops) >= 0) {
                                        loop = Integer.parseInt(loops);
                                    } else {
                                        loop = 1;
                                    }
                                    if (ed.equalsIgnoreCase("enc")) {
                                        System.out.println();
                                        System.out.println(qt[4]);
                                        System.out.print(">>> ");
                                        backup = sc.nextLine();
                                        if (backup.isEmpty()) {
                                            backup = "n";
                                        }
                                        Validate.validate(path, KSize, password, null, Mode, ed, backup, loop);
                                    } else {
                                        Validate.validate(path, KSize, password, null, Mode, ed, "n", loop);
                                    }
                                    break;
                                } else if (choice.equalsIgnoreCase(AES[3]) ||
                                        choice.equalsIgnoreCase(AES[4]) ||
                                        choice.equalsIgnoreCase(AES[5])
                                ) {
                                    System.out.println("\nInput your " + qt[0]);
                                    System.out.print(">>> ");
                                    path = sc.nextLine();
                                    if (path.isEmpty()) {
                                        System.out.println("\n\u001B[31mPlease enter a file or folder path.\u001B[0m");
                                        continue;
                                    }
                                    if (choice.substring(0, 3).equals(key_size[0])) {
                                        System.out.println("\nInput your " + qt[1] + " (16 characters)");
                                    } else if (choice.substring(0, 3).equals(key_size[1])) {
                                        System.out.println("\nInput your " + qt[1] + " (24 characters)");
                                    } else if (choice.substring(0, 3).equals(key_size[2])) {
                                        System.out.println("\nInput your " + qt[1] + " (32 characters)");
                                    }
                                    System.out.print(">>> ");
                                    password = sc.nextLine();
                                    if (ed.equalsIgnoreCase("enc")) {
                                        System.out.println("\nInput your " + qt[2] + " (16 characters)");
                                    } else {
                                        System.out.println("\nInput your " + qt[3] + " (16 characters)");
                                    }
                                    System.out.print(">>> ");
                                    ivstr = sc.nextLine();
                                    if (ivstr.isEmpty()) {
                                        IV_Generator.generateIV(Mode, password);
                                    } else {
                                        iv = ivstr.getBytes(StandardCharsets.UTF_8);
                                    }
                                    if (ivg != null) {
                                        if (ivg.length == 16) {
                                            iv = ivg;
                                        } else if (ivg.length == 12) {
                                            iv = ivg;
                                        }
                                    }
                                    System.out.println();
                                    if (ed.equalsIgnoreCase("enc")) {
                                        System.out.println(qt[5]);
                                    } else {
                                        System.out.println(qt[6]);
                                    }
                                    System.out.print(">>> ");
                                    loops = sc.nextLine();
                                    if (loops.isEmpty()) {
                                        loop = 1;
                                    } else if (Integer.parseInt(loops) >= 0) {
                                        loop = Integer.parseInt(loops);
                                    } else {
                                        loop = 1;
                                    }
                                    if (ed.equalsIgnoreCase("enc")) {
                                        System.out.println();
                                        System.out.println(qt[4]);
                                        System.out.print(">>> ");
                                        backup = sc.nextLine();
                                        if (backup.isEmpty()) {
                                            backup = "n";
                                        }
                                        Validate.validate(path, KSize, password, iv, Mode, ed, backup, loop);
                                    } else {
                                        Validate.validate(path, KSize, password, iv, Mode, ed, "n", loop);
                                    }
                                    break;
                                } else if (choice.equalsIgnoreCase(AES[6]) ||
                                        choice.equalsIgnoreCase(AES[7]) ||
                                        choice.equalsIgnoreCase(AES[8])
                                ) {
                                    System.out.println("\nInput your " + qt[0]);
                                    System.out.print(">>> ");
                                    path = sc.nextLine();
                                    if (path.isEmpty()) {
                                        System.out.println("\n\u001B[31mPlease enter a file or folder path.\u001B[0m");
                                        continue;
                                    }
                                    if (choice.substring(0, 3).equals(key_size[0])) {
                                        System.out.println("\nInput your " + qt[1] + " (16 characters)");
                                    } else if (choice.substring(0, 3).equals(key_size[1])) {
                                        System.out.println("\nInput your " + qt[1] + " (24 characters)");
                                    } else if (choice.substring(0, 3).equals(key_size[2])) {
                                        System.out.println("\nInput your " + qt[1] + " (32 characters)");
                                    }
                                    System.out.print(">>> ");
                                    password = sc.nextLine();
                                    if (ed.equalsIgnoreCase("enc")) {
                                        System.out.println("\nInput your " + qt[2] + " (12 characters)");
                                    } else {
                                        System.out.println("\nInput your " + qt[3] + " (12 characters)");
                                    }
                                    System.out.print(">>> ");
                                    ivstr = sc.nextLine();
                                    if (ivstr.isEmpty()) {
                                        IV_Generator.generateIV(Mode, password);
                                    } else {
                                        iv = ivstr.getBytes(StandardCharsets.UTF_8);
                                    }
                                    if (ivg != null) {
                                        if (ivg.length == 16) {
                                            iv = ivg;
                                        } else if (ivg.length == 12) {
                                            iv = ivg;
                                        }
                                    }
                                    System.out.println();
                                    if (ed.equalsIgnoreCase("enc")) {
                                        System.out.println(qt[5]);
                                    } else {
                                        System.out.println(qt[6]);
                                    }
                                    System.out.print(">>> ");
                                    loops = sc.nextLine();
                                    if (loops.isEmpty()) {
                                        loop = 1;
                                    } else if (Integer.parseInt(loops) >= 0) {
                                        loop = Integer.parseInt(loops);
                                    } else {
                                        loop = 1;
                                    }
                                    if (ed.equalsIgnoreCase("enc")) {
                                        System.out.println();
                                        System.out.println(qt[4]);
                                        System.out.print(">>> ");
                                        backup = sc.nextLine();
                                        if (backup.isEmpty()) {
                                            backup = "n";
                                        }
                                        Validate.validate(path, KSize, password, iv, Mode, ed, backup, loop);
                                    } else {
                                        Validate.validate(path, KSize, password, iv, Mode, ed, "n", loop);
                                    }
                                    break;
                                } else {
                                    System.err.println("\n\u001B[31mSomething wrong :(\u001B[0m\n");
                                }
                            }
                        } else if (choice.equals("1")) {
                            break;
                        } else if (choice.equals("2")) {
                            System.out.println("\n\u001B[33mThanks for using Program :>\u001B[0m");
                            sc.close();
                            return;
                        } else if (choice.isEmpty()) {
                            System.out.println("\n\u001B[31mType the options you want.\u001B[0m\n");
                        } else {
                            System.out.println("\n\u001B[31mSorry, we don't have that information :(\u001B[0m\n");
                        }
                    }
                } else if (choice.equalsIgnoreCase("ext")) {
                    System.out.println("\n\u001B[33mThanks for using Program :>\u001B[0m");
                    sc.close();
                    return;
                } else {
                    System.out.println("\n\u001B[31mType enc, dec or ext\u001B[0m\n");
                }

            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("\n\u001B[31mType enc, dec or ext\u001B[0m");
            }
        }
    }
}
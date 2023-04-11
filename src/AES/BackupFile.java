import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class BackupFile {

    public static void backup(File files) {
        String filePath = files.toString();
        File file = new File(filePath);
        File backupFol = new File(file.getParent(), "Backup");
        if (!backupFol.exists() || !backupFol.isDirectory()) {
            backupFol.mkdir();
        }
        String fileName = file.getName();
        String Extension = fileName;
        Path source = file.toPath();
        Path target = Paths.get(backupFol.getPath() + File.separator + Extension);
        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
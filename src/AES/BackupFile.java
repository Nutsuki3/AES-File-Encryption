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
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            String Extension = fileName.substring(0, index);
            Path source = file.toPath();
            Path target = Paths.get(backupFol.getPath() + File.separator + Extension + ".bak");
            try {
                Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

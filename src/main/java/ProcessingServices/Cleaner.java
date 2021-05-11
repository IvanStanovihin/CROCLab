package ProcessingServices;

import Properties.PropertyLoader;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Cleaner {

    public static void deleteOldOutDirectory(PropertyLoader property){
        String outDirectoryName = property.getOutDirectory();
        Path outDirPath = Paths.get(outDirectoryName);
        if (Files.exists(outDirPath)) {
            File dirtyDir = new File(outDirectoryName);
            cleanDir(dirtyDir);
        }
    }

    private static void cleanDir(File dirtyDir){
        if (!dirtyDir.delete()){
            for (File fileInDir : dirtyDir.listFiles()){
                cleanDir(fileInDir);
            }
        }
    }
}

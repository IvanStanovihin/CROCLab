package ProcessingServices;

import Properties.PropertyLoader;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Class for clearing the output directory
 */
public class Cleaner {

    /**
     * Method for deleting the old output directory
     * @param property app property file
     */
    public static void deleteOldOutDirectory(PropertyLoader property){
        String outDirectoryName = property.getOutDirectory();
        Path outDirPath = Paths.get(outDirectoryName);
        if (Files.exists(outDirPath)) {
            File dirtyDir = new File(outDirectoryName);
            cleanDir(dirtyDir);
        }
    }

    /**
     * Method for clearing the output directory of old files
     * @param dirtyDir old dir
     */
    private static void cleanDir(File dirtyDir){
        if (!dirtyDir.delete()){
            for (File fileInDir : dirtyDir.listFiles()){
                cleanDir(fileInDir);
            }
        }
    }
}

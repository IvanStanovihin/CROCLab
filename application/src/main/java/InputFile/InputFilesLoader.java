package InputFile;

import java.io.File;
import java.util.ArrayList;
/**Loads files that the user has prepared*/
public class InputFilesLoader {

    /**List of input files*/
    private static ArrayList<InputFile> inputFiles = new ArrayList<>();

    /**Loading files*/
    public static ArrayList<InputFile> loadInputFiles(String inputFilesDirectoryPath){
        File inputFilesDirectory = new File(inputFilesDirectoryPath);
        for(File inputFile : inputFilesDirectory.listFiles()){
            if (inputFile.isDirectory()){
                loadInputFiles(inputFile.getPath());
            }else{
                inputFiles.add(new InputFile(inputFile.getPath()));
            }
        }
        return inputFiles;
    }

}

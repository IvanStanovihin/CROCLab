package TAaC.Logic.ReplacementFile;

import TAaC.Logic.Handler.Handler;
import TAaC.Logic.InputFile.InputFile;
import TAaC.Logic.ReportLog.LogOperation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Class to create replacement files
 */
public class CreatorReplacementFile {
    /**
     * Create replacement file and log about it
     * @param outDir out directory
     * @param inputFiles files to process and read from, then write to replacement file
     */
    public static void createReplacementFile(String outDir, ArrayList<InputFile> inputFiles) {
        Handler.reportLog.startModule();
        String currentDir = outDir + "/Replacement";
        try{
            Files.createDirectories(Paths.get(currentDir));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        for (InputFile inputFile : inputFiles) {
            Handler.reportLog.startCurrentOperation(LogOperation.CREATE_REPLACEMENT_FILE, inputFile.getFileName());
            ReplacementFile replacementFile = inputFile.getReplacementFile();
            replacementFile.createFile(currentDir);
            Handler.reportLog.endOperation();
        }
        Handler.reportLog.endModule("File with replacements ");
    }
}

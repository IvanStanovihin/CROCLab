package TAaC.Logic.ProcessingServices;

import TAaC.Logic.Handler.Handler;
import TAaC.Logic.InputFile.InputFile;
import TAaC.Logic.ReportLog.LogOperation;
import java.util.ArrayList;

/**
 * Class for removing extra initials from text
 */
public class InitialsRemover {

    /**
     * Method for checking the module connection
     * @return true if the module is enabled , else false
     */
    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnableInitialsModule();
    }

    /**
     * Method of processing initials
     * @param inputFiles ArrayList with inputFiles
     */
    public static void removeInitials(ArrayList<InputFile>inputFiles){
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            for (InputFile inputFile : inputFiles) {
                Handler.reportLog.startCurrentOperation(LogOperation.REMOVE_INITIALS, inputFile.getFileName());
                processFile(inputFile);
                Handler.reportLog.endOperation();
            }
            Handler.reportLog.endModule("Initials ");
        }
    }

    /**
     * Find and delete extra initials in a single input file
     * @param inputFile input file for processing
     */
    private static void processFile(InputFile inputFile){
        String fileText = inputFile.getFileText();
        String cleanText = fileText.replaceAll("(?<=^|[\\W&&[^А-Яа-яёЁ]])[А-Я]\\.\\s*[А-Я]\\.", "");
        inputFile.setFileText(cleanText);
    }
}

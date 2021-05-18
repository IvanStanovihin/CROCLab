package ProcessingServices;

import Handler.Handler;
import InputFile.InputFile;
import ReportLog.LogOperation;

import java.util.ArrayList;

/**
 * Class for removing extra spaces
 */
public class WhitespaceService {

    /**
     * Method for checking the module connection
     * @return true if the module is enabled , else false
     */
    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnableWhitespaceRemoveModule();
    }

    /**
     * Method of processing extra spaces
     * @param inputFiles inputFiles ArrayList with inputFiles
     */
    public static void removeExtraWhitespace(ArrayList<InputFile>inputFiles){
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            for (InputFile inputFile : inputFiles) {
                Handler.reportLog.startCurrentOperation(LogOperation.REMOVE_EXTRAS_WHITESPACE, inputFile.getFileName());
                handleFile(inputFile);
                Handler.reportLog.endOperation();
            }
            Handler.reportLog.endModule("Extras whitespaces ");
        }
    }

    /**
     * Find and delete extra space in a single input file
     * @param inputFile input file for processing
     */
    private static void handleFile(InputFile inputFile) {
        ArrayList<String>sentences = inputFile.getSentences();
        ArrayList<String>cleanSentences = new ArrayList<>();
        for (String sentence : sentences) {
            sentence =  sentence.trim().replaceAll("\\s+", " ").
                    replaceAll("\\s+(?=[!?,.])", "");
            cleanSentences.add(sentence);
        }
        inputFile.setSentences(cleanSentences);
    }

}

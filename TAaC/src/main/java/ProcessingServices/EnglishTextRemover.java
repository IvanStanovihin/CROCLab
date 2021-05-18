package ProcessingServices;

import Handler.Handler;
import InputFile.InputFile;
import ReportLog.LogOperation;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for removing english words from texts
 */
public class EnglishTextRemover {

    /**
     * Method for checking the module connection
     * @return true if the module is enabled , else false
     */
    private static boolean isModuleEnable(){
            return Handler.getProperty().isEnableRemoveEnglishTextModule();
    }


    /**
     * Method of processing english words
     * @param inputFiles ArrayList with inputFiles
     */
    public static void removeEnglishText(ArrayList<InputFile>inputFiles) {
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            for (InputFile inputFile : inputFiles) {
                Handler.reportLog.startCurrentOperation(LogOperation.REMOVE_ENGLISH, inputFile.getFileName());
                handleFile(inputFile);
                Handler.reportLog.endOperation();
            }
            Handler.reportLog.endModule("Remove english words ");
        }
    }

    /**
     * Find and delete english words in a single input file
     * @param fileWithEnglishLetters input file for processing
     */
    private static void handleFile(InputFile fileWithEnglishLetters){
        ArrayList<String>fileSentences = new ArrayList<>(fileWithEnglishLetters.getSentences());
        for (String sentence : fileSentences){
            Pattern englishLettersPattern = Pattern.compile("[A-Za-z]");
            Matcher matcher = englishLettersPattern.matcher(sentence);
            if (matcher.find()){
                fileWithEnglishLetters.moveToQuarantine(sentence);
            }
        }
    }
}

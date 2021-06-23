package com.Logic.ProcessingServices;

import com.Logic.Handler.Handler;
import com.Logic.InformationFiles.FileWithEnglishText;
import com.Logic.InputFile.InputFile;
import com.Logic.ReportLog.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for searching english words
 */
public class EnglishTextFinder {

    /**
     * Method for checking the module connection
     * @return true if the module is enabled , else false
     */
    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnableFindEnglishModule();
    }

    /**
     * Method of processing english words
     * @param inputFiles ArrayList with inputFiles
     */
    public static void findEnglishText(ArrayList<InputFile>inputFiles){
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            for (InputFile inputFile : inputFiles) {
                Handler.reportLog.startCurrentOperation(LogOperation.FIND_ENGLISH, inputFile.getFileName());
                processFile(inputFile);
                Handler.reportLog.endOperation();
            }
            Handler.reportLog.endModule("Find english words ");
        }
    }

    /**
     * Find and expand english words in a single input file
     * @param inputFile input file for processing
     */
    private static void processFile(InputFile inputFile){
        ArrayList<String>fileSentences = inputFile.getSentences();
        FileWithEnglishText fileWithEnglishText = inputFile.getFileWithEnglishText();
        for (String sentence : fileSentences) {
            Pattern englishTextPattern = Pattern.compile("[A-Za-z]");
            Matcher matcher = englishTextPattern.matcher(sentence);
            if (matcher.find()){
                fileWithEnglishText.addEnglishSentences(sentence);
            }
        }
    }
}

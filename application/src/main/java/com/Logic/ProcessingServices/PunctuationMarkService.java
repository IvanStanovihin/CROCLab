package com.Logic.ProcessingServices;

import com.Logic.Handler.Handler;
import com.Logic.InputFile.InputFile;
import com.Logic.ReportLog.*;

import java.util.ArrayList;

/**
 * Class for handling different characters
 */
public class PunctuationMarkService {

    /**
     * Method for checking the module connection
     * @return true if the module is enabled , else false
     */
    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnablePunctuationMarkModule();
    }

    /**
     * Method of processing different characters
     * @param inputFiles inputFiles ArrayList with inputFiles
     */
    public static void handle(ArrayList<InputFile>inputFiles){
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            for (InputFile inputFile : inputFiles) {
                Handler.reportLog.startCurrentOperation(LogOperation.PROCESS_PUNCTUATIONS, inputFile.getFileName());
                handlePunctuationMarks(inputFile);
                Handler.reportLog.endOperation();
                System.gc();
            }
            Handler.reportLog.endModule("Punctuation marks ");
        }
    }

    /**
     * Find and disclosure different characters in a single input file
     * @param inputFile input file for processing
     */
    private static void handlePunctuationMarks(InputFile inputFile){
        String fileText = inputFile.getFileText();
        String handledFileText = fileText.replaceAll("\\.{2,}", "\\.")
                .replaceAll("#", " номер ").replaceAll("№", " номер ")
                .replaceAll("%", " процент ").replaceAll("\\+", " плюс ")
                .replaceAll("₽", " рублей ").replaceAll("\\$", " долларов ")
                .replaceAll(";", "\\.").replaceAll("<(/?[^<>]*)>", " ")
                .replaceAll(":", " ").replaceAll("[\\W&&[^-а-яА-ЯёЁA-Za-z?.!\\s]]", " ").
                replaceAll("(?<=[!?.])[\\s!?.]+", "").replaceAll("[!?]", ".");
        inputFile.setFileText(handledFileText);
    }

}

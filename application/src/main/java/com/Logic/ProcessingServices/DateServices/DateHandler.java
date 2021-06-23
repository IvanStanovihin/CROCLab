package com.Logic.ProcessingServices.DateServices;

import com.Logic.Handler.Handler;
import com.Logic.InputFile.InputFile;
import com.Logic.ReportLog.LogOperation;

import java.util.ArrayList;


/**
 * TAaC.Logic.Handler of dates
 */
public class DateHandler {
    /**
     * Verify if module is enabled
     * @return result of verifying
     */
    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnableDatesModule();
    }

    /**
     * Primary processing files
     * @param inputFiles files to process
     */
    public static void processDate(ArrayList<InputFile> inputFiles){
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            StringToData.generatePatternsQueue();
            for (InputFile inputFile : inputFiles) {
                Handler.reportLog.startCurrentOperation(LogOperation.PROCESSING_DATES, inputFile.getFileName());
                processFile(inputFile);
                Handler.reportLog.endOperation();
            }
            Handler.reportLog.endModule("Dates ");
        }
    }

    /**
     * Process files
     * @param inputFile files
     */
    private static void processFile(InputFile inputFile){
        String fileText = inputFile.getFileText();
        String processedText = StringToData.isPatterned(fileText);
        inputFile.setFileText(processedText);
    }
}

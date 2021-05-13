package ProcessingServices.DateServices;

import Handler.Handler;
import InputFile.InputFile;
import ReportLog.LogOperation;

import java.util.ArrayList;

public class DateHandler {

    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnableDatesModule();
    }

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

    private static void processFile(InputFile inputFile){
        String fileText = inputFile.getFileText();
        String processedText = StringToData.isPatterned(fileText);
        inputFile.setFileText(processedText);
    }
}

package ProcessingServices;

import Handler.Handler;
import InputFile.InputFile;
import ReportLog.*;

import java.util.ArrayList;

public class PunctuationMarkService {

    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnablePunctuationMarkModule();
    }

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

    private static void handlePunctuationMarks(InputFile inputFile){
        String fileText = inputFile.getFileText();
        String handledFileText = fileText.replaceAll("\\.{2,}", "\\.")
                .replaceAll("#", " номер ").replaceAll("№", " номер ")
                .replaceAll("%", " процент ").replaceAll("\\+", " плюс ")
                .replaceAll("₽", " рублей ").replaceAll("\\$", " долларов ")
                .replaceAll(";", "\\.").replaceAll("<(/?[^<>]*)>", " ")
                .replaceAll(":", " ").replaceAll("[\\W&&[^а-яА-ЯёЁ,?.!\\s]]", " ").
                replaceAll("(?<=[!?.])[\\s!?.]+", "");
        inputFile.setFileText(handledFileText);
    }

}

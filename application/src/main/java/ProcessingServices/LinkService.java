package ProcessingServices;

import Handler.Handler;
import InputFile.InputFile;
import ReportLog.LogOperation;

import java.util.ArrayList;



public class LinkService {

    private static final String[] linksRegex = {"(https?:\\/\\/)?([\\w-]{1,32}\\.[\\w-]{1,32})[^\\s@]*",
    "([a-z0-9_-]+\\.)*[a-z0-9_-]+@[a-z0-9_-]+(\\.[a-z0-9_-]+)*\\.[a-z]{2,6}"};

    public static void handle(ArrayList<InputFile> inputFiles){
        Handler.reportLog.startModule();
        for (InputFile inputFile : inputFiles){
            Handler.reportLog.startCurrentOperation(LogOperation.PROCESS_LINKS, inputFile.getFileName());
            removeLinks(inputFile);
            Handler.reportLog.endOperation();
        }
        Handler.reportLog.endModule("Link module ");
    }


    private static void removeLinks(InputFile inputFile){
        String fileText = inputFile.getFileText();
        String cleanText = fileText;
        cleanText = cleanText.replaceAll("e mail", "email")
                .replaceAll("e-mail", "email").replaceAll("E-mail", "email");
        for (String linkRegexp : linksRegex) {
            cleanText = cleanText.replaceAll(linkRegexp, " ");
        }
        inputFile.setFileText(cleanText);
    }


}

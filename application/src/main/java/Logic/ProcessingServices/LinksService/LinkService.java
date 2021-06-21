package TAaC.Logic.ProcessingServices.LinksService;

import TAaC.Logic.Handler.Handler;
import TAaC.Logic.InputFile.InputFile;
import TAaC.Logic.ReportLog.LogOperation;
import java.util.ArrayList;



public class LinkService {

    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnableLinksModule();
    }

    public static void handle(ArrayList<InputFile> inputFiles){
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            for (InputFile inputFile : inputFiles) {
                Handler.reportLog.startCurrentOperation(LogOperation.PROCESS_LINKS, inputFile.getFileName());
                removeLinks(inputFile);
                Handler.reportLog.endOperation();
            }
            Handler.reportLog.endModule("Links  ");
        }
    }


    private static void removeLinks(InputFile inputFile){
        String fileText = inputFile.getFileText();
        String cleanText = LinksRemover.removeLinks(fileText);
        inputFile.setFileText(cleanText);
    }


}

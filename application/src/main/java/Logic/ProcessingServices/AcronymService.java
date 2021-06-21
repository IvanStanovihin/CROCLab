package TAaC.Logic.ProcessingServices;

import TAaC.Logic.ProtectWords.ProtectedWordsStorage;
import TAaC.Logic.Handler.Handler;
import TAaC.Logic.InputFile.InputFile;
import TAaC.Logic.ReportLog.LogOperation;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for disclosure acronym into text
 */
public class AcronymService {

    /**
     * Method for checking the module connection
     * @return true if the module is enabled , else false
     */
    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnableAcronymsModule();
    }

    /**
     * Method of processing acronyms
     * @param inputFiles ArrayList with inputFiles
     */
    public static void acronymsInQuarantine(ArrayList<InputFile> inputFiles){
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            for (InputFile inputFile : inputFiles) {
                Handler.reportLog.startCurrentOperation(LogOperation.REMOVE_ACRONYMS, inputFile.getFileName());
                handleAcronyms(inputFile);
                Handler.reportLog.endOperation();
            }
            Handler.reportLog.endModule("Acronyms ");
        }
    }

    /**
     * Find and adding to quarantine sentences with acronyms
     * @param inputFile input file for processing
     */
    private static void handleAcronyms(InputFile inputFile){
        ArrayList<String>fileSentences = new ArrayList<>(inputFile.getSentences());
        for (String sentence : fileSentences){
            Pattern acronymPattern = Pattern.compile("(?<=^|[\\W&&[^А-Яа-я]])[А-ЯЁ]{2,5}(?=$|[\\W&&[^А-Яа-я]])");
            Matcher matcher = acronymPattern.matcher(sentence);
            if (matcher.find()){
                String foundAcronym = sentence.substring(matcher.start(), matcher.end());
                if (!ProtectedWordsStorage.isWordProtected(foundAcronym)) {//Если найденного слова нет в списке исключений,
                    //помещаем в карантин предложение, в котором оно находится.
                    inputFile.moveToQuarantine(sentence);
                    inputFile.getQuarantineWordsFile().addQuarantineWord(foundAcronym);
                }
            }
        }

    }
}

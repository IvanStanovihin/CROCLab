package TAaC.Logic.ProcessingServices;

import TAaC.Logic.Handler.Handler;
import TAaC.Logic.InputFile.InputFile;
import TAaC.Logic.ReportLog.*;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for disclosure abbreviation into text
 */
public class AbbreviationFinder {

    /**
     * Method for checking the module connection
     * @return true if the module is enabled , else false
     */
    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnableAbbreviationsFindModule();
    }

    /**
     * Method of abbreviation processing
     * @param inputFiles ArrayList with inputFiles
     */
    public static void processAbbreviations(ArrayList<InputFile> inputFiles) {
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            for (InputFile inputFile : inputFiles) {
                Handler.reportLog.startCurrentOperation(LogOperation.FIND_ABBREVIATIONS, inputFile.getFileName());
                findAbbreviationsInInputFile(inputFile);
                Handler.reportLog.endOperation();
            }
            Handler.reportLog.endModule("Abbreviations ");
        }
    }

    /**
     * Method for searching for abbreviations in the text
     * @param inputFile single input file
     */
    private static void findAbbreviationsInInputFile(InputFile inputFile) {
        String fileText = inputFile.getFileText();
        Pattern abbreviationPattern = Pattern.compile("\\s([А-Яа-яёЁA-Za-z]){1,3}\\.");
        Matcher matcher = abbreviationPattern.matcher(fileText);
        while (matcher.find()) {
            String foundAbbreviation = fileText.substring(matcher.start(), matcher.end());
            String sentenceWithAbbreviation = getSentenceWithAbbreviation(fileText, matcher.start(),
                    matcher.end() + 1);
            inputFile.getFileWithAbbreviations().addAbbreviationSentences(sentenceWithAbbreviation + "(" + foundAbbreviation + ")");
        }
    }

    /**
     * Method for getting an offer with an abbreviation
     * @param fileText text of the input file
     * @param startAbbreviationIndex index of the beginning of the abbreviation
     * @param endAbbreviationIndex index of the end of the abbreviation
     * @return sentence containing an abbreviation
     */
    private static String getSentenceWithAbbreviation(String fileText, int startAbbreviationIndex, int endAbbreviationIndex) {
        int startSentenceIndex = 0;
        int endSentenceIndex = fileText.length();
        for (int i = startAbbreviationIndex; i > 0; i--) {
            char currentCharacter = fileText.charAt(i);
            if (currentCharacter == '.') {
                startSentenceIndex = i + 1;
                break;
            }
        }
        for (int i = endAbbreviationIndex; i < fileText.length(); i++) {
            char currentCharacter = fileText.charAt(i);
            if (currentCharacter == '.') {
                endSentenceIndex = i;
                break;
            }
        }
        return fileText.substring(startSentenceIndex, endSentenceIndex);
    }


}

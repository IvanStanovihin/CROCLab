package TAaC.Logic.ReplacingWords;

import TAaC.Logic.Dictionary.*;
import TAaC.Logic.Dictionary.DictionaryWhitespaceWords;
import TAaC.Logic.Handler.Handler;
import TAaC.Logic.InputFile.InputFile;
import TAaC.Logic.ReportLog.*;


import java.util.ArrayList;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Replace group of words with whitespaces
 */
public class ReplacerSpaceWords {
    /**
     * Verify if module is needed
     * @return result of verifying
     */
    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnableDictionaryWordsModule();
    }

    public static void handleWhitespaceWords(DictionaryWhitespaceWords dictionaryWhitespaceWords, ArrayList<InputFile> inputFiles) {
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            for (InputFile inputfile : inputFiles) {
                Handler.reportLog.startCurrentOperation(LogOperation.WHITESPACE_DICTIONARY, inputfile.getFileName());
                processInputFile(dictionaryWhitespaceWords, inputfile);
                Handler.reportLog.endOperation();
            }
            Handler.reportLog.endModule("Whitespace dictionary words ");
        }
    }

    /**
     * Processing files
     * @param whiteSpaceWordsDictionary information concluded group of words with whitespaces
     * @param inputFile file to process
     */
    private static void processInputFile(DictionaryWhitespaceWords whiteSpaceWordsDictionary, InputFile inputFile){
        Map<String, UnreadableWordHandler> wordsReplacements = whiteSpaceWordsDictionary.getWordReplacement();
        for (Map.Entry<String, UnreadableWordHandler> wordReplacement : wordsReplacements.entrySet()){
            String fileText = inputFile.getFileText();
            StringBuilder cleanText = new StringBuilder();
            UnreadableWordHandler unreadableWordHandler = wordReplacement.getValue();
            Pattern unreadableWordPattern = unreadableWordHandler.getUnreadableWordPattern();
            String wordFromDictionary = wordReplacement.getKey();

            Matcher matcher = unreadableWordPattern.matcher(fileText);
            while (matcher.find()){
                String unreadableWordReplacement = unreadableWordHandler.getReplacement();
                matcher.appendReplacement(cleanText, unreadableWordReplacement);
                inputFile.getReplacementFile().addReplacement(wordFromDictionary, unreadableWordReplacement, "dictionaryWhitespaceWords");
            }
            matcher.appendTail(cleanText);
            inputFile.setFileText(cleanText.toString());
        }
    }
}

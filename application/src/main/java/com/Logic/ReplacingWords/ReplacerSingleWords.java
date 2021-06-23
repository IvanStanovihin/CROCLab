package com.Logic.ReplacingWords;

import com.Logic.Dictionary.DictionarySingleWords;
import com.Logic.Handler.Handler;
import com.Logic.InputFile.InputFile;
import com.Logic.ReportLog.*;

import java.util.ArrayList;

/**
 * Class to replace words saved in SingleWords-file
 */
public class ReplacerSingleWords {
    /**
     * Verify if module is needed
     * @return result of verifying
     */
    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnableDictionaryWordsModule();
    }

    /**
     * Start of processing single words
     * @param dictionarySingleWords information concluded each single word
     * @param inputFiles files to process
     */
    public static void handleSingleWords(DictionarySingleWords dictionarySingleWords, ArrayList<InputFile> inputFiles){
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            for (InputFile inputFile : inputFiles) {
                Handler.reportLog.startCurrentOperation(LogOperation.SINGLE_DICTIONARY, inputFile.getFileName());
                processFile(inputFile, dictionarySingleWords);
                Handler.reportLog.endOperation();
            }
            Handler.reportLog.endModule("Single dictionary words ");
        }
    }

    /**
     * Process file to replace word token with dot or not
     * @param inputFile file to process
     * @param dictionarySingleWords information concluded each single word
     */
    private static void processFile(InputFile inputFile, DictionarySingleWords dictionarySingleWords){
        String[] wordToken = tokenizeFile(inputFile);
        for (int i = 0; i < wordToken.length; i++){//ветка для токенов содержащих точку
            if (wordToken[i].contains("!") || wordToken[i].contains("?") || wordToken[i].contains(",")){
                wordToken[i] = processTokenWithPunctuation(inputFile, dictionarySingleWords, wordToken[i]);
            }else {
                if (wordToken[i].contains(".")) {
                    wordToken[i] = processDotToken(inputFile, dictionarySingleWords, wordToken[i]);
                } else {//ветка для токенов не содержащих точку
                    wordToken[i] = processNoDotToken(inputFile, dictionarySingleWords, wordToken[i]);
                }
            }
        }
        String result = String.join(" ", wordToken);
        inputFile.setFileText(result);
    }

    /**
     * Evaluate token
     * @param inputFile file to process
     * @param dictionarySingleWords information concluded each single word
     * @param tokenWithPunctuation token with punctuation mark
     * @return processed token
     */
    private static String processTokenWithPunctuation(InputFile inputFile, DictionarySingleWords dictionarySingleWords,
                                               String tokenWithPunctuation){
        String lastTokenCharacter = tokenWithPunctuation.substring(tokenWithPunctuation.length()-1 );
        if (lastTokenCharacter.equals("!") || lastTokenCharacter.equals("?") || lastTokenCharacter.equals(",") || lastTokenCharacter.equals(".")){
            String tokenWithoutPunctuation = tokenWithPunctuation.substring(0, tokenWithPunctuation.length()-1 );
            String replacement = dictionarySingleWords.getSingleWordReplacement(tokenWithoutPunctuation);
            if (replacement != null){
                inputFile.getReplacementFile().addReplacement(tokenWithoutPunctuation, replacement, "singleWordAbbreviation");
                return replacement + lastTokenCharacter;
            }else {
                return tokenWithPunctuation;
            }
        }else{
            return tokenWithPunctuation;
        }
    }

    /**
     * Evaluate token with dot
     * @param inputFile file to process
     * @param dictionarySingleWords information concluded each single word
     * @param dotToken token with dot
     * @return new token
     */
    private static String processDotToken(InputFile inputFile, DictionarySingleWords dictionarySingleWords,
                                   String dotToken){
        if (dotToken.lastIndexOf(".") == dotToken.length()-1 ){
            String dotTokenReplacement = processNoDotToken(inputFile, dictionarySingleWords, dotToken);
            if (dotTokenReplacement.equals(dotToken)){
                String tokenWithoutDot = dotToken.substring(0, dotToken.lastIndexOf("."));
                String tokenWithoutDotReplacement = dictionarySingleWords.getSingleWordReplacement(tokenWithoutDot);
                if (tokenWithoutDotReplacement != null){
                    inputFile.getReplacementFile().addReplacement(tokenWithoutDot, tokenWithoutDotReplacement, "singleWordAbbreviation");
                    return tokenWithoutDotReplacement;
                }else{
                    return dotTokenReplacement;
                }
            }else{
                return dotTokenReplacement;
            }
        }else{
            return processNoDotToken(inputFile, dictionarySingleWords,dotToken);
        }
    }

    /**
     * Evaluate token without dot
     * @param inputFile file to process
     * @param dictionarySingleWords information concluded each single word
     * @param noDotToken token without dot
     * @return new token
     */
    private static String processNoDotToken(InputFile inputFile, DictionarySingleWords dictionarySingleWords,
                                     String noDotToken){
        String replacement = dictionarySingleWords.getSingleWordReplacement(noDotToken);
        if (replacement != null){
            inputFile.getReplacementFile().addReplacement(noDotToken, replacement, "singleWordAbbreviation");
            return replacement;
        }
        return noDotToken;
    }

    /**
     * Get words(=tokens) from file
     * @param inputFile file to read
     * @return tokens
     */
    private static String[] tokenizeFile(InputFile inputFile){
        String fileText = inputFile.getFileText();
        return fileText.split(" ");
    }

}

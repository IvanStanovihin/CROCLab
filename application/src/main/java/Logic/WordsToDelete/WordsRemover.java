package TAaC.Logic.WordsToDelete;

import TAaC.Logic.Handler.Handler;
import TAaC.Logic.InputFile.InputFile;
import TAaC.Logic.Properties.PropertyLoader;
import TAaC.Logic.ReportLog.LogOperation;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**A class that deletes words written by the user in "TAaC.Logic.WordsToDelete" file*/
public class WordsRemover {

    /**The list of words that the user wrote*/
    private static ArrayList<String> wordsToDelete;

    /**
     * Method for checking the module connection
     * @return true if the module is enabled , else false
     */
    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnableRemoveWordsModule();
    }

    /**
     * Start deleting words
     * @param property - "property.json" file with the settings for enabling modules
     * @param inputFiles - list of input files
     */
    public static void removeWords(PropertyLoader property, ArrayList<InputFile>inputFiles){
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            Handler.reportLog.startCurrentOperation(LogOperation.LOAD_WORDS_TO_DELETE);
            WordsToDeleteStorage wordsToDeleteStorage = new WordsToDeleteStorage(property);
            wordsToDelete = wordsToDeleteStorage.getWordsToDelete();
            Handler.reportLog.startCurrentOperation(LogOperation.REMOVE_WORDS_TO_DELETE);
            processFiles(inputFiles);
            wordsToDelete = null;
            Handler.reportLog.endModule("Remove words ");
        }
    }

    /**Finds the words to delete in each input file*/
    private static void processFiles(ArrayList<InputFile> inputFiles){
        for (String wordToDelete : wordsToDelete){
            Pattern deleteWordPattern = Pattern.compile("(?<=[\\W&&[^А-Яа-яёЁ]])" + wordToDelete + "(?=[\\W&&[^А-Яа-яёЁ]])",
                    Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
            for (InputFile inputFile : inputFiles){
                handleFile(inputFile, deleteWordPattern);
            }
        }
    }

    /**Deleting "words to delete" from the input file*/
    private static void handleFile(InputFile inputFile, Pattern wordToDeletePattern){
        String fileText = inputFile.getFileText();
        StringBuilder handledText = new StringBuilder();
        Matcher matcher = wordToDeletePattern.matcher(fileText);
        while(matcher.find()){
            String foundWord = fileText.substring(matcher.start(), matcher.end());
            inputFile.getDeletedWordsStorage().addDeletedWord(foundWord);
            matcher.appendReplacement(handledText, "");
        }
        matcher.appendTail(handledText);
        inputFile.setFileText(handledText.toString());
    }
}

package WordsToDelete;

import Handler.Handler;
import InputFile.InputFile;
import Properties.PropertyLoader;
import ReportLog.LogOperation;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsRemover {

    private static ArrayList<String> wordsToDelete;

    public static void removeWords(PropertyLoader property, ArrayList<InputFile>inputFiles){
        Handler.reportLog.startCurrentOperation(LogOperation.LOAD_WORDS_TO_DELETE);
        WordsToDeleteStorage wordsToDeleteStorage = new WordsToDeleteStorage(property);
        wordsToDelete = wordsToDeleteStorage.getWordsToDelete();
        Handler.reportLog.endOperation();
        processFiles(inputFiles);
    }

    private static void processFiles(ArrayList<InputFile> inputFiles){
        for (String wordToDelete : wordsToDelete){
            Pattern deleteWordPattern = Pattern.compile("(?<=[\\W&&[^А-Яа-яёЁ]])" + wordToDelete + "(?=[\\W&&[^А-Яа-яёЁ]])");
            for (InputFile inputFile : inputFiles){
                Handler.reportLog.startCurrentOperation(LogOperation.REMOVE_WORDS_TO_DELETE, inputFile.getFileName());
                handleFile(inputFile, deleteWordPattern);
                Handler.reportLog.endOperation();
            }
        }
    }

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

package ProcessingServices;

import FileToProcess.ProcessedFile;
import InputFile.InputFile;
import ReportLog.ReportLog;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceSeparator {


    //Принимает текст файла. Возвращает текст разбитый на список предложений.
    public static ArrayList<ProcessedFile>getSentences(ArrayList<InputFile>inputFiles){
        ReportLog.logCurrentOperation("Разделение текста на предложения.");
        ArrayList<ProcessedFile>processedFiles = new ArrayList<>();
        for (InputFile inputFile : inputFiles){
            ProcessedFile processedFile = new ProcessedFile(inputFile.getFileName());
            ArrayList<String>fileSentences = splitOnSentences(inputFile);
            processedFile.setSentences(fileSentences);
            processedFiles.add(processedFile);
        }
        return processedFiles;
    }

    private static ArrayList<String>splitOnSentences(InputFile inputFile){
        String fileText = inputFile.getFileText();
        ArrayList<String>fileSentences = new ArrayList<>();
        //Регулярное выражение для поиска предложений в строке.
        Pattern pattern = Pattern.compile("[^\\!\\?\\.].*?[\\!\\?\\.]");
        Matcher matcher = pattern.matcher(fileText);
        while(matcher.find()) {
            String foundSentence = fileText.substring(matcher.start(), matcher.end());
            fileSentences.add(foundSentence);
        }
        return fileSentences;
    }


}

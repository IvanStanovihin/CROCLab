package Statistic;

import InputFile.InputFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**Calculates statistics on words and sentences in input file.*/
public class RowFileStatistic {

    /**Input processed file name*/
    private String processedFileName;
    /**General count sentences*/
    private int countSentences = 0;
    /**General count words*/
    private int countWords = 0;
    /**Statistics on row words. Word and how many times it appear in the input file.*/
    private LinkedHashMap<String, Integer> wordsStatistic = new LinkedHashMap<>();
    /**Statistics on row sentences. Sentence and how many times it appear in the input file.*/
    private LinkedHashMap<String, Integer> sentencesStatistic = new LinkedHashMap<>();
    /**Input row file name*/
    private transient String fileName;
    /**Row file content*/
    private transient String rowText;

    /**Start generating row file statistic*/
    public RowFileStatistic(InputFile inputFile) {
        this.fileName = "StatisticRow" + inputFile.getFileName();
        this.processedFileName = inputFile.getFileName();
        this.rowText = inputFile.getFileText();
        generateWordsStatistic();
        generateSentencesStatistic();
    }


    /**Generate words statistic for row input file*/
    private void generateWordsStatistic() {
        Pattern wordsPattern = Pattern.compile("(?<=[\\s.])([A-Za-zА-Яа-я_]+?)(?=[\\s!?.$])");
        Matcher matcher = wordsPattern.matcher(rowText);
        while (matcher.find()) {
            countWords++;
            String foundWord = rowText.substring(matcher.start(), matcher.end());
            if (wordsStatistic.containsKey(foundWord)) {
                Integer countWord = wordsStatistic.get(foundWord);
                wordsStatistic.replace(foundWord, ++countWord);
            } else {
                wordsStatistic.put(foundWord, 1);
            }
        }
    }

    /**Generate sentences statistic for row input file*/
    private void generateSentencesStatistic() {
        Pattern wordsPattern = Pattern.compile("([^.!?]+)");
        Matcher matcher = wordsPattern.matcher(rowText);
        while (matcher.find()) {
            countSentences++;
            String foundWord = rowText.substring(matcher.start(), matcher.end());
            if (sentencesStatistic.containsKey(foundWord)) {
                Integer countWord = sentencesStatistic.get(foundWord);
                sentencesStatistic.replace(foundWord, ++countWord);
            } else {
                sentencesStatistic.put(foundWord, 1);
            }
        }
    }

    /**Writing statistic to a file*/
    public void create(String outDir){
        try(OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(outDir + "/" + fileName), StandardCharsets.UTF_8)){
            os.write(getJsonFormat());
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /**Converting statistics to json format for writing to a file*/
    public String getJsonFormat(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(this);
        return result;
    }

}

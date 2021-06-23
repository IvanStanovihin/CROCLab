package com.Logic.Statistic.GeneralStatistic;

import com.Logic.Statistic.ProcessedFileStatistic;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**A class that generates general statistics for all processed words.
 * This statistics are written to a file in a single list*/
public class GeneralWordsStatistic {

    /**Number of processed words.*/
    private int countWords = 0;

    /**Statistics on processed words for all processed files. The words and how many times it appear in the processed files.*/
    private Map<String, Integer> generalWordsStatistic = new LinkedHashMap<>();

    public GeneralWordsStatistic(ArrayList<ProcessedFileStatistic> processedFileStatistic){
        generateGeneralWordsStatistic(processedFileStatistic);
        sortWordsStatistic();
    }

    /**
     * Generates general words statistics for processed files.
     * @param processedFileStatistics - List of statistics for each processed file.
     */
    private void generateGeneralWordsStatistic(ArrayList<ProcessedFileStatistic> processedFileStatistics){
        for (ProcessedFileStatistic processedFileStatistic : processedFileStatistics){
            countWords += processedFileStatistic.getCountWords();
            Map<String, Integer> fileWordsStatistic = processedFileStatistic.getWordsStatistic();
            for (Map.Entry entries : fileWordsStatistic.entrySet()){
                String word = (String)entries.getKey();
                Integer countWordInFile = (Integer)entries.getValue();
                if (generalWordsStatistic.containsKey(word)){
                    Integer countWordInGenStat = generalWordsStatistic.get(word);
                    generalWordsStatistic.replace(word, countWordInGenStat + countWordInFile);
                }else{
                    generalWordsStatistic.put(word, countWordInFile);
                }
            }
        }
    }

    /**Sorting the number of words in descending order*/
    private void sortWordsStatistic(){
        Map<String, Integer>sortedWordsStatistic = new LinkedHashMap<>();
        ArrayList<Map.Entry<String, Integer>>listWordsStatistic = new ArrayList<>(generalWordsStatistic.entrySet());
        listWordsStatistic.sort((e1, e2) -> e2.getValue() - e1.getValue());
        for (Map.Entry entries : listWordsStatistic){
            String word = (String)entries.getKey();
            Integer countWords = (Integer)entries.getValue();
            sortedWordsStatistic.put(word, countWords);
        }
        this.generalWordsStatistic = sortedWordsStatistic;
    }

    /**Writing statistics to a file*/
    public void createFile(String outDir){
        try(OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(outDir + "/GeneralWordsStatistic.txt"), StandardCharsets.UTF_8)){
            os.write(getJsonFormat());
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /**Converting statistics to json format for writing to a file*/
    private String getJsonFormat(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }
}

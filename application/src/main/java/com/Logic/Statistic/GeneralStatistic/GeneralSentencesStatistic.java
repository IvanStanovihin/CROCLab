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

/**A class that generates general statistics for all processed sentences.
 * This statistics are written to a file in a single list*/
public class GeneralSentencesStatistic {

    /**Number of processed sentences.*/
    private int countSentences = 0;
    /**Statistics on processed sentences. The sentence and how many times it appear in the processed files.*/
    private Map<String, Integer>generalSentencesStatistic = new LinkedHashMap<>();

    public GeneralSentencesStatistic(ArrayList<ProcessedFileStatistic> processedFileStatistics){
        generateGeneralSentencesStatistic(processedFileStatistics);
        sortSentenceStatistic();
    }

    /**
     * Generates general sentences statistics for processed files.
     * @param processedFileStatistics - List of statistics for each processed file.
     */
    private void generateGeneralSentencesStatistic(ArrayList<ProcessedFileStatistic>processedFileStatistics){
        for (ProcessedFileStatistic processedFileStatistic : processedFileStatistics){
            countSentences += processedFileStatistic.getCountSentences();
            Map<String, Integer> fileWordsStatistic = processedFileStatistic.getSentencesStatistic();
            for (Map.Entry entries : fileWordsStatistic.entrySet()){
                String sentence = (String)entries.getKey();
                Integer countSentences = (Integer)entries.getValue();
                if (generalSentencesStatistic.containsKey(sentence)){
                    Integer countWordInGenStat = generalSentencesStatistic.get(sentence);
                    generalSentencesStatistic.replace(sentence, countWordInGenStat + countSentences);
                }else{
                    generalSentencesStatistic.put(sentence, countSentences);
                }
            }
        }
    }

    /**Sorting the number of sentences in descending order*/
    private void sortSentenceStatistic(){
        Map<String, Integer>sortedSentencesStatistic = new LinkedHashMap<>();
        ArrayList<Map.Entry<String, Integer>>listSentencesStatistic = new ArrayList<>(generalSentencesStatistic.entrySet());
        listSentencesStatistic.sort((e1, e2) -> e2.getValue() - e1.getValue());
        for (Map.Entry entries : listSentencesStatistic){
            String sentence = (String)entries.getKey();
            Integer countSentences = (Integer)entries.getValue();
            sortedSentencesStatistic.put(sentence, countSentences);
        }
        this.generalSentencesStatistic = sortedSentencesStatistic;
    }

    /**Writing statistics to a file*/
    public void createFile(String outDir){
        try(OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(outDir + "/GeneralSentencesStatistic.txt"), StandardCharsets.UTF_8)){
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

package com.Logic.Statistic;

import com.Logic.Quarantine.QuarantineSentencesFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**Statistics file. Contains the sentences quarantine statistics for each processed file.*/
public class QuarantineStatisticFile {

    /**Name of the input processed file*/
    private String processedFileName;
    /**Name of the output quarantine statistic file*/
    private transient String fileName;
    /**A data structure that stores quarantined sentences.*/
    private transient QuarantineSentencesFile quarantineSentencesFile;
    /**Statistics on quarantine sentences. Sentence and how many times it appear in the processed files.*/
    private Map<String, Integer>quarantineSentencesStatistic = new LinkedHashMap<>();
    /**Quarantined sentences count*/
    private Integer countQuarantineSentences = null;


    /**Start generating quarantine statistics*/
    public QuarantineStatisticFile(QuarantineSentencesFile quarantineSentencesFile){
        this.processedFileName = quarantineSentencesFile.getFileName();
        this.quarantineSentencesFile = quarantineSentencesFile;
        generateQuarantineSentencesStatistic();
        fileName = "TAaC/Logic/Statistic" + quarantineSentencesFile.getFileName();
    }

    /**Counts the number of times the sentence has been quarantined.*/
    private void generateQuarantineSentencesStatistic(){
        ArrayList<String>quarantineSentences = quarantineSentencesFile.getQuarantineSentences();
        for (String quarantineSentence : quarantineSentences){
            if (quarantineSentencesStatistic.containsKey(quarantineSentence)){
                int countQuarantineSentence = quarantineSentencesStatistic.get(quarantineSentence);
                quarantineSentencesStatistic.replace(quarantineSentence, ++countQuarantineSentence);
            }else{
                quarantineSentencesStatistic.put(quarantineSentence, 1);
            }
        }
        sortStatistic();
    }

    /**Sorting the number of sentences in descending order*/
    private void sortStatistic(){
        ArrayList<Map.Entry<String, Integer>>quarantineSentencesPairs
                = new ArrayList<>(quarantineSentencesStatistic.entrySet());
        quarantineSentencesPairs.sort((s1, s2) -> (s2.getValue() - s1.getValue()) );
        Map<String, Integer>sortedStatisticMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entries : quarantineSentencesPairs){
            sortedStatisticMap.put(entries.getKey(), entries.getValue());
        }
        quarantineSentencesStatistic = sortedStatisticMap;
    }

    /**Writing statistic in a file*/
    public void createFile(String outDir){
        if (!fileIsEmpty()) {
            try (OutputStreamWriter os = new OutputStreamWriter
                    (new FileOutputStream(outDir + "/" + fileName), StandardCharsets.UTF_8)) {
                os.write(getJsonFormat());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public String getFileName(){
        return fileName;
    }

    /**Converting statistics to json format for writing to a file*/
    public String getJsonFormat(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(this);
        return result;
    }

    public Map<String, Integer> getQuarantineSentencesStatistic() {
        return quarantineSentencesStatistic;
    }

    /**Counts the number of sentences in quarantine statistic file. And returns it.*/
    public int getCountQuarantineSentences(){
        if (this.countQuarantineSentences == null){
            this.countQuarantineSentences = 0;
            for (Map.Entry entries : quarantineSentencesStatistic.entrySet()){
                countQuarantineSentences += (Integer)entries.getValue();
            }
        }else{
            return countQuarantineSentences;
        }
        return countQuarantineSentences;
    }

    public boolean fileIsEmpty(){
        return quarantineSentencesStatistic.size() == 0;
    }
}

package TAaC.Logic.Statistic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/** Class that generates statistics for user words*/
public class UserStatisticFile {

    /**Name file with "words for user statistic"*/
    private String processedFileName;
    /**Statistics on user words.*/
    private Map<String, Integer> userStatistic = new LinkedHashMap<>();
    /**List of words for user statistic*/
    private transient ArrayList<String>wordsForUserStatistic;
    /**Output file name*/
    private transient String userStatisticFileName;

    /**Start generating statistic for user words*/
    public UserStatisticFile(ArrayList<String>wordsForUserStatistic, ProcessedFileStatistic processedFileStatistic,
                             String userStatisticFileName) {
        this.userStatisticFileName = userStatisticFileName;
        this.wordsForUserStatistic = wordsForUserStatistic;
        this.processedFileName = processedFileStatistic.getProcessedFileName();
        createStatistic(processedFileStatistic);
    }

    /**Generating statistic for user words*/
    private void createStatistic(ProcessedFileStatistic processedFileStatistic) {
        Map<String, Integer> processedWordsStatistic = processedFileStatistic.getWordsStatistic();
        for (String wordForStatistic : wordsForUserStatistic) {
            if (processedWordsStatistic.containsKey(wordForStatistic)) {
                Integer countWordInFile = processedWordsStatistic.get(wordForStatistic);
                userStatistic.put(wordForStatistic, countWordInFile);
            }
        }
        sortStatistic();
    }

    /**Sorting the number of words in descending order*/
    private void sortStatistic() {
        ArrayList<Map.Entry<String, Integer>> sortedStatisticEntries = new ArrayList<>(userStatistic.entrySet());
        sortedStatisticEntries.sort((w1, w2) -> (w2.getValue() - w1.getValue()));
        Map<String, Integer> sortedStatisticMap = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entries : sortedStatisticEntries) {
            sortedStatisticMap.put(entries.getKey(), entries.getValue());
        }
        userStatistic = sortedStatisticMap;
    }

    /**Converting statistics to json format for writing to a file*/
    private String getJsonFormat() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    /**Writing statistic in a file*/
    public void createFile(String outDir) {
        if (!fileIsEmpty()) {
            try (OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(outDir + "/" + userStatisticFileName + processedFileName), StandardCharsets.UTF_8)) {
                os.write(getJsonFormat());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    private boolean fileIsEmpty() {
        return userStatistic.size() == 0;
    }

    public Map<String, Integer> getUserStatistic() {
        return userStatistic;
    }
}


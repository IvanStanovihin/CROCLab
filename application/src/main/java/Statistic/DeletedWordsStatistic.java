package Statistic;

import InputFile.InputFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**Generates a file with statistics for words that have been deleted.*/
public class DeletedWordsStatistic {

    /**Statistics of deleted words. The word and the number of its deletions.*/
    Map<String, Integer>deletedWordsStatistic = new LinkedHashMap<>();

    public DeletedWordsStatistic(ArrayList<InputFile>inputFiles){
        generateStatistic(inputFiles);
        sortStatistic();
    }

    /**
     * Generates statistics for each input file
     * @param inputFiles - list of files to process
     */
    private void generateStatistic(ArrayList<InputFile>inputFiles){
        for (InputFile inputFile : inputFiles){
            handleFile(inputFile);
        }
    }

    /**
     *For the processed file, the number of deleted words is calculated and added to the general deleted word statistics.
     * @param inputFile - file to process
     */
    private void handleFile(InputFile inputFile){
       Map<String, Integer>deletedWords = inputFile.getDeletedWordsStorage().getDeletedWords();
        for (Map.Entry<String, Integer> entries : deletedWords.entrySet()){
            String deletedWord = entries.getKey();
            Integer countDeletedWords = entries.getValue();
            if (deletedWordsStatistic.containsKey(deletedWord)){
                Integer statisticCountDeletedWord = deletedWordsStatistic.get(deletedWord);
                deletedWordsStatistic.replace(deletedWord, countDeletedWords + statisticCountDeletedWord);
            }else{
                deletedWordsStatistic.put(deletedWord, countDeletedWords);
            }
        }
    }

    /**The number of deleted words is sorted in descending order*/
    private void sortStatistic(){
        Map<String, Integer>sortedStatisticMap = new LinkedHashMap<>();
        ArrayList<Map.Entry<String, Integer>>sortedStatisticList = new ArrayList<>(deletedWordsStatistic.entrySet());
        sortedStatisticList.sort((e1, e2) -> (e2.getValue() - e1.getValue()));
        for (Map.Entry entries : sortedStatisticList){
            sortedStatisticMap.put((String)entries.getKey(), (Integer)entries.getValue());
        }
        deletedWordsStatistic = sortedStatisticMap;
    }

    /**Writing statistic to a file*/
    public void createStatistic(String outDir){
        if (!isFileEmpty()) {
            try (OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(outDir + "/DeletedWords.txt"), StandardCharsets.UTF_8)) {
                os.write(getJsonFormat());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**Converting statistics to json format for writing to a file*/
    private String getJsonFormat(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }


    private boolean isFileEmpty(){
        return deletedWordsStatistic.size() == 0;
    }

}

package TAaC.Logic.Statistic;

import TAaC.Logic.InputFile.InputFile;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
/**Statistics file. Contains the words quarantine statistics for each processed file.*/
public class QuarantineWordsStatistic {

    /**Statistics on quarantine words. Word and how many times it appear in the processed files.*/
    private  Map<String, Integer> quarantineWordsStatistic = new HashMap<>();

    /**Start generating quarantine statistics*/
    public QuarantineWordsStatistic(ArrayList<InputFile> inputFiles){
        generateStatistic(inputFiles);
        sortStatistic();
    }

    /**Generate quarantine words statistic for each processed files*/
    private void generateStatistic(ArrayList<InputFile> inputFiles){
        for (InputFile inputFile : inputFiles){
            processFile(inputFile);
        }
    }

    /**Takes the word quarantined and counts how many times it occurs in the input file*/
    private void processFile(InputFile inputFile){
        Map<String, Integer>fileWordsStatistic = inputFile.getQuarantineWordsFile().getQuarantineWords();
        for (Map.Entry<String, Integer> entries : fileWordsStatistic.entrySet()){
            String quarantineWord = entries.getKey();
            Integer countWordInFile = entries.getValue();
            if (quarantineWordsStatistic.containsKey(quarantineWord)){
                Integer generalCountWord = quarantineWordsStatistic.get(quarantineWord);
                quarantineWordsStatistic.replace(quarantineWord, countWordInFile + generalCountWord);
            }else{
                quarantineWordsStatistic.put(quarantineWord, countWordInFile);
            }
        }
    }

    /**Sorting the number of sentences in descending order*/
    private void sortStatistic(){
        LinkedHashMap<String, Integer> sortedStatistic = new LinkedHashMap<>();
        ArrayList<Map.Entry<String, Integer>>statisticList = new ArrayList<>(quarantineWordsStatistic.entrySet());
        statisticList.sort( (s1, s2) -> (s2.getValue() - s1.getValue()));
        for (Map.Entry<String, Integer> entries : statisticList){
            sortedStatistic.put(entries.getKey(), entries.getValue());
        }
        quarantineWordsStatistic = sortedStatistic;
    }

    /**Converting statistics to json format for writing to a file*/
    private String getJsonFormat(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    private boolean isFileEmpty(){
        return quarantineWordsStatistic.size() == 0;
    }

    /**Writing statistic to a file*/
    public void createFile(String filePath){
        if (!isFileEmpty()){
            try(OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(filePath + "/QuarantineWordsStatistic.txt"), StandardCharsets.UTF_8)){
                os.write(getJsonFormat());
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }
}

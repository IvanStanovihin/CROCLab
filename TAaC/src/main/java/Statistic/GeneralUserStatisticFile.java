package Statistic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**Statistics on words that the user added to the directoryForUserStatistic */
public class GeneralUserStatisticFile {

    /**The name of the file that the user added to generate statistics.*/
    private String userStatisticFileName;
    /**Statistics on user words. The word and the amount it is used in the processed files*/
    private Map<String, Integer>generalUserStatistic;

    /**
     *
     * @param userStatisticFileName - Path for the output file with general user statistics
     * @param userStatisticFiles - List of user statistics for each processed file
     */
    public GeneralUserStatisticFile(String userStatisticFileName, ArrayList<UserStatisticFile>userStatisticFiles){
        this.userStatisticFileName = userStatisticFileName;
        fillInStatistic(userStatisticFiles);
        sortGeneralStatistic();
        sortGeneralStatistic();
    }

    /**Writing statistic to a file*/
    public void createGeneralStatistic(String outDir){
        if (!isFileEmpty()) {
            try (OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(outDir + "/GeneralUserStatistic_" + userStatisticFileName + ".txt"), StandardCharsets.UTF_8)) {
                os.write(getJsonFormat());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }


    /**For each processed file, statistics on user words are calculated and added to the путукфд гыук statistics.*/
    private void fillInStatistic(ArrayList<UserStatisticFile> userStatisticFiles){
        generalUserStatistic = new LinkedHashMap<>();
        for (UserStatisticFile userStatisticFile : userStatisticFiles){
            Map<String, Integer>eachFileUserStatistic = userStatisticFile.getUserStatistic();
            for (Map.Entry<String, Integer> entries : eachFileUserStatistic.entrySet()){
                String wordForStatistic = entries.getKey();
                Integer countWord = entries.getValue();
                if (generalUserStatistic.containsKey(wordForStatistic)){
                    Integer generalCountWord = generalUserStatistic.get(wordForStatistic);
                    generalUserStatistic.replace(wordForStatistic, countWord + generalCountWord);
                }else{
                    generalUserStatistic.put(wordForStatistic, countWord);
                }
            }
        }
    }

    /**The number of "user statistic words" is sorted in descending order*/
    private void sortGeneralStatistic(){
        ArrayList<Map.Entry<String, Integer>>statisticPairs = new ArrayList<>(generalUserStatistic.entrySet());
        statisticPairs.sort((p1, p2) -> (p2.getValue() - p1.getValue()) );
        Map<String, Integer>sortedGeneralStatistic = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> entries : statisticPairs){
            sortedGeneralStatistic.put(entries.getKey(), entries.getValue());
        }
        generalUserStatistic = sortedGeneralStatistic;
    }

    /**Converting statistics to json format for writing to a file*/
    private String getJsonFormat(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    private boolean isFileEmpty(){
        return generalUserStatistic.size() == 0;
    }
}

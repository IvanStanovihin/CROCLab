package com.Logic.Statistic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**General statistics for all offers that have been moved to quarantine.*/
public class GeneralQuarantineSentencesStatistic {

    /**List of quarantine statistics for each processed file*/
    ArrayList<QuarantineStatisticFile>quarantineStatisticFiles = new ArrayList();

    /**If the quarantine for a processed file is not empty then statistics are generated for that processed file*/
    public GeneralQuarantineSentencesStatistic(ArrayList<QuarantineStatisticFile>quarantineStatisticFiles){
            for (QuarantineStatisticFile quarantineStatisticFile : quarantineStatisticFiles){
                if (!quarantineStatisticFile.fileIsEmpty()){
                    this.quarantineStatisticFiles.add(quarantineStatisticFile);
                }
            }
    }

    /**Writing statistic to a file*/
    public void createFile(String outDir){
        if (!isFileEmpty()) {
            try (OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(outDir + "/QuarantineSentencesStatistic.txt"), StandardCharsets.UTF_8)) {
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

    /**Checking the quarantine for emptiness*/
    private boolean isFileEmpty(){
        return quarantineStatisticFiles.size() == 0;
    }
}



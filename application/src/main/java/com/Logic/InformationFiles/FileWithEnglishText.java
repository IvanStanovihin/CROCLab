package com.Logic.InformationFiles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
/**A class that stores all sentences with English text for each processed file*/
public class FileWithEnglishText {

    /**Processed file name*/
    private String processedFileName;
    /**Sentences that contain English text*/
    private ArrayList<String>sentencesWithEnglish = new ArrayList<>();

    public FileWithEnglishText(String processedFileName){
        this.processedFileName = processedFileName;
    }

    /**Adding English text to the storage*/
    public void addEnglishSentences(String englishSentences){
        this.sentencesWithEnglish.add(englishSentences);
    }

    /**Creating output file*/
    public void createFile(String outDir){
        if (!isFileEmpty()) {
            try (OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(outDir + "/EnglishText_" + processedFileName), StandardCharsets.UTF_8)) {
                os.write(getJsonFormat());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String getJsonFormat(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    private boolean isFileEmpty(){
        return sentencesWithEnglish.size() == 0;
    }
}

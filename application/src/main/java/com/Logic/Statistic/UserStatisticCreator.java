package com.Logic.Statistic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**A class that creates statistics for each file "words for user statistic"*/
public class UserStatisticCreator {

    /**File with words for user statistic*/
    private String filePath;
    /**Word for user statistic*/
    private ArrayList<String> wordsForUserStatistic;
    /**Output file name*/
    private String userStatisticFileName;
   /**Files with statistic for single processed file*/
    private ArrayList<ProcessedFileStatistic>processedFileStatistics;
    /**Files with statistic for user words*/
    private ArrayList<UserStatisticFile>userStatisticFiles = new ArrayList<>();


    /***/
    public UserStatisticCreator(String filePath, ArrayList<ProcessedFileStatistic> processedFileStatistics){
        this.filePath = filePath;
        readFile();
        this.userStatisticFileName = createFileName();
        this.processedFileStatistics = processedFileStatistics;
        for (ProcessedFileStatistic processedFileStatistic : processedFileStatistics){
            userStatisticFiles.add(new UserStatisticFile(wordsForUserStatistic, processedFileStatistic, userStatisticFileName));
        }
    }

    private String createFileName(){
        return filePath.substring(filePath.lastIndexOf("\\") + 1, filePath.lastIndexOf("."));
    }

    private void readFile(){
        ArrayList<String>fileData = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8") )){
            String readString;
            while((readString = br.readLine()) != null){
                fileData.add(readString.trim());
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        wordsForUserStatistic = fileData;
    }


    public void createStatistic(String outDir){
        createEachFileStatistic(outDir);
        createGeneralStatistic(outDir);
    }

    private void createEachFileStatistic(String outDir){
        String fileDir = outDir + "/EachFile_" + userStatisticFileName;
        try {
            Files.createDirectories(Paths.get(fileDir));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        for (UserStatisticFile userStatisticFile : userStatisticFiles){
            userStatisticFile.createFile(fileDir);
        }
    }

    private void createGeneralStatistic(String outDir){
        String fileDir = outDir + "/General_" + userStatisticFileName;
        try{
            Files.createDirectories(Paths.get(fileDir));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        GeneralUserStatisticFile generalUserStatisticFile = new GeneralUserStatisticFile(userStatisticFileName, userStatisticFiles);
        generalUserStatisticFile.createGeneralStatistic(fileDir);
    }


}

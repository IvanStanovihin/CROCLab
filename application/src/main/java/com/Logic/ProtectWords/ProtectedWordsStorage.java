package com.Logic.ProtectWords;

import com.Logic.Properties.PropertyLoader;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

/**
 * Storage of not-to-process files, they are protected files
 */
public class ProtectedWordsStorage {
    /**
     * Storage
     */
    private static ArrayList<FileWithProtectedWords> fileWithProtectedWords = new ArrayList<>();

    public ProtectedWordsStorage(PropertyLoader property){
        loadFiles(property.getProtectedWordsDir());
    }

    /**
     * Load files with protected words into storage.
     * @param directoryPath directory with files concluded protected words
     */
    private void loadFiles(String directoryPath){
            File directory = new File(directoryPath);
        for (File file : directory.listFiles()){
            Gson gson = new Gson();
            FileWithProtectedWords fileWithExcludeWords = gson.fromJson(readFileData(file.getPath()),
                    FileWithProtectedWords.class);
            fileWithProtectedWords.add(fileWithExcludeWords);
        }
    }

    /**
     * Additional function to read data in any file
     * @param filePath path to file
     * @return text from file
     */
    private String readFileData(String filePath){
        StringBuilder fileData = new StringBuilder();
        String readLine;
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"))){
            while((readLine = br.readLine()) != null){
                fileData.append(readLine);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        return fileData.toString();
    }

    /**
     * Check if word is protected
     * @param wordToCheck word
     * @return result of checking
     */
    public static boolean isWordProtected(String wordToCheck){
        for (FileWithProtectedWords file : fileWithProtectedWords){
            if (file.isWordProtected(wordToCheck)) {
                return true;
            }
        }
        return false;
    }

}

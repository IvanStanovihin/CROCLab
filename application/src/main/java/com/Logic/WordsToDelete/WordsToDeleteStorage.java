package com.Logic.WordsToDelete;

import com.Logic.Properties.PropertyLoader;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
/**Loads from a file and stores the words to be deleted from the input files*/
public class WordsToDeleteStorage {

    /**Files in which written "words to delete"*/
    private ArrayList<FileWordToDelete> filesWordsToDelete = new ArrayList<>();
    /**List of "words to delete"*/
    private ArrayList<String>wordsToDelete = new ArrayList<>();


    public WordsToDeleteStorage(PropertyLoader property){
        loadFiles(property.getWordsToDeleteDir());
        extractWordsToDelete();
    }

    /**Reading files and loading "words to delete"*/
    private void loadFiles(String dir){
        File currentDirectory = new File(dir);
        for (File file : currentDirectory.listFiles()){
            Gson gson = new Gson();
            String fileData = readFile(file.getPath());
            filesWordsToDelete.add(gson.fromJson(fileData, FileWordToDelete.class));
        }
    }

    /**Reading file*/
    private String readFile(String filePath){
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

    /**Adding "words to delete" to the storage*/
    private void extractWordsToDelete(){
        for (FileWordToDelete fileWordToDelete : filesWordsToDelete){
            wordsToDelete.addAll(fileWordToDelete.getWordsToDelete());
        }
    }

    public ArrayList<String> getWordsToDelete() {
        return wordsToDelete;
    }
}

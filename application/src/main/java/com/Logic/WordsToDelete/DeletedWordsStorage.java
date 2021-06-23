package com.Logic.WordsToDelete;

import java.util.LinkedHashMap;
import java.util.Map;
/**A class containing all the words removed from the processed file*/
public class DeletedWordsStorage {

    /**Processed file name*/
    private String processedFileName;
    /**The word and the number of its deletions*/
    private Map<String, Integer> deletedWords = new LinkedHashMap<>();

    public DeletedWordsStorage(String fileName){
        this.processedFileName = fileName;
    }


    public void addDeletedWord(String word){
        if (deletedWords.containsKey(word)){
            Integer countWord = deletedWords.get(word);
            deletedWords.replace(word, ++countWord);
        }
        else {
            deletedWords.put(word, 1);
        }
    }

    public Map<String, Integer> getDeletedWords() {
        return deletedWords;
    }
}

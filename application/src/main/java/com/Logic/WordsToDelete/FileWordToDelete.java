package com.Logic.WordsToDelete;

import java.util.ArrayList;
/**Class for storage deleted words*/
public class FileWordToDelete {

    public ArrayList<String> wordsToDelete = new ArrayList<>();

    public ArrayList<String> getWordsToDelete() {
        return wordsToDelete;
    }

    public void setWordsToDelete(ArrayList<String> wordsToDelete) {
        this.wordsToDelete = wordsToDelete;
    }
}

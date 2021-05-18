package ProtectWords;


import java.util.ArrayList;

/**
 * File concluded words not to processed
 */
public class FileWithProtectedWords {
    /**
     * List of not-to-process words
     */
    public ArrayList<String> protectedWords = new ArrayList<>();


    public ArrayList<String> getProtectedWords() {
        return protectedWords;
    }

    public void setProtectedWords(ArrayList<String> protectedWords) {
        protectedWords = protectedWords;
    }

    /**
     * Check if the word is not-to-process.
     * @param wordToCheck word
     * @return result of checking
     */
    public boolean isWordProtected(String wordToCheck){
        return protectedWords.contains(wordToCheck);
    }
}

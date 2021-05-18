package Dictionary;

import java.util.ArrayList;

/**Class for loading a dictionary from json*/
public class JsonDictionary {

    /**List of words and replacements from the dictionary*/
    public ArrayList<WordReplacements> dictionaryWords;

    public ArrayList<WordReplacements> getDictionaryWords() {
        return dictionaryWords;
    }

    public void setDictionaryWords(ArrayList<WordReplacements> dictionaryWords) {
        this.dictionaryWords = dictionaryWords;
    }

}

/**A data structure that stores a word and its replacements from a dictionary*/
class WordReplacements {

    public String word;
    public String[] replacements;

    public String[] getReplacements() {
        return replacements;
    }

    public void setReplacements(String[] replacements) {
        this.replacements = replacements;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}


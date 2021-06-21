package TAaC.Logic.Quarantine;

import java.util.HashMap;
import java.util.Map;

/**
 * File with quarantine words
 */
public class QuarantineWordsFile {
    /**
     * Map quarantine word to count of quarantine words.
     */
    private Map<String, Integer> quarantineWords = new HashMap<>();

    /**
     * Add quarantine word into map
     * @param word
     */
    public  void addQuarantineWord(String word){
        if (quarantineWords.containsKey(word)){
            Integer countWord = quarantineWords.get(word);
            quarantineWords.replace(word, ++countWord);
        }else{
            quarantineWords.put(word, 1);
        }
    }
    public Map<String, Integer> getQuarantineWords() {
        return quarantineWords;
    }
}

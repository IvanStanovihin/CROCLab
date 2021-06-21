package TAaC.Logic.Dictionary;


import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

/***
 * A data structure that stores single dictionary words
 */
public class DictionarySingleWords {

    /**
     * TAaC.Logic.Dictionary word and its replacements
     */
    private Map<String, String[]> unreadableWordReplacements = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    public DictionarySingleWords(ArrayList<WordReplacements>wordReplacements){
        convertUnreadableWords(wordReplacements);
    }

    /**
     * Filling the unreadableWordReplacements
     * @param wordReplacements - list of dictionary words and replacements
     */
    private void convertUnreadableWords(ArrayList<WordReplacements>wordReplacements){
        for (WordReplacements wordReplacement : wordReplacements){
            unreadableWordReplacements.put(wordReplacement.getWord(), wordReplacement.getReplacements());
        }
    }

    public String getSingleWordReplacement(String unreadableWord){
        String replacement[] = unreadableWordReplacements.get(unreadableWord);
        if (replacement != null){
            return replacement[0];
        }else {
            return null;
        }
    }


}

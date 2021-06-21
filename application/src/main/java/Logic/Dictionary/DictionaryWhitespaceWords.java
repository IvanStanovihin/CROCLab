package TAaC.Logic.Dictionary;


import java.util.*;
/**A data structure that stores compound dictionary words.
 * Compound dictionary word - several words separated by a space.*/
public class DictionaryWhitespaceWords {

    /**Map which stores the dictionary word and the rules by which it is processed*/
    private Map<String, UnreadableWordHandler> wordReplacement = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

    private String fileName;

    public DictionaryWhitespaceWords(ArrayList<WordReplacements>wordReplacements){
        convertWordReplacements(wordReplacements);
    }

    /**
     * Filling the wordReplacement
     * @param wordReplacements - list of dictionary words and replacements
     */
    private void convertWordReplacements(ArrayList<WordReplacements>wordReplacements){
        for (WordReplacements wordFromDictionary : wordReplacements){
            String unreadableWord = wordFromDictionary.getWord();
            String[]replacements = wordFromDictionary.getReplacements();
            wordReplacement.put(unreadableWord, new UnreadableWordHandler(unreadableWord, replacements));
        }
    }

    public Map<String, UnreadableWordHandler> getWordReplacement() {
        return wordReplacement;
    }

    public String getFileName() {
        return fileName;
    }
}

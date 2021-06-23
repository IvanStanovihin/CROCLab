package com.Logic.Dictionary;

import java.util.regex.Pattern;

/**The class creates a pattern for a compound dictionary word.*/
public class UnreadableWordHandler{

    /**A replacement for a unreadable word written in the dictionary*/
    private String[] replacements;
    /**Pattern for unreadable word*/
    private Pattern unreadableWordPattern;

    /**
     *
     * @param wordFromDictionary - a word written in the dictionary
     * @param replacement - replacements for words written in the dictionary
     */
    public UnreadableWordHandler(String wordFromDictionary, String[] replacement){
        this.replacements = replacement;
        this.unreadableWordPattern = createPatternForUnreadableWord(wordFromDictionary);
    }

    /**Creating unreadable word pattern*/
    private Pattern createPatternForUnreadableWord(String wordFromDictionary){
        String unreadableWordRegexp = wordFromDictionary.replaceAll("\\.", "\\\\.");
//        unreadableWordRegexp = "[^\\s]" + unreadableWordRegexp + "[\\s\\n\\W]";
        unreadableWordRegexp = "(?<=\\s|^)" + unreadableWordRegexp + "(?=\\s|\\W|$)";
//        unreadableWordRegexp = "(?<=\\s|^)" + unreadableWordRegexp + "(?=[\\s\\W])(?!.ES)";
        Pattern unreadableWordPattern = Pattern.compile(unreadableWordRegexp);
        return unreadableWordPattern;
    }

    public String getReplacement() {
        return replacements[0];
    }

    public Pattern getUnreadableWordPattern() {
        return unreadableWordPattern;
    }
}

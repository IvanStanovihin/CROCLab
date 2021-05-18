package ReplacementFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * File with replaced information
 */
public class ReplacementFile {
    /**
     * file name to process
     */
    private String processedFileName;
    /**
     * counter of replacement
     */
    private int countReplacement = 0;
    /**
     * Map replacement to count of replacing
     */
    private transient Map<Replacement, Integer> replacementsCount = new HashMap<>();
    /**
     * file name
     */
    private transient String fileName;
    /**
     * Replacements to save into file
     */
    @SerializedName("replacements")
    private ArrayList<Replacement>replacementForOut = new ArrayList<>();


    public ReplacementFile(String processedFileName){
        this.processedFileName = processedFileName;
        this.fileName = "Replacements" + processedFileName;
    }

    /**
     * Add replacement to map
     * @param unreadableWord word
     * @param replacement replacement of word
     * @param description description of replacement
     */
    public void addReplacement(String unreadableWord, String replacement, String description){
        Replacement newReplacement = new Replacement(unreadableWord, replacement, description);
        if (replacementsCount.containsKey(newReplacement)) {
            Integer countReplacement = replacementsCount.get(newReplacement);
            replacementsCount.replace(newReplacement, ++countReplacement);
        }else{
            replacementsCount.put(newReplacement, 1);
        }
        countReplacement++;
    }

    /**
     * get json format from this file
     * @return json format
     */
    public String getJsonFormat() {
        if (replacementForOut.size() == 0) {
            initializeOutList();
            sortOutList();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = gson.toJson(this);
        return result;
    }

    public String getFileName() {
        return fileName;
    }

    /**
     * Look map and save info from it into replacements
     */
    private void initializeOutList(){
        for (Map.Entry entries : replacementsCount.entrySet()) {
            Replacement replacement = (Replacement) entries.getKey();
            Integer countReplacement = (Integer) entries.getValue();
            replacement.countIdenticalReplacements = countReplacement;
            replacementForOut.add(replacement);
        }
    }

    /**
     * Sort by counter identical replacements
     */
    private void sortOutList(){
        replacementForOut.sort(Comparator.comparing(r -> r.countIdenticalReplacements));
        Collections.reverse(replacementForOut);
    }

    /**
     * Create file with information about replacements in json format
     * @param outDir out directory
     */
    public void createFile(String outDir) {
        if (!fileIsEmpty()) {
            try (OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(outDir + "/" + fileName), StandardCharsets.UTF_8)) {
                os.write(getJsonFormat());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Check if file is empty by count of replacements
     * @return result of checking
     */
    private boolean fileIsEmpty(){
        return replacementsCount.size() == 0;
    }


    /**
     * Inner class to save replacement
     */
    class Replacement{
        /**
         * Initial word to replace
         */
        private String unreadableWord;
        /**
         * Replacement of word
         */
        private String replacement;
        /**
         * Description about replacement
         */
        private String description;
        /**
         * count of identical replacements
         */
        @SerializedName("count")
        private Integer countIdenticalReplacements;

        public Replacement(String unreadableWord, String replacement, String description) {
            this.unreadableWord = unreadableWord;
            this.replacement = replacement;
            this.description = description;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = result * prime + (unreadableWord == null ? 0 : unreadableWord.hashCode());
            result = result * prime + (replacement == null ? 0 : replacement.hashCode());
            result = result * prime + (description == null ? 0 : description.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj){
                return true;
            }
            if (obj == null || this.getClass() != obj.getClass()){
                return false;
            }
            Replacement objReplacement = (Replacement)obj;
            return (this.unreadableWord.equals(objReplacement.unreadableWord))
                    && (this.description.equals(objReplacement.description))
                    && (this.replacement.equals(objReplacement.replacement));
        }
    }


}

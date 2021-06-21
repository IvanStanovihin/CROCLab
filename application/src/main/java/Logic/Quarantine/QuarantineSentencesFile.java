package TAaC.Logic.Quarantine;

import java.util.ArrayList;

/**
 * TAaC.Logic.Quarantine in a file
 */
public class QuarantineSentencesFile {
    /**
     * Name of processed file
     */
    private String processedFileName;
    /**
     * Storage with quarantine sentences in file
     */
    private ArrayList<String>quarantineSentences = new ArrayList<>();
    /**
     * Name of file to process.
     */
    private transient String fileName;



    public QuarantineSentencesFile(String processedFileName){
        this.processedFileName = processedFileName;
        this.fileName = "QuarantineSentences" + processedFileName;
    }

    /**
     * Add quarantine sentence to storage
     * @param sentence sentence to add
     */
    public void addQuarantineSentence(String sentence){
        quarantineSentences.add(sentence);
    }

    public ArrayList<String> getQuarantineSentences() {
        return quarantineSentences;
    }

    public String getFileName() {
        return fileName;
    }
}



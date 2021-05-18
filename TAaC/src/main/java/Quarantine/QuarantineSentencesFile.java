package Quarantine;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * Quarantine in a file
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



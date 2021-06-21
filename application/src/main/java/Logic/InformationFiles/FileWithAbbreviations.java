package TAaC.Logic.InformationFiles;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
/**Contains words similar to abbreviations in single input file*/
public class FileWithAbbreviations {

    /**Input file name*/
    private String inputFileName;
    /**A sentence that contains a word that similar an abbreviation*/
    private ArrayList<String>sentencesWithAbbreviation = new ArrayList<>();

    public FileWithAbbreviations(String inputFileName){
        this.inputFileName = inputFileName;
    }

    public void addAbbreviationSentences(String sentence){
        sentencesWithAbbreviation.add(sentence);
    }

    /**Writing sentences with abbreviation in a file*/
    public void createFile(String outDir){
        if (!fileIsEmpty()) {
            try (OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(outDir + "/AbbreviationSentences" + inputFileName), StandardCharsets.UTF_8)) {
                os.write(getJsonFormat());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    /**Converting statistics to json format for writing to a file*/
    private String getJsonFormat(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    private boolean fileIsEmpty(){
        return sentencesWithAbbreviation.size() == 0;
    }

}

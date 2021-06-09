package InputFile;

import Handler.Handler;
import InformationFiles.FileWithAbbreviations;
import InformationFiles.FileWithEnglishText;
import Properties.PropertyLoader;
import Quarantine.QuarantineSentencesFile;
import Quarantine.QuarantineWordsFile;
import ReplacementFile.ReplacementFile;
import ReportLog.LogOperation;
import WordsToDelete.DeletedWordsStorage;
import org.apache.any23.encoding.TikaEncodingDetector;
import ucar.nc2.util.IO;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**Loaded file that the user prepared*/
public class InputFile {

    private boolean validEncoding;
    private String filePath;
    private String fileName;
    private String fileText;
    /**Replacements that will be made in the file during processing*/
    private ReplacementFile replacementFile;
    /**Abbreviations found in this file*/
    private FileWithAbbreviations fileWithAbbreviations;
    /**Quarantine sentences for this file*/
    private QuarantineSentencesFile quarantineFile;
    /**English sentences found in this file*/
    private FileWithEnglishText fileWithEnglishText;
    /**This file content*/
    private ArrayList<String> sentences = new ArrayList<>();
    /**Word storage for deletion*/
    private DeletedWordsStorage deletedWordsStorage;
    /**Quarantine words storage*/
    private QuarantineWordsFile quarantineWordsFile;


    public InputFile(String filePath) {
        this.filePath = filePath;
        this.fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        readFile();
        this.replacementFile = new ReplacementFile(fileName);
        this.fileWithAbbreviations = new FileWithAbbreviations(fileName);
        this.quarantineFile = new QuarantineSentencesFile(fileName);
        this.fileWithEnglishText = new FileWithEnglishText(fileName);
        this.deletedWordsStorage = new DeletedWordsStorage(fileName);
        this.quarantineWordsFile = new QuarantineWordsFile();
    }

    public String getFileText() {
        return fileText;
    }

    public void setFileText(String fileText) {
        this.fileText = fileText;
    }

   /**Reading a file to a one string*/
    private void readFile() {
        StringBuilder fileData = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8))) {
            String readData = "";
            while ((readData = br.readLine()) != null) {
                fileData.append(readData.trim()).append(" . ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileText = fileData.toString();
    }

    /**Creating output file*/
    public void createOutputFile(String outputDirectory, PropertyLoader property){
        ArrayList<String>fileParts = splitOutputFile(property);
        if (fileParts.size() > 1){
            writePartsFile(outputDirectory, fileParts);
        }else{
            writeSmallFile(outputDirectory, fileParts);
        }
    }

    /**Splitting the output file into user defined parts*/
    private ArrayList<String> splitOutputFile(PropertyLoader property){
        ArrayList<String> fileParts = new ArrayList<>();
        int countBytes = 0;
        int countSentences = 0;
        int outputFileSize = property.getOutFileSize();
        int outputFileCountStrings = property.getOutFileCountStrings();
        StringBuilder filePart = new StringBuilder();
        for (int i = 0; i < sentences.size(); i++){
            String currentSentences = sentences.get(i);
            countBytes += currentSentences.getBytes().length;
            countSentences++;
            if (i == sentences.size() - 1 || countBytes >= (outputFileSize * 1024 * 1024) || countSentences >= outputFileCountStrings) {
                filePart.append(currentSentences) ;
            }else{
                filePart.append(currentSentences).append("\n");
            }

            if(countBytes >= (outputFileSize * 1024 * 1024) || countSentences >= outputFileCountStrings){
                fileParts.add(filePart.toString());
                filePart = new StringBuilder();
                countBytes = 0;
                countSentences = 0;
            }
        }
        fileParts.add(filePart.toString());
        return fileParts;
    }

    /**Record files that do not exceed the user-defined size. Written sentences are reduced to lowercase*/
    private void writeSmallFile(String outputDirectory, ArrayList<String>fileParts){
        try(OutputStreamWriter os = new OutputStreamWriter(
                new FileOutputStream(outputDirectory + "/" + fileName), StandardCharsets.UTF_8)){
            os.write(fileParts.get(0).toLowerCase(Locale.ROOT));
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /**Record files that are separated due to exceeding the user-defined size. The text is reduced to lowercase.*/
    private void writePartsFile(String outputDirectory, ArrayList<String>fileParts){
        for (int i = 0; i < fileParts.size(); i++) {
            String outputFileName = fileName.replace(".txt", "_" + (i+1) + ".txt");
            try (OutputStreamWriter os = new OutputStreamWriter(
                    new FileOutputStream(outputDirectory + "/" + outputFileName), StandardCharsets.UTF_8)){
                os.write(fileParts.get(i).toLowerCase(Locale.ROOT));
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }

    /**Move the sentence to quarantine*/
    public void moveToQuarantine(String sentence){
        quarantineFile.addQuarantineSentence(sentence);
        sentences.remove(sentence);
    }

    public DeletedWordsStorage getDeletedWordsStorage() {
        return deletedWordsStorage;
    }

    public QuarantineWordsFile getQuarantineWordsFile() {
        return quarantineWordsFile;
    }


    public String getFileName() {
        return fileName;
    }

    public ReplacementFile getReplacementFile() {
        return replacementFile;
    }

    public FileWithAbbreviations getFileWithAbbreviations() {
        return fileWithAbbreviations;
    }

    public QuarantineSentencesFile getQuarantineFile() {
        return quarantineFile;
    }

    public FileWithEnglishText getFileWithEnglishText() {
        return fileWithEnglishText;
    }

    public ArrayList<String> getSentences() {
        return sentences;
    }
    public void setSentences(ArrayList<String>sentences){
        this.sentences = sentences;
    }
}

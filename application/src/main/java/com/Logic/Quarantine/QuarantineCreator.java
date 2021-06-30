package com.Logic.Quarantine;

import com.Logic.InputFile.InputFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Class to create quarantine directory.
 */
public class QuarantineCreator {
    /**
     * Counter of quarantine sentences to statistics and logging.
     */
    public static int countQuarantineSentences = 0;

    /**
     * Create quarantine directory if needed and files into them
     * @param outDirectory root directory to save into
     * @param inputFiles files will be concluded quarantine sentences
     * @param modificationQuarantineFilePath common information in this file
     */
    public static void createQuarantine(String outDirectory, ArrayList<InputFile> inputFiles,
                                        String modificationQuarantineFilePath){
        String quarantineSentencesFilesDir = outDirectory + "/Quarantine";
        String failedValidationFiles = outDirectory + "/BadFile";
        try{
            Files.createDirectories(Paths.get(quarantineSentencesFilesDir));
            Files.createDirectories((Paths.get(failedValidationFiles)));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        generateQuarantineSentencesFiles(quarantineSentencesFilesDir, inputFiles, modificationQuarantineFilePath);
    }

    /**
     * Create quarantine sentences and save them into files
     * @param outDir out directory
     * @param inputFiles files to process
     * @param modificationQuarantineFilePath common information in this file
     */
    private static void generateQuarantineSentencesFiles(String outDir, ArrayList<InputFile>inputFiles, String modificationQuarantineFilePath){
        ArrayList<String>quarantineSentencesFiles = new ArrayList<>();
        for (InputFile inputFile : inputFiles){
            ArrayList<String>fileQuarantineSentences = inputFile.getQuarantineFile().getQuarantineSentences();
            quarantineSentencesFiles.addAll(fileQuarantineSentences);
            countQuarantineSentences += fileQuarantineSentences.size();
        }
        if (quarantineSentencesFiles.size() != 0){
            createFile(quarantineSentencesFiles, outDir);
            createModificationQuarantine(quarantineSentencesFiles, modificationQuarantineFilePath);
        }
    }

    /**
     * Create file with quarantine sentences in it
     * @param allQuarantineSentences quarantine sentences to write into file
     * @param outDir out directory
     */
    private static void createFile(ArrayList<String>allQuarantineSentences, String outDir){
        try(OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(outDir + "/QuarantineSentences.txt"), StandardCharsets.UTF_8)){
            os.write("в карантин попало " + countQuarantineSentences + " предложений\n");
            for (String sentence : allQuarantineSentences){
                os.write(sentence + "\n");
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    /**
     * Create common information inside quarantine sentences
     * @param quarantineSentences quarantine sentences
     * @param outputDirectory out directory
     */
    private static void createModificationQuarantine(ArrayList<String>quarantineSentences, String outputDirectory){
        try(OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(outputDirectory + "/Quarantine.txt"), StandardCharsets.UTF_8)){
            os.write("в карантин попало " + countQuarantineSentences + " предложений\n");
            for (String quarantineSentence : quarantineSentences){
                os.write(quarantineSentence.toLowerCase(Locale.ROOT) + "\n");
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }


}

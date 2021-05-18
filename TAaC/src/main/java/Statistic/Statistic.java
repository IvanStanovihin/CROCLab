package Statistic;

import Handler.Handler;
import InputFile.InputFile;
import InformationFiles.FileWithAbbreviations;
import Properties.PropertyLoader;
import Statistic.GeneralStatistic.GeneralProcessedFilesStatistic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

/**Class in which creating all statistic.*/
public class Statistic {

    /**List of row input files*/
    private ArrayList<InputFile>inputFiles;
    /**List of quarantine statistic for each processed file*/
    private ArrayList<QuarantineStatisticFile> quarantineStatisticFiles = new ArrayList<>();
    /**List of statistic for each processed file*/
    private ArrayList<ProcessedFileStatistic> processedFilesStatistic = new ArrayList<>();
    /**List of statistic for each row input file*/
    private ArrayList<RowFileStatistic> rowFilesStatistic = new ArrayList<>();
    /**List of sentences with abbreviation*/
    ArrayList<FileWithAbbreviations> filesWithAbbreviations = new ArrayList<>();
    /**Output statistic directory*/
    private String statisticFilesDir;


    /**
     *
     * @param property - a file that contains the paths for the configuration files and statistics output files
     * @param rowFiles - row input files
     */
    public Statistic(PropertyLoader property, ArrayList<InputFile>rowFiles){
        String outputDirectory = property.getOutDirectory();
        this.inputFiles = rowFiles;
        for (InputFile inputFile : rowFiles){
            this.filesWithAbbreviations.add(inputFile.getFileWithAbbreviations());
            this.quarantineStatisticFiles.add(new QuarantineStatisticFile(inputFile.getQuarantineFile()));
            this.processedFilesStatistic.add(new ProcessedFileStatistic(inputFile));
        }
        this.statisticFilesDir = outputDirectory + "/Statistic";
    }


    /**Start generating statistics and creating output statistic directories*/
    public void createStatisticFiles(){
        String generalFilesStatisticDir = statisticFilesDir + "/GeneralProcessedFilesStatistic";
        String eachFileStatisticDir = statisticFilesDir + "/EachProcessedFileStatistic";
        String quarantineStatisticDir = statisticFilesDir + "/QuarantineStatistic";
        String userStatisticDir = statisticFilesDir + "/UserStatistic";
        String filesWithAbbreviationsDir = statisticFilesDir + "/FilesWithAbbreviations";
        String filesWithEnglishTextDir = statisticFilesDir + "/FilesWithEnglish";
        String deletedWordsStatisticDir = statisticFilesDir + "/DeletedWords";
        try {
            Files.createDirectories(Paths.get(statisticFilesDir));
            Files.createDirectories(Paths.get(generalFilesStatisticDir));
            Files.createDirectories(Paths.get(eachFileStatisticDir));
            Files.createDirectories(Paths.get(quarantineStatisticDir));
            Files.createDirectories(Paths.get(userStatisticDir));
            Files.createDirectories(Paths.get(filesWithAbbreviationsDir));
            Files.createDirectories(Paths.get(filesWithEnglishTextDir));
            Files.createDirectories(Paths.get(deletedWordsStatisticDir));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        generateStatisticEachFile(eachFileStatisticDir);
        //Создание общей статистики по обработанным файлам
        GeneralProcessedFilesStatistic generalProcessedFilesStatistic = new GeneralProcessedFilesStatistic(processedFilesStatistic);
        generalProcessedFilesStatistic.createGeneralStatisticFile(generalFilesStatisticDir);
        //Создание статистики по карантину
        generateQuarantineStatistic(quarantineStatisticDir);
        generateUserStatistic(userStatisticDir);
        generateFilesWithAbbreviations(filesWithAbbreviationsDir);
        generateFilesWithEnglish(filesWithEnglishTextDir);
        generateDeletedWordsStatistic(deletedWordsStatisticDir);
        generateQuarantineWordsStatistic(quarantineStatisticDir);
    }

    /**Creating file with abbreviations*/
    private void generateFilesWithAbbreviations(String outDir){
        for(FileWithAbbreviations fileWithAbbreviations : filesWithAbbreviations){
            fileWithAbbreviations.createFile(outDir);
        }
    }

    /**Creating words statistic for each processed files*/
    private void generateQuarantineWordsStatistic(String outDir){
        new QuarantineWordsStatistic(inputFiles).createFile(outDir);
    }

    /**Creating words ad sentences statistic for processed files*/
    private void generateStatisticEachFile(String dir){
        for (ProcessedFileStatistic processedFileStatistic : processedFilesStatistic){
            try(OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(dir + "/" + processedFileStatistic.getOutFileName()), StandardCharsets.UTF_8)){
                os.write(processedFileStatistic.getJsonStatistic());
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
    }


    /**Creating quarantine statistic file*/
    private void generateQuarantineStatistic(String dir){
        GeneralQuarantineSentencesStatistic generalQuarantineSentencesStatistic =
                new GeneralQuarantineSentencesStatistic(quarantineStatisticFiles);
        generalQuarantineSentencesStatistic.createFile(dir);
    }

    /**Creating statistic file for row file*/
    public static void generateRowFilesStatistic(ArrayList<InputFile>inputFiles, PropertyLoader property){
        String statisticFilesDir = property.getOutDirectory() + "/Statistic";
        ArrayList<RowFileStatistic>rowFilesStatistic = new ArrayList<>();
        for (InputFile inputFile : inputFiles){
            rowFilesStatistic.add(new RowFileStatistic(inputFile));
        }
        String rowFilesStatisticDir = statisticFilesDir + "/RowFilesStatistic";
        try{
            Files.createDirectories(Paths.get(rowFilesStatisticDir));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        for (RowFileStatistic rowFileStatistic : rowFilesStatistic){
            rowFileStatistic.create(rowFilesStatisticDir);
        }
    }

    /**Creating user statistic file*/
    private void generateUserStatistic(String outDir){
        ArrayList<String>filesPathsWithWordsForStatistic = new ArrayList<>();
        generateFileForStatisticPaths(Handler.getProperty().getFilesForStatisticDirectory(), filesPathsWithWordsForStatistic);
        for (String fileForStatisticPath : filesPathsWithWordsForStatistic){
            new UserStatisticCreator(fileForStatisticPath, processedFilesStatistic).createStatistic(outDir);
        }
    }

    /**Creating paths for statistic files*/
    private void generateFileForStatisticPaths(String directoryForUserStatistic, ArrayList<String>filesPaths){
        File directory = new File(directoryForUserStatistic);
        for (File file : directory.listFiles()){
            if (file.isDirectory()){
                generateFileForStatisticPaths(file.getPath(), filesPaths);
            }else{
                filesPaths.add(file.getPath());
            }
        }
    }


    /**Creating files with english text*/
    private void generateFilesWithEnglish(String outDir){
        for (InputFile inputFile : inputFiles){
            inputFile.getFileWithEnglishText().createFile(outDir);
        }
    }

    /**Creating files with deleting words*/
    private void generateDeletedWordsStatistic(String outputDirectory){
        new DeletedWordsStatistic(inputFiles).createStatistic(outputDirectory);
    }

    /**Start generating statistics and creating output directories*/
    public void printQuarantineSentencesInfo(){
        int countQuarantineSentences = 0;
        int countProcessedSentences = 0;
        for (QuarantineStatisticFile quarantineStatisticFile : quarantineStatisticFiles){
            countQuarantineSentences += quarantineStatisticFile.getCountQuarantineSentences();
        }
        for (ProcessedFileStatistic processedFileStatistic : processedFilesStatistic){
            countProcessedSentences += processedFileStatistic.getCountSentences();
        }
        System.out.println("The quarantine contains " + countQuarantineSentences + " of the "
                + (countProcessedSentences + countQuarantineSentences) + " processed sentences");
    }

}



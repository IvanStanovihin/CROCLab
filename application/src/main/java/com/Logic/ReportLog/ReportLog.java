package com.Logic.ReportLog;

import com.Logic.Handler.Handler;
import com.Logic.Properties.PropertyLoader;


import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Class to make report log
 */
public class ReportLog {

    public ReportLog(int countInputFiles){
        this.countInputFiles = countInputFiles;
        TextAreaAppender customAppender = new TextAreaAppender(Handler.logArea);
        LOGGER.addAppender(customAppender);
    }

    /**
     * logger
     */
    private static final Logger LOGGER = Logger.getLogger(ReportLog.class);
    /**
     * Counter of input files
     */
    private  int countInputFiles = 0;
    /**
     * Index of current processed file
     */
    private  int indexCurrentProcessedFile = 1;
    /**
     * Information about previous operation
     */
    private  LogOperation previousOperation = null;
    /**
     * Time when operation starts
     */
    private Long startOperationTime;
    /**
     * Time when operation ends
     */
    private Long endOperationTime;
    /**
     * Common time when module starts
     */
    private Long startCurrentModuleTime;
    /**
     * Final time when module ends
     */
    private Long endCurrentModuleTime;
    /**
     * List of all times
     */
    private ArrayList<String>moduleWorkTime = new ArrayList<>();
    /**
     * Current log message
     */
    private String currentMessage;
    /**
     * Common time through all modules
     */
    private double totalModulesWorkingTime = 0;

    /**
     * Log current operation
     * @param currentOperation log operation
     */
    public void startCurrentOperation(LogOperation currentOperation){
        startCurrentOperation(currentOperation, "");
    }

    /**
     * Get description of log operation to file
     * @param currentOperation current log operation
     * @param fileName file name to save log operation
     */
    public  void startCurrentOperation(LogOperation currentOperation, String fileName) {
        startOperationTime = System.currentTimeMillis();
        switch (currentOperation) {
            case DICTIONARIES_LOAD:
            case LOAD_WORDS_TO_DELETE:
            case REMOVE_WORDS_TO_DELETE:
                loadingResources(currentOperation);
                break;
            case SINGLE_DICTIONARY:
            case CAMELCASE_SEPARATOR:
            case PROCESSING_DATES:
            case WHITESPACE_DICTIONARY:
            case FIND_ENGLISH:
            case REMOVE_ENGLISH:
            case CREATE_QUARANTINE_FILES:
            case CREATE_PROCESSED_FILES:
            case PROCESSING_DAYS_OF_WEEK:
            case REMOVE_INITIALS:
            case FIND_ABBREVIATIONS:
            case PROCESS_NUMBERS:
            case PROCESS_LINKS:
            case PROCESS_PHONE_NUMBERS:
            case PROCESS_PUNCTUATIONS:
            case SEPARATE_ON_SENTENCES:
            case REMOVE_EXTRAS_WHITESPACE:
            case PROCESSING_TIME:
            case PROCESSING_MONEY:
            case PROCESSING_FRACTIONS:
            case REMOVE_ACRONYMS:
            case CREATE_REPLACEMENT_FILE:
                processingFiles(currentOperation, fileName);
                break;
        }
    }

    /**
     * Pring current operation
     * @param currentOperation current log operation
     */
    private  void loadingResources(LogOperation currentOperation) {
        System.out.println(currentOperation);
    }

    /**
     * Evaluate index of file and current message
     * @param currentOperation current log operation
     * @param fileName filename
     */
    private  void processingFiles(LogOperation currentOperation, String fileName) {
        updateProcessedFileIndex(currentOperation);
        currentMessage = (currentOperation +" (" + fileName + " " + ( indexCurrentProcessedFile) + "/" + countInputFiles + ")");
    }

    /**
     * Update index of file
     * @param currentOperation current log operation
     */
    private  void updateProcessedFileIndex(LogOperation currentOperation) {
        if (previousOperation != currentOperation) {
            indexCurrentProcessedFile = 1;
        } else {
            indexCurrentProcessedFile++;
        }
        previousOperation = currentOperation;
    }

    /**
     * Log when operation ends
     */
    public void endOperation(){
        endOperationTime = Calendar.getInstance().getTimeInMillis();
        currentMessage += " " + (((double)endOperationTime - startOperationTime)/1000);
        LOGGER.log(Level.INFO, currentMessage);
    }

    /**
     * Save time when current module starts
     */
    public void startModule(){
        startCurrentModuleTime = Calendar.getInstance().getTimeInMillis();
    }

    /**
     * Save time when current module ends
     * @param moduleName name of module
     */
    public void endModule(String moduleName){
        endCurrentModuleTime = Calendar.getInstance().getTimeInMillis();
        double moduleWorkingTime = ((double) endCurrentModuleTime - startCurrentModuleTime) /1000;
        this.totalModulesWorkingTime += moduleWorkingTime;
        String moduleTime = moduleName + moduleWorkingTime + " s.";
        System.out.println(moduleTime);
        this.moduleWorkTime.add(moduleTime);
    }

    /**
     * Write log to file
     * @param property info from property.json
     */
    public void create(PropertyLoader property){
        String outDirectory  = property.getOutDirectory() + "/Report";
        try{
            Files.createDirectories(Paths.get(outDirectory));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        try(OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(outDirectory + "/Report.txt"), StandardCharsets.UTF_8)){
            os.write("Number of input files " + countInputFiles + "\n");
            for (String reportEntry : moduleWorkTime){
                os.write(reportEntry + "\n");
            }
            String workTime = String.format("%.3f", totalModulesWorkingTime);
            os.write("Total modules working time " + workTime + " s.");
        }catch(IOException ex){
            System.out.println("Ошибка при создании файла отчёта");
            ex.printStackTrace();
        }
    }

    /**
     * Save if module is disabled
     * @param moduleName name of disabled module
     */
    public void moduleIsDisable(String moduleName){
        moduleWorkTime.add(moduleName + " is disable");
    }


}


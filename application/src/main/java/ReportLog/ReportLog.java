package ReportLog;

import Properties.PropertyLoader;

import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;

public class ReportLog {

    public ReportLog(int countInputFiles){
        this.countInputFiles = countInputFiles;
    }

    private  int countInputFiles = 0;
    private  int indexCurrentProcessedFile = 1;
    private  LogOperation previousOperation = null;
    private Long startOperationTime;
    private Long endOperationTime;
    private Long startModuleTime;
    private Long endModuleTime;
    private ArrayList<String>moduleWorkTime = new ArrayList<>();

    public void startCurrentOperation(LogOperation currentOperation){
        startCurrentOperation(currentOperation, "");
    }

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
            case REMOVE_CAMEL_CASE:
            case CREATE_REPLACEMENT_FILE:
                processingFiles(currentOperation, fileName);
                break;
        }
    }

    private  void loadingResources(LogOperation currentOperation) {
        System.out.println(currentOperation);
    }

    private  void processingFiles(LogOperation currentOperation, String fileName) {
        updateProcessedFileIndex(currentOperation);
        System.out.print(currentOperation + " (" + fileName + " " + ( indexCurrentProcessedFile) + "/" + countInputFiles + ")");
    }

    private  void updateProcessedFileIndex(LogOperation currentOperation) {
        if (previousOperation != currentOperation) {
            indexCurrentProcessedFile = 1;
        } else {
            indexCurrentProcessedFile++;
        }
        previousOperation = currentOperation;
    }

    public void endOperation(){
        endOperationTime = Calendar.getInstance().getTimeInMillis();
        System.out.println(" " + ((double)endOperationTime - startOperationTime)/1000 );
    }

    public void startModule(){
        startModuleTime = Calendar.getInstance().getTimeInMillis();
    }

    public void endModule(String moduleName){
        endModuleTime = Calendar.getInstance().getTimeInMillis();
        String moduleTime = moduleName + " отработал за " + ((double)endModuleTime - startModuleTime) /1000 + " с.";
        System.out.println(moduleTime);
        this.moduleWorkTime.add(moduleTime);
    }

    public void createReportFile(PropertyLoader property){
        String outDirectory  = property.getOutDirectory() + "/ReportFile";
        try{
            Files.createDirectories(Paths.get(outDirectory));
        }catch(IOException ex){
            ex.printStackTrace();
        }
        try(OutputStreamWriter os = new OutputStreamWriter(new FileOutputStream(outDirectory + "/Report.txt"))){
            for (String reportEntry : moduleWorkTime){
                os.write(reportEntry + "\n");
            }
        }catch(IOException ex){
            System.out.println("Ошибка при создании файла отчёта");
            ex.printStackTrace();
        }
    }


}


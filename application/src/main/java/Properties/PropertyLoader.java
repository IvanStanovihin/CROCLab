package Properties;

import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
/**The class stores information loaded from property.json*/
public class PropertyLoader {
    private String inputFilesDirectory = null;
    /**Size of each output processed file(mb)*/
    private Integer outFileSize = null;
    private String outDirectory = null;
    /**Paths to configuration files*/
    private String dictionariesDirectory = null;
    private String filesForStatisticDirectory = null;
    private String protectedWordsDir = null;
    private String wordsToDeleteDir = null;

    /**Flags for enabling modules*/
    private boolean enableRemoveEnglishTextModule = false;
    private boolean enableDictionaryWordsModule = false;
    private boolean enableFindEnglishModule = false;
    private boolean enablePhoneNumberModule = false;
    private boolean enableDatesModule = false;
    private boolean enableTimesModule = false;
    private boolean enableMoneyModule = false;
    private boolean enableFractionsModule = false;
    private boolean enableNumbersModule = false;
    private boolean enableLinksModule = false;
    private boolean enablePunctuationMarkModule = false;
    private boolean enableRemoveWordsModule = false;
    private boolean enableDaysOfWeekModule = false;
    private boolean enableInitialsModule = false;
    private boolean enableAbbreviationsFindModule = false;
    private boolean enableCamelCaseModule = false;
    private boolean enableMonthsModule = false;
    private boolean enableWhitespaceRemoveModule = false;
    private boolean enableAcronymsModule = false;



    public PropertyLoader(String filePath){
        load(filePath);
    }

    /**Reading a "property.json" and initializing paths for configuration files*/
    private void load(String filePath){

        try(Scanner in = new Scanner(new File(filePath))){

            String fileContent = "";

            while(in.hasNext()) {
                fileContent += in.nextLine() + "\r\n";
            }

            Gson gson = new Gson();
            PropertyData propertyData = gson.fromJson(fileContent , PropertyData.class);

            inputFilesDirectory = propertyData.getInputFilesDirectory();
            outFileSize = propertyData.getOutputFileSize();
            outDirectory = propertyData.getOutputDirectory();
            dictionariesDirectory = propertyData.getDictionariesDirectory();
            filesForStatisticDirectory = propertyData.getFilesForStatisticDirectory();
            protectedWordsDir = propertyData.getProtectedWordsDir();
            wordsToDeleteDir = propertyData.getWordsToDeleteDir();
            initializeModulesFlags(propertyData);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**Initializing flags for enabling modules*/
    private void initializeModulesFlags(PropertyData propertyData){
        enableRemoveEnglishTextModule = propertyData.getRemoveEnglishTextModule().equalsIgnoreCase("true");
        enableDictionaryWordsModule = propertyData.getDictionaryWordsModule().equalsIgnoreCase("true");
        enableFindEnglishModule = propertyData.getFindEnglishModule().equalsIgnoreCase("true");
        enablePhoneNumberModule = propertyData.getPhoneNumberModule().equalsIgnoreCase("true");
        enableDatesModule = propertyData.getDatesModule().equalsIgnoreCase("true");
        enableTimesModule = propertyData.getTimesModule().equalsIgnoreCase("true");
        enableMoneyModule = propertyData.getMoneyModule().equalsIgnoreCase("true");
        enableFractionsModule = propertyData.getFractionsModule().equalsIgnoreCase("true");
        enableNumbersModule = propertyData.getNumbersModule().equalsIgnoreCase("true");
        enableLinksModule = propertyData.getLinksModule().equalsIgnoreCase("true");
        enablePunctuationMarkModule = propertyData.getPunctuationMarkModule().equalsIgnoreCase("true");
        enableRemoveWordsModule = propertyData.getRemoveWordsModule().equalsIgnoreCase("true");
        enableDaysOfWeekModule = propertyData.getDaysOfWeekModule().equalsIgnoreCase("true");
        enableInitialsModule = propertyData.getInitialsModule().equalsIgnoreCase("true");
        enableAbbreviationsFindModule = propertyData.getAbbreviationsFindModule().equalsIgnoreCase("true");
        enableCamelCaseModule = propertyData.getCamelCaseModule().equalsIgnoreCase("true");
        enableMonthsModule = propertyData.getMonthsModule().equalsIgnoreCase("true");
        enableWhitespaceRemoveModule = propertyData.getWhitespaceRemoveModule().equalsIgnoreCase("true");
        enableAcronymsModule = propertyData.getAcronymsModule().equalsIgnoreCase("true");
    }



    public boolean isEnableAcronymsModule() {
        return enableAcronymsModule;
    }
    public String getInputFilesDirectory() {
        return inputFilesDirectory;
    }
    public Integer getOutFileSize() {
        return outFileSize;
    }
    public String getOutDirectory() {
        return outDirectory;
    }

    public String getDictionariesDirectory() {
        return dictionariesDirectory;
    }



    public String getWordsToDeleteDir() {
        return wordsToDeleteDir;
    }

    public String getProtectedWordsDir() {
        return protectedWordsDir;
    }

    public boolean isEnableRemoveEnglishTextModule() {
        return enableRemoveEnglishTextModule;
    }

    public boolean isEnableDictionaryWordsModule() {
        return enableDictionaryWordsModule;
    }

    public boolean isEnableFindEnglishModule() {
        return enableFindEnglishModule;
    }

    public boolean isEnablePhoneNumberModule() {
        return enablePhoneNumberModule;
    }

    public boolean isEnableDatesModule() {
        return enableDatesModule;
    }

    public boolean isEnableTimesModule() {
        return enableTimesModule;
    }

    public boolean isEnableMoneyModule() {
        return enableMoneyModule;
    }

    public boolean isEnableFractionsModule() {
        return enableFractionsModule;
    }

    public boolean isEnableNumbersModule() {
        return enableNumbersModule;
    }

    public boolean isEnableLinksModule() {
        return enableLinksModule;
    }

    public boolean isEnablePunctuationMarkModule() {
        return enablePunctuationMarkModule;
    }

    public boolean isEnableRemoveWordsModule() {
        return enableRemoveWordsModule;
    }

    public boolean isEnableDaysOfWeekModule() {
        return enableDaysOfWeekModule;
    }

    public String getFilesForStatisticDirectory() {
        return filesForStatisticDirectory;
    }

    public void setFilesForStatisticDirectory(String filesForStatisticDirectory) {
        this.filesForStatisticDirectory = filesForStatisticDirectory;
    }

    public boolean isEnableInitialsModule() {
        return enableInitialsModule;
    }

    public boolean isEnableAbbreviationsFindModule() {
        return enableAbbreviationsFindModule;
    }

    public boolean isEnableCamelCaseModule() {
        return enableCamelCaseModule;
    }

    public boolean isEnableMonthsModule() {
        return enableMonthsModule;
    }

    public boolean isEnableWhitespaceRemoveModule() {
        return enableWhitespaceRemoveModule;
    }
}

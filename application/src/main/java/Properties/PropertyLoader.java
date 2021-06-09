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
    private Integer outFileCountStrings = null;
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

            inputFilesDirectory = propertyData.getInputFiles();
            outFileSize = propertyData.getOutputFileSize();
            outFileCountStrings = propertyData.getOutputFileCountStrings();
            outDirectory = propertyData.getOutputDirectory();
            dictionariesDirectory = propertyData.getDictionaries();
            filesForStatisticDirectory = propertyData.getFilesForStatistic();
            protectedWordsDir = propertyData.getProtectedWords();
            wordsToDeleteDir = propertyData.getWordsToDelete();
            initializeModulesFlags(propertyData);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    /**Initializing flags for enabling modules*/
    private void initializeModulesFlags(PropertyData propertyData){
        enableRemoveEnglishTextModule = propertyData.getRemoveEnglishText().equalsIgnoreCase("true");
        enableDictionaryWordsModule = propertyData.getDictionaryWords().equalsIgnoreCase("true");
        enableFindEnglishModule = propertyData.getFindEnglish().equalsIgnoreCase("true");
        enablePhoneNumberModule = propertyData.getPhoneNumber().equalsIgnoreCase("true");
        enableDatesModule = propertyData.getDates().equalsIgnoreCase("true");
        enableTimesModule = propertyData.getTimes().equalsIgnoreCase("true");
        enableMoneyModule = propertyData.getMoney().equalsIgnoreCase("true");
        enableFractionsModule = propertyData.getFractions().equalsIgnoreCase("true");
        enableNumbersModule = propertyData.getNumbers().equalsIgnoreCase("true");
        enableLinksModule = propertyData.getLinks().equalsIgnoreCase("true");
        enablePunctuationMarkModule = propertyData.getPunctuationMark().equalsIgnoreCase("true");
        enableRemoveWordsModule = propertyData.getRemoveWords().equalsIgnoreCase("true");
        enableDaysOfWeekModule = propertyData.getDaysOfWeek().equalsIgnoreCase("true");
        enableInitialsModule = propertyData.getInitials().equalsIgnoreCase("true");
        enableAbbreviationsFindModule = propertyData.getAbbreviationsFind().equalsIgnoreCase("true");
        enableCamelCaseModule = propertyData.getCamelCase().equalsIgnoreCase("true");
        enableMonthsModule = propertyData.getMonths().equalsIgnoreCase("true");
        enableWhitespaceRemoveModule = propertyData.getWhitespaceRemove().equalsIgnoreCase("true");
        enableAcronymsModule = propertyData.getAcronyms().equalsIgnoreCase("true");
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

    public Integer getOutFileCountStrings() {
        return outFileCountStrings;
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

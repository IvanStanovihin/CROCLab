package Properties;

/**Class for deserializing file "property.json"*/
public class PropertyData {
    private String inputFilesDirectory;
    private Integer outputFileSize;
    private String outputDirectory;
    private String dictionariesDirectory;
    private String filesForStatisticDirectory;
    private String protectedWordsDir;
    private String wordsToDeleteDir;

    private String removeEnglishTextModule;
    private String dictionaryWordsModule;
    private String findEnglishModule;
    private String phoneNumberModule;
    private String datesModule;
    private String timesModule;
    private String moneyModule;
    private String fractionsModule;
    private String numbersModule;
    private String linksModule;
    private String punctuationMarkModule;
    private String removeWordsModule;
    private String daysOfWeekModule;
    private String initialsModule;
    private String abbreviationsFindModule;
    private String camelCaseModule;
    private String monthsModule;
    private String whitespaceRemoveModule;
    private String acronymsModule;

    public void setRemoveEnglishTextModule(String removeEnglishTextModule) {
        this.removeEnglishTextModule = removeEnglishTextModule;
    }

    public String getDictionaryWordsModule() {
        return dictionaryWordsModule;
    }

    public void setDictionaryWordsModule(String dictionaryWordsModule) {
        this.dictionaryWordsModule = dictionaryWordsModule;
    }

    public String getFindEnglishModule() {
        return findEnglishModule;
    }

    public void setFindEnglishModule(String findEnglishModule) {
        this.findEnglishModule = findEnglishModule;
    }

    public String getPhoneNumberModule() {
        return phoneNumberModule;
    }

    public void setPhoneNumberModule(String phoneNumberModule) {
        this.phoneNumberModule = phoneNumberModule;
    }

    public String getDatesModule() {
        return datesModule;
    }

    public void setDatesModule(String datesModule) {
        this.datesModule = datesModule;
    }

    public String getTimesModule() {
        return timesModule;
    }

    public void setTimesModule(String timesModule) {
        this.timesModule = timesModule;
    }

    public String getMoneyModule() {
        return moneyModule;
    }

    public void setMoneyModule(String moneyModule) {
        this.moneyModule = moneyModule;
    }

    public String getFractionsModule() {
        return fractionsModule;
    }

    public void setFractionsModule(String fractionsModule) {
        this.fractionsModule = fractionsModule;
    }

    public String getNumbersModule() {
        return numbersModule;
    }

    public void setNumbersModule(String numbersModule) {
        this.numbersModule = numbersModule;
    }

    public String getLinksModule() {
        return linksModule;
    }

    public void setLinksModule(String linksModule) {
        this.linksModule = linksModule;
    }

    public String getPunctuationMarkModule() {
        return punctuationMarkModule;
    }

    public void setPunctuationMarkModule(String punctuationMarkModule) {
        this.punctuationMarkModule = punctuationMarkModule;
    }

    public String getRemoveWordsModule() {
        return removeWordsModule;
    }

    public void setRemoveWordsModule(String removeWordsModule) {
        this.removeWordsModule = removeWordsModule;
    }

    public String getDaysOfWeekModule() {
        return daysOfWeekModule;
    }

    public void setDaysOfWeekModule(String daysOfWeekModule) {
        this.daysOfWeekModule = daysOfWeekModule;
    }

    public String getInitialsModule() {
        return initialsModule;
    }

    public void setInitialsModule(String initialsModule) {
        this.initialsModule = initialsModule;
    }

    public String getAbbreviationsFindModule() {
        return abbreviationsFindModule;
    }

    public void setAbbreviationsFindModule(String abbreviationsFindModule) {
        this.abbreviationsFindModule = abbreviationsFindModule;
    }

    public String getCamelCaseModule() {
        return camelCaseModule;
    }

    public void setCamelCaseModule(String camelCaseModule) {
        this.camelCaseModule = camelCaseModule;
    }

    public String getMonthsModule() {
        return monthsModule;
    }

    public void setMonthsModule(String monthsModule) {
        this.monthsModule = monthsModule;
    }

    public String getWhitespaceRemoveModule() {
        return whitespaceRemoveModule;
    }

    public void setWhitespaceRemoveModule(String whitespaceRemoveModule) {
        this.whitespaceRemoveModule = whitespaceRemoveModule;
    }

    public String getAcronymsModule() {
        return acronymsModule;
    }

    public void setAcronymsModule(String acronymsModule) {
        this.acronymsModule = acronymsModule;
    }




    public String getWordsToDeleteDir() {
        return wordsToDeleteDir;
    }

    public void setWordsToDeleteDir(String wordsToDeleteDir) {
        this.wordsToDeleteDir = wordsToDeleteDir;
    }

    public String getProtectedWordsDir() {
        return protectedWordsDir;
    }

    public void setProtectedWordsDir(String protectedWordsDir) {
        this.protectedWordsDir = protectedWordsDir;
    }

    public String getRemoveEnglishTextModule() {
        return removeEnglishTextModule;
    }
    public void setEnableEnglishText(String removeEnglishTextModule) {
        this.removeEnglishTextModule = removeEnglishTextModule;
    }

    public String getInputFilesDirectory() {
        return inputFilesDirectory;
    }
    public void setInputFilesDirectory(String inputFilesDirectory) {
        this.inputFilesDirectory = inputFilesDirectory;
    }

    public Integer getOutputFileSize() {
        return outputFileSize;
    }
    public void setOutputFileSize(Integer outputFileSize) {
        this.outputFileSize = outputFileSize;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }
    public void setOutputDirectory(String outputDirectory) {
        this.outputDirectory = outputDirectory;
    }

    public String getDictionariesDirectory() {
        return dictionariesDirectory;
    }
    public void setDictionariesDirectory(String dictionariesDirectory) {
        this.dictionariesDirectory = dictionariesDirectory;
    }

    public String getFilesForStatisticDirectory() {
        return filesForStatisticDirectory;
    }
    public void setFilesForStatisticPaths(String filesForStatisticPaths) {
        this.filesForStatisticDirectory = filesForStatisticDirectory;
    }
}

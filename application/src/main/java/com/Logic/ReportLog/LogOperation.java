package com.Logic.ReportLog;

/**
 * Name and description each log operation
 */
public enum LogOperation {

    DICTIONARIES_LOAD("Loading dictionaries"),
    WHITESPACE_DICTIONARY("Deleting whitespace dictionary words"),
    SINGLE_DICTIONARY("Deleting single dictionary words"),
    FIND_ENGLISH("Searching english text "),
    REMOVE_ENGLISH("Deleting English text"),
    PROCESSING_DAYS_OF_WEEK("Processing days of the week"),
    REMOVE_INITIALS("Deleting initials"),
    FIND_ABBREVIATIONS("Searching abbreviations"),
    PROCESS_NUMBERS("Processing numbers"),
    PROCESS_LINKS("Deleting links"),
    PROCESS_PHONE_NUMBERS("Processing phone numbers"),
    PROCESS_PUNCTUATIONS("Processing punctuation marks"),
    SEPARATE_ON_SENTENCES("Splitting files into sentences"),
    REMOVE_EXTRAS_WHITESPACE("Deleting extra spaces"),
    REMOVE_ACRONYMS("Processing acronyms"),
    CREATE_REPLACEMENT_FILE("Creating a file with replacements"),
    PROCESSING_DATES("Processing dates"),
    PROCESSING_TIME("Processing times"),
    PROCESSING_MONEY("Processing moneys"),
    CAMELCASE_SEPARATOR("Separating camel case words"),
    PROCESSING_FRACTIONS("Processing fractions"),
    CREATE_PROCESSED_FILES("Creating processed files");
    /**
     * Description of log operation
     */
    private String operationName;

    LogOperation(String operationName){
        this.operationName = operationName;
    }

    public String toString(){
        return operationName;
    }
}

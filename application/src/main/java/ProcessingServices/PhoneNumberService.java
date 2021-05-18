package ProcessingServices;

import Handler.Handler;
import InputFile.InputFile;
import NumberService.*;
import ReportLog.LogOperation;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for processing phone numbers
 */
public class PhoneNumberService {

    /**
     * Const - module name
     */
    private static final String MODULE_NAME = "Phone numbers ";
    /**
     * Const - Regex to search for phone numbers
     */
    private static final String regexMobilePhoneNumbers = "(\\+7|8)[- _]*\\(?[- _]*(\\d{3}[- _]*\\)?([- _]*\\d){7}|\\d\\d[- _]*\\d\\d[- _]*\\)?([- _]*\\d){6})";

    /**
     * Method for checking the module connection
     * @return true if the module is enabled , else false
     */
    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnablePhoneNumberModule();
    }

    /**
     * Method of processing phone humber
     * @param inputFiles inputFiles ArrayList with inputFiles
     */
    public static void handle(ArrayList<InputFile>inputFiles){
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            for (InputFile inputFile : inputFiles) {
                Handler.reportLog.startCurrentOperation(LogOperation.PROCESS_PHONE_NUMBERS, inputFile.getFileName());
                handlePhoneNumbers(inputFile);
                Handler.reportLog.endOperation();
            }
            Handler.reportLog.endModule(MODULE_NAME);
        }else{
            Handler.reportLog.moduleIsDisable(MODULE_NAME);
        }
    }

    /**
     * Find and disclosure phone number in a single input file
     * @param inputFile input file for processing
     */
    private static void handlePhoneNumbers(InputFile inputFile){
        String fileText = inputFile.getFileText();
        Pattern phoneNumberPattern = Pattern.compile(regexMobilePhoneNumbers);
        Matcher matcher = phoneNumberPattern.matcher(fileText);
        StringBuffer processedText = new StringBuffer();
        while(matcher.find()){
            String foundPhoneNumber = fileText.substring(matcher.start(), matcher.end());
            boolean containsPlus = foundPhoneNumber.contains("+");
            foundPhoneNumber = foundPhoneNumber.replaceAll("[\\s+()-]", "");
            String replacement = NumberHandler.numberToString(foundPhoneNumber.substring(0, 1))
                    + NumberHandler.numberToString(foundPhoneNumber.substring(1,4))
                    + NumberHandler.numberToString(foundPhoneNumber.substring(4, 7))
                    + NumberHandler.numberToString(foundPhoneNumber.substring(7, 9))
                    + NumberHandler.numberToString(foundPhoneNumber.substring(9));
            if (containsPlus){
                replacement = "+" + replacement;
            }
            matcher.appendReplacement(processedText, replacement);
        }
        matcher.appendTail(processedText);
        inputFile.setFileText(processedText.toString());
    }
}

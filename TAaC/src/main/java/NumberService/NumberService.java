package NumberService;

import Handler.Handler;
import InputFile.InputFile;
import ReportLog.LogOperation;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service for numbers.
 */
public class NumberService {
    /**
     * Verify if module is active
     * @return result of verifying
     */
    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnableNumbersModule();
    }

    /**
     * Do processing if needed
     * @param inputFiles files to process
     */
    public static void handleNumbers(ArrayList<InputFile> inputFiles) {
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            for (InputFile inputFile : inputFiles) {
                Handler.reportLog.startCurrentOperation(LogOperation.PROCESS_NUMBERS, inputFile.getFileName());
                handleFile(inputFile);
                Handler.reportLog.endOperation();
            }
            Handler.reportLog.endModule("Numbers ");
        }
    }

    /**
     * Write of processing to files
     * @param inputFile list of files to process
     */
    private static void handleFile(InputFile inputFile) {
        String fileText = inputFile.getFileText();
        StringBuilder cleanText = new StringBuilder();
        Pattern numbersPattern = Pattern.compile("[\\d]+");
        Matcher matcher = numbersPattern.matcher(fileText);
        while (matcher.find()) {
            String foundNumber = fileText.substring(matcher.start(), matcher.end()).trim();
            String replacement = "";
            if (foundNumber.length() > 4) {
                replacement = processLongNumber(foundNumber);
            } else {
                replacement = NumberHandler.numberToString(foundNumber).trim();
            }
            matcher.appendReplacement(cleanText, " " + replacement + " ");
        }
        matcher.appendTail(cleanText);
        inputFile.setFileText(cleanText.toString());
    }

    /**
     * Process of long numbers to write them by triads.
     * @param longNumber long number to process
     * @return result of processing
     */
    public static String processLongNumber(String longNumber) {
        StringBuilder result = new StringBuilder();
        while (longNumber.length() > 0) {
            if (longNumber.length() > 3) {
                result.append(NumberHandler.numberToString(longNumber.substring(0, 3)));
                longNumber = longNumber.substring(3);
            } else {
                result.append(NumberHandler.numberToString(longNumber));
                break;
            }
        }
        return result.toString();
    }


}